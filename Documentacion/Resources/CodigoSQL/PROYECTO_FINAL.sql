DROP DATABASE PROYECTO_FINAL;
CREATE SCHEMA IF NOT EXISTS PROYECTO_FINAL;
USE PROYECTO_FINAL;
CREATE TABLE IF NOT EXISTS USUARIO(
  codigo_usuario INT NOT NULL AUTO_INCREMENT,
  password VARCHAR(200) NOT NULL,
  rol VARCHAR(20) NOT NULL,
  PRIMARY KEY (codigo_usuario),
  UNIQUE (codigo_usuario)
);
CREATE TABLE IF NOT EXISTS CAJERO(
  codigo INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  turno VARCHAR(20) NOT NULL,
  dpi VARCHAR(13) NOT NULL,
  direccion VARCHAR(200) NOT NULL,
  sexo VARCHAR(20) NOT NULL,
  PRIMARY KEY (codigo),
  UNIQUE (codigo)
);
CREATE TABLE IF NOT EXISTS GERENTE(
  codigo INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  turno VARCHAR(20) NOT NULL,
  dpi VARCHAR(13) NOT NULL,
  direccion VARCHAR(200) NOT NULL,
  sexo VARCHAR(20) NOT NULL,
  PRIMARY KEY (codigo),
  UNIQUE (codigo)
);
CREATE TABLE IF NOT EXISTS CLIENTE(
  codigo INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  fecha_nacimiento DATE NOT NULL,
  dpi VARCHAR(13) NOT NULL,
  direccion VARCHAR(200) NOT NULL,
  sexo VARCHAR(20) NOT NULL,
  PRIMARY KEY (codigo),
  UNIQUE (codigo)
);
CREATE TABLE IF NOT EXISTS CUENTA(
  codigo INT NOT NULL AUTO_INCREMENT,
  codigo_CLIENTE INT NOT NULL,
  credito DOUBLE NOT NULL,
  fecha_creacion DATE NOT NULL,
  PRIMARY KEY (codigo),
  FOREIGN KEY (codigo_CLIENTE) REFERENCES CLIENTE(codigo),
  UNIQUE (codigo)
);
CREATE TABLE IF NOT EXISTS PDF_DPI(
  id INT NOT NULL AUTO_INCREMENT,
  codigo_CLIENTE INT NOT NULL,
  pdf MEDIUMBLOB NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (codigo_CLIENTE) REFERENCES CLIENTE(codigo),
  UNIQUE (id)
);
CREATE TABLE IF NOT EXISTS MODIFICACION_CLIENTE(
  id INT NOT NULL AUTO_INCREMENT,
  codigo_CLIENTE INT NOT NULL,
  historial_modificacion MEDIUMBLOB NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (codigo_CLIENTE) REFERENCES CLIENTE(codigo),
  UNIQUE (id)
);
CREATE TABLE IF NOT EXISTS MODIFICACION_CAJERO(
  id INT NOT NULL AUTO_INCREMENT,
  codigo_CAJERO INT NOT NULL,
  historial_modificacion MEDIUMBLOB NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (codigo_CAJERO) REFERENCES CAJERO(codigo),
  UNIQUE (id)
);
CREATE TABLE IF NOT EXISTS MODIFICACION_GERENTE(
  id INT NOT NULL AUTO_INCREMENT,
  codigo_GERENTE INT NOT NULL,
  historial_modificacion MEDIUMBLOB NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (codigo_GERENTE) REFERENCES GERENTE(codigo),
  UNIQUE (id)
);
CREATE TABLE IF NOT EXISTS ASOCIACION(
  id INT NOT NULL AUTO_INCREMENT,
  codigo_CUENTA INT NOT NULL,
  codigo_CLIENTE_solicitante INT NOT NULL,
  codigo_CLIENTE_propietario INT NOT NULL,
  estado VARCHAR (15) NOT NULL,
  intento INT NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(codigo_CLIENTE_solicitante) REFERENCES CLIENTE(codigo),
  FOREIGN KEY(codigo_CLIENTE_propietario) REFERENCES CLIENTE(codigo),
  FOREIGN KEY (codigo_CUENTA) REFERENCES CUENTA(codigo),
  UNIQUE (id)
);
CREATE TABLE IF NOT EXISTS TRANSACCION(
  codigo INT NOT NULL AUTO_INCREMENT,
  codigo_CUENTA INT NOT NULL,
  codigo_CAJERO INT NOT NULL,
  tipo VARCHAR (10) NOT NULL,
  fecha DATE NOT NULL,
  hora TIME NOT NULL,
  monto DOUBLE NOT NULL,
  PRIMARY KEY(codigo),
  FOREIGN KEY(codigo_CAJERO) REFERENCES CAJERO(codigo),
  FOREIGN KEY (codigo_CUENTA) REFERENCES CUENTA(codigo),
  UNIQUE (codigo)
);
INSERT INTO
  USUARIO (codigo_usuario, password, rol)
VALUES
  (
    "101",
    "8CX7%%tedj4!yJm4",
    "BANCA-VIRTUAL"
  );
INSERT INTO
  CAJERO (
    codigo,
    nombre,
    turno,
    dpi,
    direccion,
    sexo
  )
VALUES
  (
    "101",
    "Banca Virtual",
    "24/7",
    "101",
    "--",
    "--"
  );