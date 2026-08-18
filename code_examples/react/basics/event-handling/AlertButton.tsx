/*
    Basic Event Handling
*/
function AlertButton() {
    // This function describes what we want to happen
    // in response to an event (onClick)
    function handleClick() {
        alert("You clicked the button!");
    }

    return (
        <button onClick={handleClick}>
            Click Me!
        </button>
    );
}

export default AlertButton;