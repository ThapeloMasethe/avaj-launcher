find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java simulator.Simulator scenario.txt e6e219eddaae29b43f1e5b6960ce80e9