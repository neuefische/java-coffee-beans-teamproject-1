import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {Link} from "react-router-dom";
import {Button} from "react-bootstrap";
import Logo from "../../public/logo.svg";

type HeaderProps = {
    logout: () => void;
}

const Header = ({logout}: HeaderProps) => {
    return <>
        <Navbar bg="primary" data-bs-theme="dark" className="mb-3">
            <Container className="w-100">
                <div className="header-container">
                    <div className="header-container__nav">
                        <Navbar.Brand href="/">
                            <img src={Logo} alt="" className="logo"/>
                        </Navbar.Brand>
                        <Nav className="me-auto">
                            <Nav.Link as={Link} to="/">Home</Nav.Link>
                            <Nav.Link as={Link} to="/about">About</Nav.Link>
                        </Nav>
                    </div>
                    <div className="d-flex">
                        <Button onClick={logout}>Logout</Button>
                    </div>
                </div>
            </Container>
        </Navbar>
    </>
}

export default Header;