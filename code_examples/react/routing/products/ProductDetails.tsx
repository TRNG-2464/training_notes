import { Outlet, Link, useParams } from "react-router-dom";
import products from "./products-data";

function ProductDetails() {
  // "productId" comes back as a STRING, since it's parsed
  // straight from the URL — URLs don't have a concept of numbers.
  const { productId } = useParams();

  // Converting to a number so it matches our data's "id" field,
  // then finding the product with a matching id.
  const product = products.find((p) => p.id === Number(productId));

  // Always handle the "not found" case — someone could type an
  // invalid product ID directly into the URL bar.
  if (!product) {
    return <p>Product not found.</p>;
  }

  return (
    <div>
      <h1>{product.name}</h1>
      <p>Price: ${product.price}</p>
      <p>{product.description}</p>
    

      {/* Relative path. We are currently at a URL like "/products/101"
          This relative path would navigate to "/products/101/reviews"
          without needing to know or hardcode an absolute URL path */}
      <Link to="reviews">See Reviews</Link>

      {/* any time a route would be a parent (it has a route nested under
          it). It requires this Outlet. (Note: Reviews is nested under 
          Product details in our Routes */}
      <Outlet />
    </div>
  );
}

export default ProductDetails;