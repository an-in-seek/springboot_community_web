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
                        v-model="board.boardTitle"
                        required="required"
                        :placeholder="phBoardTitle"></b-form-input>
                </b-form-group>

                <b-form-group id="boardSubTitleGroup" :label="lblBoardSubTitleGroup" label-for="boardSubTitle">
                    <b-form-input
                        id="boardSubTitle"
                        v-model="board.boardSubTitle"
                        required="required"
                        :placeholder="phBoardSubTitle"></b-form-input>
                </b-form-group>

                <b-form-group id="boardTypeGroup" :label="lblBoardTypeGroup" label-for="boardType">
                    <b-form-select
                        id="boardType"
                        v-model="board.boardType"
                        :options="boardTypes"
                        required="required"></b-form-select>
                </b-form-group>

                <b-form-group id="boardContentGroup" :label="lblBoardContentGroup" label-for="boardContent">
                    <b-form-textarea
                        id="boardContent"
                        v-model="board.boardContent"
                        :placeholder="phBoardContent"
                        rows="6"
                        max-rows="6"></b-form-textarea>
                </b-form-group>

                <b-row>
                    <b-col lg="12" class="pb-1" v-if="showCreateButton">
                        <b-button block size="lg" type="submit" variant="primary" @click="handleCreate">{{btnCreate}}</b-button>
                    </b-col>
                    <b-col lg="4" class="pb-1" v-if="showUpdateButton">
                        <b-button block size="lg" type="submit" variant="primary" @click="handleUpdate">{{btnUpdate}}</b-button>
                    </b-col>
                    <b-col lg="4" class="pb-1" v-if="showDeleteButton">
                        <b-button block size="lg" type="reset" variant="danger" @click="handleDelete">{{btnDelete}}</b-button>
                    </b-col>
                    <b-col lg="4" class="pb-1" v-if="showCommentButton">
                        <b-button block size="lg" variant="success" @click="handleComment">{{btnComment}}</b-button>
                    </b-col>
                </b-row>
            </b-form>
        </div>
    </div>
</template>

<script>
    import Board from '../models/board';
    import BoardService from '../services/board.service';
    import CommonUtil from '../util/common-util';

    export default {
        name: 'adminBoardDetail',
        data() {
            return {
                // show 상태값
                showMainForm: true,
                showCreateButton: true,
                showUpdateButton: true,
                showDeleteButton: true,
                showCommentButton: true,

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
                        text: '타입을 선택해주세요.',
                        value: null
                    },
                    {
                        text: '공지사항',
                        value: 'NOTICE'
                    },
                    {
                        text: '자유게시판',
                        value: 'FREE'
                    }
                ]
            };
        },
        mounted() {
            this.showCreateButton = false;
            this.showUpdateButton = false;
            this.showDeleteButton = false;
            this.showCommentButton = false;
            const storeUsername = this.$store.state.auth.user.username;
            const boardNo = Number(this.$route.params.boardNo);
            if(boardNo){
                // 수정
                this.showUpdateButton = true;
                this.showDeleteButton = true;
                this.showCommentButton = true;
                BoardService.getAdminBoardDetail(boardNo).then(response => {
                    this.board.boardNo = response.data.boardNo;
                    this.board.username = CommonUtil.isEmpty(response.data.user) ? '' : response.data.user.username;
                    this.board.createdDate = this.$moment(response.data.createdDate).format('YYYY-MM-DD HH:MM:SS');
                    this.board.boardTitle = response.data.boardTitle;
                    this.board.boardSubTitle = response.data.boardSubTitle;
                    this.board.boardType = response.data.boardType;
                    this.board.boardContent = response.data.boardContent;
                }, error => {
                    this.board.boardContent = (error.response && error.response.data.message) || error.message || error.toString();
                });
            } else {
                // 등록
                this.showCreateButton = true;
                this.board.boardNo = null;
                this.board.username = storeUsername;
                this.board.createdDate = this.$moment(new Date()).format('YYYY-MM-DD HH:MM:SS');
                this.board.boardTitle = null;
                this.board.boardSubTitle = null;
                this.board.boardType = null;
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
                        this.$router.push({ path: '/admin' });
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
                        this.$router.push({ path: '/admin' });
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
                        this.$router.push({ path: '/admin' });
                    }, error => {
                        this.board.boardContent = (error.response && error.response.data.message) || error.message || error.toString();
                    });
            },
            // 댓글
            handleComment(evt){
                evt.preventDefault()
                // 아래쪽에 댓글창 보이도록
                alert('댓글 버튼 클릭됨!!');
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