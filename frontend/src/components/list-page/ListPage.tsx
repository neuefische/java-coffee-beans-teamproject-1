import {useLocation} from "react-router-dom";
import SearchBar from "./SearchBar.tsx";
import ProductsTable from "./ProductsTable.tsx";
import {Container} from "react-bootstrap";

const ListPage = () => {
    const location = useLocation();
    const {list} = location.state || {};

    return (<>
        <Container>
            <div className="list-group-header">
                <h2> List {list} </h2>
            </div>
            <SearchBar/>
            <ProductsTable/>
        </Container>
    </>)
}

export default ListPage;