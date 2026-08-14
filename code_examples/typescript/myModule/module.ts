export const myData: string = "This is my exported data";

export function sayHello() {
    console.log("Hello, World!");
}

class Employee {
    readonly employeeId: number;
    empName: string;

    constructor(employeeId: number, empName: string) {
        this.employeeId = employeeId;
        this.empName = empName;
    }

    getDetails(): string {
        return `Employee ID: ${this.employeeId}, Employee Name: ${this.empName}`;
    }
}

// Note: You can use 'export' with the declaration of the entity (see above)
// Or you can consolidate your exports in a single statement
// This consolidation is useful for large files with many exports

/*
    A default export is an export that uses the keyword default.

    A file can only have a single Default export! This default export
    can be imported by other classes, but it doesn't require the import
    to be surrounded by '{}'

    It acts as the 'default - fallback' import for a file.

    Traditionally, the name of the default export should match
    the name of a single exported entity in the file.
*/
export default Employee;