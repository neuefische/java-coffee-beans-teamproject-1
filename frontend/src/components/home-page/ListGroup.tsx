import {Button, Container} from "react-bootstrap";
import ListTable from "./ListTable.tsx";
import ListModal from "./ListModal.tsx";
import {useState} from "react";

const ListGroup = () => {
    const [isModalOpen, setModalOpen] = useState<boolean>(false);

    return (<>
        <Container>
            <div className="list-group-header">
                <h2> Your Shopping Lists </h2>
                <Button onClick={() => setModalOpen(prev => !prev)}>Add new</Button>
            </div>
            <ListTable/>
        </Container>

        <ListModal
            show={isModalOpen}
            handleClose={() => setModalOpen(false)}
        />
    </>)
}
export default ListGroup;