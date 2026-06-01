Init:
    Redis:
        Go to the location and run redis-server.exe
        C:\Users\hassan.najeeb\Desktop\SpringVault\dependencies\Redis\redis-server.exe
    Postgres:
        To initialize the DB:
            C:\Users\hassan.najeeb\Desktop\SpringVault\dependencies\postgres\pgsql\bin\initdb.exe -D 
            C:\Users\hassan.najeeb\Desktop\SpringVault\dependencies\pgdata -U postgres --auth-local=trust
        To start the DB:
            C:\Users\hassan.najeeb\Desktop\SpringVault\dependencies\postgres\pgsql\bin\pg_ctl.exe -D "C:\Users\hassan.najeeb\Desktop\SpringVault\dependencies\pgdata" -l logfile start
        To create the project DB:
            C:\Users\hassan.najeeb\Desktop\SpringVault\dependencies\postgres\pgsql\bin\createdb.exe -U postgres springvault

How to run:
    Start Postgres server:
        C:\Users\hassan.najeeb\Desktop\SpringVault\dependencies\postgres\pgsql\bin\pg_ctl.exe -D "C:\Users\hassan.najeeb\Desktop\SpringVault\dependencies\pgdata" -l logfile start
    Start Redis server:
        C:\Users\hassan.najeeb\Desktop\SpringVault\dependencies\Redis> .\redis-server.exe
    Start Spring app:
        .\gradlew.bat bootRun
        OR
        Click play on top

How to stop:
    Postgres:
        C:\Users\hassan.najeeb\Desktop\SpringVault\dependencies\postgres\pgsql\bin\pg_ctl.exe -D "C:\Users\hassan.najeeb\Desktop\SpringVault\dependencies\pgdata" stop

How to test:
    Postgres:
        Test-NetConnection -ComputerName localhost -Port 5432 
        OR
        C:\Users\hassan.najeeb\Desktop\SpringVault\dependencies\postgres\pgsql\bin\psql.exe -U postgres -d springvault

        Add your connection as localhost on the psql explorer to see your tables
    Redis:
        Test-NetConnection -ComputerName localhost -Port 6379 
        OR
        C:\Users\hassan.najeeb\Desktop\SpringVault\dependencies\Redis\redis-cli.exe
        > ping

Extensions:
    PostgresSQL
