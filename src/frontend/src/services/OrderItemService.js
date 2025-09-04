import axios from 'axios'

const BASE_URL = "/petstore/order-item"

class OrderItemService{
    getOrderItem(){
        return axios.get(`${BASE_URL}/getAll`)
    }
}
export default new OrderItemService()