/*
    Simple, hard-coded "database" used to showcase
    relative paths. These "reviews" are tied to a 
    product ID
*/

// Review is the Object that represents a Review
interface Review {
  id: number;
  reviewer: string;
  comment: string;
  rating: number;
}

// Keys here match the "id" values from products-data.ts
const reviewsByProduct: Record<number, Review[]> = {
  101: [
    { id: 1, reviewer: "Jordan", comment: "Great tactile feel!", rating: 5 },
    { id: 2, reviewer: "Sam", comment: "A bit loud for open offices.", rating: 3 },
  ],
  102: [
    { id: 3, reviewer: "Alex", comment: "Comfortable for long sessions.", rating: 4 },
  ],
  103: [
    { id: 4, reviewer: "Taylor", comment: "Colors are vivid, love it.", rating: 5 },
    { id: 5, reviewer: "Morgan", comment: "Stand wobbles a little.", rating: 3 },
  ],
};

export default reviewsByProduct;
export type { Review };