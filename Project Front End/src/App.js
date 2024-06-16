import { Route, Routes } from 'react-router-dom';
import './App.css';
import Cart from './customer/components/Cart/Cart';
import CheckOut from './customer/components/CheckOut/CheckOut';
import Footer from './customer/components/Footer/Footer';
import Navigation from './customer/components/Nav/Nav';
import Order from './customer/components/Order/Order';
import OrderDetails from './customer/components/Order/OrderDetails';
import ProductDetail from './customer/components/ProductDetail/ProductDetail';
import Product from './customer/components/ProductPage/Product';
import Homepage from './customer/pages/Homepage/Homepage';
import CustomerRouters from './Routers/customerRouters';


function App() {
  return (
    <div className="App">

      <div>
        <Navigation/>

      </div>

      <Routes>

        <Route path='/' element={<CustomerRouters />}></Route>
        <Route path='/signin' element={<Homepage />}></Route>
        <Route path='/register' element={<Homepage />}></Route>
        <Route path='/cart' element={<Cart/>}/>
        <Route path='/:levelOne/:levelTwo/:levelThree' element={<Product />}/>
        <Route path='/product/:productId' element={<ProductDetail/>}/>
        <Route path='/checkout' element={<CheckOut/>}/>
        <Route path='/account/order' element={<Order/>}/>
        <Route path='/account/order/:orderId' element={<OrderDetails/>}/>
      </Routes>




      <div>
      <Footer/>
    </div>

    </div>
  );
}

export default App;
