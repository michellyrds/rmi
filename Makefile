JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		Main.java \
		Part.java \
		PartClient.java \
		PartImpl.java \
		PartRepository.java \
		PartRepositoryImpl.java \
		PartServer.java
	
default: CLASSES

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class