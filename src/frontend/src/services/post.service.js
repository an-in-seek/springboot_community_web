import axios from 'axios';
import authHeader from './auth-header';
import Constant from '../constant'

const API_URL = Constant.SERVER_IP+'/api/post/';

class PostService {
  getPostDetail(postNo) {
    return axios.get(API_URL + 'detail', { headers: authHeader(), params: {postNo} });
  }

  createPost(post) {
    return axios.post(API_URL + 'create', {
      postNo: post.postNo,
      postTitle: post.postTitle,
      postImage: post.postImage,
      postContent: post.postContent
    }, {
      headers: authHeader()
    });
  }

  updatePost(post) {
    return axios.post(API_URL + 'update', {
      postNo: post.postNo,
      postTitle: post.postTitle,
      postSubTitle: post.postSubTitle,
      postType: post.postType,
      postContent: post.postContent
    }, {
      headers: authHeader()
    });
  }

  deletePost(post) {
    return axios.post(API_URL + 'delete', {
      postNo: post.postNo
    }, {
      headers: authHeader()
    });
  }
}

export default new PostService();
