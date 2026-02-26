echo off
set automation=%~dp0

Set Var=%automation:~0,2%
%Var%
cd %automation%

For /F "tokens=1* delims==" %%A IN (src\main\resources\config.properties) DO (
IF "%%A"=="utilPath" (
set util=%%B
goto :break
))

Set Var1=%util:~0,2%
%Var1%
cd %util%
echo on
call mvn install

echo off
Set Var2=%automation:~0,2%
%Var2%
cd %automation%
echo on

call mvn install -Dtestng.filename=CICD_RunScript.xml -DskipTests=false
