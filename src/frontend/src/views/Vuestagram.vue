<template>
  <div>
    <div class="mt-4">
      <post v-for="post in posts" :post="post" :key="posts.indexOf(post)"></post>
    </div>
    <div class="pt-4 mt-4">
      <hr/>
      <div class="text-center py-3">
         &copy; 2020 Copyright: <a href="#"> Community.com </a>
      </div>
    </div>
    <a href='#' class='btn-floating'>
      <div><font-awesome-icon icon="plus"/></div>
    </a> 
  </div>
</template>

<script>
  // import posts from "../data/posts"; // mock 데이터 -> 추후 DB에서 데이터 가져오도록 수정
  import UserService from '../services/user.service';
  import Post from '../views/Post';

  export default {
    name: 'Vuestagram',
    components: { post: Post },
    data() {
      return {
        posts: []
      };
    },
    mounted() {
      UserService.getPostList().then(
        response => {
          this.content = response.data.content;
          this.posts = response.data.posts;
        },
        error => {
          this.content = (error.response && error.response.data.message) || error.message || error.toString();
        }
      );
    }
  };
</script>
<style>
.btn-floating {
  position:fixed;
	width:60px;
	height:60px;
	bottom:30px;
	right:30px;
	background-color:#0C9;
	color:#FFF;
	border-radius:50px;
	text-align:center;
	box-shadow: 2px 2px 3px #999;
  z-index: 9999;
}
.btn-floating:hover {
    background: #42A5F5;
}
.btn-floating div {
    font-size: 1.8em;
    padding: 0;
    margin: .30em 0 0 0;
}
</style>