/*
    Service defined to handle Async data-fetching logic
    extracted into its own module, separate from any component.
*/
import type { PokeApiResponse, Pokemon } from "../utils/types";

async function getPokemonFetch(name: string): Promise<Pokemon> {
  const response = await fetch(`https://pokeapi.co/api/v2/pokemon/${name}`);

  // fetch() does NOT reject on HTTP errors — response.ok must
  // be checked manually
  if (!response.ok) {
    throw new Error(`Pokémon not found (Status: ${response.status})`);
  }

  const data : PokeApiResponse = await response.json();

  return {
    id: data.id,
    name: data.name,
    height: `${data.height * 10} cm`, // PokeAPI returns decimetres
    weight: `${data.weight / 10} kg`, // PokeAPI returns hectograms
    types: data.types.map((t: { type: { name: string } }) => t.type.name),
    spriteUrl: data.sprites.front_default,
  };
}

// Fetches the sprite image as a Blob, and converts it to a
// local object URL the <img> can use as its src.
async function getSpriteBlobUrlFetch(spriteUrl: string): Promise<string> {
  const response = await fetch(spriteUrl);
  const blob = await response.blob();
  return URL.createObjectURL(blob);
}

export { getPokemonFetch, getSpriteBlobUrlFetch };