// ---- SPREAD OPERATOR ----
// Copying an array — without spread, you'd just copy the reference
const original = [1, 2, 3];
const copy = [...original];
copy.push(4);
console.log(original); // [1, 2, 3] — untouched
console.log(copy);     // [1, 2, 3, 4]

// Merging arrays
const firstHalf = [1, 2, 3];
const secondHalf = [4, 5, 6];
const merged = [...firstHalf, ...secondHalf];
console.log(merged); // [1, 2, 3, 4, 5, 6]

// Copying and merging objects
const defaults = { theme: "light", language: "en" };
const userPreferences = { language: "fr", fontSize: 14 };

// Later keys overwrite earlier ones — userPreferences.language wins here
const settings = { ...defaults, ...userPreferences };
console.log(settings); // { theme: "light", language: "fr", fontSize: 14 }

// Passing array elements as individual function arguments
function add(a, b, c) {
  return a + b + c;
}
const numbers = [1, 2, 3];
console.log(add(...numbers)); // 6 — spread unpacks the array into three arguments

// Shallow copy caveat — nested objects are shared as a reference
const original2 = { name: "Alex", address: { city: "Denver", state: "CO" } };
const shallowCopy = { ...original2 };
shallowCopy.address.city = "Boulder"; // Modifying the nested object
console.log(original2.address.city); // "Boulder" — the original was affected too!

// ---- REST OPERATOR ----
// Collecting all arguments into an array
function sumAll(...numbers) {
  return numbers.reduce(
    (total, num) => total + num, 0);
}
console.log(sumAll(1, 2, 3));       // 6
console.log(sumAll(10, 20, 30, 40)); // 100

// Rest alongside named parameters — rest must always be LAST
function greetGroup(greeting, ...names) {
  names.forEach((name) => console.log(`${greeting}, ${name}!`));
}
greetGroup("Hello", "Alex", "Jordan", "Sam");
// "Hello, Alex!"
// "Hello, Jordan!"
// "Hello, Sam!"

// Showcasing the WRONG way — rest parameter not in last position
function broken(...names, greeting) {
  // SyntaxError: Rest parameter must be last formal parameter
}