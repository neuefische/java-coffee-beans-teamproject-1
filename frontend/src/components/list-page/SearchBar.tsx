import {Button, FormControl, InputGroup} from "react-bootstrap";
import {useState} from "react";

function SearchBar() {
    const [query, setQuery] = useState('');

    function handleAdd() {
    }

    return <>
        <InputGroup className="mb-4 mt-5">
            <FormControl type="text" placeholder="Enter product" aria-label="Product name" value={query}
                         onChange={(e) => setQuery(e.target.value)}/>
            <Button variant="primary" onClick={handleAdd}> Add </Button>
        </InputGroup>
    </>
}

export default SearchBar