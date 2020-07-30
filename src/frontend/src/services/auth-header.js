export default function authHeader(isMultipart) {
  let result = {};
  let user = JSON.parse(localStorage.getItem('user'));
  if (user && user.accessToken) {
    if(isMultipart){
      result = { 
        'Authorization': 'Bearer ' + user.accessToken,
        'Content-Type': 'multipart/form-data'
      };  
    }else{
      result = { 
        'Authorization': 'Bearer ' + user.accessToken 
      };
    }
  } else {
    if(isMultipart){
      result = {'Content-Type': 'multipart/form-data'};
    } else {
      result = {};
    }
  }
  // return { 'x-access-token': user.accessToken };       // for Node.js Express back-end
  return result;
}
