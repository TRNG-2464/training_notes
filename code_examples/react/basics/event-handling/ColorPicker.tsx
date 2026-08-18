/*
    Showcases onChange event using a dropdown button,
    referencing the React.ChangeEvent SyntheticEvent Type
*/
import { useState } from "react";

function ColorPicker() {
  const [color, setColor] = useState("blue");

  function handleChange(event: React.ChangeEvent<HTMLSelectElement>) {
    setColor(event.target.value);
  }

  return (
    <div>
      <select value={color} onChange={handleChange}>
        <option value="blue">Blue</option>
        <option value="red">Red</option>
        <option value="green">Green</option>
      </select>
      <p>Selected color: {color}</p>
    </div>
  );
}

export default ColorPicker;