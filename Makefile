default:
	mv deps/manifest.txt .
	mv deps/Stopwatch.class .
	mv deps/Draw.class .
	javac *.java
	jar cmf manifest.txt Jetris.jar *.class
	rm Game.class
	rm Menu.class
	mv manifest.txt deps
	mv Stopwatch.class deps
	mv Draw.class deps