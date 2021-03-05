
all: sources testing

sources: src/Jauge.java src/Position.java src/FauxPassager.java src/FauxVehicule.java src/Passager.java src/Vehicule.java src/PassagerStandard.java src/Autobus.java src/Simple.java src/PassagerIndecis.java src/PassagerStresse.java src/CollecteJson.java src/Tramway.java
	mkdir -p build
	cp src/json-simple-1.1.1.jar build
	javac -d build -cp build/json-simple-1.1.1.jar src/*.java
testing:tst/TestCompact.java tst/TestParcours.java tst/TestJauge.java  tst/TestPosition.java tst/AssertionTest.java tst/TestPassagerStandard.java tst/TestAutobus.java tst/TestPassagerAbstrait.java tst/TestPassagerIndecis.java tst/TestPassagerStresse.java tst/TestExceptionsControlees.java tst/TestTramway.java
	mkdir -p build
	javac -d build -cp build tst/*.java

clean:
	rm -f src/*.class tst/*.class
	rm -rf build
	rm -f -rf *[#~]* tst/*[#~]* src/*[#~]*

testAutobus: testing
	java -ea -cp build tec.LancerTests tec.TestAutobus

testPassagerStandard: testing
	java -ea -cp build tec.TestPassagerStandard

simple: sources
	java -ea -cp build Simple

simple_collecte_has: sources
	rm -f build/log.txt
	java -ea -cp build:build/json-simple-1.1.1.jar SimpleCollecteHas

simple_collecte_has_json: sources
	java -ea -cp build:build/json-simple-1.1.1.jar SimpleCollecteJSONHas

simple_collecte_is: sources
	rm -f build/log.txt
	java -ea -cp build:build/json-simple-1.1.1.jar SimpleCollecteIs

simple_collecte_is_json: sources
	java -ea -cp build:build/json-simple-1.1.1.jar SimpleCollecteJSONIs

test: all testAutobus testPassager testPosition testJauge testMontee testArret testExceptionsControlees testCompact testParcours

testPosition: all testing
	java -ea -cp build tec.LancerTests tec.TestPosition

testJauge: all testing
	java -ea -cp build tec.LancerTests tec.TestJauge

testParcours: all testing
	java -ea -cp build tec.LancerTests TestParcours

testCompact: all testing
	java -ea -cp build tec.LancerTests tec.TestCompact

testPassager: all testing
	java -ea -cp build tec.LancerTests tec.TestPassagerStandard
	java -ea -cp build tec.LancerTests tec.TestPassagerIndecis
	java -ea -cp build tec.LancerTests tec.TestPassagerStresse

testMontee: all testing
	java -ea -cp build tec.LancerTests tec.TestMonteeFatigue

testArret: all testing
	java -ea -cp build tec.LancerTests tec.TestArretPrudent
testExceptionsControlees: all tst/TestExceptionsControlees.java
	java -ea -cp build tec.LancerTests TestExceptionsControlees

testGenerique: tst/LancerTests.java
	java -ea -cp build  tec.LancerTests tec.TestJauge

testTramway: testing
	java -ea -cp build tec.LancerTests tec.TestTramway
