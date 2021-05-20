# Advisor Telegram Bot

Данный проект представляет из себя Poling телегрм-бота созданного на базе TelegramBots API (https://github.com/rubenlagus/TelegramBots).
Telegram-бот предоставляет информацию о городе, введенном пользователем (в случае когда информация о искомом городе, либо городе с похожим названием присутствует в базе даннх, используемой ботом).
##### Адрес бота в Telegram: `@AdvisorForTripBot`;
- `bot name` = AdvisorForTripBot

##### Так же включено Web-приложение для менеджмента базы данных (`H2`), используемой ботом (добавление/редактирование/удаление стран и городов):
Адрес доступа к веб-приложению через браузер: `http://localhost:8080/advisor-bot/`:
> Примечание:
Таким образом вы попадете на страницу со входом в web-сервисом по менеджменту базы данных бота, где необходимо будет ввести логин и пароль обозначенные ниже.
- `login` - traveler
- `pssword` - advice

Логин и пароль для доступа к базе данных url:`jdbc:h2:file:./src/database/citiesDB`
- `login` - user
- `pssword` - pass

# Запуск
- ### Среда разработки (IDE):
  Из среды разработки - запуск класса com.example.advisorbot.AdvisorBotApplication
- ### Tomcat
  .war файл в директории /AdvisorBot/runing/advisor-bot.war (Использовалась версия tomcat 9.0.46);
  - В директорию c контейнером сервлетов tomcat копировать advisor-bot.war файл в папку webapps;
  - В папке bin контейнера сервлетов запустить скрипт `startup.bat` (windows), или `startup.sh` (linux);
  - перейти в браузере по адресу:
     ```sh
     http://localhost:8080/advisor-bot/
     ```

  Дополнительная информация по запуску:
  - Скачать Tomcat - https://tomcat.apache.org/download-90.cgi.
  - Пример запуска .war файла - https://coderoad.ru/5109112/%D0%9A%D0%B0%D0%BA-%D1%80%D0%B0%D0%B7%D0%B2%D0%B5%D1%80%D0%BD%D1%83%D1%82%D1%8C-%D1%84%D0%B0%D0%B9%D0%BB-war-%D0%B2-Tomcat-7.

- ### Docker
  В случе, если у вас установлен Docker - вы можете загрузить image данного проекта (содержит слой tomcat с установленным в него .war файлом приложения):

     ```sh
      docker-compose up --build
     ```


## Используемые технологии

AdvisorForTripBot использует следующие технологии:
- `Java v.11`
- `Maven` - сборка проекта
- `Spring (Boot)` - универсальный фреймворк с открытым исходным кодом для Java-платформы.
  Модули Spring Framework:
  -  `Spring MVC` - каркас Web-приложения, основанный на HTTP и сервлетах;
  -  `Spring data` - для доступа к базе данных;
  -  `Spring security` - щащита Web-приложения.

- `TelegramBots` - Java Api для создания телеграм-ботов.
  -  `telegrambots-spring-boot-starter` - для работы с API телеграм-бота с использование Spring Boot;
  -  `emoji-java` - парсинг строк для вывода в сообщения emoji;
- `h2` -   открытая кроссплатформенная СУБД, полностью написанная на языке Java.
- `.jsp` - представления Web-страниц.
- `Bootstrap` - UI;
- `Jquery` - скрипты JavaScript;
- `project lombok`.

MIT

**Серов Никита**
