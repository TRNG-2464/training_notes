/*
    Showcases onKeyDown event for a specific key press.
    References the React.KeyboardEvent SyntheticEvent Type
*/
import { useState } from "react";

function EnterToSubmit() {
  const [message, setMessage] = useState("");

  function handleKeyDown(event: React.KeyboardEvent<HTMLInputElement>) {
    // Checking event.key lets us respond to a SPECIFIC key press,
    // rather than every keystroke.
    if (event.key === "Enter") {
      console.log("Message sent:", message);
      setMessage(""); // clear the field after "sending"
    }
  }

  return (
    <input
      value={message}
      onChange={(e) => setMessage(e.target.value)}
      onKeyDown={handleKeyDown}
      placeholder="Type a message and press Enter"
    />
  );
}

export default EnterToSubmit;