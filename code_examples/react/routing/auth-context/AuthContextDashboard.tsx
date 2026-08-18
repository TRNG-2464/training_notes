/*
    This component simulates the protect page
    in which the Protected Route can display - if
    the user is authenticated

    This is a copy of the 'Dashboard' within 
    the 'auth-intro' example
*/
function AuthContextDashboard() {
    return(
        <div>
            <h1>Dashboard</h1>
            <p>Welcome back! You must be logged in if you can see this!</p>
        </div>
    )
}

export default AuthContextDashboard;