import "./styles/List.css"
import Header from "./components/Header.tsx";
import Footer from "./components/Footer.tsx";
import {Route, Routes} from "react-router-dom";
import Home from "./components/home-page/Home.tsx";
import ListPage from "./components/list-page/ListPage.tsx";
import About from "./components/about-page/About.tsx";

function App() {
    return (
        <>
            <div className="d-flex flex-column min-vh-100">
                <Header/>
                <Routes>
                    <Route path="/" element={<Home/>} />
                    <Route path="/list/:id" element={<ListPage/>} />
                    <Route path="/about" element={<About/>} />
                </Routes>
                <Footer/>
            </div>

        </>
    )
}

export default App
