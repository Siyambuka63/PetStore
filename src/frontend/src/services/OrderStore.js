import { defineStore } from 'pinia'
export const orderStore = defineStore('orders', {
    state: () => ({
        orderId: null
    }),
    actions: {
        setOrderId(id) {
            this.orderId = id;
        },
        getOrderId() {
            return this.orderId;
        }
    }
})