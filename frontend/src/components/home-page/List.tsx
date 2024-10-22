import { Button, Card } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import axios, { AxiosError } from "axios";
import {ListType} from "../../types/List.ts";

type ListTypes = {
    list: ListType;
    setHasChanged: (value: (prev: boolean) => boolean) => void;
};

const List = ({ list, setHasChanged }: ListTypes) => {
    const navigate = useNavigate();

    const handleEdit = (list: ListType) => {
        navigate(`/list/${list.id}`, { state: { list } });
    };

    const handleDelete = async () => {
        try {
            await axios.delete(`/api/lists/${list.id}`);
            setHasChanged((state: boolean) => !state);
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
                    <Card.Title>{list.title}</Card.Title>
                    <Card.Text>{list.description}</Card.Text>
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
