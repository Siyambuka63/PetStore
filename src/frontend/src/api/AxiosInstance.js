import axios from "axios";

const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/petstore", // backend base URL
    headers: {
        "Content-Type": "application/json",
    },
});

axiosInstance.interceptors.request.use(function (config) {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers["Authorization"] = `Bearer ${token}`;
    }
        return config;
    }, function (error) {
        return Promise.reject(error);
    }
);

axiosInstance.interceptors.response.use(function (response) {
    return response;
    },
    function (error) {
        if (error.response.status === 401) {
            window.location.href = "/login";
        }
        return Promise.reject(error);
    }
);

export default axiosInstance;
