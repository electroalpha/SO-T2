JFLAGS = -g
JC = javac
JVM = java
MAIN = Tarea2p2
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Tarea2p2.java \
	MergeSort.java \

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(JVM) $(MAIN)

clean:
	$(RM) *.class
