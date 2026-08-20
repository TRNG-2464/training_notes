import { useRef } from "react";

/*
    Uncontrolled components let the DOM manage the input
    value. Data is only read and used by React when
    needed (on form submission/button click, etc...)
*/
function UncontrolledInput() {
  // useRef gives us a direct handle to the actual DOM element.
  // Unlike state, updating a ref does NOT cause a re-render.
  const inputRef = useRef<HTMLInputElement>(null);

  function handleSubmit() {
    // We only "reach in" and read the value when we actually need it —
    // React has no idea what the user typed until this moment.
    console.log("Current value:", inputRef.current?.value);
  }

  return (
    <div>
      {/* Notice: no "value" prop and no "onChange" tied to state */}
      <input ref={inputRef} placeholder="Type something..." />
      <button onClick={handleSubmit}>Log Value</button>
    </div>
  );
}

export default UncontrolledInput;