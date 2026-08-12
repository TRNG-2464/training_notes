/* 
    -Arrays in Javascript allow us to hold onto multiple pieces of data in a single location.
    -In Javascript Arrays can hold onto any combination of different datatypes.
    -Arrays are denoted using square brackets []
    -To access information inside of an array, we use the index of the element
*/
let arr = [123,43,76,214];
console.log(arr);       // [123,43,76,214]
console.log(arr[0]);    // 123
console.log(arr[1]);    // 43
console.log(arr[2]);    // 76
console.log(arr[3]);    // 214

/*
    arrayName[index] references the information at that position in the array. We can use the assignment operator (=) to change its value
*/
arr[3] = 555;           // '214' changed to '555'
console.log(arr);

/*
    You can also create an Array using a Constructor
*/
let otherArr = new Array(10); // This array looks like this: [,,,,,,,,,]...it has no data whatsoever
console.log(otherArr); // This will print [ emptyx10 ], indicating 10 blank spaces

/* 
    The fill function is a built-in function for arrays that allow us fill an array with a specified value
*/
otherArr.fill("word"); // Now array looks like this: ["word","word","word","word","word","word","word","word","word","word"]
console.log(otherArr);

console.log("::: MULTI ARRAY :::");
let multi = ["apple", 75, true];
console.log(multi[0]);  // "apple"
console.log(multi[1]);  // 75
console.log(multi[2]);  // true

/* 
    Javascript allows us to expand arrays dynamically (after the array has been created)
*/
console.log("::: EXPANDING ARRAYS :::");
let expand = [2,4,6];
console.log(expand);    // [2,4,6]
expand.push(8); // This will put 8 at the end of our array
console.log(expand);    // [2,4,6,8]
expand.pop();   // This will remove the last element of our array
console.log(expand);    // [2,4,6]

expand[7] = 8;
console.log(expand);    // [2,4,6,,,,8]


/* 
    Arrays are objects, and yes, you can have an array of Objects. This means you can place Arrays within other Arrays.
    
    When placing arrays within other Arrays, this is known as a 'multi-dimensional' array.
*/

/*
For the Array below:

index:   0  1  2
        [11,12,13],         // array at index 0

index:   0  1  2  3  4    
        [21,22,23,24,25],   // array at index 1

index    0  1
        [31,32]             // array at index 2
*/
let twoDee = [ [11,12,13], [21,22,23,24,25], [31,32] ];
console.log(twoDee[0][2]);       // value is 13
console.log(twoDee[2][1]);       // value is 32
console.log(twoDee[1][2]);       // value is 23


/*
    Each time you nest an array, you add a "dimension". The following is a "3D" array. An array which contains multiple 2-D arrays
*/
let otherMulti = [
    [
        [1,2,3],[4,5,6],[7,8,9]
    ],
    [
        ["A","B","C"],["D","E","F"],["G","H","I"]
    ],
    [
        [true, false, false],[true, true, false],[true, true, true]
    ]
];