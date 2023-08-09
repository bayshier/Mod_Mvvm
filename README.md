
### 1. 项目架构
1. 项目采用 Kotlin 语言编写，结合 Jetpack 相关控件，`Navigation`，`Lifecyle`，`DataBinding`，`LiveData`，`ViewModel`等搭建的 **MVVM** 架构模式；
2. 通过**组件化**，**模块化**拆分，实现项目更好解耦和复用，[ARouter](https://github.com/alibaba/ARouter) 实现模块间通信；
3. 使用 **协程+Flow+Retrofit+OkHttp** 优雅地实现网络请求；
4. 通过 **mmkv**，**Room** 数据库等实现对数据缓存的管理；
5. 使用谷歌 **ExoPlayer** 实现短视频播放；
6. 使用 **Glide** 完成图片加载；
7. 通过 **WanAndroid** 提供的 API 实现的一款玩安卓客户端。

#### APP壳工程
#### mod_main
#### mod_user
#### mod_login
#### mod_search
#### mod_video
#### lib_framework
#### lib_common
#### lib_network
#### lib_stater
#### lib_banner
#### lib_glide
#### lib_room

