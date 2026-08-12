// Manually building an object that follows the iterator protocol
function createCountdownIterator(start) {
  let current = start;
  return {
    next() {
        if (current < 0) {
          return { value: undefined, done: true }; // Signal that we're finished
        }
        return { value: current--, done: false };
    },
  };
}

const countdown = createCountdownIterator(3);
console.log(countdown.next()); // { value: 3, done: false }
console.log(countdown.next()); // { value: 2, done: false }
console.log(countdown.next()); // { value: 1, done: false }
console.log(countdown.next()); // { value: 0, done: false }
console.log(countdown.next()); // { value: undefined, done: true }

// Built-in types already follow this protocol you rarely call .next() directly,
// but it's happening under the hood whenever you use for-of
const colors = ["red", "green", "blue"];
const colorIterator = colors[Symbol.iterator](); // Manually grabbing the iterator
console.log(colorIterator.next()); // { value: "red", done: false }
console.log(colorIterator.next()); // { value: "green", done: false }

// This is exactly what for-of is doing automatically:
for (const color of colors) {
  console.log(color); // "red", "green", "blue"
}

// Showcasing why plain objects do NOT work with for-of by default
const user = { name: "Alex", role: "Associate" };
try {
  for (const value of user) {
    console.log(value); // This line never runs
  }
} catch (error) {
  console.log(error.message); // "user is not iterable" no Symbol.iterator implemented
}

// Making a custom object iterable by implementing Symbol.iterator manually
const customRange = {
  from: 1,
  to: 3,
  [Symbol.iterator]() {
    let current = this.from;
    const last = this.to;
    return {
      next() {
        return current <= last
          ? { value: current++, done: false }
          : { value: undefined, done: true };
      },
    };
  },
};



for (const num of customRange) {
  console.log(num); // 1, 2, 3 for-of now works on our custom object!
}




// Basic generator function note the asterisk after 'function'
function* countdownGenerator(start) {
  while (start >= 0) {
    yield start; // Pauses here, returns the current value
    start--;     // Resumes here on the next .next() call
  }
}

// Calling a generator function does NOT run its body
// it returns a generator object (which is also an iterator)
const countdown = countdownGenerator(3);

console.log(countdown.next()); // { value: 3, done: false }
console.log(countdown.next()); // { value: 2, done: false }
console.log(countdown.next()); // { value: 1, done: false }
console.log(countdown.next()); // { value: 0, done: false }
console.log(countdown.next()); // { value: undefined, done: true }

// Generators implement the iterator protocol automatically,
// so they work directly with for-of no manual .next() calls needed
for (const num of countdownGenerator(3)) {
  console.log(`for-of: ${num}`); // 3, 2, 1, 0
}

// Comparing this to the manual iterator from the previous topic
// the generator version requires far less boilerplate to achieve the same result

// Generators are great for lazy sequences values computed only when needed
function* infiniteIdGenerator() {
  let id = 1;
  while (true) {
    yield id++; // Runs forever, but only produces a value when .next() is called
  }
}

const idGen = infiniteIdGenerator();
console.log(idGen.next().value); // 1
console.log(idGen.next().value); // 2
console.log(idGen.next().value); // 3
// The generator never tries to compute "all" IDs upfront that would be impossible
// with an infinite sequence, but works fine here because values are produced lazily

// Using a generator to make a custom class iterable, instead of manually
// implementing Symbol.iterator with a hand-built { value, done } object
class Playlist {
  constructor(songs) {
    this.songs = songs;
  }

  *[Symbol.iterator]() {
    // A generator method much simpler than the manual version of an iterator
    for (const song of this.songs) {
      yield song;
    }
  }
}

const playlist = new Playlist(["Song A", "Song B", "Song C"]);
for (const song of playlist) {
  console.log(song); // "Song A", "Song B", "Song C"
}