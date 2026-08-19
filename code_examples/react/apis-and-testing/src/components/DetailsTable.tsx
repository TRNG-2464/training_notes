/*
    Component to render a table with additional
    details about the pokemon: 
    ID / Height / Weight / Type(s)
*/
import type { Pokemon } from "../utils/types";

interface DetailsTableProps {
  pokemon: Pokemon | null;
}

function DetailsTable({ pokemon }: DetailsTableProps) {
  if (!pokemon) {
    return (
      <div className="empty-state">
        Search for a Pokémon to see its details here.
      </div>
    );
  }

  return (
    <table>
      <thead>
        <tr>
          <th>Field</th>
          <th>Value</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>ID</td>
          <td>#{String(pokemon.id).padStart(3, "0")}</td>
        </tr>
        <tr>
          <td>Height</td>
          <td>{pokemon.height}</td>
        </tr>
        <tr>
          <td>Weight</td>
          <td>{pokemon.weight}</td>
        </tr>
        <tr>
          <td>Type(s)</td>
          <td>{pokemon.types.join(", ")}</td>
        </tr>
      </tbody>
    </table>
  );
}

export default DetailsTable;