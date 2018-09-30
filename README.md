# StatusView：简单的 Android 页面多状态布局切换控件

[StatusView Demo 体验](https://fir.im/3y8x?release_id=5bb05700959d694dd3e7169b)
#### 一、效果预览
#### 二、主要功能
* 可作用于 Activity、Fragment 的根布局或其子View
* 默认支持 Loading、Empty、Error 三种状态布局，可进行常规配置
* 可自定义状态布局，并提供对应接口来完成需要的配置
* 状态布局懒加载，仅在初次显示时初始化

注意：当 ViewPager 嵌套 Fragment 时，请在 XML 中使用 StatusView！

