/*
    This file defines the Context itself. It include a
    Provide component that supplies real auth state and
    a custom 'useAuth' Hook for other components to 
    consume the state cleanly.
*/
import { createContext, useContext, useState, type ReactNode } from "react";

interface AuthContextType {
    isAuthenticated: boolean;
    login: () => void;
    logout: () => void;
}

/*
    createContext needs an initial value matching the shape of
    the AuthContextType interface. Here we'll just provide some
    basic defaults

    These values will be overridden by the AuthProvider
*/
const AuthContext = createContext<AuthContextType> (
   {
    isAuthenticated: false,
    login: () => {},
    logout: () => {}
   } 
);

interface AuthProviderProps {
    children: ReactNode;
}

/*
    This Provider component wraps part of our APP, and owns the actual
    authentication state.
*/
function AuthProvider( {children}: AuthProviderProps ) {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    function login() {
        /*
            In a real full-stack app, this would run after a successful API call
            to your authentication server (i.e. checking that credentials)
            provided match the expected from the DB
        */
        setIsAuthenticated(true);
    }

    function logout() {
        setIsAuthenticated(false);
    }

    /*
        Here, we return the actual data isAuthenticated (set to false initially via useState)
        the login() function and the logout() function - this overrides the initial
        values of the AuthContext

        Note: '.Provider' on AuthContext is a special Wrapper Component which makes 
        the AuthContext values available to children of this AuthProvider.
    */
    return(
        <AuthContext.Provider value={{ isAuthenticated, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
}

/*
    This custom hook just wraps our AuthContext so that other
    components can use this instead of using the useContext(AuthContext)
    hook directly elsewhere in our application - managing the bloat
    and imports
*/
function useAuth() {
    return useContext(AuthContext);
}

export { AuthProvider, useAuth };