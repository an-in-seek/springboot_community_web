<template>
    <div class="container">
        <header class="jumbotron">
          <h2>{{content}}</h2>
        </header>
        <body>
            <div class="text-right"><b-button variant="success" @click="handleRegister">글쓰기</b-button></div>
            <div class="overflow-auto">
                <b-table 
                  id="user-table"
                  striped="striped" 
                  hover="hover" 
                  :fields="fields" 
                  :items="items" 
                  :busy="isBusy" 
                  :per-page="perPage"
                  :current-page="currentPage"
                  caption-top
                  @row-clicked="rowClick">
                  <template v-slot:table-caption>글 목록</template>
                  <template v-slot:table-busy>
                    <div class="text-center text-danger my-2">
                      <b-spinner class="align-middle"></b-spinner>
                      <strong>Loading...</strong>
                    </div>
                  </template>
                </b-table>
                <div class="mt-3">
                  <b-pagination
                    v-model="currentPage"
                    :total-rows="rows"
                    :per-page="perPage"
                    first-number
                    last-number
                    align="center"
                    aria-controls="user-table"
                  ></b-pagination>
                </div>
            </div>
        </body>
    </div>
</template>

<script>
import UserService from '../services/user.service';

export default {
  name: 'User',
  data() {
    return {
      content: '',
      isBusy: false,
      fields: [
        { key: 'boardNo', label: 'NO', sortable: true }, 
        { key: 'boardTitle', label: '제목', sortable: true }, 
        { key: 'boardSubTitle', label: '부제목', sortable: true }, 
        { key: 'boardType', label: '타입', sortable: true },
        { key: 'user.username', label: '작성자', sortable: true }
      ],
      items: [],
      perPage: 10,
      currentPage: 1
    };
  },
  mounted() {
    UserService.getUserBoard().then(
      response => {
        this.content = response.data.content;
        this.items = response.data.items;
      },
      error => {
        this.content =
          (error.response && error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  },
  methods: {
    rowClick(item) {
      this.$router.push({
        path: `/user/board/detail/${item.boardNo}`
      });
    },
    handleRegister(evt){
      evt.preventDefault();
      this.$router.push({
        path: `/user/board/detail`
      });
    }
  },
  computed: {
    rows() {
      return this.items.length
    }
  }
};
</script>
