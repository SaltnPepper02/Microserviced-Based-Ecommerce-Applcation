import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Homepage from '../customer/pages/Homepage/Homepage';
import Cart from '../customer/components/Cart/Cart';
import Navigation from '../customer/components/Nav/Nav';
import Footer from '../customer/components/Footer/Footer';
import Product from '../customer/components/ProductPage/Product';

const customerRouters = () => {
    return (
        <div>
            {/* <div>
                <Navigation/>

            </div> */}
            <Routes>

                <Route path='/' element={<Homepage/>}/>
                <Route path='/cart' element={<Cart/>}/>
                <Route path='/:levelOne/:levelTwo/:levelThree' element={<Product/>}/>

                {/* <Product/> */}
                {/* <ProductDetail/> */}
                {/* <Cart/> */}
                {/* <CheckOut/> */}
                {/* <Order/> */}
                {/* <OrderDetails/> */}


            </Routes>

            {/* <div>
                <Footer />
            </div> */}
        </div>
    )
}

export default customerRouters