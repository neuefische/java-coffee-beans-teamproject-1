import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {Link} from "react-router-dom";

const Header =()=>{
    return <>
    <Navbar bg="primary" data-bs-theme="dark" className="mb-3">
        <Container>
            <Navbar.Brand href="#home">Logo</Navbar.Brand>
            <Nav className="me-auto">
                <Link to="/">Home </Link>
                <Nav.Link href="/about">About</Nav.Link>
                <Nav.Link href="/products">Products</Nav.Link>
            </Nav>
        </Container>
    </Navbar>
    </>
}

export default Header;