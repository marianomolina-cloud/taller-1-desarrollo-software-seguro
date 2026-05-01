package com.ias.gestion.view;

import com.ias.gestion.dao.CargoDao;
import com.ias.gestion.dao.DepartamentoDao;
import com.ias.gestion.dao.FuncionarioDao;
import com.ias.gestion.dao.impl.CargoDaoImpl;
import com.ias.gestion.dao.impl.DepartamentoDaoImpl;
import com.ias.gestion.dao.impl.FuncionarioDaoImpl;
import com.ias.gestion.exception.DaoException;
import com.ias.gestion.model.Cargo;
import com.ias.gestion.model.Departamento;
import com.ias.gestion.model.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private final FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
    private final DepartamentoDao departamentoDao = new DepartamentoDaoImpl();
    private final CargoDao cargoDao = new CargoDaoImpl();

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtNombre, txtApellido, txtDocumento, txtCorreo, txtTelefono;
    private JComboBox<Cargo> cbCargo;
    private JComboBox<Departamento> cbDepartamento;
    private JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar;
    private int selectedFuncionarioId = -1;

    public MainFrame() {
        setTitle("Gestión de Funcionarios - Taller Seguros");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initComponents();
        loadData();
    }

    private void initComponents() {
        // Panel de Formulario
        JPanel pnlForm = new JPanel(new GridLayout(8, 2, 10, 10));
        pnlForm.setBorder(BorderFactory.createTitledBorder("Datos del Funcionario"));

        pnlForm.add(new JLabel(" Nombre:"));
        txtNombre = new JTextField();
        pnlForm.add(txtNombre);

        pnlForm.add(new JLabel(" Apellido:"));
        txtApellido = new JTextField();
        pnlForm.add(txtApellido);

        pnlForm.add(new JLabel(" Documento:"));
        txtDocumento = new JTextField();
        pnlForm.add(txtDocumento);

        pnlForm.add(new JLabel(" Correo:"));
        txtCorreo = new JTextField();
        pnlForm.add(txtCorreo);

        pnlForm.add(new JLabel(" Teléfono:"));
        txtTelefono = new JTextField();
        pnlForm.add(txtTelefono);

        pnlForm.add(new JLabel(" Cargo:"));
        cbCargo = new JComboBox<>();
        pnlForm.add(cbCargo);

        pnlForm.add(new JLabel(" Departamento:"));
        cbDepartamento = new JComboBox<>();
        pnlForm.add(cbDepartamento);

        // Botones
        JPanel pnlButtons = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);

        pnlButtons.add(btnGuardar);
        pnlButtons.add(btnEditar);
        pnlButtons.add(btnEliminar);
        pnlButtons.add(btnLimpiar);

        // Tabla
        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Documento", "Correo", "Teléfono", "Cargo", "Depto", "idC", "idD"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollTable = new JScrollPane(table);

        add(pnlForm, BorderLayout.NORTH);
        add(scrollTable, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        // Ocultar las columnas de IDs técnicos (idC e idD)
        table.getColumnModel().getColumn(8).setMinWidth(0);
        table.getColumnModel().getColumn(8).setMaxWidth(0);
        table.getColumnModel().getColumn(8).setPreferredWidth(0);
        table.getColumnModel().getColumn(9).setMinWidth(0);
        table.getColumnModel().getColumn(9).setMaxWidth(0);
        table.getColumnModel().getColumn(9).setPreferredWidth(0);

        // Eventos
        btnGuardar.addActionListener(e -> saveFuncionario());
        btnLimpiar.addActionListener(e -> clearForm());
        btnEliminar.addActionListener(e -> deleteFuncionario());
        btnEditar.addActionListener(e -> updateFuncionario());
        
        table.getSelectionModel().addListSelectionListener(e -> fillFormFromTable());

        loadCombos();
    }

    private void loadCombos() {
        try {
            List<Cargo> cargos = cargoDao.listar();
            for (Cargo c : cargos) cbCargo.addItem(c);

            List<Departamento> deptos = departamentoDao.listar();
            for (Departamento d : deptos) cbDepartamento.addItem(d);
        } catch (DaoException e) {
            JOptionPane.showMessageDialog(this, "Error cargando catálogos: " + e.getMessage());
        }
    }

    private void loadData() {
        try {
            tableModel.setRowCount(0);
            List<Funcionario> funcionarios = funcionarioDao.listar();
            for (Funcionario f : funcionarios) {
                tableModel.addRow(new Object[]{
                    f.getIdFuncionario(), f.getNombre(), f.getApellido(), f.getDocumento(),
                    f.getCorreo(), f.getTelefono(), f.getNombreCargo(), f.getNombreDepartamento(),
                    f.getIdCargo(), f.getIdDepartamento() // Mantener ocultos al final
                });
            }
        } catch (DaoException e) {
            JOptionPane.showMessageDialog(this, "Error cargando datos: " + e.getMessage());
        }
    }

    private void saveFuncionario() {
        try {
            Funcionario f = getFuncionarioFromForm();
            funcionarioDao.crear(f);
            JOptionPane.showMessageDialog(this, "Funcionario guardado correctamente.");
            clearForm();
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateFuncionario() {
        try {
            Funcionario f = getFuncionarioFromForm();
            f.setIdFuncionario(selectedFuncionarioId);
            funcionarioDao.actualizar(f);
            JOptionPane.showMessageDialog(this, "Funcionario actualizado.");
            clearForm();
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void deleteFuncionario() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este registro?");
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                funcionarioDao.eliminar(selectedFuncionarioId);
                clearForm();
                loadData();
            } catch (DaoException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private Funcionario getFuncionarioFromForm() {
        Funcionario f = new Funcionario();
        f.setNombre(txtNombre.getText());
        f.setApellido(txtApellido.getText());
        f.setDocumento(txtDocumento.getText());
        f.setCorreo(txtCorreo.getText());
        f.setTelefono(txtTelefono.getText());
        f.setIdCargo(((Cargo) cbCargo.getSelectedItem()).getIdCargo());
        f.setIdDepartamento(((Departamento) cbDepartamento.getSelectedItem()).getIdDepartamento());
        return f;
    }

    private void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row != -1) {
            selectedFuncionarioId = (int) tableModel.getValueAt(row, 0);
            txtNombre.setText(tableModel.getValueAt(row, 1).toString());
            txtApellido.setText(tableModel.getValueAt(row, 2).toString());
            txtDocumento.setText(tableModel.getValueAt(row, 3).toString());
            txtCorreo.setText(tableModel.getValueAt(row, 4).toString());
            txtTelefono.setText(tableModel.getValueAt(row, 5) != null ? tableModel.getValueAt(row, 5).toString() : "");
            
            // Seleccionar en combos (simplificado por ID)
            int idCargo = (int) tableModel.getValueAt(row, 8);
            int idDepto = (int) tableModel.getValueAt(row, 9);
            
            for (int i = 0; i < cbCargo.getItemCount(); i++) {
                if (cbCargo.getItemAt(i).getIdCargo() == idCargo) cbCargo.setSelectedIndex(i);
            }
            for (int i = 0; i < cbDepartamento.getItemCount(); i++) {
                if (cbDepartamento.getItemAt(i).getIdDepartamento() == idDepto) cbDepartamento.setSelectedIndex(i);
            }

            btnGuardar.setEnabled(false);
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
    }

    private void clearForm() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDocumento.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        cbCargo.setSelectedIndex(0);
        cbDepartamento.setSelectedIndex(0);
        selectedFuncionarioId = -1;
        
        btnGuardar.setEnabled(true);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        table.clearSelection();
    }
}
