JAVACC_PATH=javacc-6.0/bin/lib/javacc.jar
JAVACC=java -cp $(JAVACC_PATH) javacc
HILFERUF_PATH=hsfulda.bachelor.javacc.testat2.generatedParser
JUNIT_VERSION=4.12

default:
	echo "Use `make hilferuf` or `make morseparser`"
	exit 1

hilferuf: testHilferufParser

morseparser: compileMorseParser

llparser: compileLLParser

# Generate Parsers
#

generateHilferufParser: src/Testatuebung_2/Hilferuf.jj installJavaCC
	$(JAVACC) src/Testatuebung_2/Hilferuf.jj

generateMorseParser: src/Testatuebung_1/MorseParser.jj
	$(JAVACC) src/Testatuebung_1/MorseParser.jj

# Compiling
#
compile: compileHilferufParser compileHilferufParserTest

compileHilferufParser: generateHilferufParser HilferufParser.java
	javac -d . *.java
	make clean_source

compileHilferufParserTest: compileHilferufParser installJUnit
	javac -encoding ISO-8859-2 -d . -cp .:.downloads/junit.jar tests/HilferufParserTest.java

compileMorseParser: generateMorseParser MorseParserDir
	mv MorseParserConstants.java MorseParser.java  MorseParser/
	cp *.java MorseParser/
	javac MorseParser/*.java

compileLLParser: LLParserDir
	javac -d . src/Testatuebung_3/*.java



# Tests
#
test: testHilferufParser

testHilferufParser: compileHilferufParser compileHilferufParserTest
	java -cp .:.downloads/junit.jar:.downloads/hamcrest-core.jar org.junit.runner.JUnitCore  hsfulda.bachelor.javacc.testat2.test.HilferufParserTest




# Install external ressource
#

dep: installJavaCC installJUnit

installJUnit: downloadDir
	[ ! -f ".downloads/junit.jar" ] && wget -O ".downloads/junit.jar" "https://github.com/junit-team/junit4/releases/download/r$(JUNIT_VERSION)/junit-$(JUNIT_VERSION).jar" || echo "junit is already present"
	[ ! -f ".downloads/hamcrest-core.jar" ] && wget -O ".downloads/hamcrest-core.jar" "https://github.com/mdm-releases/hamcrest-core-releases/blob/master/v1.3/hamcrest-core.jar?raw=true" || echo "hamcrest-core is already present"

installJavaCC: downloadDir
	[ ! -f ".downloads/javacc-6.0.zip" ] && wget -O .downloads/javacc-6.0.zip https://javacc.org/downloads/javacc-6.0.zip || echo "JavaCC is already downloaded"
	[ ! -f "$(JAVACC_PATH)" ] && unzip .downloads/javacc-6.0.zip || echo "JavaCC is already in place"

# Directories
#
downloadDir:
	mkdir -p .downloads

MorseParserDir:
	mkdir -p MorseParser

LLParserDir:
	mkdir -p hsfulda/bachelor/fixedParser

# Cleanup
#
clean: clean_downloads clean_source clean_builds

clean_downloads:
	rm -rf .downloads
	rm -rf javacc-6.0

clean_source:
	 rm -f *.java *.class

clean_builds:
	 rm -rf hsfulda
	 rm -rf MorseParser
