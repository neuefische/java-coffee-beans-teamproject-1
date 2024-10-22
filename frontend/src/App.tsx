import "./styles/List.css"
import Header from "./components/Header.tsx";
import Footer from "./components/Footer.tsx";
import {Route, Routes, useNavigate} from "react-router-dom";
import Home from "./components/home-page/Home.tsx";
import ListPage from "./components/list-page/ListPage.tsx";
import About from "./components/about-page/About.tsx";
import axios, {AxiosError} from "axios";
import {useEffect, useState} from "react";
import {Button} from "react-bootstrap";

function App() {
    const [username, setUsername] = useState<string | undefined>();
    const navigate = useNavigate();

    function login() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin

        window.open(host + '/oauth2/authorization/github', '_self')
    }

    const loadUser = () => {
        axios.get('/api/auth/me')
            .then(response => {
                const usernameResponse = response.data;
                setUsername(usernameResponse);
            })
            .catch((error: AxiosError) => {
                console.log(error)
            })
    }

    function logout() {
        axios.post("/api/auth/logout")
            .then(() => {
                setUsername("");
                navigate("/");
            })
            .catch((error: AxiosError) => {
                console.log(error)
            })
    }

    useEffect(() => {
        loadUser();
    }, []);

    return (
        <>
            {username ? (
                    <div className="d-flex flex-column min-vh-100">
                        <Header logout={logout}/>
                        <Routes>
                            <Route path="/" element={<Home/>}/>
                            <Route path="/list/:id" element={<ListPage/>}/>
                            <Route path="/about" element={<About/>} />
                        </Routes>
                        <Footer/>
                    </div>) :
                <div className="logout-btn">
                    <h3 className="mb-4">Please log in to see your Shopping list.</h3>
                    <Button onClick={login}>Login with GitHub</Button>
                </div>
            }
        </>
    )
}

export default App
