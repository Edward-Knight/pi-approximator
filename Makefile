PACKAGE = me.edwardknight.pi_approximator
MAIN_CLASS = $(PACKAGE).GUI

# todo: look over `jar`, `javac`, and `jlink` command line arguments to make build more minimal

# target: build - Compile the module using javac
build:
	mkdir -p build
	javac -d build --module-source-path src `find src -name "*.java"`

# target: clean - Recursively delete ./build and ./dist
clean:
	-rm -rf build dist

# target: dist - Make all final build artifacts
dist: jar jlink tar

# target: jar - Create a JAR
jar: build
	mkdir -p dist
	jar --create --file=dist/pi-approximator.jar --main-class=$(MAIN_CLASS) -C build/$(PACKAGE) .

# target: jlink - Create and link to a minimal Java distribution with jlink
jlink: build
	mkdir -p dist
	-rm -rf dist/pi-approximator
	jlink --module-path build --add-modules $(PACKAGE) --output dist/pi-approximator

# target: run - Run the module
run: build
	java --module-path build -m $(PACKAGE)/$(MAIN_CLASS)

# target: tar - Compress the jlink distribution into a tarball
tar: jlink
	mkdir -p dist
	tar --create --gzip --file dist/pi-approximator.tar.gz dist/pi-approximator

# target: help - Display targets
help:
	grep "^# target: " Makefile

.PHONY: clean jar jlink run tar help
