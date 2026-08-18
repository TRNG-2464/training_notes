// STORING AND READING A TOKEN WITH localStorage
// Storing a token (e.g., right after a successful login):
function storeToken(token: string) {
  localStorage.setItem("authToken", token);
}

// Reading it back later (e.g., when the app first loads,
// to check if the user is already logged in):
function getStoredToken(): string | null {
  return localStorage.getItem("authToken");
}

// Removing it (e.g., on logout):
function clearToken() {
  localStorage.removeItem("authToken");
}



// THE SAME PATTERN WITH sessionStorage
// (identical API, different persistence behavior)
function storeSessionToken(token: string) {
  sessionStorage.setItem("authToken", token);
  // This token will automatically disappear once
  // the browser tab is closed.
}


/*
    When using local or session storage for session management

    Your 'login' and 'logout' functionality should be tied
    to this object
*/
function login() {
  // In a real app, this token would come back from your
  // server's response after validating the user's credentials:
  const fakeTokenFromServer = "abc123.def456.ghi789";

  localStorage.setItem("authToken", fakeTokenFromServer);
  // setIsAuthenticated(true); (from AuthContext.tsx)
}

function logout() {
  localStorage.removeItem("authToken");
  // setIsAuthenticated(false); (from AuthContext.tsx)
}
