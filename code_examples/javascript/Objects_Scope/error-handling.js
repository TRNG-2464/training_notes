"use strict"

// Basic try/catch structure
try {
    const result = JSON.parse("{ invalid json }"); // This will throw a SyntaxError
    console.log(result); // Never reached
} catch (error) {
    console.log("Something went wrong:", error.message);
}

// The catch block receives the Error object directly
try {
    null.someProperty; // Trying to access a property on null
} catch (error) {
    console.log(error.name);    // "TypeError"
    console.log(error.message); // "Cannot read properties of null (reading 'someProperty')"
}


function divide(a, b) {
    if (b === 0) {
        throw new Error("Cannot divide by zero!");
    }
    return a / b;
}
try {
    const result = divide(10, 2);
    console.log("try:", result);          // try: 5
} catch (error) {
    console.log("catch:", error.message); // Skipped - no error was thrown
} finally {
    console.log("finally: always runs"); // finally: always runs
}

// Demonstrating a few built-in error types
try {
    const arr = new Array(-1); // Invalid array length
} catch (error) {
    console.log(error.name); // "RangeError"
}

try {
    console.log(undeclaredVariable); // Never declared anywhere
} catch (error) {
    console.log(error.name); // "ReferenceError"
}

try {
    const notAFunction = 42;
    notAFunction(); // Trying to call a number as if it were a function
} catch (error) {
    console.log(error.name); // "TypeError"
}

// Code that doesn't throw simply skips the catch block entirely
try {
    const safeResult = JSON.parse('{"valid": true}');
    console.log("Parsed successfully:", safeResult); // This runs normally
} catch (error) {
    console.log("This never executes");
}


// Using throw to manually trigger an error
function withdraw(balance, amount) {
    if (amount > balance) {
        throw new Error("Insufficient funds for this withdrawal");
    }
    return balance - amount;
}

try {
    withdraw(100, 150);
} catch (error) {
    console.log(error.message); // "Insufficient funds for this withdrawal"
}

// Showcasing the WRONG way — throwing a plain string instead of an Error
function badThrowExample() {
    throw "Something went wrong"; // Avoid this loses stack trace and .message structure
}

try {
    badThrowExample();
} catch (error) {
    console.log(typeof error); // "string" not nearly as useful as an Error object
}

// Creating a custom error class by extending Error
class ValidationError extends Error {
  constructor(message, field) {
    super(message); // Sets up the inherited .message property correctly
    this.name = "ValidationError"; // Overrides the default "Error" name
    this.field = field; // Custom property, beyond what the base Error provides
  }
}

function validateAge(age) {
    if (age < 0) {
        // Custom Errors can be thrown within your logic
        throw new ValidationError("Age cannot be negative", "age");
    }
    return age;
}

try {
    validateAge(-5);
} catch (error) {
    if (error instanceof ValidationError) {
        console.log(`${error.name}: ${error.message} (field: ${error.field})`);
    } else {
        console.log("An unexpected error occurred:", error.message);
    }
}
