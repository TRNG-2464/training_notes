/*
    This service serves the same purpose as apiFetchService.ts
    (async data-fetching logic extracted into its own module)
    but it is using Axios instead of the Fetch API.
*/
import axios from "axios";
import type { PokeApiResponse, Pokemon } from "../utils/types";

async function getPokemonAxios(name: string): Promise<Pokemon> {
  // Axios requires no manual response.ok check needed — Axios 
  // automatically throws on a bad HTTP status, caught by 
  // whichever try/catch calls this function.
  const response = await axios.get(
    `https://pokeapi.co/api/v2/pokemon/${name}`
  );

  // Axios has already parsed the JSON for us — no separate
  // .json() step, the data lives directly on response.data.
  const data : PokeApiResponse = response.data;

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
async function getSpriteBlobUrlAxios(spriteUrl: string): Promise<string> {
  // Axios needs to be told explicitly to expect binary data here,
  // via responseType: "blob". By Default Axios will try to parse
  // the image data as JSON or text and fail.
  const response = await axios.get(spriteUrl, {
    responseType: "blob",
  });

  const blob = response.data;
  return URL.createObjectURL(blob);
}

export { getPokemonAxios, getSpriteBlobUrlAxios };