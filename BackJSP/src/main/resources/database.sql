DROP DATABASE IF EXISTS bdjsp;
CREATE DATABASE IF NOT EXISTS bdjsp;

USE bdjsp;

CREATE TABLE IF NOT EXISTS user(
    id bigint PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    dni VARCHAR(8) UNIQUE NOT NULL CHECK(dni regexp '[0-9]{8}'),
    email VARCHAR(80) UNIQUE NOT NULL,
    password VARCHAR(20) NOT NULL
);

INSERT INTO user VALUES (0,'Carlos','Rodriguez','45789123','carlos@gmail.com','12345678mxeA');
INSERT INTO user VALUES (0,'Maria','Miraflor','37895212','mary@gmail.com','248975100eer0');
INSERT INTO user VALUES (0,'Ricardo','Bolilla','78945123','ricky@gmail.com','maravilla411');

CREATE TABLE IF NOT EXISTS sale(
    id BIGINT PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
    saleNum BIGINT UNIQUE NOT NULL CHECK ( sale.saleNum < 9999999999),
    amount INT NOT NULL CHECK ( amount < 5),
    finalPrice DEC(10,2) NOT NULL,
    idUser BIGINT NOT NULL,
        CONSTRAINT fk_saleUser FOREIGN KEY(idUser)
            REFERENCES user(id)
);

INSERT INTO sale VALUES (0,1,2,400.00,1);
INSERT INTO sale VALUES (0,2,1,200.00,2);
INSERT INTO sale VALUES (0,3,1,200.00,3);

CREATE TABLE IF NOT EXISTS ticket(
    id BIGINT PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
    ticketNum BIGINT UNIQUE NOT NULL CHECK ( ticketNum < 9999999999),
    price DEC(10,2) NOT NULL,
    eventDate DATE,
    idUser BIGINT NOT NULL,
        CONSTRAINT fk_ticketUser FOREIGN KEY(idUser)
            REFERENCES user(id)
);

INSERT INTO ticket VALUES (0,1,200.00,'2023-11-07',1);
INSERT INTO ticket VALUES (0,2,200.00,'2023-11-07',1);
INSERT INTO ticket VALUES (0,3,200.00,'2023-12-22',2);
INSERT INTO ticket VALUES (0,4,200.00,'2023-10-20',3);

-- SELECT ticket.id,ticket.price,ticket.idUser FROM ticket INNER JOIN user ON ticket.idUser = user.id WHERE user.email = 'mary@gmail.com';











# CREATE TABLE IF NOT EXISTS saleTicket(
#     idSale BIGINT NOT NULL,
#     idTicket BIGINT NOT NULL,
#         CONSTRAINT fk_saleTicket FOREIGN KEY(idSale)
#             REFERENCES sale(id),
#         CONSTRAINT fk_ticketSale FOREIGN KEY(idTicket)
#             REFERENCES ticket(id)
# );
#
# INSERT INTO saleTicket VALUES (1,1);
# INSERT INTO saleTicket VALUES (1,2);
# INSERT INTO saleTicket VALUES (2,3);
# INSERT INTO saleTicket VALUES (3,4);
#
#
# SELECT sale.id,sale.saleNum,sale.amount,sale.finalPrice,saleTicket.idTicket
#     FROM sale INNER JOIN saleTicket ON  sale.id = saleTicket.idSale WHERE sale.id = 1;
