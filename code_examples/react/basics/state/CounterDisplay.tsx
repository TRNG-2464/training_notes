/*
    This file represents a "Presentational Component" - one which owns
    no state of its own, but simply displays values and calls functions
    it's given as props. 
    
    This is a common pattern in React, where you separate the "stateful" 
    logic from the "display" logic.
*/
interface CounterDisplayProps {
  count: any;
  onIncrement: () => void;
  onDecrement: () => void;
}

// This component doesn't know what 'increment' or 'decrement' actually do, it just calls the functions it's given.
function CounterDisplay({ count, onIncrement, onDecrement }: CounterDisplayProps) {
  return (
    <div className="counter-display">
      <span>{count}</span>
      <br />
      <button onClick={onDecrement}>Decrease (-)</button>
      <button onClick={onIncrement}>Increase (+)</button>
    </div>
  );
}

export default CounterDisplay;