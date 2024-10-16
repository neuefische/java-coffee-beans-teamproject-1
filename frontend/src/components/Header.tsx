import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

const Header =()=>{
    return <>
    <Navbar bg="primary" data-bs-theme="dark" className="mb-3">
        <Container>
            <Navbar.Brand href="/">Logo</Navbar.Brand>
            <Nav className="me-auto">
                <Nav.Link href="/">Home</Nav.Link>
                <Nav.Link href="/">About</Nav.Link>
                <Nav.Link href="/">Products</Nav.Link>
            </Nav>
        </Container>
    </Navbar>
    </>
}

export default Header;