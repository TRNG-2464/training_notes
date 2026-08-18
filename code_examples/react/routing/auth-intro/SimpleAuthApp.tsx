/*
    This component just consolidates our 'Simple Auth' example

    This example focused on the generate structure of protected
    routes, but doesn't have any authentication logic, nor does
    it truly allow for managing authentication state. See the
    'auth-context' example for more details.
*/
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AuthNavBar from "./AuthNavBar";
import AuthHome from "./AuthHome";
import Login from "./Login";
import Dashboard from "./Dashboard";
import ProtectedRoute from "./ProtectedRoute";

function SimpleAuthApp() {
    return (
        <div>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<AuthNavBar />}>
                        <Route index element={<AuthHome />} />
                        <Route path="login" element={<Login />} />
                        
                        {/*
                            ProtectedRoute is the Parent, acting as the
                            Gatekeeper for the Child Routes. All nested
                            components require authentication before React
                            will render the component
                        */}
                        <Route element={<ProtectedRoute />}>
                            <Route path="dashboard" element={<Dashboard />} />
                            { /* Other protected routes go here... */}
                        </Route>                        
                    </Route>
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default SimpleAuthApp;