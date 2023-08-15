<script setup>
import {onMounted, ref} from "vue";

// const eventSource = new EventSourcePolyfill(
//     `http://127.0.0.1:8000/message/stream`
// );

const test= async (msg) => {
  const eventSource = new EventSource('http://127.0.0.1:8080/messagenew/stream');
  let close=false;
  eventSource.onopen = (event) => {
    console.log("开始输出后端返回值");
  };
  eventSource.onmessage = (event) => {
    if(event.data==='[DONE]'){
      close=true;
      return;
    }
    message.value = message.value + JSON.parse(event.data).content;
  };
  eventSource.onerror = (event) => {
    console.log("onerror", event);
    if (close) {
      console.log('connection is closed');
    } else {
      alert("服务异常请重试并联系开发者！")
      console.log("Error occured", event);
    }
    event.target.close();
  };
}


onMounted(() => {
  test.call("123");
});

const OpenAIStream = async (msg) => {
  const encoder = new TextEncoder();
  const decoder = new TextDecoder();

  const res = await fetch(`http://127.0.0.1:8080/messagenew/stream`, {
    headers: {
      'Content-Type': 'application/json',
      // Authorization: `Bearer ${apiKey}`
    },
    method: 'POST',
    body: JSON.stringify({
      msg: '123'
    })
  });

  if (res.status !== 200) {
    throw new Error('OpenAI API returned an error');
  }
  console.log(123)
  const stream = new ReadableStream({
    async start(controller) {
      console.log(456)
      const onParse = (event) => {
        console.log(789)
        if (event.type === 'event') {
          const data = event.data;

          if (data === '[DONE]') {
            controller.close();
            return;
          }

          try {
            const json = JSON.parse(data);
            console.log(json+"================")

            const text = json.choices[0].delta.content;
            const queue = encoder.encode(text);
            controller.enqueue(queue);
          } catch (e) {
            controller.error(e);
          }
        }
      };

      const parser = createParser(onParse);

      for await (const chunk of res.body) {
        parser.feed(decoder.decode(chunk));
      }
    }
  });

  return stream;
};


const message=ref('');
/*
* open：订阅成功（和后端连接成功）
*/




</script>
<template>
  <div>
    <button></button>
    <h2>Messages:</h2>
    <ul>
      <span>{{message}}</span>
    </ul>
  </div>
</template>

<style scoped>

</style>

