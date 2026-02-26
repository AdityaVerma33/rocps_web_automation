@Echo On
Set Var=%1
Set drive=%Var:~0,2%
Echo %drive%

%drive%
CD %Var%\bin
Installer.bat

