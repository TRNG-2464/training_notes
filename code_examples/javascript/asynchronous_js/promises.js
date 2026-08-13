/* The problem: callback hell
    Deeply nested callback functions, which cause a lot of confusion
    and make code hard to read & maintain

    getData(function (data) {
        processData(data, function (processed) {
          // some actions here...
            saveData(processed, function (saved) {
              updateUI(saved, function () {
                  console.log("Done!"); // Four levels deep and this is a simple example
              });
            });
        });
    });
*/

// ---- Creating a basic Promise ----
// The Promise constructor receives a function with two arguments:
// resolve (call when successful) and reject (call when something goes wrong)
const myPromise = new Promise((resolve, reject) => {
  const success = true; // Simulating an operation outcome

  if (success) {
    resolve("Operation completed successfully!"); // Fulfills the promise
  } else {
    reject(new Error("Something went wrong")); // Rejects the promise
  }
});

// ---- Consuming a simple Promise with .then(), .catch(), .finally() ----
myPromise
  .then(
    (value) => {
    // Runs if the promise is fulfilled receives the resolved value
    console.log("Fulfilled:", value); // "Fulfilled: Operation completed successfully!"
    return value.toUpperCase(); // Return value is passed to the next .then()
  }
)
  .then(
    (upperValue) => {
    // Chaining receives the return value of the previous .then()
    console.log("Chained result:", upperValue); // use DOM Manipulation to add that data to my WebPage
  }
)
  .catch((error) => {
    // Runs if the promise is rejected at any point in the chain
    console.log("Rejected:", error.message);
  })
  .finally(() => {
    // Always runs fulfilled or rejected
    console.log("Promise settled cleanup logic here");
  });


/*
    CONSUMING PROMISES WITH A REAL API (PokeAPI)
    Wrapping fetch() in a Promise to retrieve a Pokemon by name
    fetch() itself returns a Promise we wrap it here to add our own
    resolve/reject logic on top, returning clean data or a meaningful error
*/
function fetchPokemon(name) {
  return new Promise((resolve, reject) => {
    fetch(`https://pokeapi.co/api/v2/pokemon/${name}`)
      .then((response) => {
        // fetch() only rejects on network failure, not HTTP errors
        // we need to check response.ok to catch 404s and similar
        if (!response.ok) {
          reject(new Error(`Pokemon not found: ${response.status}`));
          return;
        }
        return response.json(); // Parse the response body as JSON
      })
      .then((data) => {
        // Build a clean object from the raw API response
        resolve(
          {
          name: data.name,
          id: data.id,
          height: data.height,
          weight: data.weight,
          types: data.types.map((t) => t.type.name),
        }
      );
      })
      .catch((error) => {
        // Handles genuine network failures (no connection, DNS error, etc.)
        reject(new Error(`Network error: ${error.message}`));
      });
  });
}

// ---- Consuming fetchPokemon() success path ----
fetchPokemon("pikachu")
  .then((pokemon) => {
    console.log(`Name: ${pokemon.name}`);       // "pikachu"
    console.log(`ID: ${pokemon.id}`);           // 25
    console.log(`Types: ${pokemon.types}`);     // "electric"
    console.log(`Height: ${pokemon.height}`);   // 4
    return pokemon.name; // Pass the name along to the next .then()
  })
  .then((name) => {
    console.log(`Finished processing: ${name}`);
  })
  .catch((error) => {
    console.log("Error:", error.message);
  })
  .finally(() => {
    console.log("Request complete");
  });

// ---- Consuming fetchPokemon() rejection path ----
// Passing an invalid name to trigger the error handling
fetchPokemon("notarealPokemon")
  .then((pokemon) => console.log(pokemon)) // Skipped promise was rejected
  .catch((error) => console.log("Caught Error:", error.message)) // "Pokemon not found: 404"
  .finally(() => console.log("Request complete"));

// ---- Promise states a promise cannot change state once settled ----
const alreadyFulfilled = Promise.resolve("Already fulfilled");
alreadyFulfilled.then((val) => console.log(val)); // "Already fulfilled"

const alreadyRejected = Promise.reject(new Error("Already rejected"));
alreadyRejected.catch((err) => console.log(err.message)); // "Already rejected"