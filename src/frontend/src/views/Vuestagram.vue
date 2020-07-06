<template>
  <div>
    <div class="mt-4">
        <b-card-group deck>
            <b-card
            title="Title"
            img-src="https://picsum.photos/300/300/?image=41"
            img-alt="Image"
            img-top="img-top"
            style="max-width: 30rem;">
                <b-card-text>
                    This is a wider card with supporting text below as a natural lead-in to additional content.
                    This content is a little bit longer.
                </b-card-text>
                <template v-slot:footer>
                    <small class="text-muted">Last updated 3 mins ago</small>
                </template>
                <b-button href="#" variant="primary">Good</b-button> 
                <b-button href="#" variant="danger">Bad</b-button>
            </b-card>
        </b-card-group>

        <b-card-group deck>
            <b-card
            title="Title"
            img-src="https://picsum.photos/300/300/?image=41"
            img-alt="Image"
            img-top="img-top"
            style="max-width: 30rem;">
                <b-card-text>
                    This card has supporting text below as a natural lead-in to additional content.
                </b-card-text>
                <template v-slot:footer>
                    <small class="text-muted">Last updated 3 mins ago</small>
                </template>
                <b-button href="#" variant="primary">Good</b-button> 
                <b-button href="#" variant="danger">Bad</b-button>
            </b-card>
        </b-card-group>

        <b-card-group deck>
            <b-card
            title="Title"
            img-src="https://picsum.photos/300/300/?image=41"
            img-alt="Image"
            img-top="img-top"
            style="max-width: 30rem;">
                <b-card-text>
                    This is a wider card with supporting text below as a natural lead-in to additional content.
                    This card has even longer content than the first to show that equal height action.
                </b-card-text>
                <template v-slot:footer>
                    <small class="text-muted">Last updated 3 mins ago</small>
                </template>
                <b-button href="#" variant="primary">Good</b-button> 
                <b-button href="#" variant="danger">Bad</b-button>
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
