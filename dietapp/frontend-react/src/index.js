import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Landing from "./components/Landing";
import WeightHistory from "./components/WeightHistory/WeightHistory";
import Login from "./components/Login";
import Signup from "./components/Signup";
import NotFound from "./components/NotFound";
import PrivateRoutes from "./components/Auths/AuthGuard";
import axios from "axios";
import {ToastContainer,toast} from "react-toastify"
import 'react-toastify/dist/ReactToastify.css'
import Footer from "./components/Footer";


axios.interceptors.response.use(
    function (response) {
        return response;
    },
    function (error) {
        if (error.response.status === 401 ) {
            if(window.location.pathname === '/login'){
                throw error
            }else {
                localStorage.clear()
                window.location.reload()
            }
        } else {
            toast.error(error.toString(), {
                position: "top-right",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
            console.error(error);
            throw error
        }
    }
)


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(

        // <React.StrictMode>
    <div>
            <ToastContainer/>
            <BrowserRouter>
                <App/>
                <Routes>
                    <Route path="/" element={<Landing />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/signup" element={<Signup />} />
                    <Route element={<PrivateRoutes/>}>
                        <Route path="/weight-history" element={<WeightHistory/>}/>
                    </Route>
                    <Route path="*" element={<NotFound />} />
                </Routes>
            </BrowserRouter>
            <Footer></Footer>
    </div>
         // </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
