IF "%1"=="" GOTO Continue
cd "%1"
GOTO exec
:Continue
cd ..
:exec
ant -buildfile example.xml -Dport 4445
