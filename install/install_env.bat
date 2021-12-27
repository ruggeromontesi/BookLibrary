@echo off
SET DB_USER=ruggero

echo Using DB_USER "%DB_USER%"

set DB_PASSWORD=test1234
set DB_SID=diq
set DB_PORT=1521
set DB_HOSTNAME=localhost
set WMS_JDBC_URL=jdbc:oracle:thin:@%DB_HOSTNAME%:%DB_PORT%:%DB_SID%
set SYS_DB_USER="sys/orasys@diq.WORLD as sysdba"


