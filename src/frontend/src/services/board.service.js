import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8090/api/board/';

class BoardService {
  getUserBoardDetail(boardNo) {
    return axios.get(API_URL + 'user/detail', { headers: authHeader(), params: {boardNo} });
  }

  getModeratorBoardDetail(boardNo) {
    return axios.get(API_URL + 'mod/detail', { headers: authHeader(), params: {boardNo} });
  }

  getAdminBoardDetail(boardNo) {
    return axios.get(API_URL + 'admin/detail', { headers: authHeader(), params: {boardNo} });
  }
}

export default new BoardService();
