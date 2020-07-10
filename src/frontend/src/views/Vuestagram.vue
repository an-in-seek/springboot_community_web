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
  </div>
</template>

<script>
  import posts from "../data/posts"; // mock 데이터 -> 추후 DB에서 데이터 가져오도록 수정
  import UserService from '../services/user.service';
  import Post from '../views/Post';
  export default {
        name: 'Vuestagram',
    data() {
      return {
        posts
      };
    },
    components: {
      post: Post
    },
    mounted() {
      UserService.getPublicContent().then(
        response => {
          this.content = response.data;
        },
        error => {
          this.content = (error.response && error.response.data.message) || error.message || error.toString();
        }
      );
    }
  };
</script>