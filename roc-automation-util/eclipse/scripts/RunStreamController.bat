@Echo Off
Set Var=%1
Set Var1=%Var:~0,2%
Echo %Var1%

%Var1%
CD %Var%\bin
sc.bat console

