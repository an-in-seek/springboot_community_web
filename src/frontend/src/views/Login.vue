<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <form name="form" @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="username">ID</label>
          <input
            v-model="user.username"
            v-validate="'required'"
            type="text"
            class="form-control"
            name="username"
          />
          <div
            v-if="errors.has('username')"
            class="alert alert-danger"
            role="alert"
          >Username is required!</div>
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            v-model="user.password"
            v-validate="'required'"
            type="password"
            class="form-control"
            name="password"
          />
          <div
            v-if="errors.has('password')"
            class="alert alert-danger"
            role="alert"
          >Password is required!</div>
        </div>
        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
            <span>로그인</span>
          </button>
        </div>
        <div class="form-group">
          <div v-if="message" class="alert alert-danger" role="alert">{{message}}</div>
        </div>
      </form>
      <form name="form" @submit.prevent="handleLoginwithGoogle">
        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <div>
            <div class="sns-icon-wrapper">
              <img class="sns-icon" src="https://upload.wikimedia.org/wikipedia/commons/5/53/Google_%22G%22_Logo.svg"/>
            </div>              
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
             <b class="sns-btn-text">Sign in with Google</b>
            </div>
          </button>
        </div>  
      </form>    
      <form name="form" @submit.prevent="handleLoginwithKaKao">
        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <div>
            <div class="sns-icon-wrapper">
              <img class="sns-icon" src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Kakao_logo.jpg/100px-Kakao_logo.jpg"/>
            </div>              
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
             <b class="sns-btn-text">Sign in with KaKao</b>
            </div>
          </button>
        </div>  
      </form>   
      <form name="form" @submit.prevent="handleLoginwithFacebook">
        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <div>
            <div class="sns-icon-wrapper">
              <img class="sns-icon" src="https://image.flaticon.com/icons/svg/174/174848.svg"/>
            </div>              
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
             <b class="sns-btn-text">Sign in with FaceBook</b>
            </div>
          </button>
        </div>  
      </form>                 
    </div>
  </div>
</template>

<script>
import User from '../models/user';

export default {
  name: 'Login',
  data() {
    return {
      user: new User('', ''),
      snsuser: new User('', ''),
      loading: false,
      message: ''
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  created() {
    if (this.loggedIn) {
      this.$router.push('/profile');
    }
  },
  methods: {
    handleLogin() {
      this.loading = true;
      this.$validator.validateAll().then(isValid => {
        if (!isValid) {
          this.loading = false;
          return;
        }

        if (this.user.username && this.user.password) {
          this.$store.dispatch('auth/login', this.user).then(
            () => {
              this.$router.push('/profile');
            },
            error => {
              this.loading = false;
              this.message =
                (error.response && error.response.data.message) ||
                error.message ||
                error.toString();
            }
          );
        }
      });
    },
    handleLoginwithGoogle() {
      this.$gAuth
        .signIn()
        .then(GoogleUser => {

          console.log(GoogleUser);

          this.snsuser.username = GoogleUser.getBasicProfile().getId();
          this.snsuser.password = GoogleUser.getBasicProfile().getId();
          this.snsuser.email = GoogleUser.getBasicProfile().getEmail();
          this.snsuser.social_type = 'google';
          this.snsuser.pincipal = GoogleUser.getAuthResponse().id_token;
          this.snsuser.user_nickname = GoogleUser.getBasicProfile().getGivenName() + GoogleUser.getBasicProfile().getFamilyName();
          console.log(GoogleUser.getAuthResponse().id_token);

          /* process
          1. 등록된 유저 확인
          2. 기 등록 유저인 경우 해당유저 정보로 그대로 로그인
          3. 등록되지 않은 유저의 경우 해당 정보(SNS)로 유저 등록 후 로그인
          */
          
          this.$store.dispatch('auth/signinwithsns', this.snsuser).then(
            () => {
              this.$router.push('/profile');
            },
            error => {
              this.loading = false;
              this.message =
                (error.response && error.response.data.message) ||
                error.message ||
                error.toString();
            }
          );     
             
        })
        .catch(error => {
          //on fail do something
          console.error(error);
        });     
    },
    handleLoginwithKaKao() {
      console.log('handleLoginwithKaKao');
    },
    handleLoginwithFacebook() {
      console.log('handleLoginwithFacebook');
    }            
  }
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}

.sns-icon-wrapper {
	position: absolute;
	margin-top: -7px;
	margin-left: -13px;
	width: 50px;
	height: 40px;
	border-radius: 2px;
	background-color: #fff;
}

.sns-icon {
	position: absolute;
	margin-top: 8px;
	margin-left: -12px;
	width: 24px;
	height: 24px;
}

.sns-btn-text {
	color: #fff;
  padding-left: 40px;
  padding-top: 10px;
  text-align: left;
	font-size: 16px;
	letter-spacing: 0.2px;
	font-family: "Roboto";
}

</style>