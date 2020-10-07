<template>
    <div>
        <div class="mt-4">
            <post v-for="post in posts" :post="post" :key="posts.indexOf(post)"></post>
        </div>
        <infinite-loading @infinite="infiniteHandler" spinner="default"></infinite-loading>
        <div class="pt-4 mt-4">
            <hr/>
            <div class="text-center py-3">
                &copy; 2020 Copyright:
                <a href="#">
                    Community.com
                </a>
            </div>
        </div>
        <a v-on:click="createPost" href="#" class='btn-floating'>
            <div><font-awesome-icon icon="plus"/></div>
        </a>
    </div>
</template>
<script>
    import UserService from '../services/user.service';
    import Post from '../views/Post';
    export default {
        name: 'Vuestagram',
        components: {
            post: Post
        },
        data() {
            return {posts: [], limit: 0};
        },
        methods: {
            createPost(evt) {
                evt.preventDefault();
                this
                    .$router
                    .push({path: `/post/detail`});
            },
            infiniteHandler($state) {
                UserService
                    .getPostList(this.limit)
                    .then(response => {
                        if (response.data.posts.length) {
                            this.posts = this
                                .posts
                                .concat(response.data.posts);
                            $state.loaded(); // 데이터 로딩
                            this.limit += 1;
                            if (this.posts.length / 5 <= 0) {
                                $state.complete(); // 바인딩 완료
                            }
                        } else {
                            $state.complete(); // 바인딩 완료
                        }
                    }, error => {
                        this.content = (error.response && error.response.data.message) || error.message || error.toString();
                    });
            }
        }
    };
</script>
<style>
    .btn-floating {
        position: fixed;
        width: 60px;
        height: 60px;
        bottom: 30px;
        right: 30px;
        background-color: #0C9;
        color: #FFF;
        border-radius: 50px;
        text-align: center;
        box-shadow: 2px 2px 3px #999;
        z-index: 9999;
    }
    .btn-floating:hover {
        background: #42A5F5;
    }
    .btn-floating div {
        font-size: 1.8em;
        padding: 0;
        margin: 0.30em 0 0;
    }
</style>