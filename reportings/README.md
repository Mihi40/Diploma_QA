## Процедура запуска автотестов:
1. #### Склонировать репозиторий с Github командой ***git clone*** по ссылке: https://github.com/Mihi40/Diploma_QA .
2. #### Открыть проект в IntelliJ IDEA.
3. #### Запустить Docker в терминале на отдельной вкладке (Local) командой: ***docker-compose up -d***.
4. #### Запустить jar-файл в терминале на отдельной вкладке (Local) командой:
- для SQL: ***java '-Dspring.datasource.url=jdbc:mysql://localhost:3306/app' -jar ./artifacts/aqa-shop.jar***
- для Postgres: ***java  "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar .\artifacts\aqa-shop.jar***
5. #### Запустить автотесты java в терминале на отдельной вкладке (Local) командой:
- для SQL: ***./gradlew clean test '-Ddb.url=jdbc:mysql://localhost:3306/app'***
- для Postgres: ***./gradlew clean test '-Ddb.url=jdbc:postgresql://localhost:5432/app'***
6. #### Сформировать отчет в терминале на отдельной вкладке (Local) командой: ***./gradlew allureServe***.
7. #### Остановить контейнер (при необходимости) в терминале на отдельной вкладке (Local) командой: ***docker compose down***.