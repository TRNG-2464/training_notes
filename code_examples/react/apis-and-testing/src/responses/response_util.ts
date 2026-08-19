/*
    The following example showcases two interfaces
    one for the expected data our application wants
    
    The other represents what a 'raw' response 
    might look like - we can then create a service
    that can perform the work to shape our data 
    from these two types
*/

// Describes what YOUR APP actually needs. This is 
// clean, stable, and unaffected by however messy the 
// real API response is.
interface Pokemon {
  id: number;
  name: string;
  height: string;
  weight: string;
  types: string[];
  spriteUrl: string;
}

// Describes what the RAW API RESPONSE actually looks like
// this can be as messy or verbose as the real API requires.
interface PokeApiResponse {
  id: number;
  name: string;
  height: number; // raw decimetres, NOT yet converted
  weight: number; // raw hectograms, NOT yet converted
  types: { slot: number; type: { name: string; url: string } }[];
  sprites: {
    front_default: string;
    front_shiny: string;
    // ...and many more fields we don't actually need
  };
}

export type { Pokemon, PokeApiResponse };