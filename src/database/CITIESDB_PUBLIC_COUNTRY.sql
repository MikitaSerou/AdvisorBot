insert into PUBLIC.CURRENCY (NAME)
values  ('BYN'),
        ('RUB'),
        ('UAH'),
        ('USD'),
        ('EUR');

insert into PUBLIC.COUNTRY (ID, CURRENCY_ID, abbreviation, NAME)
values  (1, 1, 'by', 'Беларусь'),
        (2, 3, 'ua', 'Украина'),
        (3, 4, 'us', 'США'),
        (4, 2, 'ru', 'Россия');

