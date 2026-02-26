@Echo On
Set Var=%1
Set RefDBUserName=%2
Set RefDBPassword=%3
Set drive=%Var:~1,2%
Set Var1=%Var:~0,-1%
Echo %drive%

%drive%
CD %Var1%\bin
PreSilentInstallationTasks.bat %RefDBUserName% %RefDBPassword%

