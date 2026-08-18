import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "./AuthContext";

function ContextProtectedRoute() {
    const { isAuthenticated } = useAuth();

    if (!isAuthenticated) {
        return <Navigate to="/login" replace />;
    }

    return <Outlet />
}

export default ContextProtectedRoute;