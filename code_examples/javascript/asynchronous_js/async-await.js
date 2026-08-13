// async functions always return a Promise
async function greet() {
  return "Hello!"; // Plain string automatically wrapped in a resolved Promise
}

greet().then((value) => console.log(value)); // "Hello!"
// async/await is built on Promises, as such .then() still works on async function results

//await pauses the async function until the Promise settles
function waitOneSecond() {
  return new Promise((resolve) => setTimeout(resolve, 3000));
}

async function runWithDelay() {
  console.log("Starting...");
  await waitOneSecond(); // Pauses here for 1 second only this function pauses
  console.log("One second later"); // Continues after the Promise resolves
}
runWithDelay();

// try/catch for error handling replaces .catch() in Promise chains
async function mightFail() {
  return new Promise((resolve, reject) => {
    reject(new Error("Something went wrong"));
  });
}

async function handleError() {
  try {
    const result = await mightFail(); // Rejected Promise throws into the catch block
    console.log(result); // Never reached
  } catch (error) {
    console.log("Caught:", error.message); // "Caught: Something went wrong"
  } finally {
    console.log("Always runs — cleanup logic here");
  }
}
handleError();


// ASYNC/AWAIT WITH THE POKEAPI
// Fetching a single Pokemon async/await vs .then() chaining
// The .then() chain version below works, but reads less naturally
function getPokemonThen(name) {
    return fetch(`https://pokeapi.co/api/v2/pokemon/${name}`)
        .then((response) => {
            if (!response.ok) throw new Error(`Not found: ${response.status}`);
            return response.json();
        })
        .then((data) => {
            console.log(`Name: ${data.name}`);
            console.log(`ID: ${data.id}`);
        })
        .catch((error) => console.log("Error:", error.message));
}

// The async/await version on the same logic as above, reads top to bottom like synchronous code
async function getPokemon(name) {
    try {
        const response = await fetch(`https://pokeapi.co/api/v2/pokemon/${name}`);

        if (!response.ok) {
          throw new Error(`Pokemon not found: ${response.status}`);
        }

        const data = await response.json(); // await the JSON parsing too it's also async

        console.log(`Name: ${data.name}`);   // "pikachu"
        console.log(`ID: ${data.id}`);       // 25
        console.log(`Height: ${data.height}`);
        console.log(`Types: ${data.types.map((t) => t.type.name).join(", ")}`);

    } catch (error) {
        // Handles both network failures and our manually thrown errors
        console.log("Error:", error.message);
    } finally {
        console.log("Request complete");
    }
}

getPokemon("pikachu");
getPokemon("notarealPokemon"); // Triggers the catch block "Pokemon not found: 404"

// async functions also make sequential await call easier
// Each call waits for the previous to complete
async function getPokemonTeam() {
    try {
        console.log("Fetching team...");

        // Each await pauses until that request resolves before moving to the next
        const response1 = await fetch("https://pokeapi.co/api/v2/pokemon/bulbasaur");
        const bulbasaur = await response1.json();

        const response2 = await fetch("https://pokeapi.co/api/v2/pokemon/charmander");
        const charmander = await response2.json();

        const response3 = await fetch("https://pokeapi.co/api/v2/pokemon/squirtle");
        const squirtle = await response3.json();

        console.log("Team:");
        console.log(`- ${bulbasaur.name} (ID: ${bulbasaur.id})`);   // bulbasaur (ID: 1)
        console.log(`- ${charmander.name} (ID: ${charmander.id})`); // charmander (ID: 4)
        console.log(`- ${squirtle.name} (ID: ${squirtle.id})`);     // squirtle (ID: 7)

    } catch (error) {
        console.log("Failed to fetch team:", error.message);
    }
}
getPokemonTeam();

// Returning a value from an async function for use elsewhere
async function getPokemonTypes(name) {
    try {
        const response = await fetch(`https://pokeapi.co/api/v2/pokemon/${name}`);
        if (!response.ok) throw new Error(`Not found: ${response.status}`);
        const data = await response.json();
        return data.types.map((t) => t.type.name); // Returned value is wrapped in a Promise
    } catch (error) {
        console.log("Error:", error.message);
        return []; // Return a safe fallback value on failure
    }
}

// The return value of an async function is always a Promise - consume it accordingly
getPokemonTypes("eevee").then((types) => {
  console.log(`Eevee's types: ${types.join(", ")}`); // "Eevee's types: normal"
});