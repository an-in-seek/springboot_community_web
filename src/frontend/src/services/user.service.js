import axios from 'axios';
import authHeader from './auth-header';
import Constant from '../constant'

const API_URL = Constant.SERVER_IP + '/api/';

class UserService {
    getPublicContent() {
        return axios.get(API_URL + 'all');
    }

    getUserBoard() {
        return axios.get(API_URL + 'board/user', {headers: authHeader()});
    }

    getModeratorBoard() {
        return axios.get(API_URL + 'board/mod', {headers: authHeader()});
    }

    getAdminBoard() {
        return axios.get(API_URL + 'board/admin', {headers: authHeader()});
    }

    getPostList(limit) {
        return axios.get(API_URL + 'post/list', {
            params: {
                limit: limit
            },
            headers: authHeader()
        });
    }
}

export default new UserService();
