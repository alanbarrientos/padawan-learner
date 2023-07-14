import {Outlet, Navigate} from "react-router-dom"
import {isLogged} from "../Services/LoggedService";
export default function PrivateRoutes() {

    return (
        isLogged() ? <Outlet/> : <Navigate to="/login" />
            )
}
