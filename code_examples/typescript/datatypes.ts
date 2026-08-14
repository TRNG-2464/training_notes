// Boolean
let isActive: boolean = true;
let hasPermission: boolean = false;

// Number
// TypeScript has a single "number" type for all numeric values,
// unlike languages that split ints, floats, longs, etc.
let age: number = 27;              // basic integer
let price: number = 19.99;         // basic float

let hexValue: number = 0xff;       // hexadecimal (starts with 0x) -> 255
let binaryValue: number = 0b1010;  // binary (starts with 0b) -> 10
let octalValue: number = 0o744;    // octal (starts with 0o) -> 484

// All of these are still just "number" under the hood --
// the prefix only affects how the literal is written in source code.
console.log(hexValue, binaryValue, octalValue); // 255 10 484

// String
let firstName: string = "Ada";           // double quotes
let lastName: string = 'Lovelace';       // single quotes -- functionally identical to double quotes

// Template literals (backticks) allow string interpolation using ${}
// This is the preferred approach when building strings from variables
let fullName: string = `${firstName} ${lastName}`;
let greeting: string = `Hello, ${fullName}! You are ${age} years old.`;

// Template literals also support multi-line strings without special characters
let multiLine: string = `Line one
Line two`;

// Undefined - undefined is the default value for uninitialized variables. 
// It represents the absence of a value.
let notAssignedYet: undefined = undefined;

// Null
// Represents the intentional absence of a value (as opposed to
// "no value has been set yet", which is what undefined represents).
let emptyValue: null = null;

// Any
// Opts a variable out of type checking entirely -- TypeScript will
// not flag type errors on it. Useful in rare cases (e.g. migrating
// JS to TS gradually), but generally avoid it -- it defeats the
// purpose of using TypeScript in the first place.
let flexibleValue: any = "this could be a string...";
flexibleValue = 42;         // ...now it's a number, no error
flexibleValue = true;       // ...now it's a boolean, still no error

// Void
// void is used with functions that do not return a value
function sayHello() : void {
  console.log("Hello");
}

// Arrays - note: these are two equivalent syntaxes for typing an array
let scores: number[] = [90, 85, 77];
let names: Array<string> = ["Alice", "Bob", "Carol"]; // generic syntax, same result as string[]





// Tuples
// A fixed-length array where each position has a specific,
// known type. Unlike a regular array, order and type per slot matter.
let person: [string, number, boolean] = ["Ada", 27, true]; // must be exactly [string, number]
person = ["Joseph", 55, false];
person = ["Word", 100, true];

let multiTuple: [number, string][];
multiTuple = [ [1, "one"], [2, "two"], [3, "three"] ]

// person = [27, "Ada"]; // Error -- wrong order/types for a tuple



// Enum (numeric)
// By default, numeric enums auto-increment starting at 0
enum Direction {
  UP,     // 0
  DOWN,   // 1
  LEFT,   // 2
  RIGHT,  // 3
}
let move: Direction = Direction.UP; // 0




// You can also set a custom starting value, and the rest will increment from there
enum StatusCode {
  Success = 200,
  NotFound = 404,
  ServerError = 500,
}


// If you provide a numeric value for an enum, each subsequent will be +1
enum FillInTheBlank {
  FIRST = 10,
  SECOND,       // 11
  THIRD         // 12
}


// Enum (string)
// Each member must be explicitly initialized with a string value --
// there is no auto-increment for string enums.
enum LogLevel {
  Info = "INFO",
  Warning = "WARNING",
  Error = "ERROR",
}
let currentLevel: LogLevel = LogLevel.Warning; // "WARNING"

/*
  Enums can be used with Functions, Classes, interfaces, etc...
*/
function checkStatus(value: string): StatusCode { // returns a Status Code
  return StatusCode.Success;
}

class CustomApiResponse {
  status : StatusCode;  // Uses StatusCode enum type

  constructor(status : StatusCode) {
    this.status = status;
  }
}