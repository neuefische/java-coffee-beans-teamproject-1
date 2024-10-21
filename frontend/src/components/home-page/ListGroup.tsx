import {Button, Container} from "react-bootstrap";
import ListTable from "./ListTable.tsx";
import ListModal from "./ListModal.tsx";
import {useEffect, useState} from "react";
import {ListType} from "../../types/List.ts";
import axios, {AxiosError} from "axios";

const ListGroup = () => {
    const [isModalOpen, setModalOpen] = useState<boolean>(false);
    const [lists, setLists] = useState<ListType[]>([]);
    const [hasChanged, setHasChanged] = useState<boolean>(false);

    const getAllLists = () => {
        axios.get("/api/lists")
            .then(response => {
                setLists(response.data);
            })
            .catch((error: AxiosError) => {
                console.log(error)
            });
    }

    useEffect(() => {
        getAllLists();
    }, [hasChanged]);


    return (<>
        <Container>
            <div className="list-group-header">
                <h2> Your Shopping Lists </h2>
                <Button onClick={() => setModalOpen(prev => !prev)}>Add new</Button>
            </div>
            <ListTable lists={lists} setHasChanged={setHasChanged}/>
        </Container>

        <ListModal
            show={isModalOpen}
            setHasChanged={setHasChanged}
            handleClose={() => setModalOpen(false)}
        />
    </>)
}
export default ListGroup;