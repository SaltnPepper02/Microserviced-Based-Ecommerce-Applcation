import axios from "axios"
import { API_BASE_URL } from "../../config/apiConfig"
import { GET_USER_FAILURE, GET_USER_REQUEST, GET_USER_SUCCESS, LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS, LOG_OUT, REGISTER_FAILURE, REGISTER_REQUEST, REGISTER_SUCCESS } from "./ActionType"

const token = localStorage.getItem("jwt")

const registerRequest = () =>({type:REGISTER_REQUEST});
const registerSuccess = (user) =>({type:REGISTER_SUCCESS, payload:user});
const registerFailure = (error) =>({type:REGISTER_FAILURE, payload:error});

export const registerUser = (userData) => async (dispatch) => {

    dispatch(registerRequest())

    try {
        const respond = await axios.post(`${API_BASE_URL}/auth/signup`,userData)
        const user = respond.data;
        if(user.jwt){
            localStorage.setItem("jwt", user.jwt)
        }
        console.log("user" ,user)
        dispatch(registerSuccess(user.jwt))
        
    } catch (error) {
        dispatch(registerFailure(error.message))
    }

}

const loginRequest = () =>({type:LOGIN_REQUEST});
const loginSuccess = (user) =>({type:LOGIN_SUCCESS, payload:user});
const loginFailure = (error) =>({type:LOGIN_FAILURE, payload:error});

export const loginUser = (userData) => async (dispatch) => {

    dispatch(loginRequest())

    try {
        const respond = await axios.post(`${API_BASE_URL}/auth/signin`,userData)
        const user = respond.data;
        if(user.jwt){
            localStorage.setItem("jwt", user.jwt)
        }
        dispatch(loginSuccess(user.jwt))
        
    } catch (error) {
        dispatch(loginFailure(error.message))
    }

}

const getUserRequest = () =>({type:GET_USER_REQUEST});
const getUserSuccess = (user) =>({type:GET_USER_SUCCESS, payload:user});
const getUserFailure = (error) =>({type:GET_USER_FAILURE, payload:error});

export const getUser = () => async (dispatch) => {

    dispatch(getUserRequest())

    try {
        const respond = await axios.get(`${API_BASE_URL}/api/users/profile`, {
            headers:{
                "Authorization": `Bearer {token}`
            }
        })
        const user = respond.data;
        console.log("user" ,user)
        dispatch(getUserSuccess(user))
        
    } catch (error) {
        dispatch(getUserFailure(error.message))
    }

}

export const logout = () => (dispatch) => {
    dispatch({type:LOG_OUT,payload:null})
}
