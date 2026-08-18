import './App.css'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import NavBar from "./basic-routing/NavBar";
import Home from "./basic-routing/Home";
import About from "./basic-routing/About";
import ProductDetails from './products/ProductDetails';
import Reviews from './products/Reviews';
import NotFound from './basic-routing/NotFound';


function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Our Nav Bar shows up on every page - so we make this our parent route */}
        <Route path="/" element={<NavBar />}>
          {/* Home is our landing page - it uses index. Index routes do not have a path attribute */}
          <Route index element={<Home />} />
          <Route path="/about" element={<About />} />

          {/* Reviews are tied to specific products - so we nest that component under the ProductDetails
              component - the same path param is passed to Reviews so React knows which review to display */}
          <Route path="/products/:productId" element={<ProductDetails />}>
            <Route path="reviews" element={<Reviews /> } />
          </Route>
          <Route path="*" element={<NotFound />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
