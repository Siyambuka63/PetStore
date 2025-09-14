import axios from 'axios'

const BASE_URL = "/petstore/product"

class ProductService{
    getProduct(){
        return axios.get(`${BASE_URL}/getAll`)
    }
}

export default new ProductService()