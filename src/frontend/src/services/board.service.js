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

  updateBoard(board) {
    return axios.post(API_URL + 'admin/update', {
      boardNo: board.boardNo,
      boardTitle: board.boardTitle,
      boardSubTitle: board.boardSubTitle,
      boardContent: board.boardContent,
      boardType: board.boardType
    });
  }

  deleteBoard(board) {
    return axios.post(API_URL + 'admin/delete', {
      boardNo: board.boardNo
    });
  }
}

export default new BoardService();
