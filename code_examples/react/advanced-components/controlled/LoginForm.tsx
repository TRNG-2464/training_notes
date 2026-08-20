/*
    This file showcases an example of a Controlled Component
    which  performs validation on the username and password
    of someone trying to login (or possibly this can be used
    when someone is creating their account!)
*/
import { useState } from "react";

// This utility function checks if a string contains a number. Used
// as part of our form validation
function containsNumber(value: string): boolean {
  const digits = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"];

  for (const character of value) {
    if (digits.includes(character)) {
      return true; // found a digit no need to keep checking
    }
  }

  return false; // checked every character - no digit was found
}

function LoginForm() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  /*
    Validation Logic:
        -Username must be between 3 and 15 characters
        -Password must contain a number
  */
  const isUsernameValid = username.length >= 3 && username.length <= 15;
  const isPasswordValid = containsNumber(password);

  const isFormValid = isUsernameValid && isPasswordValid;

  function handleSubmit(event: React.SubmitEvent<HTMLFormElement>) {
    event.preventDefault();

    if (!isFormValid) {
      return;   // do nothing if form input is not valid
    }
    /*
        Here is where you can call functionality to create an auth token
        or ping a server to actually check login details...
    */
    console.log("Logging in with:", { username, password });
  }

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="username">Username</label>
        <input
          id="username"
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        {/* conditionally render an error message if the username
            is not between 3 and 15 characters... */}
        {username.length > 0 && !isUsernameValid && (
          <p className="field-error">
            Username must be between 3 and 15 characters.
          </p>
        )}
      </div>

      <div>
        <label htmlFor="password">Password</label>
        <input
          id="password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        {/* Conditionally render an error message if the
            password does not contain a number... */}
        {password.length > 0 && !isPasswordValid && (
          <p className="field-error">
            Password must contain at least one number.
          </p>
        )}
      </div>

        {/* Here, we use 'isFormValid'to disable the login button
            if the username and/or password are not valid */}
      <button type="submit" disabled={!isFormValid}>
        Log In
      </button>
    </form>
  );
}

export default LoginForm;