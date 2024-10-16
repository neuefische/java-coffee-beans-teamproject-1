import {Button, FormControl, InputGroup} from "react-bootstrap";
import {useState} from "react";

function SearchBar() {
    const [query, setQuery] = useState('');

    function handleAdd() {
    }

    return <>
        <div>
            <InputGroup className="mb-3">
                <FormControl type="text" placeholder="Enter product" aria-label="Product name" value={query}
                             onChange={(e) => setQuery(e.target.value)}/>
                <Button variant="primary" onClick={handleAdd}> Add </Button>
            </InputGroup>
        </div>
    </>
}

export default SearchBar