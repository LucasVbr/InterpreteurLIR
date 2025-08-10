help:
	@echo "Available commands:"
	@echo "  make run          - Run the application"
	@echo "  make test         - Run tests"
	@echo "  make build        - Build the project"
	@echo "  make build-grammar - Generate grammar source files"
	@echo "  make format       - Format the code"
	@echo "  make clean        - Clean the build artifacts"
	@echo "  make help         - Show this help message"

run: format build-grammar
	./gradlew run

test: format build-grammar
	./gradlew test

build:
	./gradlew build

build-grammar:
	./gradlew generateGrammarSource

format:
	./gradlew spotlessApply

clean:
	./gradlew clean

.PHONY: help run clean test build-grammar build format