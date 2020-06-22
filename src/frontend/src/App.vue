<template>
    <div id="app">
        <nav class="navbar navbar-expand navbar-dark bg-dark">
            <a href="href" class="navbar-brand" @click.prevent>Community</a>
            <div class="navbar-nav mr-auto">
                <li class="nav-item">
                    <router-link to="/home" class="nav-link">
                        <font-awesome-icon icon="home"/> 메인
                    </router-link>
                </li>
                <li v-if="showAdminBoard" class="nav-item">
                    <router-link to="/admin" class="nav-link">
                        <font-awesome-icon icon="list"/> 관리자 게시판
                    </router-link>
                </li>
                <li v-if="showModeratorBoard" class="nav-item">
                    <router-link to="/mod" class="nav-link">
                        <font-awesome-icon icon="list"/> 운영자 게시판
                    </router-link>
                </li>
                <li class="nav-item">
                    <router-link v-if="currentUser" to="/user" class="nav-link">
                        <font-awesome-icon icon="user"/> 사용자 페이지
                    </router-link>
                </li>
            </div>

            <div v-if="!currentUser" class="navbar-nav ml-auto">
                <li class="nav-item">
                    <router-link to="/register" class="nav-link">
                        <font-awesome-icon icon="user-plus"/> 회원가입
                    </router-link>
                </li>
                <li class="nav-item">
                    <router-link to="/login" class="nav-link">
                        <font-awesome-icon icon="sign-in-alt"/> 로그인
                    </router-link>
                </li>
            </div>

            <div v-if="currentUser" class="navbar-nav ml-auto">
                <li class="nav-item">
                    <router-link to="/profile" class="nav-link">
                        <font-awesome-icon icon="user"/> {{ currentUser.username }}
                    </router-link>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="href" @click.prevent="logOut">
                        <font-awesome-icon icon="sign-out-alt"/> 로그아웃
                    </a>
                </li>
            </div>
        </nav>

        <div class="container">
            <router-view/>
        </div>
    </div>
</template>

<script>
    export default {
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            },
            showAdminBoard() {
                if (this.currentUser && this.currentUser.roles) {
                    return this
                        .currentUser
                        .roles
                        .includes('ROLE_ADMIN');
                }

                return false;
            },
            showModeratorBoard() {
                if (this.currentUser && this.currentUser.roles) {
                    return this
                        .currentUser
                        .roles
                        .includes('ROLE_MODERATOR');
                }

                return false;
            }
        },
        methods: {
            logOut() {
                this
                    .$store
                    .dispatch('auth/logout');
                this
                    .$router
                    .push('/login');
            }
        }
    };
</script>