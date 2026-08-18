/*
    This file showcases 'relative routes'. This Route component
    is nested UNDER ProductDetails in the Routes structure
    i.e. it reads the SAME :productId param from the URL.
*/
import { useParams, Link } from "react-router-dom";
import reviewsByProduct from "./reviews-data";

function Reviews() {
  const { productId } = useParams();
  const reviews = reviewsByProduct[Number(productId)] ?? [];

  return (
    <div>
      <h2>Reviews for Product #{productId}</h2>

      {reviews.length === 0 ? (
        <p>No reviews yet for this product.</p>
      ) : (
        <ul>
          {reviews.map((review) => (
            <li key={review.id}>
              <strong>{review.reviewer}</strong> ({review.rating}/5) —{" "}
              {review.comment}
            </li>
          ))}
        </ul>
      )}

      {/*
        RELATIVE path — no leading "/". Since the current URL is
        something like "/products/101/reviews", "../" navigates
        UP one URL segment, back to "/products/101".
      */}
      <Link to="../">Hide Reviews</Link>
    </div>
  );
}

export default Reviews;