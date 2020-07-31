<template>
  <div>
    <div class="mt-4">
        <b-card-group deck>
            <b-card no-body style="max-width: 40rem;">
                <template v-slot:header>
                  <div style="float:left;">
                    <font-awesome-icon icon="user"/> {{post.user.username}}
                  </div>
                  <div style="float:right;">
                    <a href="#" class="card-link"><font-awesome-icon icon="ellipsis-h"/></a>
                  </div>
                </template>
                <b-carousel
                      v-model="slide"
                      :interval="3000"
                      :controls="post.images.length > 1 ? true : false"
                      :indicators="post.images.length > 1 ? true : false"
                      img-width="1024"
                      img-height="768"
                      @sliding-start="onSlideStart"
                      @sliding-end="onSlideEnd">
                  <div v-for="(image, index) in post.images" v-bind:key="index">
                    <b-carousel-slide>
                      <template v-slot:img>
                      <img
                        class="d-block img-fluid w-100"
                        width="1024"
                        height="768"
                        :src="image.postImageUrl"
                        alt="image slot">
                      </template>
                    </b-carousel-slide>
                  </div>
                </b-carousel>
                <b-card-body>
                  <b-card-title>{{post.postTitle}}</b-card-title>
                  <b-card-text>{{post.postContent}}</b-card-text>
                  <b-button-group>
                    <b-button variant="danger" @click="handleLikes"><font-awesome-icon icon="heart"/> {{post.likes}}</b-button>
                    <b-button variant="secondary" @click="handleDislikes"><font-awesome-icon icon="heart-broken"/> {{post.dislikes}}</b-button>
                    <b-button variant="success" @click="handleShowCommentList"><font-awesome-icon icon="comment"/> </b-button>
                    <b-button variant="primary" @click="handleShare"><font-awesome-icon icon="share-alt"/></b-button>
                  </b-button-group>
                  <div>
                      <small class="text-muted">{{this.$moment(post.createdDate).format('YYYY-MM-DD HH:MM:SS')}}</small>
                  </div>
                </b-card-body>
                <template v-slot:footer>
                  <b-form-group class="mb-0">
                    <b-input-group>
                        <b-form-input :placeholder="phComment"></b-form-input>
                        <b-input-group-append>
                             <b-button variant="info" @click="handleCreateComment">{{btnCreate}}</b-button>
                        </b-input-group-append>
                    </b-input-group>
                  </b-form-group>
                </template>
            </b-card>
        </b-card-group>
    </div>
  </div>
</template>

<script>
import Post from '../models/post';
export default {
  name: "Post",
  props: {
    post: new Post()
  },
  data() {
    return {
        // To Do: 추후 DB에서 다국어 값으로 가져오는 방법으로 변경 필요
        phComment: '댓글을 입력하세요.',
        btnCreate: '등록',
        // 데이터 세팅
        slide: 0,
        sliding: null
    };
  },
  methods: {
    handleLikes(evt) {
      evt.preventDefault()
      alert("좋아요!");
    }, 
    handleDislikes(evt) {
      evt.preventDefault()
      alert("싫어요!");
    }, 
    handleShowCommentList(evt){
      evt.preventDefault()
      alert("댓글목록!");
    },
     handleCreateComment(evt){
      evt.preventDefault()
      alert("댓글등록!");
    },
    handleShare(evt){
      evt.preventDefault()
      alert("공유하기!");
    },
    onSlideStart() {
        this.sliding = true
    },
    onSlideEnd() {
        this.sliding = false
    }
  }
};
</script> 