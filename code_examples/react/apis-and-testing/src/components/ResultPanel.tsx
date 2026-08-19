/*
    Component which renders the sprite image
    Pokémon name, and type badges.
*/
import type { Pokemon } from "../utils/types";

interface ResultPanelProps {
  pokemon: Pokemon;
  spriteBlobUrl: string;
}

function ResultPanel({ pokemon, spriteBlobUrl }: ResultPanelProps) {
  return (
    <div className="result-panel visible">
      <img src={spriteBlobUrl} alt={pokemon.name} />
      <div>
        <div className="pokemon-name">{pokemon.name}</div>
        <div className="type-badges">
          {/* Rendering a list of type badges*/}
          {pokemon.types.map((type) => (
            <span key={type} className={`type-badge type-${type}`}>
              {type}
            </span>
          ))}
        </div>
      </div>
    </div>
  );
}

export default ResultPanel;