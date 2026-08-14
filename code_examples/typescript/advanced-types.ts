// Never represents a type that will never occur
// This is typically used in functions that always throw an exception
let impossible: never; // This is not too useful...

function alwaysThrowsError(message: string) : never {
  throw new Error(message); // This function will always result in an error - it never returns data
}


// Type Alias - A custom type that can be used to reuse an object-like structure
// later in my program
type Point = {
  x : number;
  y : string;
};
// Point Type Alias has x 5 and y of 10 for my coord variable
let coord : Point = { x:5, y:"10"};

// Union type restricts a variable can be one of several types
let id: string | number;

id = 101;        // OK -- number is one of the allowed types
id = "user-101";  // OK -- string is also allowed
// id = true;     // Error -- boolean is not part of the union


// Union types as function parameters
function printId(id: string | number): void {
  console.log(`ID: ${id}`);
}

printId(101);
printId("user-101");


/* 
    Literal types - restricts a value to a fixed set of values
    Declare literal types using the type keyword.
*/
type Status = "pending" | "active" | "closed";
let orderStatus: Status = "pending"; // OK
// let invalidStatus: Status = "cancelled"; // Error -- not one of the allowed literals

// Literal types can be used with any datatype
type amount = 1 | 2 | 3;
let quantity:amount = 3;

// Literal types are commonly used with string or numbers, but can be used with other datatypes
type check = true | false
let notuseful:check = true;

/*
    Note: Union and Literal types can be used with objects...
*/
interface Car {
  wheels: number;
}

interface Boat {
  propellers: number;
}

let vehicle: Car | Boat;
vehicle = { wheels: 4 };       // OK -- matches Car
vehicle = { propellers: 2 };   // OK -- matches Boat

// Working with a union safely often requires narrowing so
// only properties common to ALL members of the union are
// accessible without first checking which type is present
function describeVehicle(v: Car | Boat): void {
  if ("wheels" in v) {
    console.log(`Car with ${v.wheels} wheels`);
  } else {
    console.log(`Boat with ${v.propellers} propellers`);
  }
}


/*
  Functions can return Union or literal types as well
*/
function giveMeNumOrString(check: boolean) : number | string {
    return check ? 100 : "One Hundred";
}

/*
  Here, this function can only return the literal types:
    "success", "error", "pending"
  
  You could achieve something similar with Enums, however,
  if only used in a one-off way, this way is effective and
  clearly communicates the function's restrictions.
*/
function checkStatus(code: number): "success" | "error" | "pending" {
  if (code === 200) {
    return "success";
  } else if (code === 202) {
    return "pending";
  } else {
    return "error";
  }
}

let status = checkStatus(200); // status is typed as: "success" | "error" | "pending"