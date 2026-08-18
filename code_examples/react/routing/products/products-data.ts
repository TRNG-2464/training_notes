interface Product {
  id: number;
  name: string;
  price: number;
  description: string;
}

const products: Product[] = [
  {
    id: 101,
    name: "Keyboard",
    price: 49.99,
    description: "A mechanical keyboard with tactile switches.",
  },
  {
    id: 102,
    name: "Mouse",
    price: 24.99,
    description: "A wireless mouse with ergonomic design.",
  },
  {
    id: 103,
    name: "Monitor",
    price: 199.99,
    description: "A 27-inch 1440p monitor with a 144Hz refresh rate.",
  },
];

export default products;
export type { Product };