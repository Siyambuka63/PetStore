import axiosInstance from "@/api/AxiosInstance";

export async function isEmailTaken(email){
    const response = await axiosInstance.get(`/user/email-exists/${encodeURIComponent(email)}`);
    return await response.data;
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
         alert("Email address is already taken");
         return;
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

    const role = {}
    role.description = "USER";

    user.roles = [ role ];

    console.log(user);

    const response = await axiosInstance.post(`/user/create`, user);

    if (response.status !== 200) {
        throw new Error(response.data || "Failed to create user");
    }

    return response.data;
}

export async function LogIn(router, email, password) {
    try {
        let response = await axiosInstance.post("/user/verify", {
            email,
            password
        });

        const token = response.data;

        if (!token || token === "Invalid user credentials") {
            alert("Login failed. Invalid credentials.");
            return;
        }

        localStorage.setItem("token", token);
        localStorage.setItem("email", email);

        response = await axiosInstance.get(`/user/read/${email}`);
        const user = response.data;
        console.log(user);

        localStorage.setItem("roles", user.roles);

        router.push("/");
    } catch (error) {
        console.error("Login failed:", error);
        alert("Login failed. Please check your credentials and try again.");
    }
}