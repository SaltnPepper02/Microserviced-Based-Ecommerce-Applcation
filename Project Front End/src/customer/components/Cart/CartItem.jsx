import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import { Button, IconButton } from '@mui/material'
import React from 'react'
import { blue } from '@mui/material/colors';

const CartItem = () => {
    return (
        <div className='p-5 shadow-lg border rounded-md'>
            <div className='flex items-center'>
                <div className='w-[5rem] h-[5rem] lg:w-[9rem] lg:h-[9rem]'>
                    <img className='w-full h-full object-cover object top' src='' alt='' />
                </div>

                <div className='ml-5 space-y-1 text-left'>
                    <p className='font-semibold '>Test</p>
                    <p className='opacity-70'>Details</p>
                    <p className='opacity-70 mt-2'>Seller</p>

                    <div className='flex space-x-5 items-center text-lg lg:text-xl text-gray-900 pt-6 '>
                        <p className='font-semibold'>RM200</p>
                    </div>
                </div>

                
            </div>
            <div className='lg:flex items-center lg:space-x-10 pt-4'>
                    <div className='flex items-center space-x-2'>
                        <IconButton color=''>
                            <RemoveCircleOutlineIcon />
                        </IconButton>

                        <span className='py-1 px-7 border rounded-sm'>3</span>

                            <IconButton sx={{color:"blue"}}>
                                <AddCircleOutlineIcon />
                            </IconButton>
                        
                    </div>

                    <div>
                        <Button sx={{color:"blue"}}>remove</Button>
                    </div>

                </div>
        </div>
    )
}

export default CartItem