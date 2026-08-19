import './App.css'
import PokemonSearch from './basics/PokemonSearch';
import PokemonAxiosLookup from './components/PokemonAxiosLookup';
import PokemonFetchLookup from './components/PokemonFetchLookup'

function App() {
  return (
    <div>
      <PokemonSearch />
      <PokemonFetchLookup />
      <PokemonAxiosLookup />
    </div>
  );
}

export default App
