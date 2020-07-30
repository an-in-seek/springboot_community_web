import axios from 'axios';
import authHeader from './auth-header';
import Constant from '../constant'

const API_URL = Constant.SERVER_IP+'/api/post/';

class PostService {
  getPostDetail(id) {
    return axios.get(API_URL + 'detail', { headers: authHeader(), params: {id} });
  }

  createPost(post) {
    const formData = new FormData();
    formData.append('id', post.id);
    formData.append('postTitle', post.postTitle);
    for(let images of post.images){
      formData.append('images', images);
    }
    formData.append('postContent', post.postContent);
    return axios.post(API_URL + 'create', formData, {
      headers: authHeader(true)
    });
  }

  updatePost(post) {
    return axios.post(API_URL + 'update', {
      id: post.id,
      postTitle: post.postTitle,
      images: post.images,
      postContent: post.postContent
    }, {
      headers: authHeader()
    });
  }

  deletePost(post) {
    return axios.post(API_URL + 'delete', {
      id: post.id
    }, {
      headers: authHeader()
    });
  }
}

export default new PostService();
