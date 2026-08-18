
import { useEffect, useState } from "react";

interface Pokemon {
    id: number;
    name: string;
}

/*
    This component showcases a 'useEffect' on Mount
    i.e. once when the component is first rendered
*/
function PikachuProfile() {
    const [pokemon, setPokemon] = useState<Pokemon | null>(null);

    useEffect( () => {
        fetch("https://pokeapi.co/api/v2/pokemon/pikachu")
        .then((response) => response.json())
        .then((data: Pokemon) => setPokemon(data));

        console.log("Pikachu Profile Mounted!");
    }, 
    // This empty dependency array means it runs once on mount
    []);

    return <div>{pokemon ? <p>{pokemon.name}</p> : <p>Loading...</p>}</div>
}

/*
    This component showcases a 'useEffect' on Update
    i.e. every time a state change causes a re-render
*/
function PokemonProfile() {
    const [pokemon, setPokemon] = useState<Pokemon | null> (null);
    const [pokemonId, setPokemonId] = useState(1);

    useEffect( () => {
        // This re-runs any time "pokemonId" changes between renders
        fetch(`https://pokeapi.co/api/v2/pokemon/${pokemonId}`)
        .then((response) => response.json())
        .then((data: Pokemon) => setPokemon(data));
    }, 
    // dependency array: run-run only when pokemonId changes
    [pokemonId]);

    return (
        <div>
            <input
                value={pokemonId} 
                onChange={(e) => {
                    // The following code is light validation to ensure
                    // the value input is a number and 
                    let input = Number(e.target.value);
                    if (typeof input != "number")
                        input = 1;
                    
                    input = input > 0 ? input : 1;

                    setPokemonId(input)
                }}
            />
            <div>{pokemon ? <p>{pokemon.name}</p> : <p>Loading...</p>}</div>
        </div>
    );
}

export {PikachuProfile, PokemonProfile}