import { useLocation } from "react-router-dom";
import SearchBar from "./SearchBar.tsx";
import ProductsTable from "./ProductsTable.tsx";
import { Container } from "react-bootstrap";
import { useState, useEffect } from "react";
import axios from "axios";

const ListPage = () => {
    const location = useLocation();
    const { list } = location.state || {};
    const [products, setProducts] = useState<{ name: string; quantity: number }[]>([]);
    const [title] = useState(list.title || "");
    const [description] = useState(list.description || "");

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await axios.get(`/api/lists/${list.id}`);
                setProducts(response.data.products);
            } catch (error) {
                console.error("Some error occurred: ", error);
            }
        };

        fetchProducts();
    }, [list.id]);

    const addProduct = (product: { name: string; quantity: number }) => {
        const updatedProducts = [...products, product];

        const updatedList = {
            ...list,
            products: updatedProducts,
        };

        axios.put(`/api/lists/${list.id}`, updatedList)
            .then((response) => {
                setProducts(response.data.products);
            })
            .catch((error) => {
                console.error("Some error occurred: ", error);
            });
    };

    const updateProductQuantity = (name: string, quantity: number) => {
        const updatedProducts = products.map((product) =>
            product.name === name ? { ...product, quantity: product.quantity + quantity } : product
        );

        const updatedList = {
            ...list,
            products: updatedProducts,
        };

        axios.put(`/api/lists/${list.id}`, updatedList)
            .then((response) => {
                setProducts(response.data.products);
            })
            .catch((error) => {
                console.error("Some error occurred: ", error);
            });
    };

    const removeProduct = (name: string) => {
        const updatedProducts = products.filter((product) => product.name !== name);

        const updatedList = {
            ...list,
            products: updatedProducts,
        };

        axios.put(`/api/lists/${list.id}`, updatedList)
            .then((response) => {
                setProducts(response.data.products);
            })
            .catch((error) => {
                console.error("Some error occurred: ", error);
            });
    };

    return (
        <Container>
            <div className="list-group-header list-group-header__description ">
                <h2>{title}</h2>
                <p>{description}</p>
            </div>
            <SearchBar addProduct={addProduct} />
            <ProductsTable
                products={products}
                updateProductQuantity={updateProductQuantity}
                removeProduct={removeProduct}
            />
        </Container>
    );
};

export default ListPage;
