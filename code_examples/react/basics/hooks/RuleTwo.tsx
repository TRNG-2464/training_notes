import { useState } from "react";

// CORRECT — useState is only called from within
// an actual React function component.
function RuleTwo() {
  const [temperature, setTemperature] = useState(72);

  function calculateFahrenheitToCelsius(f: number) {
    // Regular helper logic can live INSIDE a component
    // and use values from state — it just can't call
    // Hooks itself.
    return ((f - 32) * 5) / 9;
  }

  return (
    <p>{temperature}°F is {calculateFahrenheitToCelsius(temperature).toFixed(1)}°C</p>
  );
}

/* The following is INCORRECT usage! - Don't do this
        This is a regular helper function, NOT a component.
        Calling useState here breaks React's rules of Hooks, 
        because it's not a component and it might be called
*/
function calculateSomething() {
  const [temp, setTemp] = useState(0);
  return temp * 2;
}

export default RuleTwo;


