import axios from "axios";

export async function getUserWishlistItems(email) {
    const items = [];
    const response = await axios.get(`/petstore/wishlist/findbyContactEmail/${email}`);
    const data = await response.data;
    console.log(data);

    for (let item of data) {
        const response = await axios.get(`/petstore/product/read/${item.id.productId}`);
        const data = await response.data;
        items.push(data);
        console.log("item: " + item);
    }

    return items;
}

export async function addItemToWishlist(email, itemID) {
    await axios.post('petstore/wishlist/create', {
        id: {
            contactId: email,
            productId: itemID
        }
    });

    return await getUserWishlistItems(email);
}

export async function removeItemFromWishlist(email, itemID) {
    await axios.delete(`petstore/wishlist/delete/${email}/${itemID}`);

    return await getUserWishlistItems(email);
}