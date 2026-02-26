@Echo On
Set Var=%1
Set RefDBUserName=%2
Set RefDBPassword=%3
Set drive=%Var:~0,2%
Echo %drive%

%drive%
CD %Var%\bin
PreSilentInstallationTasks.bat %RefDBUserName% %RefDBPassword%

