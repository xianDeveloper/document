##  第一次用Vscode 配合MarkDown 来写 作为记录和比较记录工作中遇到的一些问题

1.只是作为一个笔记测试而已：（更新日期 2019-8-24 21:44）

----
##### Vue 生命周期和钩子函数
  https://mp.weixin.qq.com/s?src=11&timestamp=1566785001&ver=1813&signature=Jph8vLs8C2e3V8tC986re-ZzCFDSY96AFlfsjpt8OkW-xTreH14ee7*QJA099H8mzKuqYXpLzjRb3oiy0skUpE9FhcaAkXgXD9Mwq363zK1bl91eggNGv8B2AbU4gC6-&new=1


####. 1谈谈你对MVVM开发模式的理解
        MVVM分为Model、View、ViewModel三者。

        Model 代表数据模型，数据和业务逻辑都在Model层中定义；
        View 代表UI视图，负责数据的展示；
        ViewModel 负责监听 Model 中数据的改变并且控制视图的更新，处理用户交互操作；
        Model 和 View 并无直接关联，而是通过 ViewModel 来进行联系的，Model 和 ViewModel 之间有着双向数据绑定的联系。因此当 Model 中的数据改变时会触发 View 层的刷新，View 中由于用户交互操作而改变的数据也会在 Model 中同步。

        这种模式实现了 Model 和 View 的数据自动同步，因此开发者只需要专注对数据的维护操作即可，而不需要自己操作 dom。

#### 2. Vue 有哪些指令？
        v-html、v-show、v-if、v-for等等

#### 3. v-if 和 v-show 有什么区别？
        v-show 仅仅控制元素的显示方式，将 display 属性在 block 和 none 来回切换；而v-if会控制这个 DOM 节点的存在与否。当我们需要经常切换某个元素的显示/隐藏时，使用v-show会更加节省性能上的开销；当只需要一次显示或隐藏时，使用v-if更加合理。

#### 4. 简述Vue的响应式原理
        当一个Vue实例创建时，vue会遍历data选项的属性，用 Object.defineProperty 将它们转为 getter/setter并且在内部追踪相关依赖，在属性被访问和修改时通知变化。 每个组件实例都有相应的 watcher 程序实例，它会在组件渲染的过程中把属性记录为依赖，之后当依赖项的 setter 被调用时，会通知 watcher 重新计算，从而致使它关联的组件得以更新。






#### 5. Vue中如何在组件内部实现一个双向数据绑定？
        假设有一个输入框组件，用户输入时，同步父组件页面中的数据

        具体思路：父组件通过 props 传值给子组件，子组件通过 $emit 来通知父组件修改相应的props值，具体实现如下：

````javascript  
import Vue from 'vue'
​
const component = {
  props: ['value'],
  template: `
    <div>
      <input type="text" @input="handleInput" :value="value">
    </div>
  `,
  data () {
    return {
    }
  },
  methods: {
    handleInput (e) {
      this.$emit('input', e.target.value)
    }
  }
}
​
new Vue({
  components: {
    CompOne: component
  },
  el: '#root',
  template: `
    <div>
      <comp-one :value1="value" @input="value = arguments[0]"></comp-one>
    </div>
  `,
  data () {
    return {
      value: '123'
    }
  }
})
````
        可以看到，当输入数据时，父子组件中的数据是同步改变的：


        我们在父组件中做了两件事，一是给子组件传入props，二是监听input事件并同步自己的value属性。那么这两步操作能否再精简一下呢？答案是可以的，你只需要修改父组件：


        template: `
            <div>
            <!--<comp-one :value1="value" @input="value = arguments[0]"></comp-one>-->
            <comp-one v-model="value"></comp-one>
            </div>
        `
v-model 实际上会帮我们完成上面的两步操作。

1. Vue中如何监控某个属性值的变化？
比如现在需要监控data中，obj.a 的变化。Vue中监控对象属性的变化你可以这样：

````javascript 
 watch: {
      obj: {
      handler (newValue, oldValue) {
        console.log('obj changed')
      },
      deep: true
    }
  }
  ````
deep属性表示深层遍历，但是这么写会监控obj的所有属性变化，并不是我们想要的效果，所以做点修改：
````javascript 
watch: {
   'obj.a': {
      handler (newName, oldName) {
        console.log('obj.a changed')
      }
   }
  }
  ````
还有一种方法，可以通过computed 来实现，只需要：
````javascript 
computed: {
    a1 () {
      return this.obj.a
    }
}
```` 
利用计算属性的特性来实现，当依赖改变时，便会重新计算一个新值。

7. Vue中给data中的对象属性添加一个新的属性时会发生什么，如何解决？
示例：
````javascript 
            <template>
            <div>
                <ul>
                <li v-for="value in obj" :key="value">
                    {{value}}
                </li>
                </ul>
                <button @click="addObjB">添加obj.b</button>
            </div>
            </template>
            <script>
            export default {
            data () {
                return {
                obj: {
                    a: 'obj.a'
                }
                }
            },
            methods: {
                addObjB () {
                this.obj.b = 'obj.b'
                console.log(this.obj)
                }
            }
            }
            </script>
            <style></style>
````
点击button会发现，obj.b 已经成功添加，但是视图并未刷新：











原因在于在Vue实例创建时，obj.b并未声明，因此就没有被Vue转换为响应式的属性，自然就不会触发视图的更新，这时就需要使用Vue的全局api $set()：
````javascript 
addObjB () {
      // this.obj.b = 'obj.b'
      this.$set(this.obj, 'b', 'obj.b')
      console.log(this.obj)
    }
 ```` 
$set()方法相当于手动的去把obj.b处理成一个响应式的属性，此时视图也会跟着改变了：






8. delete和Vue.delete删除数组的区别
delete只是被删除的元素变成了 empty/undefined 其他的元素的键值还是不变。 Vue.delete直接删除了数组 改变了数组的键值。

            var a=[1,2,3,4]
            var b=[1,2,3,4]
            delete a[1]
            console.log(a)
            this.$delete(b,1)
            console.log(b)










##### 9. 如何优化SPA应用的首屏加载速度慢的问题？
        将公用的JS库通过script标签外部引入，减小app.bundel的大小，让浏览器并行下载资源文件，提高下载速度；
        在配置 路由时，页面和组件使用懒加载的方式引入，进一步缩小 app.bundel 的体积，在调用某个组件时再加载对应的js文件；
        加一个首屏 loading 图，提升用户体验；
##### 10. 前端如何优化网站性能？
        减少 HTTP 请求数量
        在浏览器与服务器进行通信时，主要是通过 HTTP 进行通信。浏览器与服务器需要经过三次握手，每次握手需要花费大量时间。而且不同浏览器对资源文件并发请求数量有限（不同浏览器允许并发数），一旦 HTTP 请求数量达到一定数量，资源请求就存在等待状态，这是很致命的，因此减少 HTTP 的请求数量可以很大程度上对网站性能进行优化。

        CSS Sprites：国内俗称 CSS 精灵，这是将多张图片合并成一张图片达到减少 HTTP 请求的一种解决方案，可以通过 CSS background 属性来访问图片内容。这种方案同时还可以减少图片总字节数。
        合并 CSS 和 JS 文件：现在前端有很多工程化打包工具，如：grunt、gulp、webpack等。为了减少 HTTP 请求数量，可以通过这些工具再发布前将多个 CSS 或者 多个 JS 合并成一个文件。
        采用 lazyLoad：俗称懒加载，可以控制网页上的内容在一开始无需加载，不需要发请求，等到用户操作真正需要的时候立即加载出内容。这样就控制了网页资源一次性请求数量。
        控制资源文件加载优先级
        浏览器在加载 HTML 内容时，是将 HTML 内容从上至下依次解析，解析到 link 或者 script 标签就会加载 href 或者 src 对应链接内容，为了第一时间展示页面给用户，就需要将 CSS 提前加载，不要受 JS 加载影响。

        一般情况下都是 CSS 在头部，JS 在底部。

        利用浏览器缓存
        浏览器缓存是将网络资源存储在本地，等待下次请求该资源时，如果资源已经存在就不需要到服务器重新请求该资源，直接在本地读取该资源。

        减少重排（Reflow）
        基本原理：重排是 DOM 的变化影响到了元素的几何属性（宽和高），浏览器会重新计算元素的几何属性，会使渲染树中受到影响的部分失效，浏览器会验证 DOM 树上的所有其它结点的 visibility 属性，这也是 Reflow 低效的原因。如果 Reflow 的过于频繁，CPU 使用率就会急剧上升。

        减少 Reflow，如果需要在 DOM 操作时添加样式，尽量使用 增加 class 属性，而不是通过 style 操作样式。

        减少 DOM 操作
        图标使用 IconFont 替换
##### 11. 网页从输入网址到渲染完成经历了哪些过程？
        大致可以分为如下7步：

        输入网址；
        发送到DNS服务器，并获取域名对应的web服务器对应的ip地址；
        与web服务器建立TCP连接；
        浏览器向web服务器发送http请求；
        web服务器响应请求，并返回指定url的数据（或错误信息，或重定向的新的url地址）；
        浏览器下载web服务器返回的数据及解析html源文件；
        生成DOM树，解析css和js，渲染页面，直至显示完成；
##### 12. jQuery获取的dom对象和原生的dom对象有何区别？
        js原生获取的dom是一个对象，jQuery对象就是一个数组对象，其实就是选择出来的元素的数组集合，所以说他们两者是不同的对象类型不等价。

````javascript 
        原生DOM对象转jQuery对象：
        var box = document.getElementById('box');
        var $box = $(box);
        jQuery对象转原生DOM对象：
        var $box = $('#box');
        var box = $box[0];
        13. jQuery如何扩展自定义方法
        (jQuery.fn.myMethod=function () {
            alert('myMethod');
        })
        // 或者：
        (function ($) {
                $.fn.extend({
                    myMethod : function () {
                        alert('myMethod');
                    }
                })
        })(jQuery)
        使用：

        $("#div").myMethod();
````