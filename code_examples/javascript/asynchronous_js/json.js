/*
    THIS PAGE SHOWCASES EXAMPLES OF WHAT JSON LOOKS LIKE, NOT THEIR USE
    JSON stands for Javascript Object Notation. It is a lightweight way to 
    transmit data between different programming languages. 
    
        JSON is lightweight, because Strings do not take up too much space 
        in memory, as compared to more complex objects. 
    
        JSON is language agnostic, because nearly every programming language 
        has a method of writing and understanding String data.
*/
let JSONString = '{"name":"Joseph","age":30}';
let obj = {name: "Joseph",age: 30};

console.log(JSONString);
console.log(obj);

/*
    JSON.stringify() allows you to convert a Javascript object into a JSON string
    JSON.parse() allows you to convert a JSON string into a Javascript Object
*/
let stringfy = JSON.stringify(obj);     // stringify is our new JSON String
let parse = JSON.parse(JSONString);     // parse is our JS Object

console.log(stringfy); // same as JSONString
console.log(parse); // same as obj


// Some Properties are silently dropped by JSON.stringify()
const withUnsupported = {
  name: "Alex",
  greet: function () { return "Hello"; },   // Functions are dropped
  id: undefined,                            // undefined values are dropped
  sym: Symbol("key"),                       // Symbols are dropped
};

const result = JSON.stringify(withUnsupported);
console.log(result); // '{"name":"Alex"}' - only 'name' survived