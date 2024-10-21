import { Button, Card } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import axios, { AxiosError } from "axios";

type ListTypes = {
    list: string;
    id: string;
    onDelete: (id: string) => void;
};

const List = ({ list, id, onDelete }: ListTypes) => {

    const navigate = useNavigate();
    const handleEdit = (list: string) => {
        navigate("/list", { state: { list } });
    };
    const handleDelete = async () => {
        try {
            await axios.delete(`/api/lists/${id}`);
            onDelete(id);
        } catch (error) {
            if (error instanceof AxiosError) {
                console.error(error.response?.data);
            } else {
                console.error(error);
            }
        }
    };

    return (
        <>
            <Card className="list-card">
                <Card.Body>
                    <Card.Title>{list}</Card.Title>
                    <Card.Text>
                        Some quick example text to build on the card title and make up the
                        bulk of the card's content.
                    </Card.Text>
                    <div className="list-button-container">
                        <Button variant="outline-primary" onClick={() => handleEdit(list)}>Edit</Button>
                        <Button variant="outline-danger" onClick={handleDelete}>Delete</Button>
                    </div>
                </Card.Body>
            </Card>
        </>
    );
};

export default List;
