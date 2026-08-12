const firstName = "Alex";
const messageCount = 5;

// ---- The old way — string concatenation ----
// Works, but hard to read and easy to misplace a space or quote
const oldGreeting = "Hello, " + firstName + "! You have " + messageCount + " messages.";
console.log(oldGreeting);

// ---- Template literal version — much cleaner ----
const greeting = `Hello, ${firstName}! You have ${messageCount} messages.`;
console.log(greeting);

// ---- Expressions inside ${} — not just variables ----
const a = 10;
const b = 3;
console.log(`Sum: ${a + b}`);           // Arithmetic
console.log(`Is even: ${a % 2 === 0}`); // Boolean expression
console.log(`Upper: ${firstName.toUpperCase()}`); // Function call

// ---- Ternary inside a template literal ----
const isLoggedIn = true;
console.log(`Status: ${isLoggedIn ? "Online" : "Offline"}`);

// ---- Multi-line strings ----
// Old way — requires \n and concatenation
const oldMultiLine = "Line one\n" + "Line two\n" + "Line three";

// Template literal — just press enter, whitespace is preserved as-is
const multiLine = `Line one
Line two
Line three`;
console.log(multiLine);

// ---- Tagged templates — awareness-level example ----
// A tag function receives the string parts and interpolated values separately
function highlight(strings, ...values) {
  return strings.reduce((result, str, i) => {
    return result + str + (values[i] !== undefined ? `[${values[i]}]` : "");
  }, "");
}
const item = "JavaScript";
const score = 95;
console.log(highlight`You scored ${score} on the ${item} quiz.`);
// "You scored [95] on the [JavaScript] quiz."
// Tagged templates are worth knowing exist — you'll see them in libraries
// before you'll need to write one yourself