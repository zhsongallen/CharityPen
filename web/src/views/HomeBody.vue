<template>
  <div class="main">
    <br/>
    <h1 style="font-size: 4rem;font-family:MicrosoftYaHei;width: 50rem;margin: auto">Say hello to your new fundraising assistant.</h1>
    <h1 >Say goodbye to writer's block.</h1>
    <br/>
    <el-button color="#5e50a0" onclick='location.href=("/generate")' style="width: 15rem;height: 5rem;font-size: 1.5rem" round>Start Writing</el-button>
  </div>
  <div class="second two-columns">
    <div>
      <h1 style="font-size: 3rem;color:#5e50a0">Write smarter, not longer.</h1>
      <b style="margin-bottom:3rem;font-size: 1.5rem;">Instantly generate high-quality drafts for appeals, grant proposals, personal emails, articles, and more, just by entering simple information about your project.</b>
      <br/>
    </div>
    <div>
      <img src="/write.png">
    </div>
  </div>
  <div class="four two-columns">
    <div>
      <h1 style="font-size: 3rem;color:#5e50a0">30+ writing skills to grow your support</h1>
      <b style="margin-bottom:3rem;font-size: 1.5rem;">
        <li>Quickly draft highly personalized thank you letters for donors.</li>
        <li>Write multiple versions of appeals for your different donor segments.</li>
        <li>Brainstorm social media posts in seconds.</li>
        <li>Craft the perfect email to land a donor visit.</li>
        <li>Assemble a full proposal for your next big project.</li>
        <li>Keep people in the loop with a well-written project update.</li>
        <li>And so much more...</li>
      </b>
      <br/>
    </div>
    <div>
      <img src="/list.png" style="width: 40rem;">
    </div>
  </div>
  <div class="second two-columns">
    <div>
      <h1 style="font-size: 3rem;color:#5e50a0">Built with you in mind.</h1>
      <b style="margin-bottom:3rem;font-size: 1.5rem;">Developed side-by-side with nonprofit professionals to be simple and powerful, Fundwriter makes it easier for nonprofit organizations to grow and make a positive impact in the world.</b>
      <br/>
    </div>
    <div>
      <img src="/bye.png">
    </div>
  </div>

  <div style="width: 80vw;height:20rem;background: radial-gradient(white,#9df7f8);border-radius:2rem;margin: auto;display: flex;justify-content: center;align-items: center;flex-direction: column">
    <h1 style="font-size: 3rem;color:#5e50a0">Built with you in mind.</h1>
    <el-button color="#8052fd" onclick='location.href=("/generate")' style="width: 12rem;height: 3.5rem;font-size: 1.3rem" round>Start Writing</el-button>
  </div>
</template>

<script setup>
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";

const uploadRef =ref();
const submitUpload= ()=>{
  uploadRef.value.submit();
}
const uploadSuccess=()=>{
  ElMessage.success("上传成功")

}
const uploadAbout=reactive({
  uploadShow:false,
  uploadFrom:{
    sceneType:"movie"
  }
})

const openUpload = (type) => {
  uploadAbout.uploadShow = true;
  uploadAbout.uploadFrom.sceneType = type;
  uploadRef.value.clearFiles();
}
</script>

<style scoped>
.home-down {
  -webkit-text-size-adjust: 100%;
  list-style: none;
  font-weight: normal;
  font-style: normal;
  font-family: inherit;
  position: relative;
  margin: 0 auto;
  padding: 60px 0;
  align-items: center;
  color: #fff;
  transition: padding-bottom .5s;
  font-size: 15px;
  line-height: 1.45;
  padding-top: 75px;
  box-sizing: border-box;
  padding-bottom: 75px !important;
  text-align: center;
  background-repeat: no-repeat;
  background-size: cover;
  background-color: transparent;
  background-position: 50% 50%;
  width: 100%;
  height: 100%;
  background-image: url('/home-down.jpg');
  /*background-color:#cccccc;*/
}

.four {
  flex-direction: row-reverse
}


.third {
  display: grid;
  text-align: center;
  margin: 5rem 0;
  /*flex-direction: column-reverse;*/
}

.third h1 {
  font-size: 180%;
}

.third ul {
  flex-wrap: wrap;
  display: flex;
  justify-content: center;
}

.third ul button {
  border-radius: 50%;
  width: 5rem;
  height: 5rem;
  margin: 1rem;
}

.third a {
  font: 14px/1.6 -apple-system-font, BlinkMacSystemFont, Helvetica Neue, PingFang SC, Hiragino Sans GB, Microsoft YaHei UI, Microsoft YaHei, Arial, sans-serif;
  -webkit-tap-highlight-color: transparent;
  text-decoration: none;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  background-color: hsla(0, 0%, 100%, .07);
  color: hsla(0, 0%, 100%, .9);
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 14px;
  cursor: pointer;
  margin-right: 16px;
}

.two-columns {
  display: flex;
  justify-content: center
}

.two-columns div {
  width: 40%;
  margin: 5% 1%
}

.two-columns div h1 {
  text-align: left;
  word-wrap: break-word;
  white-space: pre-wrap;
  font-style: normal;
  font-family: "PingFang SC inherit";
  font-size: 150%;
  line-height: 1.25;
  color: #4f4f4f;
}

.two-columns div b {
  -webkit-text-size-adjust: 100%;
  text-align: left;
  word-wrap: break-word;
  white-space: pre-wrap;
  font-weight: normal;
  font-style: normal;
  font-size: 100%;
  font-family: inherit;
  line-height: 1.45;
  color: #260d3b;
}

.two-columns div img {
  width: 100%;
}

@media (max-width: 600px) {
  .two-columns {
    flex-direction: column-reverse;
    justify-content: center !important;
    align-items: center !important;
  }

  .two-columns div {
    width: 80%;
    margin: 3% 1%
  }

}


.main {
  -webkit-text-size-adjust: 100%;
  list-style: none;
  font-weight: normal;
  font-style: normal;
  font-family: inherit;
  position: relative;
  margin: 0 auto;
  padding: 60px 0;
  align-items: center;
  transition: padding-bottom .5s;
  color: #5e50a0;
  font-size: 15px;
  line-height: 1.45;
  padding-top: 75px;
  box-sizing: border-box;
  padding-bottom: 75px !important;
  text-align: center;
  background-repeat: no-repeat;
  background-size: cover;
  background-color: transparent;
  background-position: 50% 50%;
  width: 100%;
  height: 100%;
  /*background-color:#cccccc;*/
}
</style>