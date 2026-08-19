/*
  Pokemon Type Utility that can be referenced 
  and used throughout project.

  Note: for this small demo, I could get away
  with JUST this type - see note below
*/
interface Pokemon {
  id: number;
  name: string;
  height: string;
  weight: string;
  types: string[];
  spriteUrl: string;
}

/*
  Utility Type representing RAW Api response data 
  This data is to be shaped in our service.

  Note: We define this 'raw response' object in case
  other parts of our program may need different fields
  from the API's json response

  When shaping - be aware of your goals with the API
  response data (i.e. 'I only need xyz fields for this
  table' or 'I want to disply these two fields here').
*/
interface PokeApiResponse {
  id: number;
  name: string;
  height: number;
  weight: number;
  types: { slot: number; type: { name: string; url: string } }[];
  sprites: {
    front_default: string;
    front_shiny: string;
  };
}

export type { Pokemon, PokeApiResponse };