# Request_Paging_Schedule_Emulator

[![author: DYJ (shields.io)](https://img.shields.io/badge/author-DYJ-green)](https://lxtlovely.top/)             [![downloads: 7.8M (shields.io)](https://img.shields.io/badge/downloads-7.8M-brightgreen)](https://github.com/dyjcow/RequestPagingScheduleEmulator/releases/download/V1.0/app-release.apk)             [![gdut: OS-Course-design (shields.io)](https://img.shields.io/badge/gdut-OS--Course--design-blue)](https://github.com/dyjcow/RequestPagingScheduleEmulator)

* [背景](#背景)
* [架构](#架构)
* [页面展示](#页面展示)
* [下载体验](#下载体验)
* [使用的开源库💖](#使用的开源库)

## 背景



该项目是广东工业大学的操作系统课设，用Android设备模拟请求调页存储管理方式的APP

**操作系统** **课程设计任务书**

|   **学生姓名**   | DYJ                                                          |
| :--------------: | :----------------------------------------------------------- |
|  **题** **目**   | 请求调页存储管理方式的模拟-2                                 |
|   **指导教师**   | **李敏**                                                     |
|   **主要内容**   | 通过对页面、页表、地址转换和页面置换过程的模拟，加深对请求调页系统的原理和实现过程的理解。 |
| **基本任务要求** | 1）假设每个页面中可存放10条指令，分配给作业的内存块数为4。  <br />2）用c语言模拟一个作业的执行过程，该作业共有320条指令，即它的地址空间为32页，目前它的所有页都还未调入内存。在模拟过程中，如果所访问的指令已在内存，则显示其物理地址，并转下一条指令。如果所访问的指令还未装入内存，则发生缺页，此时需记录缺页的次数，并将相应页调入内存。如果4个内存块均已装入该作业，则需进行页面置换，最后显示其物理地址，并转下一条指令。  <br />3）在所有320指令执行完毕后，请计算并显示作业运行过程中发生的缺页率。 <br />4）置换算法：采用先进先出（FIFO）置换算法和最佳置换（OPT）算法。 |
|   **参考文献**   | [1] 计算机操作系统， 汤小丹等 ，西安电子科技大学出版社  [2] 操作系统实验指导书，傅秀芬，广东工业大学（自编）  [3] 计算机操作系统教程 ( 第二版 )， 张尧学、 史美林，清华大学出版社  [4] 现代操作系统，A.S.Tanenbaum 著，陈向群等译机械工业出版社 |
|   **审查意见**   | **指导教师签字：**  **系主任签字：**              **年**   **月**  **日** |

## 架构

项目采用简单的MVP架构，有助于新手学习

下面附项目的uml图（图片不清楚可以复制链接到浏览器查看）

![](https://pic.lxtlovely.top/blog/nLZTRzis47_tNo6WBohY2EZ7Or7KTfh4czrwTjeD68Q2bius5qhKH2gfbyL_-uwIpg52VyHMWBpOujsFn-Dz7Cdxf3JBT1wBLeid6h8P2y4BPHn4ox-33Fx9GUcKpNaoL-42ebm0nBbWMcR1b2a8klZpdSEjTzTQUJUIHvvQoDk1P15EyQTI2c39Q-9DyZ04fIvv1DiWvumBY3OYxWzMCPPc6CIC9y4DQXdq.png)

## 页面展示

<img src="https://pic.lxtlovely.top/blog/Screenshot_20220706_101409_com.dyj.requestpagingscheduleemulator.jpg" style="zoom:50%;" /><img src="https://pic.lxtlovely.top/blog/Screenshot_20220706_101412_com.dyj.requestpagingscheduleemulator.jpg" style="zoom:50%;" /><img src="https://pic.lxtlovely.top/blog/Screenshot_20220706_101419_com.dyj.requestpagingscheduleemulator.jpg" style="zoom:50%;" /><img src="https://pic.lxtlovely.top/blog/Screenshot_20220706_101426_com.dyj.requestpagingscheduleemulator.jpg" style="zoom:50%;" /><img src="https://pic.lxtlovely.top/blog/Screenshot_20220706_101438_com.dyj.requestpagingscheduleemulator.jpg" style="zoom:50%;" /><img src="https://pic.lxtlovely.top/blog/Screenshot_20220706_101447_com.dyj.requestpagingscheduleemulator.jpg" style="zoom:50%;" />

## 下载体验

[点击下载](https://github.com/dyjcow/RequestPagingScheduleEmulator/releases/download/V1.0/app-release.apk)

![](https://pic.lxtlovely.top/blog/RPSEDownload.png)

软件可以模拟演示 `LRU`、`OPT`、`FIFO`三种调度算法。使用步骤如下

1. 点击主页不同的算法按钮
2. 进入结果页后，点击 `CREATE DATA` ，会在左侧生成范围是 1~32 的随机数据，共 320 位，用以模拟作业命令的请求的页号
3. 点击 `TO DO` 会根据生成的随机数据按照对应的算法执行对应的缺页替换策略，同时右下角会显示缺页率
4. 下拉刷新可以刷新随机数据和执行算法

## 使用的开源库💖

[CymChad/BaseRecyclerViewAdapterHelper: BRVAH:Powerful and flexible RecyclerAdapter (github.com)](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

[scwang90/SmartRefreshLayout: 🔥下拉刷新、上拉加载、二级刷新、淘宝二楼、RefreshLayout、OverScroll，Android智能下拉刷新框架，支持越界回弹、越界拖动，具有极强的扩展性，集成了几十种炫酷的Header和 Footer。 (github.com)](https://github.com/scwang90/SmartRefreshLayout)

[Tamsiree/RxTool: Android开发人员不得不收集的工具类集合 | 支付宝支付 | 微信支付（统一下单） | 微信分享 | Zip4j压缩（支持分卷压缩与加密） | 一键集成UCrop选择圆形头像 | 一键集成二维码和条形码的扫描与生成 | 常用Dialog | WebView的封装可播放视频 | 仿斗鱼滑动验证码 | Toast封装 | 震动 | GPS | Location定位 | 图片缩放 | Exif 图片添加地理位置信息（经纬度） | 蛛网等级 | 颜色选择器 | ArcGis | VTPK | 编译运行一下说不定会找到惊喜 (github.com)](https://github.com/Tamsiree/RxTool)

[DylanCaiCoding/ViewBindingKTX: The most comprehensive utils of ViewBinding. (最全面的 ViewBinding 工具，支持 Kotlin 和 Java 用法，支持 BRVAH，支持封装到基类，支持 DataBinding，支持选择是否使用反射) (github.com)](https://github.com/DylanCaiCoding/ViewBindingKTX)

[bumptech/glide: An image loading and caching library for Android focused on smooth scrolling (github.com)](https://github.com/bumptech/glide)

[Zackratos/UltimateBarX: Make Android transparent statusbar and navigationbar easy. (github.com)](https://github.com/Zackratos/UltimateBarX)
