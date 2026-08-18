import './App.css'

import JsxCard from './components/JsxCard';
import {TsxCard} from './components/TsxCard';

import Optional from './properties/Optional';
import SearchBar from './properties/SearchBar';
import Wrapper from './properties/Wrapper';
import CustomButton from './properties/CustomButton';
import LightSwitch from './state/LightSwitch';
import CounterKeeper from './state/CounterKeeper';
import RuleOne from './hooks/RuleOne';
import RuleTwo from './hooks/RuleTwo';
import AlertButton from './event-handling/AlertButton';
import ProductButton from './event-handling/ProductButton';
import ColorPicker from './event-handling/ColorPicker';
import FeedbackForm from './event-handling/FeedbackForm';
import EmailField from './event-handling/EmailField';
import EnterToSubmit from './event-handling/EnterToSubmit';
import HoverCard from './event-handling/HoverCard';
import { PikachuProfile, PokemonProfile } from './rendering/PokemonRender';
import TimerToggle from './rendering/TimerToggle';
import FruitList from './rendering/FruitList';
import ProductList from './rendering/ProductList';
import PokemonList from './rendering/pokemon/PokemonList';
import Legacy from './components/Legacy';

function App() {
  return (
    <div>
      <div>
        <h1>JSX Card</h1>
        {/* Surround comments within JSX/TSX in curly braces! */}
        <JsxCard name="Joseph - JSX Example" age={30} isActive={false} />
        <h1>TSX Card</h1>
        <TsxCard name="Terry - TSX Example" age={30} isActive={false} />
      </div>

      <div>
        <h1>Legacy Component</h1>
        <Legacy />
      </div>
      
      <div>
        <h1>Wrapper</h1>
        <Wrapper>
          <p>This is a simple example of using the Wrapper component.</p>
          <p>My Wrapper can contain multiple elements</p>
          <span>Such as spans</span>
          <br />
          <span>And more spans!</span>
          <Legacy />
        </Wrapper>
      </div>

      <div>
        <h1>Optional Props</h1>
        <Optional author="Joseph" title="Subtitle Provided" subtitle="This is an optional subtitle" />
        <Optional author="Joseph" title="No Subtitle Provided" />
      </div>

      <div>
        <h1>Function-Type Props</h1>
          <SearchBar onSearch={(query) => console.log("Searching for:", query)} />
      </div>

      <div>
        <h1>Re-Using Props - Buttons</h1>
          <CustomButton 
              label="Cool Button" 
              className="btn-cool"
              onClick={() => console.log("Cool Button Clicked!")} />

            <CustomButton 
              label="Warm Button" 
              className="btn-warm"
              onClick={() => console.log("Warm Button Clicked!")} />
      </div>

      <div>
        <h1>Managing State | Light Switch</h1>
        <LightSwitch />
      </div>

      <div>
        <h1>Managing State | Counter</h1>
        {/* Notice that I only need to import and reference my parent
            component for this example, i.e. 'CounterDisplay' will be
            rendered via the CounterKeeper's component logic */}
        <CounterKeeper />
      </div>

      <div>
        <h1>Hooks</h1>
        <h2>Rule 1 | Only Call Hooks at a top level</h2>
        <RuleOne />

        <h2>Rule 2 | Only call Hooks from React function Components</h2>
        <RuleTwo />
      </div>

      <div>
        <h1>Event Handling | Basics</h1>
        <AlertButton />
      </div>

      <div>
        <h1>Event Handling | Passing Arguments</h1>
        <ProductButton productName="Mouse" />
      </div>

      <div>
        <h1>Event Handling | Synthetic Events</h1>
        <h2>onChange Example</h2>
        <ColorPicker />

        <h2>onSubmit Example</h2>
        <FeedbackForm />

        <h2>onFocus | onBlur Example</h2>
        <EmailField />

        <h2>onKeyDown Example</h2>
        <EnterToSubmit />

        <h2>onMouseEnter | onMouseExit Example</h2>
        <HoverCard />
      </div>

      <div>
          <h1>Rendering</h1>
          <h2>useEffect | Mount</h2>
          <PikachuProfile />

          <h2>useEffect | Update</h2>
          <PokemonProfile />

          <h2>useEffect | UnMount</h2>
          <TimerToggle />

          <h2>List Rendering | Simple List</h2>
          <FruitList />

          <h2>List Rendering | Product List</h2>
          <ProductList />

          <h2>List Rendering | PokeAPI List</h2>
          <PokemonList />
      </div>
    </div>
  );
}

export default App;
