@Echo On
Set Var=%1
Set drive=%Var:~1,2%
Set Var1=%Var:~0,-1%
Echo %drive%

%drive%
CD %Var1%\bin
Installer.bat

