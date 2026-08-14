// Without "as const" -- TypeScript widens the literal type
let status = "active";
// status is inferred as type "string" (widened), not "active"

// With "as const" -- TypeScript keeps the literal type
type onlyStatus = "active" | "inactive";
let myData: onlyStatus = "active";
myData = "inactive";
// myData = "anything else";

let lockedStatus = "active" as const;
// lockedStatus is inferred as type "active" (the literal itself), not "string"

let colorsTuple = ["red", "green", "blue"] as const;
// Type: readonly ["red", "green", "blue"] -- a readonly tuple of exact literals

// colorsTuple[0] = "yellow"; // Error -- readonly, cannot reassign an element
// colorsTuple.push("yellow"); // Error -- readonly tuples have no push method


// "as const" on an object
let settings = {
  theme: "dark",
  fontSize: 14,
};
// Without "as const": theme is type "string", fontSize is type "number"

let lockedSettings = {
  theme: "dark",
  fontSize: 14,
} as const;
// With "as const": theme is type "dark", fontSize is type 14
// Every property is also now readonly

// lockedSettings.theme = "light"; // Error -- cannot assign to a readonly property


// Common use case: defining a fixed set of related values
const ROLES = ["admin", "editor", "viewer"] as const;
// Type of ROLES: readonly ["admin", "editor", "viewer"]

// This can be combined with "typeof" to build a union type
// representing only these exact allowed values
// below 'number' is the 'index signature' for the ROLES tuple above.
// i.e. each role is identified via a number
type Role = typeof ROLES[number]; // "admin" | "editor" | "viewer"

let userRole: Role = "editor"; // OK
// let invalidRole: Role = "superadmin"; // Error -- not one of the allowed literals