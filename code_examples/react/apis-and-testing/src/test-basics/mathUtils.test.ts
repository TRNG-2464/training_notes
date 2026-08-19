import { describe, it, test, expect } from "vitest";
import { add, subtract } from "./mathUtils.ts";

/*
    We start our tests with a 'describe()' function call
    
    This function bundles our tests ( it() OR test() )
    for organizational purposes.
*/
describe("Add Function Tests", () => {
    /*
        it() is our actual test function. It takes a string description
        followed by an arrow-function describing the test behavior.

        For these simple tests, we just check the actual, returned value
        of our simple MathUtil functions to the expected value using
        the 'toBe()' matcher function
    */
    it("returns the correct sum of two positive numbers", () => {
        const actual = add(2, 3);
        expect(actual).toBe(5);
    });

    // Note: 'test()' is the same as 'it' - the use of either is purely
    // a stylistic/readability choice. 'it()' is typically more common
    test("returns the correct sum when one number is negative", () => {
        const actual = add(10, -2);
        expect(actual).toBe(8);
    });
});

describe("Subtract Function Tests", () => {
    it("returns the correct differences of two positive numbers", () => {
        const actual = subtract(15, 5);
        expect(actual).toBe(10);
    });

    it("returns the correct negative number, when first second argument is larger", () => {
        const actual = subtract(5, 9);
        expect(actual).toBe(-4);
    });
});