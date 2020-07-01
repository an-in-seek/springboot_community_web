import axios from 'axios';
import Constant from '../constant'

const API_URL = Constant.SERVER_IP+'/api/auth/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  signinwithsns(snsuser) {
    return axios
      .post(API_URL + 'signinwithsns', {
        username: snsuser.username,
        password: snsuser.password,
        email: snsuser.email,
        user_nickname: snsuser.user_nickname,
        social_type: snsuser.social_type,
        principal: snsuser.principal
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }  

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    });
  }
}

export default new AuthService();
