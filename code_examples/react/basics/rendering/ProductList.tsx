// This example shows a more common/realistic use of List Rendering
interface Product {
    id: number;
    name: string;
    price: number;
}

const products: Product[] = [
    { id: 101, name: "keyboard", price: 49.99 },
    { id: 102, name: "Mouse", price: 24.99 },
    { id: 103, name: "Monitor", price: 199.99 },
];

function ProductList() {
    return (
        <ul>
            {products.map( 
                product => <li key={product.id}>{product.name} - ${product.price}</li>
            )}
        </ul>
    );
}

export default ProductList;