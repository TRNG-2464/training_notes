// A basic generic function
// "T" is a placeholder that gets filled in based on the argument passed
function wrapInArray<T>(value: T): T[] {
  return [value];
}

let numberArray = wrapInArray(5);        // T is inferred as number -> number[]
let stringArray = wrapInArray("hello");  // T is inferred as string -> string[]

// The type can also be specified explicitly instead of relying on inference
let explicitArray = wrapInArray<boolean>(true);

// Why generics are better than "any" here
function wrapInArrayUnsafe(value: any): any[] {
  return [value];
}

let badResult = wrapInArrayUnsafe("hello");
// badResult is typed "any[]" - i.e. TypeScript has lost track of the fact
// that this array actually contains a string, so no autocomplete or
// type checking is available on badResult's contents.

let goodResult = wrapInArray("hello");
// goodResult is correctly typed as "string[]" and TypeScript remembers
// the relationship between input and output.


// Generics can be used with multiple type placeholders
function pairValues<T, U>(first: T, second: U): [T, U] {
  return [first, second];
}
let pair = pairValues("age", 27); // inferred as [string, number]
