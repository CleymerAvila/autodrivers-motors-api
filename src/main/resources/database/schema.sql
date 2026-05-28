
CREATE DATABASE motorsdb;

CREATE TABLE vehiculos (
    vehiculo_id INT PRIMARY KEY AUTO_INCREMENT,
    placa VARCHAR(10) UNIQUE,
    precio DECIMAL(10,2),
    marca VARCHAR(70) NOT NULL,
    modelo VARCHAR(60) NOT NULL,
    anio_fabricacion DATE,
    estado VARCHAR(40),
    color VARCHAR(20)
);

CREATE TABLE clientes (
    cliente_id INT PRIMARY KEY AUTO_INCREMENT,
    nombre_completo VARCHAR(150),
    dni VARCHAR(12) UNIQUE,
    telefono VARCHAR(12) UNIQUE,
    direccion VARCHAR(100),
    correo_electronico VARCHAR(100) UNIQUE
);

CREATE TABLE ventas (
    venta_id INT PRIMARY KEY AUTO_INCREMENT,
    fecha_hora DATETIME,
    tipo VARCHAR(40),
    estado VARCHAR(60),
    valor_final DECIMAL(10,2),
    cliente_id INT,
    vehiculo_id INT,
    FOREIGN KEY(cliente_id) REFERENCES clientes(cliente_id),
    FOREIGN KEY(vehiculo_id) REFERENCES vehiculos(vehiculo_id)
);

CREATE TABLE mantenimientos(
    mantenimiento_id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(40),
    descripcion VARCHAR(170),
    estado VARCHAR(40),
    fecha_servicio DATE,
    costo_mano_obra DECIMAL(10,2),
    costo_repuesto DECIMAL(10,2),
    costo_total DECIMAL(10,2),
    vehiculo_id INT,
    FOREIGN KEY(vehiculo_id) REFERENCES vehiculos(vehiculo_id)
);