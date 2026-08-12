// ---- this in an object method ----
const user = {
    name: "Alex",
        greet() {
        console.log(`Hello, my name is ${this.name}`); // 'this' is the user object
    },
};
user.greet(); // "Hello, my name is Alex"

// ---- this in a standalone function call ----
function showThis() {
    console.log(this);
}
showThis(); // global object (window in browser) — or undefined in strict mode

// ---- Losing 'this' context when passing a method as a callback ----
const timer = {
    label: "Countdown",
    start() {
        setTimeout(function () {
            // The function is called standalone here, not as a method of timer
            console.log(this.label); // undefined — 'this' is no longer the timer object
        }, 100);
    },
};
timer.start();

// ---- Fixing lost context with an arrow function ----
const timerFixed = {
    label: "Countdown",
    start() {
        setTimeout(() => {
            // Arrow function inherits 'this' from start(), which IS the timerFixed object
            console.log(this.label); // "Countdown"
        }, 100);
    },
};
timerFixed.start();

// ---- call() — invoke immediately with a specified 'this' ----
function introduce(role) {
    console.log(`${this.name} is a ${role}`);
}
const person = { name: "Jordan" };
introduce.call(person, "Senior Developer"); // "Jordan is a Senior Developer"
// person.introduce = function(role) ....
// person.call("Senior Developer");

// ---- apply() — same as call(), but extra arguments passed as an array ----
introduce.apply(person, ["Associate"]); // "Jordan is an Associate"

// ---- bind() — returns a NEW function with 'this' permanently locked ----
const boundIntroduce = introduce.bind(person);
boundIntroduce("Team Lead"); // "Jordan is a Team Lead"
// 'this' is always 'person' now, regardless of how boundIntroduce is called

// ---- this inside a class — refers to the instance ----
class Animal {
    constructor(name) {
        this.name = name; // 'this' is the new instance being created
    }
    speak() {
        console.log(`${this.name} makes a sound.`); // 'this' is the instance calling speak()
    }
}
const dog = new Animal("Rex");
dog.speak(); // "Rex makes a sound."