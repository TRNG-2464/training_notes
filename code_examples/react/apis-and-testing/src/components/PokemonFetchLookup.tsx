/*
    The PARENT component — owns all shared state and 
    coordinates the form, status, and both result
    components beneath it.

    This example showcases the Fetch API version
    of our Lookup
*/
import { useState, useEffect, useRef } from "react";
import { getPokemonFetch, getSpriteBlobUrlFetch } from "../services/apiFetchService";
import type { Pokemon } from "../utils/types";
import StatusMessage from "./StatusMessage";
import type { StatusMessageProps } from "./StatusMessage";
import ResultPanel from "./ResultPanel";
import DetailsTable from "./DetailsTable";

function PokemonFetchLookup() {
  const [searchInput, setSearchInput] = useState("");
  const [pokemon, setPokemon] = useState<Pokemon | null>(null);
  const [spriteBlobUrl, setSpriteBlobUrl] = useState<string>("");
  const [status, setStatus] = useState<StatusMessageProps>({ message: "", type: "" });
  const [isSearching, setIsSearching] = useState(false);

  // Tracks the previous Blob URL so it can be revoked when a new
  // search runs, freeing up the memory it was holding. A ref is
  // used (rather than state) since this value doesn't need to
  // trigger a re-render when it changes.
  const previousBlobUrl = useRef<string | null>(null);

  // Cleanup — revoke any remaining Blob URL if this component
  // ever unmounts, preventing a memory leak.
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
    setPokemon(null); // hide previous results while loading

    try {
      // await the Pokémon data — any error here throws to catch.
      const fetchedPokemon = await getPokemonFetch(name);

      // await the sprite Blob URL — runs only after getPokemon() resolves.
      const blobUrl = await getSpriteBlobUrlFetch(fetchedPokemon.spriteUrl);

      // Revoke the PREVIOUS Blob URL before assigning the new one.
      if (previousBlobUrl.current) {
        URL.revokeObjectURL(previousBlobUrl.current);
      }
      previousBlobUrl.current = blobUrl;

      setStatus({ message: `Found: ${fetchedPokemon.name}`, type: "success" });
      setPokemon(fetchedPokemon);
      setSpriteBlobUrl(blobUrl);
    } catch (error) {
      // Handles errors from either getPokemon() or getSpriteBlobUrl().
      const message =
        error instanceof Error ? error.message : "Something went wrong.";
      setStatus({ message, type: "error" });
    } finally {
      // Always runs — re-enable the button whether the request
      // succeeded or failed.
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
        <h1>Pokémon Lookup (Fetch API)</h1>
        <p>Demonstrates async/await and loading an image as a Blob</p>
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

export default PokemonFetchLookup;