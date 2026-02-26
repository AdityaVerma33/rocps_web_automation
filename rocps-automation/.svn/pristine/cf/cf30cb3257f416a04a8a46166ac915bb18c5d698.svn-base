IF "%1"=="" GOTO Continue
cd "%1"
GOTO exec
:Continue
cd ../../
:exec
ant -buildfile ui.xml -Dport 4449

