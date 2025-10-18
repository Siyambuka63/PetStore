import axiosInstance from "@/api/AxiosInstance";

export async function getCart(userID){
    if (userID) {
        const res = await axiosInstance.get(`/order/getCart/${userID}`);
        return await res.data;
    }
    return null;
}

export async function getCartItems(userId) {
    const cart = await getCart(userId);

    if (!cart) {
        return [];
    }

    const res = await axiosInstance().get(`/order-item/getByOrderId/${cart.id}`);

    const data = await res.data;
    const orderItems = Array.isArray(data) ? data : data.orderItems ?? [];

    const products = [];

    for (let item of orderItems) {
        const product = await getProduct(item.id.productId);
        products.push({
            product: product,
            quantity: item.quantity,
        });
    }
    return products;
}

export async function removeItem(userID, itemId){
    const cart = await getCart(userID);

    await  axiosInstance.delete(`/order-item/delete/${cart.id}/${itemId}`);
}

export async function addItem(userID, itemId, pricePerItem, quantity){
    const cart = await getCart(userID);
    console.log(cart);
    await axiosInstance.post("/order-item/create", {
        id: {
            orderId: cart.id,
            productId: itemId
        },
        pricePerItem: pricePerItem,
        quantity: quantity,
        totalPrice: pricePerItem * quantity
    });
}

export async function updateQuantity(userID, itemId, quantity){
    const cart = await getCart(userID);

    await axiosInstance.put(`/order-item/updateQuantity/${cart.id}/${itemId}`,{quantity:quantity});

}

export async function getProduct(id){
    const res = await axiosInstance.get(`/product/read/${id}`);
    return await res.data;
}

export async function makeOrder(userID, price){
    const cart = await getCart(userID);
    console.log(cart);
    cart.status = "Busy";
    cart.price = price;
    cart.orderDate =  new Date().toISOString();
    cart.deliveryDate = new Date(Date.now() + 7 * 24 * 60 * 60 * 1000).toISOString();

    await axiosInstance.post('/order/update', cart);
}