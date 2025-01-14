# Sauce Demo Automation

This project contains automated tests for the [Sauce Demo](https://www.saucedemo.com/) e-commerce website. The tests are designed to verify the functionality of the website, including login, product management, cart, and checkout processes.

## Technologies Used

- **Java 17**: Core language for the test framework.
- **JUnit 5**: Test framework for structuring and running tests.
- **Selenium WebDriver**: Browser automation tool.
- **WebDriverManager**: Simplifies driver management.
- **Maven**: Build and dependency management tool.
- **Allure**: Test reporting framework for enhanced visualization.
- **SLF4J**: Logging framework.

## Project Structure

```
|-- src
|   |-- main
|   |-- test
|       |-- java
|           |-- com.sasha.data       # Test data enums and helpers
|           |-- com.sasha.pages      # Page Object Model (POM) classes
|           |-- com.sasha.tests      # Test classes
|           |-- com.sasha.utils      # Utility classes (e.g., DriverWrapper, Waitable)
|       |-- resources                # allure.properties
```

### Key Classes

- **Pages**:
    - `LoginPage`: Handles login functionality.
    - `ProductPage`: Handles product management and sorting.
    - `CartPage`: Manages cart operations like adding/removing items.
    - `CheckoutStepOnePage`, `CheckoutStepTwoPage`: Handle multi-step checkout process.
    - `BurgerMenu`: Handles interactions with the side menu.
    - `CheckoutCompletePage`: Confirms successful checkout.

- **Utilities**:
    - `DriverWrapper`: A custom wrapper for Selenium WebDriver to enhance functionality, including methods for taking screenshots, waiting, clearing browser data, and handling dropdowns.
    - `Waitable`: Abstract class for custom wait conditions.

- **Tests**:
    - `SomeTests`: Contains parameterized and functional tests for login, cart operations, sorting, and checkout.
    - `TestRunner`: Base test class for setup and teardown logic.

- **Data**:
    - `Credentials`: Enum for storing user credentials.
    - `Products`: Enum for predefined product data.
    - `SortOptions`: Enum for product sorting options.

## Test Coverage

The project covers the following scenarios:

1. **Login Tests**:
    - Verifies successful and unsuccessful login scenarios.
2. **Product Management**:
    - Adding/removing products to/from the cart.
    - Validating product names, prices, and descriptions.
3. **Cart Operations**:
    - Ensures items are correctly added and removed from the cart.
4. **Checkout Process**:
    - Multi-step checkout validations.
    - Error handling for missing fields.
5. **Sorting Options**:
    - Validates sorting of products by name and price.
6. **Menu Options**:
    - Verifies visibility and functionality of side menu options.

## Setup and Execution

### Prerequisites

- Java 17 or later
- Maven 3.8 or later
- Chrome browser
- ChromeDriver
- Allure command-line tool for reporting

### Steps to Run Tests

1. Clone the repository:
   ```
   git clone https://github.com/Sasha18rt/sauce-demo-automation/tree/main
   ```

2. Navigate to the project directory:
   ```
   cd sauce-demo-automation
   ```

3. Run the tests using Maven:
   ```
   mvn clean test
   ```

4. Generate and view Allure reports:
   ```
   mvn allure:serve
   ```

## Reporting

- **Allure Reports**:
    - The project integrates Allure for comprehensive test reporting.
    - To generate and view reports, run `mvn allure:serve` after executing tests.

## Key Features

- **Page Object Model (POM)**: Ensures maintainability and scalability.
- **Parameterized Tests**: Validates multiple scenarios using test data enums.
- **Driver Wrapper**: Custom utilities for enhanced WebDriver functionality.
- **Logging**: SLF4J integration for debugging and logging.

## Future Enhancements

- Add cross-browser testing capabilities.
- Expand test coverage for additional edge cases.
- Integrate CI/CD pipeline for automated testing.

---

### Author

Sasha18rt
