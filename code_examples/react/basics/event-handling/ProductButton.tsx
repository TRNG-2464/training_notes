/*
    Passing Arguments to an Event Handler
*/

interface ProductButtonProps {
    productName: string;
}

function ProductButton({ productName }: ProductButtonProps) {
    function handleAddToCart(name: string) {
        // Other 'cart adding' logic should go here...
        console.log(`Adding ${name} to cart...`);
    }
    
    // We can pass arguments to the event handler by using an arrow function
    // This arrow function will call our 'handleAddToCart' function with the 
    // product name when the button is clicked
    return (
        <button onClick={() => handleAddToCart(productName)}>
            Add {productName} to Cart
        </button>
    );
}

export default ProductButton;