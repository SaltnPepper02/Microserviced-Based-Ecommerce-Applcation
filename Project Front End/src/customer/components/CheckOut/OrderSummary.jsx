import React from 'react'
import AddressCard from '../AddressCard/AddressCard'
import CartItem from '../Cart/CartItem'
import { Button } from '@mui/material';

const OrderSummary = () => {
    return (

        <div>
            <div className='p-5 shadow-lg rounded-s-md border'>
                <AddressCard />
            </div>

            <div>
            <div className='lg:grid grid-cols-3 relative'>

                <div className='col-span-2'>
                {[1,1,1,1].map((item)=><CartItem/>)}

                </div>

                <div className='px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0'>
                    <div className='border text-left'>
                        <p className='uppercase font-bold opacity-60 pb-4'>Price Detail</p>
                        <hr />
                        <div className='space-y-3 font-semibold'>
                            <div className='flex justify-between pt-3 text-black'>
                                <span>Price</span>
                                <span>RM1234</span>
                            </div>

                            <div className='flex justify-between pt-3'>
                                <span>Discount</span>
                                <span className='text-green-600'>RM200</span>
                            </div>

                            <div className='flex justify-between pt-3'>
                                <span>Delivery Charge</span>
                                <span className='text-green-600'>FREE</span>
                            </div>

                            <div className='flex justify-between pt-3 font-bold'>
                                <span>Total Price</span>
                                <span className='text-green-600'>RM1034</span>
                            </div>

                        </div>
                        <Button variant='contained' className='w-full' sx={{ px: "2.5rem", py: "0.7rem", bgcolor: "blue", mt: "3rem" }} >
                            Check Out
                        </Button>
                    </div>

                </div>
            </div>


        </div>
        </div>

    )
}

export default OrderSummary