import List from "./List.tsx";
import { useState } from "react";

const listsArr = [
    { id: "1", name: "list 1" },
    { id: "2", name: "list 2" },
    { id: "3", name: "list 3" },
    { id: "4", name: "list 4" },
    { id: "5", name: "list 5" },
    { id: "6", name: "list 6" },
    { id: "7", name: "list 7" },
    { id: "8", name: "list 8" },
];

const ListTable = () => {
    const [lists, setLists] = useState(listsArr);

    const removeList = (id: string) => {
        setLists((prevLists) => prevLists.filter((list) => list.id !== id));
    };

    return (
        <>
            <div className="list-table-container mt-5">
                {lists.map((list) => (
                    <List key={list.id} list={list.name} id={list.id} onDelete={removeList} />
                ))}
            </div>
        </>
    );
};

export default ListTable;
