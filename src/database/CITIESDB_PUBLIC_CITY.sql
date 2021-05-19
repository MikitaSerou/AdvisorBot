create table CITY
(
    ID          INTEGER auto_increment
        primary key,
    DESCRIPTION VARCHAR(255),
    CAPITAL     BOOLEAN      not null,
    NAME        VARCHAR(255) not null
        constraint UK_QSSTLKI7NI5OVAARIYY9U8Y79
            unique,
    COUNTRY_ID  INTEGER,
    constraint FKRPD7J1P7YXR784ADKX4PYEPBA
        foreign key (COUNTRY_ID) references COUNTRY (ID)
);

INSERT INTO PUBLIC.CITY (ID, DESCRIPTION, CAPITAL, NAME, COUNTRY_ID) VALUES (1, 'Столица', true, 'МИНСК', 1);
INSERT INTO PUBLIC.CITY (ID, DESCRIPTION, CAPITAL, NAME, COUNTRY_ID) VALUES (2, 'не столица', false, 'ГОМЕЛЬ', 1);
INSERT INTO PUBLIC.CITY (ID, DESCRIPTION, CAPITAL, NAME, COUNTRY_ID) VALUES (3, 'столица', true, 'МОСКВА', 2);
INSERT INTO PUBLIC.CITY (ID, DESCRIPTION, CAPITAL, NAME, COUNTRY_ID) VALUES (4, 'не столица', false, 'ЕКАТЕРИНБУРГ', 2);
INSERT INTO PUBLIC.CITY (ID, DESCRIPTION, CAPITAL, NAME, COUNTRY_ID) VALUES (5, 'слава', true, 'КИЕВ', 3);
INSERT INTO PUBLIC.CITY (ID, DESCRIPTION, CAPITAL, NAME, COUNTRY_ID) VALUES (6, 'героям слава', false, 'ЧЕРНИГОВ', 3);
INSERT INTO PUBLIC.CITY (ID, DESCRIPTION, CAPITAL, NAME, COUNTRY_ID) VALUES (7, 'вввв', true, 'КОПЕНГАГЕН', 4);
INSERT INTO PUBLIC.CITY (ID, DESCRIPTION, CAPITAL, NAME, COUNTRY_ID) VALUES (8, 'Утка', true, 'ПЕКИН', 5);