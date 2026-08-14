// isString() checks if the data is a string
function isString(input: any): boolean {
    return typeof input === "string";
}

function checkValue(value: string | number) : void {
    if (isString(value)) {
        console.log("value is a string");
        (<string>value).toUpperCase();
    } else {
        console.log("value was a number");
        (<number>value) * 10;
    }
}









// typeof type guard useful for narrowing primitive types
function printValue(value: string | number): void {
    if (typeof value === "string") {
        // Inside this block, TypeScript treats "value" as type "string"
        console.log(value.toUpperCase());
    } else {
        // Here, TypeScript knows "value" must be type "number"
        console.log(value * 15);
    }
}






// instanceof type guard useful for narrowing between classes
class Dog {
    bark(): void {
        console.log("Woof!");
    }
}

class Cat {
    meow(): void {
        console.log("Meow!");
    }
}

function makeSound(animal: Dog | Cat): void {
    if (animal instanceof Dog) {
        animal.bark(); // narrowed to Dog -- .bark() is safely available
    } else {
        animal.meow(); // narrowed to Cat -- .meow() is safely available
    } 
}


// "in" type guard useful for narrowing between object shapes
// that don't share a common class
interface Car {
    wheels: number;
    drive(): void;
}

interface Boat {
    propellers: number;
    sail(): void;
}

function operate(vehicle: Car | Boat): void {
    if ("wheels" in vehicle) {
        // Narrowed to Car -- "drive" only exists on Car
        vehicle.drive();
    } else {
        // Narrowed to Boat
        vehicle.sail();
    }
}