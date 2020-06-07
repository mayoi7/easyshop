<template>
  <div class="navbar">

    <breadcrumb class="breadcrumb-container" />

    <div class="right-menu">
      <div class="order icon-href" style="margin-right: 20px">
        <a href="javascript:;" @click="routeOrder">
          <svg t="1591519694902" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2647" width="20" height="20"><path d="M440.556 119.868h144c15.464 0 28-12.536 28-28s-12.536-28-28-28h-144c-15.464 0-28 12.536-28 28s12.536 28 28 28zM677.16 325.751h-344c-15.464 0-28 12.536-28 28s12.536 28 28 28h344c15.464 0 28-12.536 28-28s-12.536-28-28-28zM677.16 483.233h-344c-15.464 0-28 12.536-28 28s12.536 28 28 28h344c15.464 0 28-12.536 28-28s-12.536-28-28-28zM677.16 640.715h-344c-15.464 0-28 12.536-28 28s12.536 28 28 28h344c15.464 0 28-12.536 28-28 0-15.463-12.536-28-28-28z" p-id="2648"></path><path d="M807.126 64.223h-60.55v0.002l-0.075-0.002c-15.464 0-28 12.536-28 28s12.536 28 28 28l0.075-0.002v0.002h60.55c8.982 0 17.493 3.564 23.965 10.036 6.471 6.471 10.035 14.982 10.035 23.964v716c0 8.982-3.564 17.493-10.035 23.964s-14.982 10.036-23.965 10.036H218.025c-8.982 0-17.493-3.564-23.965-10.036s-10.035-14.982-10.035-23.964v-716c0-8.982 3.564-17.493 10.035-23.964 6.471-6.472 14.982-10.036 23.965-10.036h60.55v-0.002l0.091 0.002c15.464 0 28-12.536 28-28s-12.536-28-28-28l-0.091 0.002v-0.002h-60.55c-49.5 0-90 40.5-90 90v716c0 49.5 40.5 90 90 90h589.101c49.5 0 90-40.5 90-90v-716c0-49.5-40.5-90-90-90z" p-id="2649"></path></svg>
          我的订单
        </a>
      </div>
      <div class="cart icon-href" style="margin-right: 40px;">
        <a href="javascript:;" @click="routeCart">
          <svg t="1591062056275" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1365" width="20" height="20"><path d="M406.7 893.6m-57.4 0a57.4 57.4 0 1 0 114.8 0 57.4 57.4 0 1 0-114.8 0Z" fill="#242424" p-id="1366"></path><path d="M708.7 893.6m-57.4 0a57.4 57.4 0 1 0 114.8 0 57.4 57.4 0 1 0-114.8 0Z" fill="#242424" p-id="1367"></path><path d="M938.3 204.7c-2.3-0.4-4.6-0.7-7-0.9h-640L281 117c-7.3-28.8-33-50.3-63.9-51.2H70.6c-22.6 0-41 18.3-41 41 0 22.6 18.3 41 41 41H178c12.4 0.9 24.6 10.1 26.8 22l0.3 2.5 2.1 17.7 64.5 538.5c5.2 32.4 33.3 57.2 67.2 57.2h446.7c22.6 0 41-18.3 41-41 0-22.6-18.3-41-41-41H373.2c-11.1-1.7-21.6-9.9-24.3-20.3l-7.3-61.3 463.9-25.4c15.1-1 27.7-10.2 34-22.8l0.6-1.2 135.2-286.1c12.5-31.7 1.1-74.6-37-81.9z" fill="#242424" p-id="1368"></path></svg>
          购物车
        </a>
      </div>
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          {{name}}
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <router-link to="/">
            <el-dropdown-item>主页</el-dropdown-item>
          </router-link>
          <a target="_blank" href="https://github.com/mayoi7/easyshop">
            <el-dropdown-item>Github</el-dropdown-item>
          </a>
          <el-dropdown-item divided>
            <span style="display:block;" @click="logout">登出</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'

export default {
  components: {
    Breadcrumb
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'name'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
    routeCart() {
      this.$router.push("/cart");
    },
    routeOrder() {
      this.$router.push("/order");
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .icon-href {
      display: inline-block;
      font-weight: 700;

      svg.icon {
        position: relative;
        top: 4px;
      }
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
