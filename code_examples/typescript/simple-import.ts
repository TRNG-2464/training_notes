/*
    Note: You can use an alias for an import using the 'as'
    keyword.
*/
import { myData as data, sayHello } from "./myModule/module";

console.log(data);

// when importing a default export
// you can call the import by any name!
import Employee from "./myModule/module";

sayHello();

const emp: Employee = new Employee(101, "Joseph");
const empDetails: string = emp.getDetails();
console.log(empDetails);