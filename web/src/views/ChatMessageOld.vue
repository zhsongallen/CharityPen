<script setup>
import {onMounted, ref} from "vue";
import {fetchEventSource} from '@microsoft/fetch-event-source';
//通过json文件路径引入
import jsonData from '../../public/configJson.json'

const configJson=ref(jsonData);
const subjectTemp=ref({});
onMounted(() => {
  subjectTemp.value=configJson.value[0].arrayData[0];
  pTitle.value=configJson.value[0].name;
});
const sendMessage = () => {
  loading.value=true;
  const controller = new AbortController();
  const signal = controller.signal;
  let str='Now there is an article template, I give the relevant content in the article, you can rewrite a document according to the template style and format.\n';
  subjectTemp.value.inputList.forEach((item)=>{
    str=str+item.remark+':'+item.value+'\n';
  })
  str=str+'Article Template:\n'+subjectTemp.value.sampleOutPut;

  fetchEventSource(`http://43.156.1.129:8080/message/stream`, {
    method: 'POST',
    signal: signal,
    headers: {
      'Content-Type': 'application/json',
    },
    body:JSON.stringify(
        {
          msg: str
          // msg: "你好"
        }
    ),
    onmessage(msg) {
      if(msg.data === '[DONE]'){
        controller.abort();
        loading.value=false;
        return;
      }
      message.value = message.value + JSON.parse(msg.data).content;
    },
    onerror(err) {
      console.log("onerror", err);
      if (close) {
        console.log('connection is closed');
      } else {
        alert("服务异常请重试并联系开发者！")
        console.log("Error occured", err);
      }
      loading.value=false;
      throw err;    //必须throw才能停止
      return;
    }
  });
}
// const stopMessage = () => {
//   controller.abort();
// }
const flag=ref(true);
const pTitle=ref('');
const message=ref('');
const loading = ref(false);
const consoleName = (subject,name) => {
  subjectTemp.value=subject;
  pTitle.value=name;
  console.log(pTitle.value)
}
const drawer=ref(false);
</script>
<template>
    <div class="common-layout" style="margin-top: 2rem">
      <el-container>
        <el-drawer v-model="drawer" direction="ltr" :with-header="false" size="310">
          <div style="font-size: 12px;">
            <el-menu
                default-active="0"
                class="el-menu-vertical-demo"
            >
              <el-sub-menu v-for="(json, index) in configJson" :index="index+''">
                <template #title>
                  <!--            <el-icon><Message /></el-icon>-->
                  <span>{{ json.name }}</span>
                </template>
                <el-menu-item v-for="(subject, subjectIndex) in json.arrayData" :index="index+'-'+subjectIndex" @click="consoleName(subject,json.name)">{{ subject.title}}</el-menu-item>
              </el-sub-menu>
            </el-menu>
          </div>
        </el-drawer>

        <el-aside width="300px" style="font-size: 12px" class="pc" >
          <el-menu
            default-active="0"
            class="el-menu-vertical-demo"
          >
            <el-sub-menu v-for="(json, index) in configJson" :index="index+''">
              <template #title>
                <!--            <el-icon><Message /></el-icon>-->
                <span>{{ json.name }}</span>
              </template>
              <el-menu-item v-for="(subject, subjectIndex) in json.arrayData" :index="index+'-'+subjectIndex" @click="consoleName(subject,json.name)">{{ subject.title}}</el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-aside>
        <el-main style="margin-top: 1rem !important;font-size: 14px">
          <div style="margin: 5px">
            <el-breadcrumb separator="/">
              <el-breadcrumb-item class="mobile" @click="drawer = true" style="font-weight: bold">{{ pTitle }}</el-breadcrumb-item>
              <el-breadcrumb-item class="pc" style="font-weight: bold">{{ pTitle }}</el-breadcrumb-item>
              <el-breadcrumb-item>{{ subjectTemp.title }}</el-breadcrumb-item>
            </el-breadcrumb>
            <el-button style="float:right;" onclick='location.href=("/")'>Back Home</el-button>
          </div>
          <div style="padding: 0 25px 25px;">
            <div  v-loading="loading">
              <h1>{{ subjectTemp.title }}</h1>
              <span>{{ subjectTemp.remark }}</span>
              <div v-for="input in subjectTemp.inputList">
                <div style="margin: 20px 0" />
                <span>{{input.remark}}</span>
                <el-input
                    v-model="input.value"
                    maxlength="500"
                    :autosize="{ minRows: 1, maxRows: 4 }"
                    type="textarea"
                    placeholder="Please input"
                />
              </div>
              <div style="text-align: center">
                <el-button color="#8052fd" v-if="flag" round style="width: 12rem;height: 3rem;font-size: 1.3rem;margin: 20px 0" @click="sendMessage">Start Generate</el-button>
                <!--        <el-button color="#626aef" v-if="!flag" large round style="font-size: 20px;height: auto;margin: 20px 0" @click="flag=true;stopMessage">Stop Generate</el-button>-->
              </div>
            </div>
            <el-input
                v-model="message"
                :autosize="{ minRows: 2, maxRows: 100 }"
                type="textarea"
                placeholder="Waiting for generation"
            />
          </div>
        </el-main>
      </el-container>
    </div>
</template>
<style scoped>
.mobile{
  display: none !important;
}

@media screen and (max-width: 768px) {
  .pc {
    display: none !important;
  }
  .mobile{
    display: block !important;
  }
}
.el-menu {
  border-right: 0;
}
</style>

