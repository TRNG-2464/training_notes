// ---- Basic default parameter ----
function greet(name, greeting = "Hello") {
    console.log(`${greeting}, ${name}!`);
}
greet("Alex", "Good morning"); // "Good morning, Alex!"
greet("Alex");                 // "Hello, Alex!" — default kicks in

// ---- Default only applies for undefined, not other falsy values ----
function setVolume(level = 50) {
    console.log(`Volume set to: ${level}`);
}
setVolume(0);         // "Volume set to: 0" — 0 is a valid value, default NOT used
setVolume(null);      // "Volume set to: null" — null is passed through, default NOT used
setVolume(undefined); // "Volume set to: 50" — undefined triggers the default
setVolume();          // "Volume set to: 50" — omitted argument also triggers the default



// ---- The OLD way — manual default handling using || ----
// Problem: || treats 0 and "" as falsy, so the default activates unintentionally
function oldSetVolume(level) {
    level = level || 50; // Bug: passing 0 would incorrectly fall back to 50
    console.log(`Volume set to: ${level}`);
}
oldSetVolume(0); // "Volume set to: 50" — incorrect! 0 was a valid intended value






// ---- Default as an expression or function call ----
function generateId() {
    return Math.floor(Math.random() * 1000);
}
function createUser(name, id = generateId()) {
    // generateId() is called only if id is not provided
    console.log(`User: ${name}, ID: ${id}`);
}
createUser("Alex", 42); // "User: Alex, ID: 42"
createUser("Jordan");   // "User: Jordan, ID: 847" (random)

// ---- Default referencing an earlier parameter ----
// Parameters are evaluated left to right, so 'name' is available to 'greeting'
function personalizedGreet(name, greeting = `Hello, ${name}`) {
    
}
personalizedGreet("Sam");              // "Hello, Sam"
personalizedGreet("Sam", "Welcome!"); // "Welcome!" — custom greeting used instead

// ---- Showcasing the WRONG way — referencing a later parameter ----
function broken(a = b, b = 10) {
    // ReferenceError: Cannot access 'b' before initialization
    // Parameters are evaluated left to right — 'b' doesn't exist yet when 'a' is set
    console.log(a, b);
}