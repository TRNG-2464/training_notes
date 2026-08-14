/*
    We can use an asterisk with imports to specify all entities
    that are exported from a module. We use the 'as' keyword to
    give a name to the module in reference, then use dot notation
    to reference the imported entities.
*/
import * as EMP from './myModule/module';

EMP.sayHello();

// Decorators are applied to classes, functions, variables, etc...
// this decorator 'withEmploymentDatOnPrototype' is declared below
@withEmploymentDateOnPrototype
class MyWorker {
    task: string = "working";
    project: string = "project";

    constructor() {
        console.log("Worker created");
    }
}

const workerInstance = new MyWorker();
console.log(workerInstance)


// ClassDecoratorContext is a built-in type that provides metadata about the class being decorated. This will limit this decorator to Class declarations.
function printDecoratorData(value: Function, context: ClassDecoratorContext) {
    console.log('value: ', value);
    console.log('context: ', context);

    context.addInitializer(() => { 
        console.log('Initializer called for: ', context.name);  
    });
}

// This function is used as a decorator that adds an employment date to the prototype of the class it decorates.
function withEmploymentDateOnPrototype(value: Function, context: ClassDecoratorContext) {
    value.prototype.employmentDateOnPrototype = new Date().toISOString();
}

// This function is used as a decorator which logs the constructor of the class it decorates 
// and registers it with a framework.
function Tracked(constructor: new(...args: any[]) => any) {
    console.log(`constructor: ${constructor.name} has been registered with the framework`);
}

// Examples of classes decorated with the Tracked decorator - make note that decorators are simply functions
@Tracked
class UserDashboard {
    title = "User Dashboard";
}

@Tracked
class AdminDashboard {
    title = "Admin Dashboard";
}
