import { Outlet, Link} from "react-router-dom";

function NavBar() {
    return (
        <div id="nav">
            <nav>
                <Link to="/">Home</Link> | <Link to="/about">About</Link>
            </nav>

            {/* Outlet marks where the matched child route's content appears */}
            <Outlet />

            <footer id="footer">Store | 555.555.5555 </footer>
        </div>
    )
}

export default NavBar;