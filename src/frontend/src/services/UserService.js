export async function isEmailTaken(email){
    const response = await fetch(`http://localhost:8080/petstore/user/email-exists/${encodeURIComponent(email)}`);
    return await response.json();
}

export async function SignUp(firstName, middleName, lastName, email, phone, password, confirmPassword){
    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        return;
    }

    if (password.length < 8){
        alert("Password length is too short");
        return;
    }

     if (await isEmailTaken(email)) {
         throw new Error("Email address is already taken");
     }

    try {
        parseInt(phone);
        if (phone.length !== 10 || phone[0] !== '0') alert("Invalid number");
    } catch (e) {
        alert("Invalid billing postal code");
        return;
    }

    const user = {};
    user.firstName = firstName;
    user.middleName = middleName;
    user.lastName = lastName;
    user.password = password;
    user.phoneNumber = phone;
    user.email = email;

    const response = await fetch(`http://localhost:8080/petstore/user/create`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user)
    });
    if (!response.ok) {
        const errorMsg = await response.text();
        throw new Error(errorMsg || "Failed to create user");
    }

    return await response.json();

}

export async function LogIn(auth, router, email, password){
    const response = await fetch(`http://localhost:8080/petstore/user/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            email: email,
            password: password
        })
    });

    try {
        const signedUser = await response.json();
        auth.setUserEmail(signedUser.email);
        router.push("/");
    } catch (e) {
        alert("Log in failed. Invalid credentials.");
    }
}