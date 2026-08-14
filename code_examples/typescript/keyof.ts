// keyof produces a union of an object type's property names
interface Person {
  name: string;
  age: number;
  isActive: boolean;
}
type PersonKey = keyof Person; // "name" | "age" | "isActive"

let validKey: PersonKey = "name"; // OK
// let invalidKey: PersonKey = "email"; // Error -- "email" is not a key of Person

// keyof works the same way on a type alias
type Product = {
  title: string;
  price: number;
};
type ProductKey = keyof Product; // "title" | "price"


// and it works the same on a class
class Car {
  make: string = "Toyota";
  model: string = "Corolla";
  drive(): void {
    console.log("Vroom!");
  }
}
type CarKey = keyof Car; // "make" | "model" | "drive"
