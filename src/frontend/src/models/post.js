export default class Post {
    constructor(postNo, username, postTitle, postContent, postImage, like, dislike, createdDate, comments) {
      this.postNo = postNo;
      this.username = username;
      this.postTitle = postTitle;
      this.postContent = postContent;
      this.postImage = postImage;
      this.like = like;
      this.dislike = dislike;
      this.createdDate = createdDate;
      this.comments = comments;
    }
  }
  