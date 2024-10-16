import {useLocation} from "react-router-dom";

const ListPage = () => {
    const location = useLocation();
    const { list } = location.state || {};

    return (<>
       List {list}
    </>)
}

export default ListPage;