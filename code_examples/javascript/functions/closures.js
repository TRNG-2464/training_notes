// ---- Basic closure — inner function remembers the outer variable ----
function outer() {
    const message = "Hello from the outer scope";

    // inner() is the closure
    function inner() {
        // inner() has access to message even after outer() has returned
        console.log(message);
    }

    // This is returning my closure
    return inner;
}


const myInner = outer(); // outer() has finished executing at this point

// outside of the scope of the function 'outer'
// console.log(message); // throw an error

myInner(); // "Hello from the outer scope" — message is still alive via the closure

// ---- Practical closure: a counter with private state ----
function createCounter() {
    let count = 0; // count is private — cannot be accessed directly from outside

    return {
        increment() { count++; },
        decrement() { count--; },
        getCount() { return count; },
    };
}

const counter = createCounter();
counter.increment();    // 1
console.log(counter.getCount()); // 1
counter.increment();    // 2
console.log(counter.getCount()); // 2
counter.increment();    // 3
console.log(counter.getCount()); // 3
counter.decrement();    // 2
console.log(counter.getCount()); // 2

// count cannot be accessed directly — it only exists inside the closure
console.log(counter.count); // undefined — no direct access

// ---- Factory function — closures creating customized functions ----
function createMultiplier(multiplier) {
    // multiplier is captured in the closure returned below
    return (number) => number * multiplier;
}

const double = createMultiplier(2);
const triple = createMultiplier(3);

console.log(double(5));  // 10
console.log(triple(5));  // 15
// double and triple are separate closures, each remembering their own multiplier

// // ---- Closures reference current values, not snapshots ----
// function createTracker() {
//     let status = "inactive";

//     return {
//         activate() { status = "active"; },
//         getStatus() { return status; }, // always sees the CURRENT value of status
//     };
// }

// const tracker = createTracker();
// console.log(tracker.getStatus()); // "inactive"
// tracker.activate();
// console.log(tracker.getStatus()); // "active" — closure sees the updated value

// // ---- Connecting closures back to the var hoisting bug ----
// // All three callbacks close over the SAME var i — by the time they run, i is 3
// for (var i = 0; i < 3; i++) {
//     setTimeout(() => console.log(`var: ${i}`), 100);
// }
// // Prints: "var: 3", "var: 3", "var: 3"

// // let creates a new binding per iteration — each callback closes over its own j
// for (let j = 0; j < 3; j++) {
//     setTimeout(() => console.log(`let: ${j}`), 100);
// }
// // Prints: "let: 0", "let: 1", "let: 2"