// Best practice: place "use strict" at the TOP of the file
// so it applies to the entire script, not just one function
// "use strict";    // commented out for demonstration purposes - but include this as the first line in your scripts as a best practice


// Without strict mode (sloppy mode) — mistakes can fail silently
function sloppyExample() {
    undeclaredVar = "I was never declared with let/const/var";
    // No error is thrown — this quietly becomes a global variable
}
sloppyExample();
console.log(undeclaredVar); // Works, but this is dangerous and unintended

// With strict mode — the same mistake throws an error
function strictExample() {
    "use strict"; // Must be the first line in the function (or file)
    otherUndeclaredVar = "This will throw an error";
    // ReferenceError: otherUndeclaredVar is not defined
}


// Strict mode also catches duplicate parameter names
// function badParams(a, a) {
//     "use strict";
//     // SyntaxError: Duplicate parameter name not allowed in this context
// }
