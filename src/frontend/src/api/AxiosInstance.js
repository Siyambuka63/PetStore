import axios from "axios";

// export default(url='http://localhost:8080') => {
//     return axios.create({
//         baseURL: url,
//     })
// }

const axiosInstance = axios.create({
    baseURL: "http://localhost:8080", // change to your backend base URL
    headers: {
        "Content-Type": "application/json",
    },
});

export default axiosInstance;
