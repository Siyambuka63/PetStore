import axios from 'axios'

const BASE_URL = "/product"

class ProductService {
    getProduct() {
        return axios.get(`${BASE_URL}/getAll`)
    }

    addProduct(product) {
        return axios.post(`${BASE_URL}/create`, product);
    }

    updateProduct(product) {
        return axios.post(`${BASE_URL}/update`, product);
    }

    deleteProduct(id) {
        return axios.delete(`${BASE_URL}/delete/${id}`);
    }
}

export default new ProductService()