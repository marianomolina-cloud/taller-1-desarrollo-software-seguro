# Gestión de Funcionarios - Taller 1

Este proyecto es una aplicación de escritorio desarrollada en Java utilizando Swing para la interfaz gráfica y SQLite como motor de base de datos relacional.

## Requisitos
- Java 17 o superior.
- Maven (opcional, para gestión de dependencias).

## Características
- **Patrón DAO**: Separación de lógica de acceso a datos.
- **Seguridad**: Prevención de SQL Injection mediante `PreparedStatement`.
- **Manejo de Excepciones**: Captura y gestión controlada de errores.
- **Modelo Relacional**: Tablas de Funcionarios, Cargos y Departamentos con llaves foráneas.

## Ejecución
Si tienes Maven configurado:
```bash
mvn compile exec:java -Dexec.mainClass="com.ias.gestion.Main"
```

O importa el proyecto en tu IDE (VS Code, IntelliJ, Eclipse) como un proyecto Maven.

## Archivos Importantes
- `src/main/resources/sql/schema.sql`: Script de creación de tablas.
- `src/main/resources/sql/data.sql`: Script de datos iniciales.
- `gestion_funcionarios.db`: Archivo de base de datos (se crea automáticamente al correr la aplicación).
