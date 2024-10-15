import {Col, Container, Row} from "react-bootstrap";

const Footer = () => {
    return (<>
            <footer className="bg-dark text-white mt-auto py-3">
                <Container>
                    <Row>
                        <Col md={4}>
                            <h5>About Us</h5>
                            <p>Some information about the website or company.</p></Col>
                        <Col md={4}>
                            <h5>Links</h5>
                            <ul className="list-unstyled">
                                <li><a href="#home" className="text-white">Home</a></li>
                                <li><a href="#about" className="text-white">About</a></li>
                            </ul>
                        </Col>
                        <Col md={4}>
                            <h5>Contact Us</h5>
                            <p>Email: info@example.com</p>
                        </Col> </Row> </Container></footer>
        </>

    )
}

export default Footer;