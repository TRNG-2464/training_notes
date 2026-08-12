// Creating a Map
const userRoles = new Map();

// ---- .set() ---- adds or updates an entry; returns the Map (chainable)
userRoles.set("amanda", "Associate");
userRoles.set("bianca", "Senior Developer");
userRoles.set("charles", "Associate");

// Chaining is possible because .set() returns the Map itself
userRoles
  .set("dylan", "Manager")
  .set("frank", "Associate");

// ---- .get() ---- retrieves a value
console.log(userRoles.get("amanda")); // "Associate"
console.log(userRoles.get("unknown")); // undefined key doesn't exist

// ---- .has() ---- checks for key existence
console.log(userRoles.has("dylan")); // true
console.log(userRoles.has("casey"));  // false

// ---- .delete() ---- removes an entry, returns true/false
console.log(userRoles.delete("frank")); // true successfully removed
console.log(userRoles.delete("frank")); // false already gone, nothing to remove

// ---- .size ---- no need to manually count keys
console.log(userRoles.size); // 4

// ---- Maps are directly iterable with for-of ----
for (const [key, value] of userRoles) {
  console.log(`${key}: ${value}`);
}

// ---- Using non-string keys something plain objects can't do cleanly ----
const configObject = { env: "production" };
const settingsMap = new Map();
settingsMap.set(configObject, "Special settings tied to this exact object");

console.log(settingsMap.get(configObject)); // "Special settings tied to this exact object"
console.log(settingsMap.get({ env: "production" })); // undefined different object reference!

// ---- .clear() ---- empties the Map entirely
settingsMap.clear();
console.log(settingsMap.size); // 0

// Converting a Map to an array, if needed
const rolesArray = [...userRoles];
console.log(rolesArray); // [["alex", "Associate"], ["bianca", "Senior Developer"], ...]