import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8090/api/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  getUserBoard() {
    return axios.get(API_URL + 'board/user', { headers: authHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_URL + 'board/mod', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'board/admin', { headers: authHeader() });
  }
  
  getAdminBoardList() {
    return axios.get(API_URL + 'board/admin/list', { headers: authHeader() });
  }
}

export default new UserService();
