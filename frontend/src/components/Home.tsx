import Footer from "./Footer.tsx";
import Header from "./Header.tsx";
import ListGroup from "./ListGroup.tsx";

const Home = () => {
    return (<>
        <div className="d-flex flex-column min-vh-100">
            <Header/>
            <ListGroup/>
            <Footer/>
        </div>
    </>)
}

export default Home;