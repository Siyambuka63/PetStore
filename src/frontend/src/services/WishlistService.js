import axiosInstance from "@/api/AxiosInstance";

export async function getWishlist(email){
    try {
        const response = await axiosInstance.get(`/wishlist/findbyContactEmail/${email}`);
        return response.data;
    } catch (error) {
        console.error("Failed to get wishlist:", error);
        return null;
    }
}

export async function getUserWishlistItems(email) {
    const data = await getWishlist(email);

    if (!data) {
        return [];
    }

    const items = [];

    for (let item of data) {
        const response = await axiosInstance.get(`/product/read/${item.id.productId}`);
        const data = await response.data;
        items.push(data);
        console.log("item: " + item);
    }

    return items;
}

export async function addItemToWishlist(email, itemID) {
    await axiosInstance.post('/wishlist/create', {
        id: {
            contactId: email,
            productId: itemID
        }
    });

    return await getWishlist(email);
}

export async function removeItemFromWishlist(email, itemID) {
    await axiosInstance.delete(`/wishlist/delete/${email}/${itemID}`);

    return await getUserWishlistItems(email);
}

export async function removeWishlistItem(email, itemID) {
    await axiosInstance.delete(`/wishlist/delete/${email}/${itemID}`);

    return await getWishlist(email);
}

export async function isWishlisted(userID, productId) {
    try {
        const wishlist = await getWishlist(userID);
        if (!wishlist ) return false;

        const res = await axiosInstance.get(`/wishlist/read/${userID}/${productId}`);
        console.log(res.data);
        return res.data !== null;
    } catch (err) {
        return false;
    }
}