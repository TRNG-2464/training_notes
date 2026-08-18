import { useNavigate } from "react-router-dom";
import { useAuth } from "./AuthContext";

function AuthContextLogin() {
    const { login } = useAuth();
    const navigate = useNavigate(); // lets us redirect programmatically

    function handleLogin() {
        login();
        navigate("/dashboard");
    }

    return (
        <div>
            <h1>Login</h1>
            <p>Click the button below to login</p>
            <button onClick={handleLogin}>Log In</button>
        </div>
    );
}

export default AuthContextLogin;