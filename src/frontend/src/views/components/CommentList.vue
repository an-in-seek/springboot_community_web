<template>
  <div>
    <div class="content-title">
      {{"댓글 "+ this.content.length}}
    </div>    
    <div class="content" v-for="item in content" v-bind:key="item.id">
      <div class="content-user">      
        <div class="content-userinfo">      
          <a>{{item.user.userNickname}}</a><br>
          <a>{{item.createdDate}}</a>
        </div>
        <div class="content-remove" v-if="item.iscancel">
          <b-button class="content-remove-btn" squared @click="handleRemove($event, item)">X</b-button>
        </div>
      </div>
      <div class="content-body">      
          <p>{{item.contents}}</p>      
      </div>      
    </div>    
  </div>  

</template>

<script>
  import CommentService from '../../services/comment.service';
  export default {
    name: 'CommentList',
    props: {
      comments: Array
    },    
    data () {
      return {
        content: this.comments.map(comment => {
          comment.iscancel = comment.user.username == this.$store.state.auth.user.username ? true : false;          

          //관리자는 true
          if (this.$store.state.auth.user.roles == 'ROLE_ADMIN')  {
              comment.iscancel = true;
          }          

          return comment;
        }),
        showremoveButton: true,
      }
    },
    methods: {
      handleRemove(event, item) {
          event.preventDefault()
          CommentService
              .deleteComment(item)
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
  .content {
    border: 1px solid #ddd;
  }
  .content-body{
    position: relative;
    display: block;
    padding: 10px 15px;
    margin-bottom: -1px;
    background-color: #fff;
    min-height: 80px;
    border-top: 1px solid #ddd;
  }
  .content-user {
    display: flex;
  }
  .content-userinfo {
    position: relative;
    display: block;
    margin-bottom: -1px;
    padding: 10px 15px;
    font-size: 14px;
    background-color: #fff;
    min-height: 50px;
    width: 100%;
  }
  .content-remove {
    width: max-content;
    background-color: #fff;
    text-align: right;
  } 
  .content-remove-btn {
    width: 20px;
    height: 25px;
    display: flex;
    margin-left: auto;
    align-items: center;
    justify-content: center;
  }
    .content-title {
    border: 1px solid #ddd;
    height: 40px;  
    padding: 10px 15px;
    background-color: #f2f2f2;
    font-size: 16px;
  }
</style>