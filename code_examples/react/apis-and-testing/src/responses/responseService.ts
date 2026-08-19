/*
    The following showcases how we can use the PokeApiResponse
    to type the raw data, making the shaping step explicit
    and fully type-checked
*/
import type { Pokemon, PokeApiResponse } from "./response_util";

async function getPokemonFetch(name: string): Promise<Pokemon> {
  const response = await fetch(`https://pokeapi.co/api/v2/pokemon/${name}`);

  if (!response.ok) {
    throw new Error(`Pokémon not found (Status: ${response.status})`);
  }

  // The raw response is typed as PokeApiResponse — TypeScript
  // now knows exactly what shape to expect BEFORE we transform it.
  const data: PokeApiResponse = await response.json();

  // DATA SHAPING happens here
  return {
    id: data.id,
    name: data.name,
    height: `${data.height * 10} cm`,  // unit conversion
    weight: `${data.weight / 10} kg`,  // unit conversion
    types: data.types.map((t) => t.type.name), // flattening nested data
    spriteUrl: data.sprites.front_default,      // pulling one field out
    // Notice: front_shiny, and everything else on "sprites" and
    // the raw response, is intentionally left behind — our
    // Pokemon interface only keeps what we actually use.
  };
}

export { getPokemonFetch };