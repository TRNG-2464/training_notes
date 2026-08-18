import { Link } from "react-router-dom";

function Home() {
  return (
    <div>
      <h1>Welcome to the Store</h1>
      <ul>
        <li>
          <Link to="/products/101">Keyboard</Link>
        </li>
        <li>
          <Link to="/products/102">Mouse</Link>
        </li>
        <li>
          <Link to="/products/103">Monitor</Link>
        </li>
      </ul>
    </div>
  );
}

export default Home;