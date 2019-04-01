## 参照大佬示范代码与github上Google的todomvp做的小demo
##### A simple login demo referenced todo‑mvp，googlesamples in github
------

希望有登录、注册、编辑地址（代替个人中心）三块功能，内附详细的注释，目前实现的只有登录部分。
1. 先理解下mvp的解耦原理，以及需要具备对回调的基本认识，也顺便要知道怎么用fragment
2. app包放的是一些全局相关的东西
3. model包(也可以单独new出来一个模块，然后在build.gradle引用它)放数据操作相关东西
4. page包分模块放view和presenter(这俩耦合挺紧所以放一起)
5. 使用的lifecycle需要再build.gradle中implementation一下

------
###建议顺序：
+ 看看app包都放些什么，文件的逻辑结构
+ 理解mvp之后是page.base包各父类、接口类的通用方法，以及如何给presenter绑定生命周期
+ 结合page.login包的activity，重点看如何实现view和presenter相互绑定
+ 理解下BaseFragment中泛型的作用
+ 看看Contract类的作用
+ 运行一下app，顺着点击登录事件，理解下如何通过view的onClickListenter调用presenter，presenter又是如何调用model进行数据请求的，以及回调的作用
+ 留了一块个人中心没做，感兴趣可以仿照着写一个login或者把个人中心写完