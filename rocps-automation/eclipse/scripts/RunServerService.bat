@Echo Off
Set Var=%1
Set Var1=%Var:~1,2%
Set Var2=%Var:~0,-1%
Echo %Var1%

%Var1%
CD %Var2%\bin
ServerService.bat console
