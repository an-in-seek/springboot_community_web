export default class Board {
    constructor(boardNo, username, boardTitle, boardSubTitle, boardContent, boardType, comments) {
      this.boardNo = boardNo;
      this.username = username;
      this.boardTitle = boardTitle;
      this.boardSubTitle = boardSubTitle;
      this.boardContent = boardContent;
      this.boardType = boardType;
      this.comments = comments;
    }
  }
  