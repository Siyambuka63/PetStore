export async function getUser(userID) {
    const response = await fetch(`/petstore/user/read/${userID}`);
    await console.log(response);
    return await response.json();
}

export async function getUserWishlistItems(userID) {
    const user = await getUser(userID);
    return user["wishlistItems"] || [];
}

export async function addItemToWishlist(userID, itemID) {
    const user = await getUser(userID);

    user.wishlistItems.push({ id: itemID });

    const response = await fetch(`/petstore/user/update`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user)
    });

    return await response.json();
}

export async function removeItemFromWishlist(userID, itemID) {
    let user = await getUser(userID);

    user.wishlistItems = user.wishlistItems.filter(item => item.id !== itemID);

    console.log(user.wishlistItems);

    const response = await fetch(`/petstore/user/update`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user)
    });

    return await response.json();
}

export async function getProduct (itemID){
    const response = await fetch(`/petstore/product/read/${itemID}`);
    return await response.json();
}

export async function removeWishlistUser (userID, itemID){
    let product = await getProduct(itemID);
    console.log(product);

    product.wishlistedUsers = product.wishlistedUsers.filter(user => user.id !== userID);

    console.log(product.wishlistedUsers);

    const response = await fetch(`/petstore/product/update`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(product)
    });

    await response.json();
}
