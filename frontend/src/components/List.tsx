import {Button, Card} from "react-bootstrap";

const List = () =>{
    return (<>
            <Card className="list-card">
                <Card.Body>
                    <Card.Title>Card Title</Card.Title>
                    <Card.Text>
                        Some quick example text to build on the card title and make up the
                        bulk of the card's content.
                    </Card.Text>
                    <div className ="list-button-container">
                    <Button variant="outline-primary">Edit</Button>
                    <Button variant="outline-danger">Delete</Button>
                    </div>
                </Card.Body>
            </Card>
        </>
    )
}

export default List;