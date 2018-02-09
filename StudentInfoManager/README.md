## StudentInfoManager
* 模仿的项目链接:[Android应用开发-学生信息管理系统 - CSDN博客](http://blog.csdn.net/double2hao/article/details/52641074)

### 涉及知识点
* 包括SQLite的增删查找等功能。查找中加入了“模糊搜索”的功能。
* RecyclerView展示
* SharedPreference保存密码，以及判别是否是第一次安装APP。（如果是第一次就录入测试数据）
* 简单的Menu操作


### 项目截图
### 项目截图
<a href="../art/StudentInfoManager_main1.png"><img src="../art/StudentInfoManager_main1.png" width="20%" height="20%"/></a><img height="0" width="8px"/><a href="../art/StudentInfoManager_main2.png"><img src="../art/StudentInfoManager_main2.png" width="20%" height="20%"/></a><img height="0" width="8px"/>
<a href="../art/StudentInfoManager_login.png"><img src="../art/StudentInfoManager_login.png" width="20%" height="20%"/></a><img height="0" width="8px"/><a href="../art/StudentInfoManager_search.png"><img src="../art/StudentInfoManager_search.png" width="20%" height="20%"/></a><img height="0" width="8px"/><br/>

App体验地址:[StudentInfoManager](https://github.com/simplebam/SQLiteDB_Demo/releases/download/v1.0/StudentInfoManager-release_v1.0.apk)


### 项目中用到的知识
* 命名规范-这里主要参考Blankj:[Android 开发规范（完结版） - 简书](https://www.jianshu.com/p/45c1675bec69)
* Android基础:
  * Material Design:
      * [Android Theme.AppCompat 中，你应该熟悉的颜色属性 - 简书 ](https://www.jianshu.com/p/15c6397685a0)
        这家伙的关于MD文章也是值得一看的,简短but精辟
      * Toolbar:
          * [ToolBar使用心得(如何改变item的位置) - 泡在网上的日子](http://www.jcodecraeer.com/plus/view.php?aid=7667)
          * [Toolbar+DrawerLayout+NavigationView使用心得](http://www.jcodecraeer.com/a/anzhuokaifa/2017/0317/7694.html)
          * [Android ToolBar 使用完全解析 - 简书]( https://www.jianshu.com/p/ae0013a4f71a)
    * TextInputLayout:
        * [使用TextInputLayout创建一个登陆界面 - 泡在网上的日子](http://www.jcodecraeer.com/a/basictutorial/2015/0821/3338.html)
    * RecyclerView:
        * [RecyclerView简单使用总结 - 简书](https://www.jianshu.com/p/9b3949f7cb0f)
        * [RecyclerView使用完全指南，是时候体验新控件了（一） - 简书](https://www.jianshu.com/p/4fc6164e4709)
    * SwipeRefreshLayout:
        * [SwipeRefreshLayout详解和自定义上拉加载更多 - 简书 ](https://www.jianshu.com/p/d23b42b6360b)
        * [SwipeRefreshLayout+RecyclerView冲突的几种解决方案 - 简书](https://www.jianshu.com/p/34cbaddb668b)
    * 看不懂物料设计的话建议买郭霖先生的《第二行代码》好一点，这本书内容对于初级
      开发者来说还是蛮不错的


### 项目中用到的框架
* ButterKnife
  * [[Android开发] ButterKnife8.5.1 使用方法教程总结 - CSDN博客](http://blog.csdn.net/niubitianping/article/details/54893571)
*

### 开发中遇到的问题
* 这里的数据库开闭我处理的还不是很好,这里我建议不太熟悉数据库的朋友们可以更换为
  [LitePal](https://github.com/LitePalFramework/LitePal)

