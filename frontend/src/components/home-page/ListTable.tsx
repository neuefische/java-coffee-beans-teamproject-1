import List from "./List.tsx";
import {useEffect, useState} from "react";
import axios from "axios";


type ShoppingList = {id: string; name: string;}

const ListTable = () => {

    const [shoppingLists, setShoppingLists] = useState<ShoppingList[]>([]);
    const [error, setError] = useState<string | null>(null);

    const lists = Array.isArray(shoppingLists)
        ? shoppingLists?.map((list: ShoppingList) => {
            return <List key={list.id} list={list} />
        })
        : null;

    useEffect(()=>{
        const fetchShoppingLists = async () => {
            try{
                const response = await axios.get<ShoppingList[]>('/api/lists');
                setShoppingLists(response.data);
            } catch (error) {
                setError("failed to fetch shopping lists");
               console.log(error);
            }
        }

        fetchShoppingLists();
    },[])

    return (<>
        <div className="list-table-container mt-5">
            {error && <p>{error}</p>}
            {lists}
        </div>
    </>)
}
export default ListTable;