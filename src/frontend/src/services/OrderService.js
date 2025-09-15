import axios from 'axios'

const BASE_URL = "/petstore/order"
const USER_URL = "/petstore/user"
class OrderService{
    getOrder(){
        return axios.get(`${BASE_URL}/getAll`)
    }
    getUser(){
        return axios.get(`${USER_URL}/getAll`)
    }
    getOrderByEmail(email) {
        return axios.get(`${BASE_URL}/findByContactEmail/${email}`)
    }
}

 export default new OrderService()
