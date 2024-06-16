import { Button, Grid, TextField } from '@mui/material'
import { useNavigate } from 'react-router-dom'
import React from 'react'

const LoginForm = () => {
  const handleSubmit = (event) => {

    event.preventDefault()

    const data = new FormData(event.currentTarget);

    const userData = {
      firstName: data.get("firstName"),
      lastName: data.get("lastName"),
      email: data.get("email"),
      password: data.get("password")
    }

    console.log("userdata", userData)

  }

  const navigate = useNavigate();

  return (
    <div>

      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>

          <Grid item xs={12}>
            <TextField 
            required
            id='email'
            name='email'
            label="Email"
            fullWidth
            autoComplete='email'
            />


          </Grid>

          <Grid item xs={12}>
            <TextField 
            required
            id='password'
            name='password'
            label="Password"
            fullWidth
            autoComplete='password'
            />


          </Grid>

          <Grid item xs={12}>
            <Button className='w-full' type='submit' variant='contained' size='large' sx={{padding:".8rem 0"}}>
              Login
            </Button>
          </Grid>


          

        </Grid>
      </form>

      <div className='flex justify-center flex-col items-center'>
        <div className='py-3 flex items-center'>
          <p>If you don't have an account, Click here to</p>
          <Button onClick={() => navigate("/register")} className='ml-6' size='small'>register</Button>
        </div>
      </div>
    </div>
  )
}

export default LoginForm