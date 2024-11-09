# Polynomial Calculator

This is a Java application designed for performing arithmetic and calculus operations on polynomials. Users can input polynomial expressions and perform operations such as addition, subtraction, multiplication, division, differentiation, and integration. The application provides a user-friendly graphical interface for ease of interaction.

## Features

- **Input Polynomial Expressions**: Enter polynomial expressions as strings in the standard mathematical format.
- **Arithmetic Operations**: Add, subtract, multiply, and divide polynomials.
- **Calculus Operations**: Calculate derivatives and integrals of polynomials.
- **Result Display**: Display results in a clear and readable format.
- **Error Handling**: Provides informative error messages for invalid inputs.
- **Graphical User Interface (GUI)**: Offers a user-friendly environment for interacting with polynomial expressions.

## Project Structure

The project is organized using object-oriented programming principles to promote modularity, encapsulation, and maintainability.

### Main Classes

1. **Monomial**: Represents a term in a polynomial.
2. **Polynomial**: Manages a collection of monomials and supports polynomial manipulations.
3. **DivisionResults**: Stores the quotient and remainder for division operations.
4. **Operations**: Contains methods for all polynomial operations (arithmetic and calculus).
5. **App**: The main application class, implementing the GUI and handling user interactions.

### UML Structure

The application structure follows a clear UML package design:
- **org.Polynomial**: Core polynomial management.
- **org.Polynomial.operations**: Polynomial operations.
- **Testing**: Test classes for functionality verification.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven (for dependencies)

### Installation

1. Clone the repository:
 ```
git clone https://github.com/ButasRafael/Polynomial_Calculator.git
cd polynomial-calculator
```
2. Build the project with Maven:
```
mvn clean install
```
3. Run the application:
```
java -jar target/Polynomial_Calculator.jar
```

## Usage
* Launch the application: The GUI will open, allowing you to enter two polynomial expressions.
* Select an operation: Choose the desired operation from the available options (addition, subtraction, etc.).
* View results: Results are displayed on the interface, along with any error messages for invalid inputs.
### Testing
JUnit tests are implemented to validate all core functionalities. The following tests ensure accuracy:
* Addition, Subtraction, Multiplication, Division: Validates arithmetic operations.
* Differentiation, Integration: Checks calculus operations.
* Parsing and String Conversion: Tests polynomial input parsing and output formatting.
* To run tests:
```
mvn test
```
## Future Development
### Potential enhancements include:
* Advanced features such as symbolic computation and graph plotting.
* Performance optimizations for large polynomial expressions.
* Cross-platform compatibility for web and mobile access.
