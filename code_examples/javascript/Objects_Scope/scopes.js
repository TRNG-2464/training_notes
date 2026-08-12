/*
    Scopes are isolated sections of our code. As a general rule, 
    any variables created within a particular scope are lost once 
    we leave that scope*. In other words, variables cannot be accessed 
    outside of the scope they are created.

    *'hoisting' can break this general rule...
*/
let l = "Let variable";
var v = "Var variable";
const c = "Const variable";

/*
    Due to hoisting, you can use the declarative keyword, 'var', 
    again in the same scope with no problems, but the same cannot 
    be said for 'let' and 'const'
*/
// let l = "New Let Variable"; // error
// const c = "New Const variable"; // error
// var v = "New Var variable"; // This works. Note this does NOT create a second variable called 'v'

// console.log(l);
// console.log(c);
// console.log(v);

function myFunc() {
    let l = 11111;
    var v = 22222;  // This var v
    const c = 33333;
    if (true) {
        let l = "Green Eggs";
        var v = "Ham";  // is the same as thid var v
        const c = "Cat in the Hat";
        console.log("if statement [let]: " + l);
        console.log("if statement [var]: " + v);
        console.log("if statement [const]: " + c);
    }
    console.log("function [let]: " + l);
    console.log("function [var]: " + v);
    console.log("function [const]: " + c);
}
myFunc();
console.log("outside [let]: " + l);
console.log("outside [var]: " + v);
console.log("outside [const]: " + c);

/*
    We cannot access variables that are declared inside of a "lower" scope
*/ 
function secondFunc() {
    var funcVar = "Hello World";
    console.log(funcVar);
}

secondFunc();
console.log(funcVar); // funcVar is NOT accessible