<template>
  <div>
    <div class="mt-4">
        <b-card style="max-width: 30rem;">
          <font-awesome-icon icon="user"/> user
        </b-card>
        <b-card-group deck>
            <b-card
            title="제목"
            img-src="https://picsum.photos/300/300/?image=41"
            img-alt="Image"
            img-top
            style="max-width: 30rem;">
                <template v-slot:header>
                    <small class="text-muted">Last updated 3 mins ago</small>
                </template>

                <b-card-text>
                    This is a wider card with supporting text below as a natural lead-in to additional content.
                    This content is a little bit longer.
                    This is a wider card with supporting text below as a natural lead-in to additional content.
                    This content is a little bit longer.
                </b-card-text>

                <b-button href="#" variant="danger"><font-awesome-icon icon="heart"/> 999</b-button> 
                <b-button href="#" variant="secondary"><font-awesome-icon icon="heart-broken"/> 999</b-button>
                <b-button href="#" variant="success"><font-awesome-icon icon="comment"/> 999</b-button>
                <b-button href="#" variant="primary"><font-awesome-icon icon="share-alt"/></b-button>
                <div style="float:right;"><b-button href="#" variant="outline-secondary"><font-awesome-icon icon="ellipsis-h"/></b-button></div>

                <template v-slot:footer>
                  <b-form-group label-for="filterInput" class="mb-0">
                    <b-input-group>
                        <b-form-input placeholder="댓글을 입력하세요."></b-form-input>
                        <b-input-group-append>
                            <b-button href="#" variant="info" @click="filter = ''">등록</b-button>
                        </b-input-group-append>
                    </b-input-group>
                  </b-form-group>
                </template>
            </b-card>
        </b-card-group>
    </div>
    <div class="pt-4 mt-4">
      <hr />
      <div class="text-center py-3">
          &copy; 2020 Copyright: <a href="#"> Community.com </a>
      </div>
    </div>
  </div>
</template>

<script>
import UserService from '../services/user.service';

export default {
  name: 'Home',
  data() {
    return {
      content: '',
      slide: 0,
      sliding: null
    };
  },
  mounted() {
    UserService.getPublicContent().then(
      response => {
        this.content = response.data;
      },
      error => {
        this.content =
          (error.response && error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  },
  methods : {
    onSlideStart() {
        this.sliding = true
    },
    onSlideEnd() {
        this.sliding = false
    }
  }
};
</script>
