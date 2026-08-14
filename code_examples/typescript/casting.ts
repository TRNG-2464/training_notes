// Basic casting with "as" syntax (preferred)
let someValue: unknown = "this is a string";

// Without casting, TypeScript won't allow string-specific
// operations on a value typed as "unknown"
let strLength: number = (someValue as string).length;



// Angle-bracket syntax (equivalent to "as", but avoid in .tsx files)
let anotherValue: unknown = "another string";
let strLengthAlt: number = (<string>anotherValue).length;







// Without a cast, TS only knows this is "HTMLElement | null",
// so it won't allow access to input-specific properties like .value
const inputElement = document.getElementById("email-input") as HTMLInputElement;
console.log(inputElement.value);

