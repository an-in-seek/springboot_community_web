import axios from 'axios';
import authHeader from './auth-header';
import Constant from '../constant'

const API_URL = Constant.SERVER_IP+':8090/api/board/';

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

  createBoard(board) {
    return axios.post(API_URL + 'create', {
      boardNo: board.boardNo,
      boardTitle: board.boardTitle,
      boardSubTitle: board.boardSubTitle,
      boardType: board.boardType,
      boardContent: board.boardContent
    }, {
      headers: authHeader()
    });
  }

  updateBoard(board) {
    return axios.post(API_URL + 'update', {
      boardNo: board.boardNo,
      boardTitle: board.boardTitle,
      boardSubTitle: board.boardSubTitle,
      boardType: board.boardType,
      boardContent: board.boardContent
    }, {
      headers: authHeader()
    });
  }

  deleteBoard(board) {
    return axios.post(API_URL + 'delete', {
      boardNo: board.boardNo
    }, {
      headers: authHeader()
    });
  }
}

export default new BoardService();
