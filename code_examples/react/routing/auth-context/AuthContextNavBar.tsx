import { Outlet, Link} from "react-router-dom";
import { useAuth } from "./AuthContext";

function AuthContextNavBar() {

    const { isAuthenticated, logout } = useAuth();
    
    return (
        <div id="nav">
            <nav>
                <Link to="/">Home</Link>| 
                { isAuthenticated ? (
                    <span>
                        <Link to="/dashboard">Dashboard</Link>| 
                        <button onClick={logout}>Log Out</button>
                    </span>
                ) : (
                    <Link to="/login">Log In</Link>
                )
            }
            </nav>

            {/* Outlet marks where the matched child ruot's content appears */}
            <Outlet />

            <footer id="footer">Simple Authentication | 555.555.5555 </footer>
        </div>
    )
}

export default AuthContextNavBar;