import axios from 'axios';

const api = axios.create({
    baseURL: process.env.REACT_APP_APIURL

    // baseURL: 'http://localhost:8080'
});

export default api;