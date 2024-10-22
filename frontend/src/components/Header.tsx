import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {Link} from "react-router-dom";
import {Button, Col, Row} from "react-bootstrap";

type HeaderProps = {
    logout: () => void;
}

const Header = ({logout}: HeaderProps) => {
    return <>
        <Navbar bg="primary" data-bs-theme="dark" className="mb-3">
            <Container>
                <Row className = "header-container">
                    <Col md={4}>
                        <Navbar.Brand href="/">Logo</Navbar.Brand>
                        <Nav className="me-auto">
                            <Nav.Link as={Link} to="/">Home</Nav.Link>
                            <Nav.Link as={Link} to="/about">About</Nav.Link>
                            <Nav.Link as={Link} to="/">Products</Nav.Link>
                        </Nav>
                    </Col>
                    <Col>
                        <Button onClick={logout}>Logout</Button>
                    </Col>
                </Row>
            </Container>
        </Navbar>
    </>
}

export default Header;