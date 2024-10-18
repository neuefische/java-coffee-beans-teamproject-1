import List from "./List.tsx";

const listsArr = ["list 1", "list 2", "list 3", "list 4", "list 5", "list 6", "list 7", "list 8"];


const ListTable = () => {
    const lists = listsArr.map((list: string) => {
        return <List key={list} list={list} />
    })

    return (<>
        <div className="list-table-container mt-5">
            {lists}
        </div>
    </>)
}
export default ListTable;