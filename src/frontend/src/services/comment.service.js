import axios from 'axios';
import authHeader from './auth-header';
import Constant from '../constant'

const API_URL = Constant.SERVER_IP+'/api/comment/';

class CommentService {
  
  getComments(boardNo) {  
    return axios.get(API_URL + 'commentList', { headers: authHeader(), params: {boardNo} });
  }

  createComment(comment) {
    return axios.post(API_URL + 'create', {      
      boardNo: comment.boardNo,
      username: comment.username,
      contents: comment.contents
    }, {
      headers: authHeader()
    });
  }

  updateComment(comment) {
    return axios.post(API_URL + 'update', {
      commentNo: comment.commentNo,
      boardNo: comment.boardNo,
      contents: comment.contents
    }, {
      headers: authHeader()
    });
  }

  deleteComment(comment) {
    console.log(comment)
    console.log(API_URL + 'delete')    
    return axios.post(API_URL + 'delete', {
      commentNo: comment.commentNo
    }, {
      headers: authHeader()
    });
  }
}

export default new CommentService();
