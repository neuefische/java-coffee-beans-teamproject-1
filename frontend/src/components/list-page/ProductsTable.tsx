import {Button, Col, ListGroup, Row} from "react-bootstrap";

function ProductsTable() {
    return <>
        <ListGroup>
            <ListGroup.Item>
                <Row className="align-items-center">
                    <Col xs={8}>
                        <strong>tomato</strong>
                    </Col>
                    <Col xs={4} className="text-end">
                        <Button
                            variant="outline-primary"
                            size="sm"
                        >
                            -
                        </Button>
                        <span className="mx-2">2</span>
                        <Button
                            variant="outline-primary"
                            size="sm"
                        >
                            +
                        </Button>
                        <Button
                            variant="danger"
                            size="sm"
                            className="ms-2"
                        >
                            Delete
                        </Button>
                    </Col>
                </Row>
            </ListGroup.Item>
        </ListGroup>

    </>
}

export default ProductsTable