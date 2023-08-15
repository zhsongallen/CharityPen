<script setup>
/* eslint-disable no-unused-vars */
import {ref} from 'vue';

const activeIndex = ref('1')

// const routes = {
//   '/': HomeBody,
//   '/forum': Forum,
//   '/message/:topicId': Message
// };
// const currentView = ref(routes[window.location.hash.slice(1) || '/']);
//
// onMounted(() => {
//   window.addEventListener('hashchange', () => {
//     drawer.value = false;
//     currentView.value = routes[window.location.hash.slice(1) || '/'];
//   })
// })


const menuItems = [
  {text: '主页', url: '/'},
  {text: '论坛', url: '/generate'}
]
const drawer = ref(false);

// 鼠标移入方法
function mouseOver() {
  active.value = "background-color:#161616";
}

// 鼠标移出方法
function mouseLeave() {
  active.value = "background-color:#1c1c1c";
}

const active = ref("");

</script>

<template>
  <el-container>
    <el-header>
        <div name="logo+论坛" style="display: flex;justify-content: center;">
          <img src="/newlogo.png" style="height: 4rem;width: auto;margin: 0 20px"/>
          <!--          <span style="line-height:4rem" class="show-pc">&nbsp;&nbsp;论坛</span>-->
        </div>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>


    <footer>
      <div class="footer" style="margin: 4rem 0 2rem;">
        <div>
          <img src="/newlogo.png" style="height: 4rem;width: auto;"/>
        </div>
        <div style="display: grid;grid-template-columns: 1fr 1fr;gap: 1em;font-size: 1rem;margin: 2rem;margin-left: 5rem">
        <span>
          Features
        </span><span>
          Pricing
      </span><span>
          Sign up
      </span><span>
          App Status
      </span><span>
          F.A.Q.
      </span><span>
          About Us
      </span><span>
          Contact
        </span>
        </div>
      </div>
    </footer>
  </el-container>
</template>

<style>
.el-main {
  padding: 0 !important;
  width: 100%;
  height: 100%;
}

.el-menu--horizontal {
  border-bottom: none !important;
}

a {
  text-decoration: none;
  /*color: #ffffff;*/
}

.fixed-menu {
  display: flex;
}

.flex-grow {
  flex-grow: 1;
}

.footer {
  display: flex;
  align-items: center;
  justify-content: center;
}

.el-menu-item {
  font-size: 120% !important;
}

.el-menu--horizontal > .el-menu-item.is-active {
  border-bottom: none !important;
}

ul {
  /*display: flex;*/
  list-style: none;
  margin: 0;
  padding: 0;
}


@media (min-width: 600px) {
  .show-phone {
    display: none !important;
  }
}

@media (max-width: 600px) {
  .show-pc {
    display: none !important;
  }

  .footer{
    flex-direction:column;
  }

  .el-menu-demo {
    justify-content: space-between;
  }
}

</style>
