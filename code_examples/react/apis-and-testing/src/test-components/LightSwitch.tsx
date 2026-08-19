import { useState } from "react";

function LightSwitch() {
  const [isOn, setIsOn] = useState(false);

  function toggleLight() {
    setIsOn(!isOn);
  }

  return (
    <div className={isOn ? "room-lit" : "room-dark"}>
      <p>The light is currently: {isOn ? "ON" : "OFF"}</p>
      <button onClick={toggleLight}>Flip Switch</button>
    </div>
  );
}

export default LightSwitch;