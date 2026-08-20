import { useState } from "react";

/*
    In Controlled Components the input value of an associated
    input and the state of the react prop are tied via an
    onChange handler that keeps the values in sync
*/
function ControlledInput() {
  const [name, setName] = useState("");

  return (
    <div>
      <input
        value={name}                               // value comes FROM state
        onChange={(e) => setName(e.target.value)}  // every keystroke updates state
      />
      {/* "name" is always current, and usable anywhere in this component */}
      <p>You typed: {name}</p>
    </div>
  );
}

export default ControlledInput;