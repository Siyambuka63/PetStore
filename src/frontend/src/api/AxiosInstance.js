import axios from "axios";

const publicEndpoints = [
    "/product/getAll",
    "/product/read/",
    "/product/image/",
    "/user/email-exists/",
    "/user/login",
    "/user/create",
    "/user/verify"
];

const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/petstore", // backend base URL
    headers: {
        "Content-Type": "application/json",
    },
});

axiosInstance.interceptors.request.use(function (config) {
    const token = localStorage.getItem("token");

    const isPublic = publicEndpoints.some((endpoint) =>
        config.url.startsWith(endpoint)
    );

    if (token && !isPublic) {
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
        if (error.response && error.response.status === 401) {
            if (error.config.url.startsWith("/order/getCart") || error.config.url.startsWith("/wishlist/findbyContactEmail")) {
                return Promise.resolve({ data: null });
            } else {
                window.location.href = "/login";
            }
        }
        return Promise.reject(error);
    }
);

export default axiosInstance;
