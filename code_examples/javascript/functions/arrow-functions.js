// ---- Syntax variations ----
// Regular function — familiar baseline for comparison
function square(x) {
    return x * x;
}

// Arrow function — full syntax with parentheses and curly braces
const squareArrow = (x) => {
    return x * x;
};

// Arrow function — omitting parentheses for a single parameter
const squareConcise = x => {
    return x * x;
};

// Arrow function — implicit return for a single expression (no curly braces or return)
const squareImplicit = x => x * x;

console.log(squareImplicit(5)); // 25

// Zero parameters — parentheses are required
const greet = () => "Hello!";
console.log(greet()); // "Hello!"

// Multiple parameters — parentheses are required
const add = (a, b) => a + b;
console.log(add(3, 4)); // 7

// Multi-line body — curly braces and explicit return required
const divide = (a, b) => {
    if (b === 0) {
        throw new Error("Cannot divide by zero");
    }
    return a / b;
};

// Arrow functions are most commonly used as concise callbacks, 
// especially in array methods like map, filter, and reduce.
const numbers = [1, 2, 3, 4, 5];

// Regular function callback — more verbose
const doubledVerbose = numbers.map(function (num) {
    return num * 2;
});

// Arrow function callback — cleaner and easier to scan
const doubled = numbers.map(num => num * 2);
console.log(doubled); // [2, 4, 6, 8, 10]

// Chaining with arrow functions stays readable
const result = numbers
  .filter(num => num % 2 !== 0)  // Keep odd numbers
  .map(num => num * 10);          // Multiply each kept number by 10
console.log(result); // [10, 30, 50]

// ---- Lexical 'this' — the key behavioral difference ----

// Regular function — 'this' shifts depending on how it is called
const timer = {
    label: "Timer",
    start: function () {
        setTimeout(function () {
            // 'this' here is NOT the timer object — it has shifted to the global scope
            console.log(this.label); // undefined
        }, 100);
    },
};

// Arrow function — 'this' is inherited from the enclosing scope (the start method)
const timerFixed = {
    label: "Timer",
    start: function () {
        setTimeout(() => {
        // 'this' is still the timerFixed object, as expected
        console.log(this.label); // "Timer"
        }, 100);
    },
};

timerFixed.start();

// ---- Showcasing the WRONG way — arrow function as an object method ----
const user = {
    name: "Alex",
    greet: () => {
        // Arrow function does NOT have its own 'this' — inherits from outer scope
        console.log(`Hello, ${this.name}`); // undefined — 'this' is not the user object
    },
};
user.greet(); // "Hello, undefined" — use a regular function for object methods