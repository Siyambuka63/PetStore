import axios from 'axios'

const BASE_URL = "/petstore/order"

class OrderService{
    getOrder(){
        return axios.get(`${BASE_URL}/getAll`)
    }
}

export default new OrderService()