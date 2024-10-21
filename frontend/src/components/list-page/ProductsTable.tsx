import { Button, Col, ListGroup, Row } from "react-bootstrap";

interface Product {
    name: string;
    quantity: number;
}

interface ProductsTableProps {
    products: Product[];
    updateProductQuantity: (name: string, quantity: number) => void;
    removeProduct: (name: string) => void;
}

const ProductsTable = ({ products, updateProductQuantity, removeProduct }: ProductsTableProps) => {
    return (
        <ListGroup>
            {products.map((product) => (
                <ListGroup.Item key={product.name}>
                    <Row className="align-items-center">
                        <Col xs={8}>
                            <strong>{product.name}</strong>
                        </Col>
                        <Col xs={4} className="text-end">
                            <Button
                                variant="outline-primary"
                                size="sm"
                                onClick={() => updateProductQuantity(product.name, -1)}
                                disabled={product.quantity <= 1}
                            >
                                -
                            </Button>
                            <span className="mx-2">{product.quantity}</span>
                            <Button
                                variant="outline-primary"
                                size="sm"
                                onClick={() => updateProductQuantity(product.name, 1)}
                            >
                                +
                            </Button>
                            <Button
                                variant="danger"
                                size="sm"
                                className="ms-2"
                                onClick={() => removeProduct(product.name)}
                            >
                                Delete
                            </Button>
                        </Col>
                    </Row>
                </ListGroup.Item>
            ))}
        </ListGroup>
    );
};

export default ProductsTable;
