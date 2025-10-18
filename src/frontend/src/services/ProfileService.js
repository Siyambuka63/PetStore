import axiosInstance from "@/api/AxiosInstance";

export async function getUser(email) {
    const response =  await axiosInstance.get(`/petstore/user/read/${email}`);
    return response.data;
}

async function update(user, router, message){
    const response =  await axiosInstance.post('petstore/user/update', user);

    alert(message);

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

    await update(updated, router, "Profile Updated");
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
            await update(updated, router, "Address added");
        } else if (isShipping) {
            updated.shippingAddress.type = "Shipping";
            await update(updated, router, "Shipping address added");
        } else if (isBilling) {
            updated.billingAddress.type = "Billing";
            await update(updated, router, "Billing address added");
        }
    } else {
        alert("Please select if this is a shipping address and/or a billing address");
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

    await update(updated, router, "Shipping address updated");
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

    await update(updated, router, "Billing address updated");
}

export async function removeShippingAddress(user, router) {
    const updated = user;

    updated.shippingAddress = null;

    if (updated.billingAddress && updated.billingAddress.type === "Both") {
        updated.billingAddress.type = "Billing";
    }

    await update(updated, router, "Shipping address removed");
    await axiosInstance.delete('petstore/address/delete/'+ user.shippingAddress.addressID);
}

export async function removeBillingAddress(user, router) {
    const updated = user;

    updated.billingAddress = null;

    if (updated.shippingAddress && updated.shippingAddress.type === "Both") {
        updated.shippingAddress.type = "Shipping";
    }

    await update(updated, router, "Billing address removed");
    await axiosInstance.delete('petstore/address/delete/'+ user.billingAddress.addressID);
}

export async function addCard(user, brand, holder, number, expiry, cvv, router) {
    if (!holder) {
        alert("Please enter card holder");
        return;
    }

    if (!expiry){
        alert("Please enter expiration date");
        return;
    }

    try {
        parseInt(number);
    } catch (e) {
        alert("Invalid card number. Card number must only have digits");
        return;
    }

    if (brand === "MasterCard") {
        const range2 = parseInt(number.substring(0, 2));
        const range4 = parseInt(number.substring(0, 4));

        if (number.length !== 16) {
            alert("Invalid Master card number. Card number must be 16 digits long");
            return;
        }

        if (
            !((range2 >= 51 && range2 <= 55) || (range4 >= 2221 && range4 <= 2720))
        ) {
            alert("Invalid MasterCard number");
            return;
        }
    }

    if (brand === "Visa") {
        if (![13, 19].includes(number.length)) {
            alert("Invalid Visa number. Card number must be 13, 16, or 19 digits long");
            return;
        }

        if (!number.startsWith("4")) {
            alert("Invalid Visa number");
            return;
        }
    }

    try {
        parseInt(cvv);
        if (![3,4].includes(cvv.length)) {
            alert("Invalid CVV number. CVV must be 3 or 4 digits long");
            return;
        }
    } catch (e) {
        alert("Invalid CVV number. CVV must be 3 or 4 digits long");
        return;
    }

    const updated = user;

    updated.card = user.card || {};
    updated.card.lastFourDigits = number.substr(-4);
    updated.card.brand = brand;

    await update(updated, router, "Card added");
}

export async function removeCard(user, router){
    const updated = user;

    updated.card = null;

    await update(updated, router, "Card removed");
    await axiosInstance.delete('petstore/card/delete/'+ user.card.id);
}

export async function resetPassword(user, passwordOld, password, passwordConfirmation, router){
    if (user.password !== passwordOld) {
        alert("Old password is incorrect");
        return;
    }

    if (password !== passwordConfirmation) {
        alert("Passwords do not match");
        return;
    }

    const updated = user;

    updated.password = password;

    await update(updated, router, "Password updated");
}