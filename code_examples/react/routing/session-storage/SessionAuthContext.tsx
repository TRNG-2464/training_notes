/*
    The following is an updated version of the AuthContext.tsx
    example, showcasing Context Objects used to manage the 
    authentication state of a user on our web-app. Here
    
*/
import { createContext, useContext, useState, type ReactNode } from "react";

interface AuthContextType {
  isAuthenticated: boolean;
  login: () => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType>({
  isAuthenticated: false,
  login: () => {},
  logout: () => {},
});

interface AuthProviderProps {
  children: ReactNode;
}

function AuthProvider({ children }: AuthProviderProps) {
  // Instead of always starting as "false," we check sessionStorage
  // FIRST. This means if the user already has a valid token (e.g.,
  // they refreshed the page), they stay logged in.
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(() => {
    return sessionStorage.getItem("authToken") !== null;
  });

  function login() {
    const fakeTokenFromServer = "abc123.def456.ghi789";

    // Store the token AND update state together, keeping
    // sessionStorage and our in-memory state in sync.
    sessionStorage.setItem("authToken", fakeTokenFromServer);
    setIsAuthenticated(true);
  }

  function logout() {
    sessionStorage.removeItem("authToken");
    setIsAuthenticated(false);
  }

  return (
    <AuthContext.Provider value={{ isAuthenticated, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

function useAuth() {
  return useContext(AuthContext);
}

export { AuthProvider, useAuth };