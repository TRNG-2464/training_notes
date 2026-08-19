/*
  Single component showcasing fetch vs Axios,
  extracted into separate async functions so they
  can be compared side-by-side.
*/
import { useState } from "react";
import axios from "axios";

interface Pokemon {
  id: number;
  name: string;
  sprites: {
    front_default: string;
  };
}


/* 
  FETCH version | Two awaits: one for the response,
  one to parse the body into JSON.
*/
async function fetchPokemonWithFetch(name: string): Promise<Pokemon> {
  console.log(`Fetching Pokemon: ${name} with Fetch API`);
  const response = await fetch(
    `https://pokeapi.co/api/v2/pokemon/${name.toLowerCase()}`
  );
  /* 
    This is 'shaping' our data (kind of...)

    This works because I am following the naming conventions
    of the json object I am getting from the pokeAPI
  */
  const data: Pokemon = await response.json();
  return data;
}

/* 
  AXIOS version | one await gets you the parsed
  data directly, via response.data.
*/
async function fetchPokemonWithAxios(name: string): Promise<Pokemon> {
  console.log(`Fetching Pokemon: ${name} with Axios`);
  const response = await axios.get<Pokemon>(
    `https://pokeapi.co/api/v2/pokemon/${name.toLowerCase()}`
  );
  return response.data;
}

function PokemonSearch() {
  // Holds what the user is currently TYPING, before submission.
  const [searchInput, setSearchInput] = useState("");

  // Holds the actual fetched result, once submitted successfully.
  const [pokemon, setPokemon] = useState<Pokemon | null>(null);
  const [isLoading, setIsLoading] = useState(false);

  // The code below is just for demonstration purposes.
  // This code lets you toggle which implementation the form uses
  const [apiMethod, setApiMethod] = useState<"fetch" | "axios">("fetch");

  async function handleSubmit(event: React.SubmitEvent<HTMLFormElement>) {
    event.preventDefault(); // prevent the page from reloading

    setIsLoading(true);

    const data = apiMethod === "fetch" ? await fetchPokemonWithFetch(searchInput) : await fetchPokemonWithAxios(searchInput);

    setPokemon(data);
    setIsLoading(false);

    // Note: error handling (what if searchInput doesn't match any
    // real Pokémon?) is intentionally left out here - but should
    // be included in your actual application. See 'PokemonLookup'
    // components for more details
  }

  return (
    <div>
      <div>
        <label>
          <input
            type="radio"
            checked={apiMethod === "fetch"}
            onChange={() => setApiMethod("fetch")}
          />
          fetch
        </label>
        <label>
          <input
            type="radio"
            checked={apiMethod === "axios"}
            onChange={() => setApiMethod("axios")}
          />
          axios
        </label>
      </div>

      <form onSubmit={handleSubmit}>
        <input
          value={searchInput}
          onChange={(e) => setSearchInput(e.target.value)}
          placeholder="Enter a Pokémon name..."
        />
        <button type="submit">Search</button>
      </form>

      {isLoading && <p>Loading...</p>}

      {pokemon && (
        <div>
          <h2>
            #{pokemon.id} {pokemon.name}
          </h2>
          <img src={pokemon.sprites.front_default} alt={pokemon.name} />
        </div>
      )}
    </div>
  );
}

export default PokemonSearch;