

export async function isValidEmail(email){
    const response = await fetch(`http://localhost:8080/petstore/contact/findByEmail/${email}`);
    const contact = await response.json();
    return !contact;
}

export async function SignUp(firstName, middleName, lastName, email, phone, password, confirmPassword, auth, router){
    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        return;
    }

    if (password.length < 8){
        alert("Password length is too short");
        return;
    }

    // if (await isValidEmail(email)) {
    //     alert("Email address is taken");
    //     return;
    // }

    try {
        parseInt(phone);
        if (phone.length !== 10 || phone[0] !== '0') alert("Invalid billing postal code");
    } catch (e) {
        alert("Invalid billing postal code");
        return;
    }

    const user = {};
    user.firstName = firstName;
    user.middleName = middleName;
    user.lastName = lastName;
    user.password = password;
    user.contact = {}
    user.contact.phone = phone;
    user.contact.email = email;

    const response = await fetch(`http://localhost:8080/petstore/user/create`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user)
    });

    await router.push("/login");
    const newUser = await response.json();
    console.log(newUser);
    auth.userID = newUser.userID;
}

export async function LogIn(auth, router, email, password){
    const response = await fetch(`http://localhost:8080/petstore/user/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            contact: { email: email },
            password: password
        })
    });

    const signedUser = await response.json();

    if (signedUser){
        auth.userID = signedUser.userID;
        router.push("/")
    } else {
        alert("Log in failed. Invalid credentials.");
    }
}