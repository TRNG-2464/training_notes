// Every object has an internal prototype link
const animal = {
  eats: true,
  walk() {
    console.log("Animal is walking");
  },
};

const rabbit = {
  jumps: true,
};

// Manually linking rabbit's prototype to animal
Object.setPrototypeOf(rabbit, animal);

console.log(rabbit.jumps); // true own property
console.log(rabbit.eats);  // true found via the prototype chain, not an own property
rabbit.walk();             // "Animal is walking" inherited method works too

// Checking the prototype chain explicitly
console.log(Object.getPrototypeOf(rabbit) === animal); // true

// Functions used as constructors rely on their .prototype property
function Animal(name) {
  this.name = name;
}

// Adding a method to the constructor's prototype,
// rather than inside the function, so it is SHARED across all instances
Animal.prototype.speak = function () {
  console.log(`${this.name} makes a sound.`);
};

const dog = new Animal("Rex");
const cat = new Animal("Whiskers");

dog.speak(); // "Rex makes a sound."
cat.speak(); // "Whiskers makes a sound."

// Both instances share the SAME speak function via the prototype,
// rather than each instance having its own separate copy saves memory
console.log(dog.speak === cat.speak); // true

// Showcasing the legacy (discouraged) way to access a prototype
console.log(dog.__proto__ === Animal.prototype); // true, but avoid using __proto__ directly

// Best practice: use Object.getPrototypeOf() instead
console.log(Object.getPrototypeOf(dog) === Animal.prototype); // true preferred approach