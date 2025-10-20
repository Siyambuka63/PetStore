import axiosInstance from "@/api/AxiosInstance";

export async function getCart(userID) {
    if (!userID) return null;
    try {
        const response = await axiosInstance.get(`/order/getCart/${userID}`);
        return response.data;
    } catch (error) {
        console.error("Failed to get cart items:", error);
        return null;
    }
}

export async function getCartedItems(userID) {
    const cart = await getCart(userID);
    if (!cart) return [];
    const res = await axiosInstance.get(`/order-item/getByOrderId/${cart.id}`);
    return await res.data;
}

export async function getCartItems(userID) {
    const data = await getCartedItems(userID);
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

export async function removeItem(userID, itemId) {
    const cart = await getCart(userID);
    if (!cart) return;
    await axiosInstance.delete(`/order-item/delete/${cart.id}/${itemId}`);
}

export async function addItem(userID, itemId, pricePerItem, quantity, router) {
    const cart = await getCart(userID);
    if (!cart) {
        window.location.href = "/login";
        return;
    }
    await axiosInstance.post("/order-item/create", {
        id: { orderId: cart.id, productId: itemId },
        pricePerItem,
        quantity,
        totalPrice: pricePerItem * quantity
    });
    router.go(0);
}

export async function updateQuantity(userID, itemId, quantity) {
    const cart = await getCart(userID);
    if (!cart) return;
    await axiosInstance.put(`/order-item/updateQuantity/${cart.id}/${itemId}`, { quantity });
}

export async function getProduct(id) {
    const res = await axiosInstance.get(`/product/read/${id}`);
    return res.data;
}

export async function makeOrder(userID, price, cartItems) {
    const cart = await getCart(userID);
    if (!cart) {
        alert("Cart not found.");
        return;
    }
    try {

        // Validate product stock
        for (const item of cartItems) {
            const product = await getProduct(item.product.id);
            if (item.quantity > product.stock) {
                alert(`Insufficient stock for ${product.productName}. Available: ${product.stock}`);
                return; // stop if stock is insufficient
            }
        }

        // Order creation
        cart.status = "Busy";
        cart.price = price;
        cart.orderDate = new Date().toISOString();
        cart.deliveryDate = new Date(Date.now() + 7 * 24 * 60 * 60 * 1000).toISOString();

        await axiosInstance.post("/order/update", cart);

        // Reduce stock for each ordered product
        for (const item of cartItems) {
            const newStock = item.product.stock - item.quantity;
            await axiosInstance.put(`/product/updateStock/${item.product.id}`, {
                stock: newStock
            });
        }

        alert(" Order placed successfully and stock updated!");

    } catch (error) {
        console.error("Error making order:", error);
        alert(" An error occurred while processing your order.");
    }
}

export async function isCarted(userID, productId) {
    try {
        const cart = await getCart(userID);
        if (!cart || !cart.id) return false;

        console.log(cart.id);
        console.log(productId);
        const res = await axiosInstance.get(`/order-item/read/${cart.id}/${productId}`);
        console.log(res.data);
        return res.data !== "";
    } catch (err) {
        return false;
    }
}