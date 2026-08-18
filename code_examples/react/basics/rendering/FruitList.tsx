const fruits = ["Apple", "Banana", "Cherry"];

// A very simple list component
function FruitList() {
    return (
        <ul>
            {fruits.map(
                /*
                    Here, we are using the fruit name as the key
                    but this is a poor strategy. It only works here
                    because we have such a simple list of values
                */
                fruit => <li key={fruit}>Fruit: {fruit}</li>
            )}
        </ul>
    );
}


export default FruitList;