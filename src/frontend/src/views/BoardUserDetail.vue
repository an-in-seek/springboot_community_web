<template>
    <div>
        <div class="card card-container mt-4">
            <b-form v-if="showMainForm">
                <b-form-group id="boardNoGroup" :label="lblBoardNoGroup" label-for="boardNo">
                    <b-form-input
                        id="boardNo"
                        v-model="board.boardNo"
                        readonly
                        ></b-form-input>
                </b-form-group>

                <b-form-group id="usernameGroup" :label="lblUsernameGroup" label-for="username">
                    <b-form-input
                        id="username"
                        v-model="board.username"
                        readonly></b-form-input>
                </b-form-group>

                <b-form-group id="createdDateGroup" :label="lblCreatedDateGroup" label-for="createdDate">
                    <b-form-input
                        id="createdDate"
                        v-model="board.createdDate"
                        readonly></b-form-input>
                </b-form-group>

                <b-form-group id="boardTitleGroup" :label="lblBoardTitleGroup" label-for="boardTitle">
                    <b-form-input
                        id="boardTitle"
                        required="required"
                        v-model="board.boardTitle"
                        :placeholder="phBoardTitle"
                        :readonly="formReadonly"></b-form-input>
                </b-form-group>

                <b-form-group id="boardSubTitleGroup" :label="lblBoardSubTitleGroup" label-for="boardSubTitle">
                    <b-form-input
                        id="boardSubTitle"
                        required="required"
                        v-model="board.boardSubTitle"
                        :placeholder="phBoardSubTitle"
                        :readonly="formReadonly"></b-form-input>
                </b-form-group>

                <b-form-group id="boardTypeGroup" :label="lblBoardTypeGroup" label-for="boardType">
                    <b-form-select
                        id="boardType"
                        required="required"
                        v-model="board.boardType"
                        :options="boardTypes"
                        disabled></b-form-select>
                </b-form-group>

                <b-form-group id="boardContentGroup" :label="lblBoardContentGroup" label-for="boardContent">
                    <b-form-textarea
                        id="boardContent"
                        rows="6"
                        max-rows="6"
                        v-model="board.boardContent"
                        :placeholder="phBoardContent"
                        :readonly="formReadonly"></b-form-textarea>
                </b-form-group>

                <b-row>
                    <b-col lg="12" class="pb-1" v-if="showCreateButton">
                        <b-button block size="lg" variant="primary" @click="handleCreate">{{btnCreate}}</b-button>
                    </b-col>
                    <b-col lg="4" class="pb-1" v-if="showUpdateButton">
                        <b-button block size="lg" variant="primary" @click="handleUpdate">{{btnUpdate}}</b-button>
                    </b-col>
                    <b-col lg="4" class="pb-1" v-if="showDeleteButton">
                        <b-button block size="lg" variant="danger" @click="handleDelete">{{btnDelete}}</b-button>
                    </b-col>
                    <b-col lg="4" class="pb-1" v-if="showCommentButton">
                        <b-button block size="lg" variant="success" @click="handleComment">{{btnComment}}</b-button>
                    </b-col>
                </b-row>
                 <div v-if="showboardComment">
                    <CommentForm :board="this.board">
                    </CommentForm>
                    <CommentList :comments="this.board.comments">
                    </CommentList>
                 </div>
            </b-form>
        </div>
    </div>
</template>

<script>
    import Board from '../models/board';
    import BoardService from '../services/board.service';
    import CommentService from '../services/comment.service';
    import CommonUtil from '../util/common-util';
    export default {
        name: 'userBoardDetail',
        data() {
            return {
                // show 상태값
                showMainForm: true,
                showCreateButton: true,
                showUpdateButton: true,
                showDeleteButton: true,
                showCommentButton: true,
                formReadonly: true,
                showboardComment: false,

                // To Do: 추후 DB에서 다국어 값으로 가져오는 방법으로 변경 필요
                lblBoardNoGroup: '번호:',
                lblUsernameGroup: '작성자:',
                lblCreatedDateGroup: '작성일:',
                lblBoardTitleGroup: '제목:',
                lblBoardSubTitleGroup: '부제목:',
                lblBoardTypeGroup: '타입:',
                lblBoardContentGroup: '내용:',
                phBoardTitle: '제목을 입력하세요.',
                phBoardSubTitle: '부제목을 입력하세요.',
                phBoardContent: '내용을 입력하세요.',
                btnCreate: '등록',
                btnUpdate: '수정',
                btnDelete: '삭제',
                btnComment: '댓글',

                // 데이터 세팅
                board: new Board('', '', ''),

                // To Do: 추후 DB에서 코드 데이터로 가져오는 방법으로 변경 필요
                boardTypes: [
                    {
                        text: '자유게시판',
                        value: 'FREE'
                    }
                ] 
            };
        },
        mounted() {
            this.formReadonly = false;
            this.showCreateButton = false;
            this.showUpdateButton = false;
            this.showDeleteButton = false;
            this.showCommentButton = false;
            const storeUsername = this.$store.state.auth.user.username;
            const boardNo = Number(this.$route.params.boardNo);
            if(boardNo){
                // 수정
                BoardService.getUserBoardDetail(boardNo).then(response => {
                    this.board.boardNo = response.data.board.boardNo;
                    this.board.username = CommonUtil.isEmpty(response.data.board.user) ? '' : response.data.board.user.username;
                    this.showCommentButton = true;
                    if(storeUsername == this.board.username){
                        this.showUpdateButton = true;
                        this.showDeleteButton = true;
                    } else {
                        this.formReadonly = true;
                    }
                    this.board.createdDate = this.$moment(response.data.board.createdDate).format('YYYY-MM-DD HH:MM:SS');
                    this.board.boardTitle = response.data.board.boardTitle;
                    this.board.boardSubTitle = response.data.board.boardSubTitle;
                    this.board.boardType = response.data.board.boardType;
                    this.board.boardContent = response.data.board.boardContent;
                    
                }, error => {
                    this.board.boardContent = (error.response && error.response.data.message) || error.message || error.toString();
                });
                
                //코멘트 리스트
                CommentService.getComments(boardNo).then(response => {
                    this.board.comments = response.data.items.map(item => {
                      item.createdDate = this.$moment(item.createdDate).format('YYYY-MM-DD HH:MM:SS'); 
                      item.updatedDate = this.$moment(item.updatedDate).format('YYYY-MM-DD HH:MM:SS');
                      return item;
                    })
                }, error => {
                    console.log(error);
                });                
            } else {
                // 등록
                this.showCreateButton = true;
                this.showCommentButton = false,
                this.board.boardNo = null;
                this.board.username = storeUsername;
                this.board.createdDate = this.$moment(new Date()).format('YYYY-MM-DD HH:MM:SS');
                this.board.boardTitle = null;
                this.board.boardSubTitle = null;
                this.board.boardType = 'FREE';
                this.board.boardContent = null;
            }
        },
        methods: {
            // 등록
            handleCreate(evt) {
                evt.preventDefault()
                BoardService
                    .createBoard(this.board)
                    .then(response => {
                        alert(response.data.message);
                        this.$router.push({ path: '/user' });
                    }, error => {
                        this.board.boardContent = (error.response && error.response.data.message) || error.message || error.toString();
                    });
            },
            // 수정
            handleUpdate(evt) {
                evt.preventDefault()
                BoardService
                    .updateBoard(this.board)
                    .then(response => {
                        alert(response.data.message);
                        this.$router.push({ path: '/user' });
                    }, error => {
                        this.board.boardContent = (error.response && error.response.data.message) || error.message || error.toString();
                    });
            },
            // 삭제
            handleDelete(evt) {
                evt.preventDefault()
                BoardService
                    .deleteBoard(this.board)
                    .then(response => {
                        alert(response.data.message);
                        this.$router.push({ path: '/user' });
                    }, error => {
                        this.board.boardContent = (error.response && error.response.data.message) || error.message || error.toString();
                    });
            },
            // 댓글
            handleComment(evt){
                evt.preventDefault()
                this.showboardComment = true;          
            }
        }
    };
</script>

<style scoped>
label {
  display: block;
  margin-top: 5px;
}

.card-container.card {
  padding: 20px 20px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
</style>