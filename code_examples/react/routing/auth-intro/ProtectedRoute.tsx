/*
    This component acts as a wrapper that conditionally
    renders its child routes (via Outlet) or redirects
    the user to a login page.
*/
import { Navigate, Outlet } from "react-router-dom";

// HARDCODED for now — a simple boolean standing in for real
// authentication status.
const isAuthenticated = true;

function ProtectedRoute() {
  if (!isAuthenticated) {
    /*
        The Navigate component is used to redirect the user
        baased on whether they are authenticated.
        
        "replace" swaps out the current history entry, so the
        user can't hit "back" and land right back on the page
        they were just blocked from.
    */
    return <Navigate to="/login" replace />;
  }

  // If authenticated, render whichever child route matched.
  return <Outlet />;
}

export default ProtectedRoute;