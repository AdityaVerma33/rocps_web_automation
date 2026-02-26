@Echo On
Set Var=%1
Set Var1=%Var:~1,2%
Set Var2=%Var:~0,-1%
Echo %Var1%

Set PATH=%JDK_11%\bin;%PATH%
%Var1%
CD %Var2%\bin
sc.bat console
