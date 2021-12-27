@ECHO OFF

IF "%DB_USER%" == "" call install_env.bat

echo ********************************************
echo %time% Kill DB user %DB_USER% sessions
echo ********************************************
(
 echo BEGIN
 echo FOR x IN ( SELECT Sid, Serial# FROM v$session WHERE schemaname = '%DB_USER%')
 echo   LOOP
 echo     EXECUTE immediate 'Alter System Kill Session '''^|^| x.Sid ^|^| ',' ^|^| x.Serial# ^|^| ''' IMMEDIATE';
 echo   END LOOP;
 echo END;
) | sqlplus %SYS_DB_USER%

echo ********************************************
echo %time% Drop DB user %DB_USER%
echo ********************************************
(
 echo WHENEVER SQLERROR EXIT SQL.SQLCODE
 echo DROP USER %DB_USER% CASCADE;
 echo PURGE RECYCLEBIN;
) | sqlplus %SYS_DB_USER%


echo ********************************************
echo %time% Create DB user %DB_USER%
echo ********************************************

(
echo create user %DB_USER% identified by %DB_PASSWORD% default tablespace users account unlock profile default;
echo GRANT CONNECT, RESOURCE TO %DB_USER%;
echo GRANT CREATE VIEW TO %DB_USER%;
echo GRANT CREATE SEQUENCE TO %DB_USER%;
echo GRANT CREATE SYNONYM TO %DB_USER%;
echo GRANT CREATE MATERIALIZED VIEW TO %DB_USER%;
echo GRANT CREATE ANY MATERIALIZED VIEW TO %DB_USER%;
echo GRANT ALTER ANY MATERIALIZED VIEW TO %DB_USER%;
echo GRANT SELECT ANY DICTIONARY TO %DB_USER%;
echo GRANT UNLIMITED TABLESPACE TO %DB_USER%;
echo GRANT CHANGE NOTIFICATION TO %DB_USER%;
echo GRANT SELECT ON SYS.DBA_PENDING_TRANSACTIONS TO %DB_USER%; 
echo GRANT EXECUTE ON SYS.DBMS_SYSTEM TO %DB_USER%;
echo GRANT SELECT ON SYS.PENDING_TRANS$ TO %DB_USER%;
echo GRANT SELECT ON SYS.DBA_2PC_NEIGHBORS TO %DB_USER%;
echo GRANT EXECUTE ON SYS.DBMS_XA TO %DB_USER%;
echo GRANT SELECT ON SYS.DBA_2PC_PENDING TO %DB_USER%;
echo GRANT FORCE ANY TRANSACTION TO %DB_USER%;
) | sqlplus %SYS_DB_USER%