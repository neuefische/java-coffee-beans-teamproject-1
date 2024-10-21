export type Product = {
    name: string
}
export type ListType = {
    id: string,
    title: string,
    description: string,
    products: Product[]
}

export type newListType = {
    title: string,
    description: string,
    products: Product[]
}

