/* ==== POPULATE CLIENTES ==== */
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (1, 'Andres', 'Guzman', 'profesor@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (2, 'John', 'Doe', 'profesor2@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (3, 'Denis', 'Lopez', 'profesor3@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (4, 'Marcos', 'Gonzales', 'profesor4@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (5, 'Luis', 'Altamirano', 'profesor5@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (6, 'Manuel', 'Garcia', 'profesor6@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (7, 'Joel', 'Herrera', 'profesor7@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (8, 'Erich', 'Gamma', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (9, 'Richard', 'Guzman', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (10, 'John', 'Helm', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (11, 'James', 'Johnson', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (12, 'Bruce', 'Gosling', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (13, 'Sam', 'Webb', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (14, 'Bruce', 'Lee', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (15, 'Phillip', 'Webb', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (16, 'Samsung', 'Corea', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (17, 'John', 'Stiles', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (18, 'Bill', 'Gates', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (19, 'Steve', 'Jobs', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (20, 'Gustavo', 'Maincra', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (21, 'Janie', 'Roe', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (22, 'Gucci', 'Gucci', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (23, 'Michael', 'Jordan', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (24, 'Michael', 'Jackson', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (25, 'Fredie', 'Mercuri', 'profesor8@email.com', '2017-08-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (26, 'Madam', 'Curie', 'profesor8@email.com', '2017-08-28', '');

/* ==== POPULATE PRODUCTOS ==== */
INSERT INTO productos (nombre, precio, create_at) VALUES("PANASONIC Pantalla LCD", 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES("Sony Camara digital DSC-W320B", 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES("Apple iPod shuffle", 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES("Sony NoteBook Z110", 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES("Hewlett Packard Multifuncional F2280", 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES("Bianchi Bicicleta Aro 26", 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES("Mica Comoda 5 Cajones", 299990, NOW());

/* ==== POPULATE FACTURAS ==== */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES("Factura Equipos de oficina", null, 1, NOW());
INSERT INTO factura_items(cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO factura_items(cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO factura_items(cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO factura_items(cantidad, factura_id, producto_id) VALUES(1, 1, 7);
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES("Factura Bicicleta", "Alguna nota importante", 1, NOW());
INSERT INTO factura_items(cantidad, factura_id, producto_id) VALUES(3, 2, 6);
