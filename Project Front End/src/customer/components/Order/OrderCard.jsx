import React from 'react'
import { Grid } from '@mui/material'
import FiberManualRecordIcon from '@mui/icons-material/FiberManualRecord';
import AdjustIcon from '@mui/icons-material/Adjust';
import { useNavigate } from 'react-router-dom';

const OrderCard = () => {
    const navigate = useNavigate();

    return (
        <div onClick={()=> navigate(`/account/order/${5}`)} className='p-5 shadow-lg hover:shadow-2xl border'>
            <Grid container spacing={2} sx={{ justifyContent: "space-between" }}>

                <Grid item xs={6}>
                    <div className='flex cursor-pointer '>
                        <img className='w-[5rem] h-[5rem] object-cover object-top' src=" " alt='' />
                        <div className='ml-5 space-y-2'>

                            <p className=''>Test Name</p>
                            <p className='opacity-50 text-xs font-semibold text-left'>Size</p>
                            <p className='opacity-50 text-xs font-semibold text-left'>Color</p>

                        </div>
                    </div>
                </Grid>

                <Grid item xs={2}>
                    <p>RM120</p>

                </Grid>

                <Grid item xs={4}>
                    {true && <div>
                        <p>
                            <AdjustIcon sx={{ width: "15px", height: "15px" }} className='text-green-600 mr-2' />
                            <span>
                                Delivered On March 23
                            </span>
                            
                        </p>

                        <p className='text-xs'>
                            Your Package has been Delivered
                        </p>
                    </div>}

                    {false && <p>
                        <span>
                            Delivered On March 23
                        </span>
                    </p>}
                </Grid>

            </Grid>
        </div>
    )
}

export default OrderCard