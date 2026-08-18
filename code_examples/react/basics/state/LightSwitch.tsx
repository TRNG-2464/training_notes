import { useState } from "react";

function LightSwitch() {
  // "isOn" starts as false (the light starts off).
  // TypeScript infers this as a "boolean" automatically.
  const [isOn, setIsOn] = useState(false);

  function toggleLight() {
    // A common pattern for booleans: flip the current value.
    // "!isOn" simply means "the opposite of whatever isOn is right now."
    setIsOn(!isOn);
  }

  return (
    <div className={isOn ? "room-lit" : "room-dark"}>
      <p>The light is currently: {isOn ? "ON 💡" : "OFF"}</p>
      <button onClick={toggleLight}>
        Flip Switch
      </button>
    </div>
  );
}

export default LightSwitch;