<template>
    <div class="col-md-12">
        <div class="card card-container">
            <b-form v-if="show">
                <b-form-group id="input-group-0" label="번호:" label-for="boardNo">
                    <b-form-input
                        id="boardNo"
                        v-model="board.boardNo"
                        required="required"></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-1" label="작성자:" label-for="username">
                    <b-form-input
                        id="username"
                        v-model="board.username"
                        required="required"></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-2" label="작성일:" label-for="createdDate">
                    <b-form-input
                        id="createdDate"
                        v-model="board.createdDate"
                        required="required"></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-3" label="제목:" label-for="boardTitle">
                    <b-form-input
                        id="boardTitle"
                        v-model="board.boardTitle"
                        required="required"
                        placeholder="제목을 입력하세요."></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-4" label="부제목:" label-for="boardSubTitle">
                    <b-form-input
                        id="boardSubTitle"
                        v-model="board.boardSubTitle"
                        required="required"
                        placeholder="부제목을 입력하세요."></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-5" label="타입:" label-for="boardType">
                    <b-form-select
                        id="boardType"
                        v-model="board.boardType"
                        :options="boardTypes"
                        required="required"></b-form-select>
                </b-form-group>

                <b-form-group id="input-group-6" label="내용:" label-for="boardContent">
                    <b-form-textarea
                        id="boardContent"
                        v-model="board.boardContent"
                        placeholder="내용을 입력하세요."
                        rows="6"
                        max-rows="6"></b-form-textarea>
                </b-form-group>

                <b-row>
                    <b-col lg="6" class="pb-1"><b-button block size="lg" type="submit" variant="primary" @click="handleUpdate">수정</b-button></b-col>
                    <b-col lg="6" class="pb-1"><b-button block size="lg" type="reset" variant="danger" @click="handleDelete">삭제</b-button></b-col>
                </b-row>
            </b-form>
        </div>
    </div>
</template>

<script>
    import Board from '../models/board';
    import BoardService from '../services/board.service';

    export default {
        name: 'adminBoardDetail',
        data() {
            return {
                board: new Board('', '', ''),
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
                ],
                show: true
            };
        },
        methods: {
            handleUpdate(evt) {
                evt.preventDefault()
                BoardService
                    .updateBoard(this.board)
                    .then(response => {
                        alert(response.data.message);
                    }, error => {
                        this.board.boardContent = (error.response && error.response.data.message) || error.message || error.toString();
                    });
            },
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
            }
        },
        mounted() {
            const boardNo = Number(this.$route.params.boardNo);
            BoardService
                .getAdminBoardDetail(boardNo)
                .then(response => {
                    this.board.boardNo = response.data.boardNo;
                    this.board.boardTitle = response.data.boardTitle;
                    this.board.boardSubTitle = response.data.boardSubTitle;
                    this.board.boardType = response.data.boardType;
                    this.board.boardContent = response.data.boardContent;
                    this.board.createdDate = this.$moment(response.data.createdDate).format('YYYY-MM-DD HH:MM:SS');
                    this.board.username = response.data.user.username;
                }, error => {
                    this.board.boardContent = (error.response && error.response.data.message) || error.message || error.toString();
                });
        }
    };
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
</style>