import React from 'react'
import AddressCard from '../AddressCard/AddressCard'
import OrderTracker from './OrderTracker'
import { Grid, Box } from '@mui/material'
import { deepPurple } from '@mui/material/colors'
import StarBorderIcon from '@mui/icons-material/StarBorder';


const OrderDetails = () => {
  return (
    <div className='px-5 lg:px-20'>
      <div>
        <h1 className='font-bold text-xl py-7 text-left'>Delivery Address</h1>
        <AddressCard />

      </div>

      <div className='mt-10 py-10 shadow-lg border'>
        <OrderTracker activeStep={3} />
      </div>

      <Grid container className='space-y-5 '>
        {[1, 1, 1, 1, 1].map((item) => <Grid item container className='shadow-xl rounded-md p-5 border' sx={{ alignItems: "center", justifyContent: "space-between" }}>

          <Grid item xs={6}>
            <div className='flex items-center space-x-4'>
              <img className='w-[5rem] h-[5rem] object-cover object-top' src='' alt='' />

              <div className='text-left space-y-2 ml-5'>
                <p className='font-semibold'>Test Name</p>
                <p className='space-x-5 opacity-50 text-xs font-semibold'><span>Color</span> <span>Size</span></p>
                <p>Test Seller</p>
                <p>RM 120</p>

              </div>
            </div>
          </Grid>

          <Grid item>
            <Box sx={{ color: deepPurple[500] }}>

              <StarBorderIcon sx={{ fontSize: "2rem" }} className='px-2' />
              <span>Rate & Review Product</span>

            </Box>
          </Grid>
        </Grid>)}



      </Grid>

    </div>
  )
}

export default OrderDetails