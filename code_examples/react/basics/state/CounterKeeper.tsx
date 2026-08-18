/*
  Instead of managing the 'state' (the count) on
  the children (CounterDisplay) - the state is 
  "lifted up" to the parent component, which is then
  responsible for managing state.

  "lifting up state" is a common pattern seen in React
  to ensure that shared state data only flows in
  one direction.
*/
import {useState} from 'react';
import CounterDisplay from './CounterDisplay';

function CounterKeeper() {
  const [count, setCount] = useState<number>(0);

  function handleIncrement() {
    setCount(count + 1);
  }

  function handleDecrement() {
    setCount(count - 1);
  }

  // In this return, the CounterKeeper component is passing down the current count 
  // and the increment/decrement functions as props to the CounterDisplay component. 
  // This allows CounterDisplay to remain a presentational component, unaware of 
  // how the state is managed.
  return (
    <CounterDisplay
      count={count}
      onIncrement={handleIncrement}
      onDecrement={handleDecrement}
    />
  );
}

export default CounterKeeper;