INSERT INTO IDPOOL (NEXT) values (1);
INSERT INTO IDPOOL (NEXT) values (1);
INSERT INTO IDPOOL (NEXT) values (1);
INSERT INTO IDPOOL (NEXT) values (1);
INSERT INTO USER_ (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, IDPOOL_IDPOOL_ID) values ('emp@e.e', SHA2('emp', 512), 'Emp1', 'loyee', 3); -- ID:3
INSERT INTO USER_GROUP (email, groupname) values ('emp@e.e','EMPLOYEE');
INSERT INTO USER_ (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, IDPOOL_IDPOOL_ID) values ('cust@c.c', SHA2('cust', 512), 'Cust1', 'omer', 1); -- ID:1
INSERT INTO USER_GROUP (email, groupname) values ('cust@c.c','CUSTOMER');
INSERT INTO USER_ (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, IDPOOL_IDPOOL_ID) values ('cust2@c.c', SHA2('cust', 512), 'Cust2', 'omer', 2); -- ID:2
INSERT INTO USER_GROUP (email, groupname) values ('cust2@c.c','CUSTOMER');

INSERT INTO USER_ (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, IDPOOL_IDPOOL_ID) values ('emp2@e.e', SHA2('emp', 512), 'Emp2', 'loyee', 4); -- ID:4
INSERT INTO USER_GROUP (email, groupname) values ('emp2@e.e','EMPLOYEE');


INSERT INTO PACKAGE (NAME, SHOWCASED, USER_idUSER) values ('Vieni a ballare in Puglia', true, 3); -- ID:1

INSERT INTO PRODUCT (TYPE, ARRAIRPORT, DEPAIRPORT, FLIGHTLENGTH, PRICE) values ('FLIGHT', 'Bari', 'Brindisi', 20, 96); -- ID:1
INSERT INTO PRODUCT (TYPE, ARRAIRPORT, DEPAIRPORT, FLIGHTLENGTH, PRICE) values ('FLIGHT', 'Roma', 'Brindisi', 50, 70); -- ID:2
INSERT INTO PRODUCT (TYPE, NAME, DESCRIPTION, LOCATION, RATING, PRICE) values ('HOTEL', 'Hotel \'Mira Terronia\'', 'Accogliente hotel a conduzione familiare','Brindisi', 2, 16); -- ID:3
INSERT INTO PRODUCT (TYPE, NAME, DESCRIPTION, LOCATION, PRICE) values ('EXCURSION', 'Visita Castello', 'Il Castello Svevo voluto da Federico II, risale al 1227, ma al nucleo originario fu aggiunto un antemurale con poderosi torri angolari ad opera degli Aragonesi', 'Brindisi', 5); -- ID:4
INSERT INTO PRODUCT (TYPE, NAME, DESCRIPTION, LOCATION, PRICE) values ('EXCURSION', 'Monumento al Marinaio', 'Il Monumento al Marinaio disegnato da Luigi Brunati (1933-1934), ha la forma di un timone alto 53 m in pietra di carparo che spicca sul porto della citta.', 'Brindisi', 5); -- ID:5

INSERT INTO PACKAGE_PRODUCT (PACKAGE_IDPACKAGE, PRODUCT_IDPRODUCT, FIRST_CHOICE) values (1, 1, 1);
INSERT INTO PACKAGE_PRODUCT (PACKAGE_IDPACKAGE, PRODUCT_IDPRODUCT, FIRST_CHOICE) values (1, 2, 0);
INSERT INTO PACKAGE_PRODUCT (PACKAGE_IDPACKAGE, PRODUCT_IDPRODUCT, FIRST_CHOICE) values (1, 3, 1);
INSERT INTO PACKAGE_PRODUCT (PACKAGE_IDPACKAGE, PRODUCT_IDPRODUCT, FIRST_CHOICE) values (1, 4, 1);
INSERT INTO PACKAGE_PRODUCT (PACKAGE_IDPACKAGE, PRODUCT_IDPRODUCT, FIRST_CHOICE) values (1, 5, 1);


INSERT INTO PACKAGE (NAME, SHOWCASED, USER_idUSER) values ('Vieni a sballarti in Padania', true, 4); -- ID:2

INSERT INTO PRODUCT (TYPE, ARRAIRPORT, DEPAIRPORT, FLIGHTLENGTH, PRICE) values ('FLIGHT', 'Roma', 'Milano', 65, 75); -- ID:6
INSERT INTO PRODUCT (TYPE, ARRAIRPORT, DEPAIRPORT, FLIGHTLENGTH, PRICE) values ('FLIGHT', 'Cagliari', 'Milano', 85, 90); -- ID:7
INSERT INTO PRODUCT (TYPE, NAME, DESCRIPTION, LOCATION, RATING, PRICE) values ('HOTEL', 'Hotel \'Polenta\'', 'Centralissimo, ben servito dai mezzi', 'Milano', 3, 30); -- ID:8
INSERT INTO PRODUCT (TYPE, NAME, DESCRIPTION, LOCATION, RATING, PRICE) values ('HOTEL', 'Hotel \'Padre Pio\'', 'Centralissimo, ben servito dai mezzi', 'Milano', 4, 30); -- ID:9
INSERT INTO PRODUCT (TYPE, NAME, DESCRIPTION, LOCATION, RATING, PRICE) values ('HOTEL', 'Hotel \'Sciura\'', 'Centralissimo, ben servito dallo smog', 'Milano', 2, 20); -- ID:10
INSERT INTO PRODUCT (TYPE, NAME, DESCRIPTION, LOCATION, PRICE) values ('EXCURSION', 'Navigli', 'Meranavigliati anche tu', 'Milano', 5); -- ID:11
INSERT INTO PRODUCT (TYPE, NAME, DESCRIPTION, LOCATION, PRICE) values ('EXCURSION', 'Duomo', 'Beh, quello', 'Milano', 5); -- ID:12

INSERT INTO PACKAGE_PRODUCT (PACKAGE_IDPACKAGE, PRODUCT_IDPRODUCT, FIRST_CHOICE) values (2, 6, 1);
INSERT INTO PACKAGE_PRODUCT (PACKAGE_IDPACKAGE, PRODUCT_IDPRODUCT, FIRST_CHOICE) values (2, 7, 0);
INSERT INTO PACKAGE_PRODUCT (PACKAGE_IDPACKAGE, PRODUCT_IDPRODUCT, FIRST_CHOICE) values (2, 8, 1);
INSERT INTO PACKAGE_PRODUCT (PACKAGE_IDPACKAGE, PRODUCT_IDPRODUCT, FIRST_CHOICE) values (2, 9, 0);
INSERT INTO PACKAGE_PRODUCT (PACKAGE_IDPACKAGE, PRODUCT_IDPRODUCT, FIRST_CHOICE) values (2, 10, 0);
INSERT INTO PACKAGE_PRODUCT (PACKAGE_IDPACKAGE, PRODUCT_IDPRODUCT, FIRST_CHOICE) values (2, 11, 0);
INSERT INTO PACKAGE_PRODUCT (PACKAGE_IDPACKAGE, PRODUCT_IDPRODUCT, FIRST_CHOICE) values (2, 12, 1);

INSERT INTO FINALPACKAGE (PACKAGE_idPACKAGE, USER_idUSER, IDFINAL_PACKAGE_RELATIVE) values (1, 1, 1); -- ID:1

/*INSERT INTO FINAL_FLIGHT (DEPARTURE, PRODUCT_idPRODUCT, IDFINAL_PRODUCT_RELATIVE) values ('2014-03-20 14:00', 1, 2); -- ID:1
INSERT INTO final_package_finalproduct (`FinalPackage_IDFINAL_PACKAGE`,`finalProducts_IDFINAL_PRODUCT`) VALUES (1,1);
INSERT INTO FINAL_PACKAGE_HAS_PRODUCT (FINAL_PACKAGE_idFINAL_PACKAGE, PRODUCT_idPRODUCT) values (1, 3);
INSERT INTO FINAL_EXCURSION (DATE, PRODUCT_idPRODUCT, IDFINAL_PRODUCT_RELATIVE) values ('2014-03-21', 4, 3); -- ID:1
INSERT INTO final_package_finalproduct (`FinalPackage_IDFINAL_PACKAGE`,`finalProducts_IDFINAL_PRODUCT`) VALUES (1,2);
INSERT INTO FINAL_PACKAGE_HAS_PRODUCT (FINAL_PACKAGE_idFINAL_PACKAGE, PRODUCT_idPRODUCT) values (1, 5);*/


INSERT INTO FINALPACKAGE (PACKAGE_idPACKAGE, USER_idUSER, IDFINAL_PACKAGE_RELATIVE) values (1, 1, 2); -- ID:2
UPDATE IDPOOL SET NEXT=3 WHERE IDPOOL_ID=1;

INSERT INTO FINALPACKAGE_PRODUCT (FINAL_PACKAGE_idFINAL_PACKAGE, PRODUCT_idPRODUCT) values (2, 1);
INSERT INTO FINALPACKAGE_PRODUCT (FINAL_PACKAGE_idFINAL_PACKAGE, PRODUCT_idPRODUCT) values (2, 3);
INSERT INTO FINALPACKAGE_PRODUCT (FINAL_PACKAGE_idFINAL_PACKAGE, PRODUCT_idPRODUCT) values (2, 4);
INSERT INTO FINALPACKAGE_PRODUCT (FINAL_PACKAGE_idFINAL_PACKAGE, PRODUCT_idPRODUCT) values (2, 5);


INSERT INTO FINALPACKAGE (PACKAGE_idPACKAGE, USER_idUSER, IDFINAL_PACKAGE_RELATIVE) values (2, 2, 1); -- ID:3
/*
INSERT INTO FINAL_FLIGHT (DEPARTURE, PRODUCT_idPRODUCT, IDFINAL_PRODUCT_RELATIVE) values ('2014-03-20 17:30', 7, 2); -- ID:2
INSERT INTO FINAL_HOTEL (CHECK_IN, CHECK_OUT, PRODUCT_idPRODUCT, IDFINAL_PRODUCT_RELATIVE) values ('2014-03-20', '2014-03-27', 9, 3); -- ID:1
INSERT INTO FINAL_EXCURSION (DATE, PRODUCT_idPRODUCT, IDFINAL_PRODUCT_RELATIVE) values ('2014-03-21', 11, 4); -- ID:2*/

UPDATE IDPOOL SET NEXT=2 WHERE IDPOOL_ID=2;
