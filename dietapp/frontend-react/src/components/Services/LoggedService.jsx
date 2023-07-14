import {useNavigate} from "react-router-dom";

export function isLogged(){
    if(localStorage.getItem('username') != null){
        return true
    }
    return false
}
export function useRedirectToLogin() {
    const navigate= useNavigate()
    navigate('/login')
    return
}
