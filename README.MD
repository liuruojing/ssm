#### Introduction
本项目的目的在于搭建一个最简洁的ssm+swargger整合项目，采用maven作为构建工具，在有新项目开发时可以借助此demo快速构建项目
项目中的所有细节都会按照企业级开发的标准。
后续会持续加入shiro+redis+nginx的tomcat集群方案

####涉及到的技术
springmvc+spring+mybatis：轻量级敏捷开发框架
swargger:快速构建RestFul接口测试页面
shiro：Apache开源权限管理框架，包括登录验证，授权，加密，会话管理
redis：Nosql数据库，搭配shiro的会话管理功能将session存入redis中，实现tomcat多服务器集群的session共享
nginx：反向代理服务器，用来调度多台tomcat

####开发环境
jdk1.8+mysql5.7.22+tomcat8.5.32+IDEA

####项目部署
第一次部署项目
1.修改ssm-rs\resources目录下database.properties的数据库账号密码信息
2.创建数据库train_db并执行根目录下的train_db.sql数据库脚本
3.进入到ssm-build目录下，执行clean install -Dmaven.test.skip=true，对整个项目进行构建 
4.启动ssm-rs项目，浏览器访问http://localhost/ssm-rs/swagger-ui.html

####项目模块
#### ssm-build
项目聚合模块,可以进入该项目目录，对整个项目进行构建。
clean install -Dmaven.test.skip=true 

#### ssm-parent
父模块，其他模块会继承该模块，引入公共的依赖

#### ssm-model
模型层模块，提供各种POJO。包括与数据库表对应的模型、传输模型等。提供给service层(ssm-cs)、controller层(ssm-rs)。

#### ssm-commons
包含各种工具类

#### ssm-cs
service层和dao层，提供具体的业务逻辑和数据库访问，需要依赖ssm-model模块，并提供出来给ssm-rs模块调用

#### ssm-rs
controller层，提供RESTFul接口。

####个人博客
http://119.29.59.101/blog/index.action

####联系方式
809882301@qq.com
####need your help
如果我的分享让客观从中受益了,打发一点服务器租赁费给小的吧
