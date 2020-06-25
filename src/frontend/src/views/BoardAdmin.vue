<template>
    <div class="container">

        <header class="jumbotron">
          <h1 class="display-4">{{content}}</h1>
          <p class="lead">게시판 관리</p>
          <hr class="my-4">
              <p>관리자는 모든 게시글을 관리할 수 있습니다.</p>
              <!--<p class="lead"><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>-->
        </header>

        <body>
            <div class="text-right"><b-button variant="success">글쓰기</b-button></div>
            <div class="overflow-auto">
                <b-table 
                  id="admin-table"
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
                    aria-controls="admin-table"
                  ></b-pagination>
                </div>
            </div>
        </body>

    </div>
</template>

<script>
import UserService from '../services/user.service';

export default {
  name: 'Admin',
  data() {
    return {
      content: '',
      isBusy: false,
      fields: [
        { key: 'boardNo', label: 'NO', sortable: true }, 
        { key: 'boardTitle', label: '제목', sortable: true }, 
        { key: 'boardSubTitle', label: '부제목', sortable: true }, 
        { key: 'boardType', label: '타입', sortable: true }
      ],
      items: [],
      perPage: 10,
      currentPage: 1
    };
  },
  mounted() {
    UserService.getAdminBoard().then(
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
        path: `/admin/board/detail/${item.boardNo}`
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
