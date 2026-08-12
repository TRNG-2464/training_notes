// ---- var hoisting — declaration hoisted, initialized to undefined ----

// JavaScript implicitly declares variables that you haven't declared
// in the same way as 'var' keyword delcared variables
// var undeclaredVar = "Some value";
undeclaredVar = "Some value";

console.log(myVar); // undefined — no error, but the value hasn't been assigned yet
var myVar = "Hello";
console.log(myVar); // "Hello"

// What JavaScript actually sees after hoisting:
// var myVar;           <- hoisted to the top, set to undefined
// console.log(myVar);  <- undefined
// myVar = "Hello";
// console.log(myVar);  <- "Hello"

// ---- let and const — Temporal Dead Zone (TDZ) ----
try {
    console.log(myLet); // ReferenceError: Cannot access 'myLet' before initialization
    let myLet = "Hello";
} catch (error) {
    console.log(error.message);
}

// Same behavior with const
try {
    console.log(myConst); // ReferenceError
    const myConst = "Hello";
} catch (error) {
    console.log(error.message);
}

// ---- Function declarations — fully hoisted, body and all ----
// This works because the entire function is hoisted before any code runs
console.log(add(2, 3)); // 5 — called before the declaration, no error

function add(a, b) {
    return a + b;
}

// When using function Expressions JavaScript will treat the variable
// as if it were just a variable (because it is...) - as such, the body
// of the function is NOT hoisted!
var myFunc = function () { console.log("My Function") };



// ---- Function expressions — NOT fully hoisted ----
// Only the variable declaration is hoisted, not the function assignment
try {
    console.log(multiply(2, 3)); // TypeError: multiply is not a function
    const multiply = (a, b) => a * b;
} catch (error) {
    console.log(error.message);
}

// ---- var hoisting bug — a realistic example ----
// Showcasing the WRONG way: var inside a loop can produce unexpected behavior
for (var i = 0; i < 3; i++) {
    setTimeout(() => console.log(i), 100);
}
// Prints: 3, 3, 3 — because var is function-scoped and hoisted,
// all three callbacks share the same 'i', which is 3 by the time they run

// Best practice: use let, which is block-scoped and avoids this issue
for (let j = 0; j < 3; j++) {
    setTimeout(() => console.log(j), 100);
}
// Prints: 0, 1, 2 — each iteration gets its own block-scoped 'j'