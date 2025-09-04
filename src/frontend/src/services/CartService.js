export async function getCart(userID){
    const response = await fetch(`http://localhost:8080/petstore/order/getCart/${userID}`);
    return await response.json();
}

export async function getCartItems(userId) {
    const cart = await getCart(userId);

    if (!cart) {
        return [];
    }

    const response = await fetch(`http://localhost:8080/petstore/order-item/getByOrderId/${cart.id}`);
    const data = await response.json();
    const orderItems = Array.isArray(data) ? data : data.orderItems ?? [];

    const products = [];

    for (let item of orderItems) {
        const product = await getProduct(item.id.productId);
        products.push({
            product: product,
            quantity: item.quantity,
        });
    }

    console.log("cart:", JSON.stringify(cart, null, 2));
    console.log("orderItems:", JSON.stringify(orderItems, null, 2));
    console.log("products:", JSON.stringify(products, null, 2));
    return products;
}

export async function removeItem(userID, itemId){
    const cart = await getCart(userID);

    await fetch(`http://localhost:8080/petstore/order-item/delete/${cart.id}/${itemId}`);
}

export async function addItem(userID, itemId, pricePerItem, quantity){
    const cart = await getCart(userID);
    await fetch(`http://localhost:8080/petstore/order-item/create`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            id: {
                orderId: cart.id,
                productId: itemId
            },
            pricePerItem: pricePerItem,
            quantity: quantity,
            totalPrice: pricePerItem * quantity
        })
    });
}

export async function getProduct(id){
    const response = await fetch(`http://localhost:8080/petstore/product/read/${id}`);
    return await response.json()
}

export async function makeOrder(userID, price){
    const cart = await getCart(userID);
    cart.status = "Busy";
    cart.price = price;
    cart.orderDate = Date.now();
    cart.deliveryDate = Date.now() + 7;

     await fetch(`http://localhost:8080/petstore/order/create`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({cart})
    });
}