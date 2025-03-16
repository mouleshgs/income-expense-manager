# income-expense-manager

A simple Java application to manage income and expenses using Swing and AWT.

---

## Requirements
Before running this project, ensure you have the following:
- **Java Development Kit (JDK)** 8 or higher installed.
- A **command-line interface (CLI)** or terminal to run commands.

---

## Setup and Usage

Follow the steps below to compile and run the application:

### 1. Clone the Repository
To get a local copy of the project, use the following command:
```bash
git clone https://github.com/mouleshgs/income-expense-manager.git
```
### 2. Navigate the directory
```bash
cd income-expense-manager
```

### 3. Compile the source code
```bash
javac -d build src/main/*.java src/main/models/*.java src/main/utils/*.java
```

### 4. Run the application
```bash
java -cp build main.MainApplication
```

## Project structure
```
IncomeExpenseManager/
├── src/
│   ├── main/
│   │   ├── MainApplication.java  # Entry point of the application
│   │   ├── models/
│   │   │   └── (Model classes)
│   │   ├── utils/
│   │       └── (Utility classes)
├── build/                        # Compiled bytecode
├── README.md                     # Project documentation
```
