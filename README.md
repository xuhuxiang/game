# 项目介绍
本项目为本人web开发期末大作业，实现的是一个《在线游戏商城》
# 需求分析
![w4](https://github.com/xuhuxiang/game/assets/101508698/fc98e44c-2ecd-4d2a-8f2f-3bce2c9d96da)<br/>
用户模块需求分析：<br/>
1、首页：轮播图 热销游戏的浏览<br/>
2、所有游戏：用户可分类浏览所有游戏信息。<br/>
3、充值功能：用户可在个人中心给账户充值金额<br/>
4、购物车功能：用户可以把游戏添加到购物车内<br/>
5、下单支付功能：用户可直接支付游戏或者在购物车内批量支付游戏<br/>
6、订单功能：用户可查看自己的所有订单，查看订单状态<br/>
7、申请退款功能：用户在订单内申请退款，退款成功后用户账户余额相应增加<br/>
8、游戏评价功能：订单完成后，用户可评价游戏，回复游戏评价<br/>
9、点赞功能：用户可对游戏进行点赞和取消点赞，点赞数量相应增减<br/>
10、搜索功能：用户可根据关键字模糊搜索游戏名称<br/>
![w1](https://github.com/xuhuxiang/game/assets/101508698/3f59217e-97a2-4151-ba73-456be8e0e11d)<br/>
管理员模块需求分析：<br/>
1、管理员管理：管理员对管理员信息的增删改查<br/>
2、用户管理：管理员对用户信息的增删改查<br/>
3、商家管理：管理员对商家信息的增删改查<br/>
4、轮播图管理：管理员对轮播图的增删改查<br/>
5、游戏分类：管理员对游戏分类信息的增删改查<br/>
5、游戏管理：管理员对游戏信息的增删改查，商品管理模块跟商品分类关联，可编辑商品详情和视频播放，游戏商品的模糊查询，设置热销游戏商品<br/>
6、热销管理：展示热销游戏商品的模块<br/>
7、评论管理：用户前台可评论游戏商品，管理员可对评论信息进行增删查<br/>
![w2](https://github.com/xuhuxiang/game/assets/101508698/059e9f23-574f-457b-a078-b35ddc96f058)<br/>
商家模块需求分析：<br/>
1、注册开店<br/>
2、对自己的店进行管理：可以对自己的游戏产品进行增删改查，可编辑游戏介绍与视频播放<br/>
3、订单管理：进行游戏的销售，退换货处理<br/>
![w3](https://github.com/xuhuxiang/game/assets/101508698/5dfd81d8-a6fa-4563-9f9c-6ceb33b30383)<br/>
# 数据库设计
1. 管理员表<br/>
字段：id username(用户名) password(密码) avatar(头像地址) phone(手机号) sex(性别) role(角色类型)<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/c93863dd-7460-49c3-a590-1cf323079739)<br/>
2.用户表<br/>
字段：id username(用户名) password(密码) avatar(头像地址) phone(手机号) sex(性别) age(年龄) role(角色类型) account(账户余额) realname(实名)<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/9cde1121-8c85-4ff9-8353-1f7291b98b57)<br/>
3.商家表<br/>
字段：id username(用户名) password(密码) avatar(头像地址) phone(手机号) sex(性别) role(角色类型)<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/5b93e32c-3899-43b6-a6a1-db0b016ea9ab)<br/>
4.游戏类型表<br/>
字段：id name(游戏类型名称) description(详细介绍)<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/5bac36dd-7815-4edf-ace1-568bbb60ae1c)<br/>
5.商品表<br/>
字段：id img(游戏图片地址) name(游戏名字) price(价格) description(介绍视图片地址) typeId(游戏类型) praise(点赞量) ishot(是否热门) video(介绍视频地址) goodslink(游戏文件地址) businessId(发行商家编号) time(发行时间)  gpu(显存需求)<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/9eb5c98c-d54f-4b3a-8928-cd18e20869f0)<br/>
6.购物车表<br/>
字段：id goodsId(商品编号) userId(用户Id) <br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/e6b14a2b-4fc3-4eda-b06b-a3aabd31566b)<br/>
7.订单表<br/>
字段：id userId(用户编号) orderId(订单编号) time(下单时间) price(支付金额) goodsId(商品编号) status(订单状态)<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/c719bd18-5c61-4c0f-8711-219a76a27f7b)<br/>
8.轮播图表<br/>
字段: id img(图片地址) goodsId(商品编号)<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/acaa6971-51e1-42eb-a350-9cda846f759f)<br/>
9.点赞信息表<br/>
字段：
Id userId(用户编号) role(用户类型) relId(关联商品Id) <br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/701f688a-9159-4096-8dc6-62e92bd10475)<br/>
10、评论表<br/>
字段：ID、content（评论内容）、userId（用户Id）、role（用户角色）、time（评论时间）、parentId（父Id）、relled（关联Id）<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/69e1a054-ee5a-4983-9c7d-d8ed0475f656)<br/>
数据库的ER图如下图所示：<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/e4db582c-651a-4ec9-b5ac-0987b2098dad)<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/346c0ebb-4a2e-4665-aee1-ab08745d895c)<br/>
# 界面展示
注册界面<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/4f4572b0-8bce-4574-abe2-565212a7a8f8)<br/>
登录界面<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/d589f37c-2118-436b-9de0-11477537ba56)<br/>
修改密码<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/29ba4063-e408-4c05-9df2-d7a4639c7eb6)<br/>
系统主页<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/356352bb-7fbc-4cc9-9bd3-7f28f842d89f)<br/>
购物车页面<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/4037bc97-0ba2-4649-9131-691d9e337c4d)<br/>
订单页面<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/327bae67-ef49-4f54-a0a5-d0bb82268a3e)<br/>
游戏详情页<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/f6e3bed1-f930-41c9-8c6f-1fdf3d1f647a)<br/>
评论<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/c88a43ef-b29b-4b29-b564-777de16b488a)<br/>
游戏分类管理<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/0286c752-2d06-4dfe-9e15-71cb9cad4322)<br/>
游戏管理<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/c9a1862a-a9db-4f13-a983-c0a1b4f2f30a)<br/>
热销管理<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/695f43ec-89d3-4d12-bdc2-d54cbec83093)<br/>
轮播图管理<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/c8fc9d3d-967f-4f1f-96a1-7eea027ac956)<br/>
评论管理<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/3d2f8539-240c-460e-b204-5da96ebad791)<br/>
订单管理<br/>
![image](https://github.com/xuhuxiang/game/assets/101508698/2a4058a8-5dd9-4102-9772-134f26496276)<br/>
