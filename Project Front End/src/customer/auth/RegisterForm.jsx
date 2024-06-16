import { Button, Grid, TextField } from '@mui/material'
import { useNavigate } from 'react-router-dom'
import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { getUser, registerUser } from '../../State/Auth/Action'

const RegisterForm = () => {

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt")
  const {auth} = useSelector(store => store)

  useEffect(() => {
    if(jwt){
      dispatch(getUser())
    }

  }, [jwt, auth.jwt])

  

  const handleSubmit = (event) => {

    event.preventDefault()

    const data = new FormData(event.currentTarget);

    const userData = {
      firstName: data.get("firstName"),
      lastName: data.get("lastName"),
      email: data.get("email"),
      password: data.get("password")
    }

    dispatch(registerUser(userData))

    console.log("userdata", userData)

  }



  return (
    <div>

      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6}>
            <TextField
              required
              id='firstName'
              name='firstName'
              label="First Name"
              fullWidth
              autoComplete='given-name'
            />


          </Grid>

          <Grid item xs={12} sm={6}>
            <TextField
              required
              id='lastName'
              name='lastName'
              label="Last Name"
              fullWidth
              autoComplete='given-name'
            />


          </Grid>

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
            <Button className='w-full' type='submit' variant='contained' size='large' sx={{ padding: ".8rem 0" }}>
              Register
            </Button>
          </Grid>




        </Grid>
      </form>

      <div className='flex justify-center flex-col items-center'>
        <div className='py-3 flex items-center'>
          <p>If you already have an account, Click here to</p>
          <Button onClick={() => navigate("/signin")} className='ml-6' size='small'>sign in</Button>
        </div>
      </div>

    </div>
  )
}

export default RegisterForm