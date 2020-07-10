<template>
  <div class="content-body">  
    <div class="content-body-user-info">
      {{this.$store.state.auth.user.username}}
    </div>
    <div class="content-regist">
      <div class="content-regist-form">
        <b-form-group id="boardContentGroup">
            <b-form-textarea 
            v-if="isLogin"
            v-model="comment.contents">
            </b-form-textarea>          
            <b-form-textarea v-if="!isLogin"
            disabled="true"
            placeholder="로그인을 하시면 댓글을 등록할 수 있습니다.">
            </b-form-textarea>
        </b-form-group>           
      </div>       
      <div class="content-regist-btnform">
        <b-col lg="12" class="pb-1" >
            <b-button block size="lg" variant="primary" @click="handleCreateComment">등록</b-button>
        </b-col>         
      </div>
    </div>
  </div>
</template>

<script>
  import CommentService from '../../services/comment.service';

  export default {
    name: 'CommentForm',
    props: {
      board: Object
    },
    mounted() {
    this.comment.username = this.$store.state.auth.user.username;      
    this.comment.boardNo = this.board.boardNo;
    },
    data () {
      return {
        title: void 0,
        isLogin: this.$store.state.auth.status.loggedIn,
        comment: new Comment('', '', '')
      }
    },
    methods: {
      handleCreateComment(evt) {
          evt.preventDefault()
          CommentService
              .createComment(this.comment)
              .then(response => {
                  alert(response.data.message);                  
                  this.$emit('commentchanged');
              }, error => {
                  console.log(error)
              });
      }     
      
    }
  }
</script>

<style scoped>
  .content-body {
    padding-top: 20px;
  }
  .content-body-user-info {
    border: 1px solid #ddd;
    height: 40px;  
    padding: 10px 15px;
    background-color: #f2f2f2;
  }
  .content-regist{
    display: flex;
  }
  .content-regist-form {
    width: 90%;
  }  
  .content-regist-btnform {
    width: 10%;
    background-color: #f2f2f2;
    padding-top: 10px;
  }
</style>