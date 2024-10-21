import List from "./List.tsx";
import {ListType} from "../../types/List.ts"
import {Row} from "react-bootstrap";

type ListTableProps = {
    lists: ListType[];
    setHasChanged: (value: (prev: boolean) => boolean) => void;
}
const ListTable = ({lists, setHasChanged}: ListTableProps) => {

    const listItems = lists.map((list: ListType) => {
        return <List key={list.id} list={list} setHasChanged={setHasChanged} />
    })

    return (<>
        <Row>
            <div className="list-table-container mt-5">
                {listItems}
            </div>
        </Row>
    </>)
}
export default ListTable;