<template>
    <div>
        <div class="card card-container mt-4">
            <b-form v-if="showMainForm">
                <b-form-group id="idGroup" :label="lblIdGroup" label-for="id">
                    <b-form-input
                        id="id"
                        v-model="post.id"
                        readonly
                        ></b-form-input>
                </b-form-group>

                <b-form-group id="usernameGroup" :label="lblUsernameGroup" label-for="username">
                    <b-form-input
                        id="username"
                        v-model="post.username"
                        readonly></b-form-input>
                </b-form-group>

                <b-form-group id="createdDateGroup" :label="lblCreatedDateGroup" label-for="createdDate">
                    <b-form-input
                        id="createdDate"
                        v-model="post.createdDate"
                        readonly></b-form-input>
                </b-form-group>

                <b-form-group id="postTitleGroup" :label="lblPostTitleGroup" label-for="postTitle">
                    <b-form-input
                        id="postTitle"
                        required
                        trim
                        v-model="post.postTitle"
                        :state="postTitleState"
                        :placeholder="phPostTitle"
                        :readonly="formReadonly">
                    </b-form-input>
                    <b-form-invalid-feedback id="postTitleFeedback"></b-form-invalid-feedback>
                </b-form-group>

                <b-form-group id="imagesGroup" :label="lblPostImageGroup" label-for="images">
                    <b-form-file 
                        id="images"
                        v-model="post.images"
                        :placeholder="phPostImage"
                        :readonly="formReadonly"
                        multiple="multiple"
                        :file-name-formatter="formatNames"></b-form-file>
                </b-form-group>

                <b-form-group id="postContentGroup" :label="lblPostContentGroup" label-for="postContent">
                    <b-form-textarea
                        id="postContent"
                        required
                        trim
                        rows="6"
                        max-rows="6"
                        v-model="post.postContent"
                        :state="postContentState"
                        :placeholder="phPostContent"
                        :readonly="formReadonly">
                    </b-form-textarea>
                    <b-form-invalid-feedback id="postContentFeedback"></b-form-invalid-feedback>
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
                 <div v-if="showpostComment">
                    <CommentForm :post="this.post" v-on:commentchanged="commentchanged">
                    </CommentForm>
                    <CommentList :comments="this.post.comments" v-on:commentchanged="commentchanged">
                    </CommentList>
                 </div>
            </b-form>
        </div>
    </div>
</template>

<script>
    import Post from '../models/post';
    import PostService from '../services/post.service';
    import CommentService from '../services/comment.service';
    import CommonUtil from '../util/common-util';
    export default {
        name: 'userPostDetail',
        computed: {
            postTitleState(){
                return this.post.postTitle ? true : false;
            },
            postContentState(){
                return this.post.postContent ? true : false;
            }
        },
        data() {
            return {
                // show 상태값
                showMainForm: true,
                showCreateButton: true,
                showUpdateButton: true,
                showDeleteButton: true,
                showCommentButton: true,
                formReadonly: true,
                showpostComment: false,

                // To Do: 추후 DB에서 다국어 값으로 가져오는 방법으로 변경 필요
                lblIdGroup: '번호:',
                lblUsernameGroup: '작성자:',
                lblCreatedDateGroup: '작성일:',
                lblPostTitleGroup: '제목:',
                lblPostImageGroup: '사진:',
                lblPostContentGroup: '내용:',
                phPostTitle: '제목을 입력하세요.',
                phPostImage: '사진을 선택하세요.',
                phPostContent: '내용을 입력하세요.',
                btnCreate: '등록',
                btnUpdate: '수정',
                btnDelete: '삭제',
                btnComment: '댓글',

                // 데이터 세팅
                post: new Post(),
            };
        },
        mounted() {
            this.formReadonly = false;
            this.showCreateButton = false;
            this.showUpdateButton = false;
            this.showDeleteButton = false;
            this.showCommentButton = false;
            const storeUsername = this.$store.state.auth.user.username;
            const id = Number(this.$route.params.id);
            if(id){
                // 수정
                PostService.getPostDetail(id).then(response => {
                    this.post.id = response.data.post.id;
                    this.post.username = CommonUtil.isEmpty(response.data.post.user) ? '' : response.data.post.user.username;
                    this.showCommentButton = true;
                    if(storeUsername == this.post.username){
                        this.showUpdateButton = true;
                        this.showDeleteButton = true;
                    } else {
                        this.formReadonly = true;
                    }
                    this.post.createdDate = this.$moment(response.data.post.createdDate).format('YYYY-MM-DD HH:MM:SS');
                    this.post.postTitle = response.data.post.postTitle;
                    //this.post.images = response.data.post.images[0].postImageUrl;
                    this.post.postContent = response.data.post.postContent;
                }, error => {
                    this.post.postContent = (error.response && error.response.data.message) || error.message || error.toString();
                });
                
                //코멘트 리스트
                CommentService.getComments(id).then(response => {
                    this.post.comments = response.data.items.map(item => {
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
                this.post.id = null;
                this.post.postTitle = null;
                this.post.images = [];
                this.post.postContent = null;
                this.post.username = storeUsername;
                this.post.createdDate = this.$moment(new Date()).format('YYYY-MM-DD HH:MM:SS');
            }
        },
        methods: {
            // 등록
            handleCreate(evt) {
                evt.preventDefault();
                if(!this.post.postTitle){
                    CommonUtil.showToast(this, "danger", this.phPostTitle);
                    return false;
                }
                if(!this.post.postContent){
                    CommonUtil.showToast(this, "danger", this.phPostContent);
                    return false;
                }
                PostService
                    .createPost(this.post)
                    .then(response => {
                        console.log(response.data.message);
                        this.$router.push({ path: '/vuestagram' });
                    }, error => {
                        this.post.postContent = (error.response && error.response.data.message) || error.message || error.toString();
                    });
            },
            // 수정
            handleUpdate(evt) {
                evt.preventDefault()
                PostService
                    .updatePost(this.post)
                    .then(response => {
                        console.log(response.data.message);
                        this.$router.push({ path: '/vuestagram' });
                    }, error => {
                        this.post.postContent = (error.response && error.response.data.message) || error.message || error.toString();
                    });
            },
            // 삭제
            handleDelete(evt) {
                evt.preventDefault()
                PostService
                    .deletePost(this.post)
                    .then(response => {
                        console.log(response.data.message);
                        this.$router.push({ path: '/vuestagram' });
                    }, error => {
                        this.post.postContent = (error.response && error.response.data.message) || error.message || error.toString();
                    });
            },
            // 댓글
            handleComment(evt){
                evt.preventDefault()
                this.showpostComment = this.showpostComment == true ? false : true;
            },
            //
            commentchanged(){
                this.showpostComment = false;
                const id = Number(this.$route.params.id);
                CommentService.getComments(id).then(response => {
                    this.post.comments = response.data.items.map(item => {
                      item.createdDate = this.$moment(item.createdDate).format('YYYY-MM-DD HH:MM:SS'); 
                      item.updatedDate = this.$moment(item.updatedDate).format('YYYY-MM-DD HH:MM:SS');
                      return item;
                    })
                    this.showpostComment = true;
                }, error => {
                    console.log(error);
                });   
            },
            // 파일 이름 포맷
            formatNames(files){
                return files.length === 1 ? files[0].name : `${files.length} files selected`;
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