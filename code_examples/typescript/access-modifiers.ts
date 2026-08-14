/*
    Access modifiers control visibility of class members:
    public (default), private, and protected
*/
class BankAccount {
  accountHolder: string;   // accessible from anywhere (public is default if keyword omitted)
  private balance: number;        // accessible only within this class
  protected accountNumber: string; // accessible within this class and subclasses

  constructor(accountHolder: string, balance: number, accountNumber: string) {
    this.accountHolder = accountHolder;
    this.balance = balance;
    this.accountNumber = accountNumber;
  }

  // public method -- part of the class's external API
  public getBalance(): number {
    // "private" members are still accessible from WITHIN the class itself
    return this.balance;
  }

  // private method -- only usable internally, not exposed outside the class
  private applyInterest(rate: number): void {
    this.balance += this.balance * rate;
  }

  public runMonthlyInterest(): void {
    this.applyInterest(0.01); // OK -- called from within the class
  }
}

const account = new BankAccount("Ana Smith", 1000, "ACC-001");

console.log(account.accountHolder); // OK -- "public", accessible from outside
console.log(account.getBalance());  // OK -- public method

// console.log(account.balance);        // Error -- "balance" is private
// account.applyInterest(0.01);         // Error -- "applyInterest" is private



// "protected" - similar to private, but accessible in subclasses
class SavingsAccount extends BankAccount {
  showAccountNumber(): void {
    // OK -- "accountNumber" is "protected", so it's accessible
    // here in the subclass, even though it's not "public"
    console.log(`Account Number: ${this.accountNumber}`);
  }
}

const savings = new SavingsAccount("Carol White", 5000, "ACC-002");
savings.showAccountNumber(); // OK
// console.log(savings.accountNumber); // Error -- "protected" is not accessible
                                        // from outside the class hierarchy


/*
    Access modifiers can be applied directly to constructor parameters
    automatically creating and assigning the corresponding class property
*/
class Employee {
  // This single line replaces declaring "name" and "id" as properties
  // AND manually assigning them in the constructor body
  constructor(public name: string, private id: number) {}

  getId(): number {
    return this.id; // OK -- accessible within the class
  }
}
const employee = new Employee("Bob Smith", 42);
console.log(employee.name);   // OK -- public
// console.log(employee.id);  // Error -- private