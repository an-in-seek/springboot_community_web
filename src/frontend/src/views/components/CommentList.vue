<template>
  <div>
    <div class="content" v-for="item in content" v-bind:key="item.id">
      <div class="content-userinfo">      
        <a>{{item.username}}</a>
        <div> 
         {{item.createtime}}
        </div>
      </div>
      <div class="content-body">      
          <p>{{item.comment}}</p>      
      </div>      
    </div>    
  </div>  

</template>

<script>
  export default {
    name: 'CommentList',
    mounted() {
      console.log(this.$parent.childData);
    },    
    data () {
      return {
        content: this.$parent.childData.comments
      }
    },
    methods: {
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
  .content-userinfo {
    position: relative;
    display: block;
    margin-bottom: -1px;
    padding: 10px 15px;
    font-size: 14px;
    background-color: #fff;
    min-height: 50px;
  }
</style>