/*
    Identical structure to the fetch-based version (PokemonFetchLookup.tsx)
    the ONLY change is which service functions are imported and called.
*/
import { useState, useEffect, useRef } from "react";
import { getPokemonAxios, getSpriteBlobUrlAxios } from "../services/apiAxiosService";
import type { Pokemon } from "../utils/types";
import StatusMessage from "./StatusMessage";
import type { StatusMessageProps } from "./StatusMessage";
import ResultPanel from "./ResultPanel";
import DetailsTable from "./DetailsTable";

function PokemonAxiosLookup() {
  const [searchInput, setSearchInput] = useState("");
  const [pokemon, setPokemon] = useState<Pokemon | null>(null);
  const [spriteBlobUrl, setSpriteBlobUrl] = useState<string>("");
  const [status, setStatus] = useState<StatusMessageProps>({ message: "", type: "" });
  const [isSearching, setIsSearching] = useState(false);

  const previousBlobUrl = useRef<string | null>(null);

  useEffect(() => {
    return () => {
      if (previousBlobUrl.current) {
        URL.revokeObjectURL(previousBlobUrl.current);
      }
    };
  }, []);

  async function handleSearch() {
    const name = searchInput.trim().toLowerCase();

    if (!name) {
      setStatus({ message: "Please enter a Pokémon name.", type: "error" });
      return;
    }

    setIsSearching(true);
    setStatus({ message: "Loading...", type: "loading" });
    setPokemon(null);

    try {
      // await the Pokémon data — any error here throws to catch.
      // With Axios, this SAME catch block also handles a 404
      // automatically, no manual status check required.
      const fetchedPokemon = await getPokemonAxios(name);

      const blobUrl = await getSpriteBlobUrlAxios(fetchedPokemon.spriteUrl);

      if (previousBlobUrl.current) {
        URL.revokeObjectURL(previousBlobUrl.current);
      }
      previousBlobUrl.current = blobUrl;

      setStatus({ message: `Found: ${fetchedPokemon.name}`, type: "success" });
      setPokemon(fetchedPokemon);
      setSpriteBlobUrl(blobUrl);
    } catch (error) {
      const message =
        error instanceof Error ? error.message : "Something went wrong.";
      setStatus({ message, type: "error" });
    } finally {
      setIsSearching(false);
    }
  }

  function handleSubmit(event: React.SubmitEvent<HTMLFormElement>) {
    event.preventDefault();
    handleSearch();
  }

  return (
    <div className="card">
      <header>
        <h1>Pokémon Lookup (Axios)</h1>
        <p>Same UI and logic as the fetch version — powered by Axios instead</p>
      </header>

      <form onSubmit={handleSubmit} className="form-row">
        <input
          type="text"
          value={searchInput}
          onChange={(e) => setSearchInput(e.target.value)}
          placeholder="Enter a Pokémon name (e.g. gengar)"
        />
        <button type="submit" disabled={isSearching}>
          {isSearching ? "Searching..." : "Search"}
        </button>
      </form>

      <StatusMessage message={status.message} type={status.type} />

      {pokemon && (
        <ResultPanel pokemon={pokemon} spriteBlobUrl={spriteBlobUrl} />
      )}

      <div id="results-container">
        <DetailsTable pokemon={pokemon} />
      </div>
    </div>
  );
}

export default PokemonAxiosLookup;