
export default class Post {
    constructor(postNo, username, postTitle, postContent, postImage, likes, dislikes, createdDate) {
      this.postNo = postNo;
      this.postTitle = postTitle;
      this.postContent = postContent;
      this.postImage = postImage;
      this.likes = likes;
      this.dislikes = dislikes;
      this.createdDate = createdDate;
      this.username = username;
    }
  }
