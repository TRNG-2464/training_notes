/*
    Showcases onFocus and onBlur events using a text input
*/
import { useState } from "react";

function EmailField() {
  const [isFocused, setIsFocused] = useState(false);

  return (
    <div>
      <input
        type="email"
        placeholder="Enter your email"
        onFocus={() => setIsFocused(true)}
        onBlur={() => setIsFocused(false)}
      />
      {/* A simple visual cue driven entirely by focus state */}
      <p>{isFocused ? "Editing email..." : "Click the field to edit"}</p>
    </div>
  );
}

export default EmailField;