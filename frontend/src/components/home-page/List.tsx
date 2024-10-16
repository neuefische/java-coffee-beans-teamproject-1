import {Button, Card} from "react-bootstrap";
import {useNavigate} from "react-router-dom";

type ListTypes = {
    list: string
}

const List = ({list}: ListTypes ) =>{
    const navigate = useNavigate();

    const handleEdit = (list: string) => {
        navigate("/list", { state: { list } });
    };

    return (<>
            <Card className="list-card">
                <Card.Body>
                    <Card.Title>{list}</Card.Title>
                    <Card.Text>
                        Some quick example text to build on the card title and make up the
                        bulk of the card's content.
                    </Card.Text>
                    <div className ="list-button-container">
                    <Button variant="outline-primary" onClick={() => handleEdit(list)}>Edit</Button>
                    <Button variant="outline-danger">Delete</Button>
                    </div>
                </Card.Body>
            </Card>
        </>
    )
}

export default List;