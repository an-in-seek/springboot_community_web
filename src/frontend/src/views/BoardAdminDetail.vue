<template>
    <div class="col-md-12">
        <div class="card card-container">
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
                <b-form-group id="input-group-1" label="작성자:" label-for="username">
                    <b-form-input
                        id="username"
                        v-model="form.username"
                        required="required"></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-2" label="작성일:" label-for="createdDate">
                    <b-form-input
                        id="createdDate"
                        v-model="form.createdDate"
                        required="required"></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-3" label="제목:" label-for="boardTitle">
                    <b-form-input
                        id="boardTitle"
                        v-model="form.boardTitle"
                        required="required"
                        placeholder="제목을 입력하세요."></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-4" label="부제목:" label-for="boardSubTitle">
                    <b-form-input
                        id="boardSubTitle"
                        v-model="form.boardSubTitle"
                        required="required"
                        placeholder="부제목을 입력하세요."></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-5" label="타입:" label-for="boardType">
                    <b-form-select
                        id="boardType"
                        v-model="form.boardType"
                        :options="boardTypes"
                        required="required"></b-form-select>
                </b-form-group>

                <b-form-group id="input-group-6" label="내용:" label-for="boardContent">
                    <b-form-textarea
                        id="boardContent"
                        v-model="form.boardContent"
                        placeholder="내용을 입력하세요."
                        rows="6"
                        max-rows="6"></b-form-textarea>
                </b-form-group>

                <b-row>
                    <b-col lg="6" class="pb-1"><b-button block size="lg" type="submit" variant="primary">수정</b-button></b-col>
                    <b-col lg="6" class="pb-1"><b-button block size="lg" type="reset" variant="danger">삭제</b-button></b-col>
                </b-row>
            </b-form>
        </div>
    </div>
</template>

<script>
    import BoardService from '../services/board.service';

    export default {
        name: 'adminBoardDetail',
        data() {
            return {
                form: {
                    username: '',
                    boardTitle: '',
                    boardSubTitle: '',
                    boardType: null,
                    boardContent: '',
                    createdDate: ''
                },
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
            onSubmit(evt) {
                evt.preventDefault()
                alert(JSON.stringify(this.form))
            },
            onReset(evt) {
                evt.preventDefault()
                this.form.boardTitle = ''
                this.form.boardSubTitle = ''
                this.form.boardType = null
                this.form.boardContent = ''
                this.show = false
                this.$nextTick(() => {
                    this.show = true
                })
            }
        },
        mounted() {
            const boardNo = Number(this.$route.params.boardNo);
            BoardService
                .getAdminBoardDetail(boardNo)
                .then(response => {
                    this.form.username = response.data.user.username;
                    this.form.boardTitle = response.data.boardTitle;
                    this.form.boardSubTitle = response.data.boardSubTitle;
                    this.form.boardType = response.data.boardType;
                    this.form.boardContent = response.data.boardContent;
                    this.form.createdDate = this.$moment(response.data.createdDate).format('YYYY-MM-DD HH:MM:SS');
                }, error => {
                    this.form.boardContent = (error.response && error.response.data.message) || error.message || error.toString();
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