import axios from 'axios'

export async function getUser(email) {
    const response =  await axios.get(`/petstore/user/read/${email}`);
    return response.data;
}

async function update(user, router){
    const response =  await axios.post('petstore/user/update', user);

    alert("Profile Updated");

    await router.go(0);
    return await response.data;
}

export async function updateUser(user, firstName, middleName, lastName, phone, router){
    if (!firstName){
        alert("Please enter your first name");
        await router.go(0);
        return user;
    }
    if (!lastName) {
        alert("Please enter your last name");
        await router.go(0);
        return user;
    }
    if (!phone) {
        alert("Please enter your phone number");
        await router.go(0);
        return user;
    }
    if (phone) {
        try {
            parseInt(phone);
            if (phone.length !== 10 || phone[0] !== '0') {
                alert("Invalid phone number");
                await router.go(0);
                return user;
            }
        } catch (e) {
            alert("Invalid phone number");
            await router.go(0);
            return user;
        }
    }

    const updated = user;
    updated.firstName = firstName;
    updated.middleName = middleName;
    updated.lastName = lastName;
    updated.phoneNumber = phone;

    await update(updated, router);
}

async function isInvalidAddress(street, suburb, city, postal){
    if (!street) {
        alert("Please enter your street address");
        return true;
    }

    if (!suburb) {
        alert("Please enter your suburb");
        return true;
    }

    if (!city) {
        alert("Please enter your city");
        return true;
    }

    if (!postal) {
        alert("Please enter your postal code");
        return true;
    }

    if (postal){
        try {
            parseInt(postal);
            if (postal.length !== 4) {
                alert("Invalid postal code");
                return true;
            }
        } catch (e) {
            alert("Invalid postal code");
            return true;
        }
    }
}

export async function addAddress (user, street, suburb, city, postal, complex, isShipping, isBilling, router){
    if(await isInvalidAddress(street, suburb, city, postal)) return;

    if (isShipping || isBilling) {
        const updated = user;

        if (isShipping) {
            updated.shippingAddress = user.shippingAddress || {};
            updated.shippingAddress.streetAddress = street;
            updated.shippingAddress.complexDetail = complex;
            updated.shippingAddress.suburb = suburb;
            updated.shippingAddress.city = city;
            updated.shippingAddress.postalCode = postal;
        }
        if (isBilling) {
            updated.billingAddress = user.billingAddress || {};
            updated.billingAddress.streetAddress = street;
            updated.billingAddress.complexDetail = complex;
            updated.billingAddress.suburb = suburb;
            updated.billingAddress.city = city;
            updated.billingAddress.postalCode = postal;
        }

        if (isShipping && isBilling) {
            updated.shippingAddress.type = "Both";
            updated.billingAddress.type = "Both";
            await update(updated, router);
        } else if (isShipping) {
            updated.shippingAddress.type = "Shipping";
            await update(updated, router);
        } else if (isBilling) {
            updated.billingAddress.type = "Billing";
            await update(updated, router);
        }
    } else {
        alert("Please select if this is a shipping address or a billing address");
    }
}

export async function updateShippingAddress(user, street, suburb, city, postal, complex, router) {
    if(await isInvalidAddress(street, suburb, city, postal)) return;

    const updated = user;

    updated.shippingAddress = user.shippingAddress || {};
    updated.shippingAddress.streetAddress = street;
    updated.shippingAddress.complexDetail = complex;
    updated.shippingAddress.suburb = suburb;
    updated.shippingAddress.city = city;
    updated.shippingAddress.postalCode = postal;

    if (updated.billingAddress) {
        if (
        updated.billingAddress.streetAddress === street &&
        updated.billingAddress.complexDetail === complex &&
        updated.billingAddress.suburb === suburb &&
        updated.billingAddress.city === city &&
        updated.billingAddress.postalCode === postal){
            updated.shippingAddress.type = "Both";
            updated.billingAddress.type = "Both";
        }
    } else {
        updated.shippingAddress.type = "Shipping";
    }

    await update(updated, router);
}

export async function updateBillingAddress(user, street, suburb, city, postal, complex, router) {
    if (await isInvalidAddress(street, suburb, city, postal)) return;

    const updated = user;

    updated.billingAddress = user.billingAddress || {};
    updated.billingAddress.streetAddress = street;
    updated.billingAddress.complexDetail = complex;
    updated.billingAddress.suburb = suburb;
    updated.billingAddress.city = city;
    updated.billingAddress.postalCode = postal;

    if (updated.shippingAddress) {
        if (
            updated.shippingAddress.streetAddress === street &&
            updated.shippingAddress.complexDetail === complex &&
            updated.shippingAddress.suburb === suburb &&
            updated.shippingAddress.city === city &&
            updated.shippingAddress.postalCode === postal
        ) {
            updated.billingAddress.type = "Both";
            updated.shippingAddress.type = "Both";
        } else {
            updated.billingAddress.type = "Billing";
        }
    } else {
        updated.billingAddress.type = "Billing";
    }

    await update(updated, router);
}

export async function removeShippingAddress(user, router) {
    const updated = user;

    updated.shippingAddress = {};

    if (updated.billingAddress && updated.billingAddress.type === "Both") {
        updated.billingAddress.type = "Billing";
    }

    await update(updated, router);
}

export async function removeBillingAddress(user, router) {
    const updated = user;

    updated.billingAddress = {};

    if (updated.shippingAddress && updated.shippingAddress.type === "Both") {
        updated.shippingAddress.type = "Shipping";
    }

    await update(updated, router);
}

export async function removeCard(user, router){
    const updated = user;

    updated.card = {}

    await update(updated, router);
}