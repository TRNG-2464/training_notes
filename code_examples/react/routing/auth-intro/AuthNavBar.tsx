import { Outlet, Link} from "react-router-dom";

function AuthNavBar() {
    return (
        <div id="nav">
            <nav>
                <Link to="/">Home</Link> | <Link to="/login">Login</Link> | <Link to="/dashboard">Dashboard</Link>
            </nav>

            {/* Outlet marks where the matched child ruot's content appears */}
            <Outlet />

            <footer id="footer">Simple Authentication | 555.555.5555 </footer>
        </div>
    )
}

export default AuthNavBar;