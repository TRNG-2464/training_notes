import { BrowserRouter, Routes, Route } from "react-router-dom"
import AuthContextNavBar from "./AuthContextNavBar"
import AuthContextHome from "./AuthContextHome"
import AuthContextLogin from "./AuthContextLogin"
import ContextProtectedRoute from "./ContextProtectedRoute"
import AuthContextDashboard from "./AuthContextDashboard"
import { AuthProvider } from "./AuthContext"

function AuthContextApp() {
    return (
        // Here - AuthProvider wraps our entire app so that all descendants have
        // potential access to the AuthContext
        <AuthProvider>
            <div>
                <BrowserRouter>
                    <Routes>
                        <Route path="/" element={<AuthContextNavBar />} >
                            <Route index element={<AuthContextHome />} />
                            <Route path="login" element={<AuthContextLogin /> } />

                            <Route element={<ContextProtectedRoute />}>
                                {/* Other protected routes would go here */}
                                <Route path="dashboard" element={<AuthContextDashboard />} />
                            </Route>

                        </Route>
                    </Routes>
                </BrowserRouter>
            </div>
        </AuthProvider>
    );
}

export default AuthContextApp;