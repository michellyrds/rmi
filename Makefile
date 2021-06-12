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

MAIN = \
	Main.java

SERVER = \
	PartServer.java

CLIENT = \
	PartClient.java
	
default: $(CLASSES:.java=.class)

main: $(MAIN:.java=.class)

server: $(SERVER:.java=.class)

client: $(CLIENT:.java=.class)

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class