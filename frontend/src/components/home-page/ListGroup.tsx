import {Button, Container} from "react-bootstrap";
import ListTable from "./ListTable.tsx";

const ListGroup = () => {
    return (<>
        <Container>
        <div className="list-group-header">
            <h2> Your Shopping Lists </h2>
            <Button>Add new</Button>
        </div>
        <ListTable/>
        </Container>
    </>)
}
export default ListGroup;