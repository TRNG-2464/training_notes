import { useEffect, useState } from "react";
import type { Pokemon } from "./Pokemon";   // import the type Pokemon

const POKEMON_NAMES = ["pikachu", "eevee", "dragonite"];

function PokemonList() {
    const [pokemonList, setPokemonList] = useState<Pokemon[]>([]);

    // Note: 'isLoading' isn't required for this example to work - but any
    // asynchronous operation is not guaranteed to return in a specific 
    // amount of time so this is used for visual feedback to the user
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => 
        {
            const fetchAllPokemon = async () => {
                const requests = POKEMON_NAMES.map(
                    async (name) => {
                        const response = await fetch(`https://pokeapi.co/api/v2/pokemon/${name}`);
                        return await response.json();
                    }
                );

                // Promise.all allows us to wait for all requests and return 
                // all the promise objects together as an array
                const results = await Promise.all(requests);

                /*
                    Note: The Raw API response has a ton of data we don't need
                    The following code is used to pare down the data into just
                    the shape of the Pokemon we need (based on the Pokemon Interface)
                */
               const formattedPokemon: Pokemon[] = results.map((jsonData) => (
                {
                    id: jsonData.id,
                    name: jsonData.name,
                    sprite: jsonData.sprites.front_default,
                    types: jsonData.types.map(
                        // 'types' are a nested JSON object -
                        // for each type in our jsonData object, map the string name of that type to our array output
                        (t: { type: { name: string }}) => t.type.name
                    )
                }
               ));

               // change the state of 'pokemonList'
               setPokemonList(formattedPokemon);

               // change the state of 'is loading'
               setIsLoading(false);
            };

            fetchAllPokemon();
        }, []);

        // isLoading is usedful for showing something to the screen to the user
        // while our app is performing the fetch operation! - not required, but makes
        // the UX of our app better
        if (isLoading) {
            return <p>Loading Pokemon...</p>
        }

        // Return the list of pokemon - showing the sprite, the id, name and types
        // Note: here we could add a className to elements of this output for 
        // styling rules
        return (
            <div className="custom-pokemon-list-styles">
                {pokemonList.map( (pokemon) => (
                    <div key={pokemon.id} className="custom-pokemon-card-styles">
                        <img src={pokemon.sprite} alt={pokemon.name } />
                        <h3>#{pokemon.id} | {pokemon.name}</h3>
                        <p>Types: {pokemon.types.join(", ")}</p>
                    </div>
                ))}
            </div>
        )
}

export default PokemonList;