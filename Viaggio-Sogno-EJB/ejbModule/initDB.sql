INSERT INTO USER_ (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME) values ('ciao@ciao.ciao','A0C299B71A9E59D5EBB07917E70601A3570AA103E99A7BB65A58E780EC9077B1902D1DEDB31B1457BEDA595FE4D71D779B6CA9CAD476266CC07590E31D84B206','hi', 'hello');
INSERT INTO USER_GROUP (email, groupname) values ('ciao@ciao.ciao','CUSTOMER');
INSERT INTO USER_ (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME) values ('a@a.a','A0C299B71A9E59D5EBB07917E70601A3570AA103E99A7BB65A58E780EC9077B1902D1DEDB31B1457BEDA595FE4D71D779B6CA9CAD476266CC07590E31D84B206','employee', 'a');
INSERT INTO USER_GROUP (email, groupname) values ('a@a.a','EMPLOYEE');
INSERT INTO PACKAGE (NAME, SHOWCASED, USER_idUSER) values ('vieni a ballare in puglia', true, 2);
INSERT INTO PACKAGE (NAME, SHOWCASED, USER_idUSER) values ('vieni a ballare in salento', true, 2);
INSERT INTO PACKAGE (NAME, SHOWCASED, USER_idUSER) values ('vieni a ballare sotto le stelle', false, 2);
INSERT INTO FINAL_PACKAGE (PACKAGE_idPACKAGE, USER_idUSER) values (2, 1);
INSERT INTO FINAL_PACKAGE (PACKAGE_idPACKAGE, USER_idUSER) values (3, 2);

INSERT INTO PRODUCT (NAME, TYPE) values ('Gita in montagna', 'excursion');
INSERT INTO PRODUCT (NAME, TYPE) values ('Volo dalla nonna', 'flight');
INSERT INTO PRODUCT (NAME, TYPE) values ('Volo dalla mamma', 'flight');
INSERT INTO PRODUCT (NAME, TYPE) values ('Hotel da Zio Berto', 'hotel');

