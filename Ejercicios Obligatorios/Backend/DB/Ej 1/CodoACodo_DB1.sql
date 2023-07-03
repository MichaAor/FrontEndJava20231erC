
CREATE DATABASE IF NOT EXISTS Com23050DB;

USE Com23050DB;

CREATE TABLE IF NOT EXISTS Usuario (
    id INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(40)  COLLATE latin1_swedish_ci NOT NULL,
    apellido VARCHAR(40)  COLLATE latin1_swedish_ci NOT NULL,
    edad TINYINT(2) NOT NULL,
    fechaNac TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    provincia VARCHAR(30) COLLATE latin1_swedish_ci NOT NULL
);

INSERT INTO Usuario(nombre,apellido,edad,fechaNac,provincia) VALUES ("Jorge", "Pinto",23,"1999-06-25","Buenos aires");
INSERT INTO Usuario(nombre,apellido,edad,fechaNac,provincia) VALUES ("Matilda", "Gonzales",26,"1997-04-10","Catamarca");
INSERT INTO Usuario(nombre,apellido,edad,fechaNac,provincia) VALUES ("Patricio", "Estrella",37,"1985-10-13","Formosa");
INSERT INTO Usuario(nombre,apellido,edad,fechaNac,provincia) VALUES ("Mauro", "Svilar",52,"1970-08-22","Tucuman");
INSERT INTO Usuario(nombre,apellido,edad,fechaNac,provincia) VALUES ("Agustina", "Cañon",12,"2003-07-05","La Pampa");