/*
    INTERFACE
    Defines the *shape* that an object must conform to.
    Cannot hold implementation -- only describes what properties
    and methods something must have.
*/
interface Employee {
    id: number;
    name?: string;  // a '? indicates an optional value
    department: string;
    giveRaise(amount: number): void; // interfaces can require methods too, no implementation here
}

// keyof used on an interface -- produces a union of its property/method names
type EmployeeKey = keyof Employee; // "id" | "name" | "department" | "giveRaise"

let employeeKey: EmployeeKey = "name"; // OK -- "name" is a real key on Employee
// let badEmployeeKey: EmployeeKey = "salary"; // Error -- "salary" is not a key of Employee


/*
    TYPE ALIAS
    Gives a name to any type - object shapes, unions, primitives, etc.
    Similar to an interface for object shapes, but more flexible
    (can also represent unions, tuples, primitives).
*/
type Department = "Engineering" | "Sales" | "Executive"; // union type alias

type PayrollInfo = {
    salary: number;
    bonusEligible: boolean;
};

// keyof used on a type alias -- works the same way as with an interface
type PayrollKey = keyof PayrollInfo; // "salary" | "bonusEligible"

let payrollKey: PayrollKey = "salary"; // OK
// let badPayrollKey: PayrollKey = "id"; // Error -- "id" is not a key of PayrollInfo


/*
    CLASS
    Unlike an interface, a class provides actual implementation:
    state (properties) and behavior (methods) together.
    A class can also "implement" an interface, forcing it to
    provide everything that interface requires.
*/

/*
    In pure JS this would work:
    class Dog {
    constructor(name, age) {
        this.name = name;
        this.age = age;
    }
}

However, in TypeScript, we need to declare the datatype in our constructor
params list, and we must declare the fields on the object as well!
*/
class Dog {
    name? : string;
    age : number;
    constructor(name : string, age : number) {
        this.name = name;
        this.age = age;
    }
}


class FullTimeEmployee implements Employee {
    id: number;
    name: string;
    department: string;
    payroll: PayrollInfo; // classes can use type aliases as property types

    constructor(id: number, name: string, department: Department, payroll: PayrollInfo) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.payroll = payroll;
    }

    // Required because the Employee interface declares this method
    giveRaise(amount: number): void {
        this.payroll.salary += amount;
    }
}

// keyof used on a class
type FullTimeEmployeeKey = keyof FullTimeEmployee;  // "id" | "name" | "department" | "payroll" | "giveRaise"

let instanceKey: FullTimeEmployeeKey = "payroll"; // OK
// let badInstanceKey: FullTimeEmployeeKey = "salary";  // Error -- "salary" lives on PayrollInfo, 
                                                        // not FullTimeEmployee directly

// PUTTING IT ALL TOGETHER
const carol = new FullTimeEmployee(
    1,
    "Carol White",
    "Executive",
    { salary: 95000, bonusEligible: true }
);

console.log(carol.name);            // "Carol White"
console.log(carol.payroll.salary);  // 95000

carol.giveRaise(5000);
console.log(carol.payroll.salary);  // 100000