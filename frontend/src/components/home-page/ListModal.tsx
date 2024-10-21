import {FormEvent, useState} from "react";
import {Button, Form, Modal} from "react-bootstrap";
import {newListType} from "../../types/List.ts"
import axios, {AxiosError} from "axios";

type ListModalProps = {
    show: boolean,
    handleClose: () => void,
    setHasChanged: (value: (prev: boolean) => boolean) => void;
}

const ListModal = ({show, handleClose, setHasChanged}: ListModalProps) => {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();
        const newList: newListType = {
            title: title,
            description: description,
            products: []
        }

        axios.post("/api/lists", newList)
            .then(() => {
                setTitle("");
                setDescription("");
                setHasChanged((state: boolean) => !state);
            })
            .catch((error: AxiosError) => console.log(error));

        handleClose();
    }

    return (
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Add a list</Modal.Title>
            </Modal.Header>
            <Form onSubmit={handleSubmit}>
                <Modal.Body>

                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>Title</Form.Label>
                        <Form.Control
                            type="text"
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                            autoFocus
                        />
                    </Form.Group>
                    <Form.Group
                        className="mb-3"
                        controlId="exampleForm.ControlTextarea1"
                    >
                        <Form.Label>Description</Form.Label>
                        <Form.Control
                            as="textarea"
                            rows={3}
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                        />
                    </Form.Group>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Cancel
                    </Button>
                    <Button variant="primary" type="submit">
                        Save
                    </Button>
                </Modal.Footer>
            </Form>

        </Modal>
    )

}

export default ListModal;