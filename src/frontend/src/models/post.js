
export default class Post {
    constructor(id, username, postTitle, postContent, images, likes, dislikes, createdDate) {
      this.id = id;
      this.postTitle = postTitle;
      this.postContent = postContent;
      this.images = images;
      this.likes = likes;
      this.dislikes = dislikes;
      this.createdDate = createdDate;
      this.username = username;
    }
  }
