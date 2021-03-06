Introduction
====
目的<br/>
1.搭建一个最简洁，模块划分最明确的ssm+swargger+shiro+redis+nginx整合项目，采用maven作为构建工具，在有新项目开发时可以借助此demo快速构建项目<br/>
2.实现shiro的授权信息缓存到redis数据库，减少关系数据库访问压力<br/>
3.实现session共享到redis，实现多服务器集群方案<br/>
4.配置文档中包含丰富的注释，搭建思路清晰的ssm项目框架<br/>
5.项目中的所有细节都会按照企业级开发的标准，展示如何遵循代码规范以及类文件doc注释的编写。<br/>
6.采用RESTFul的controller接口，展示RESTFul风格的API编写(shiro基于url的权限拦截与RESTFul API兼容性不好，后期可能会改写shiro以匹配RESTFul)<br/>
7.Junit单元测试，展示如何正确的使用Junit单元测试验证自己接口代码的健壮性<br/>

涉及到的技术
====
springmvc+spring+mybatis：轻量级敏捷开发框架<br/>
swargger:快速构建RestFul接口测试页面<br/>
shiro：Apache开源权限管理框架，包括登录验证，授权，加密，会话管理<br/>
redis：Nosql数据库，搭配shiro的会话管理功能将session存入redis中，实现tomcat多服务器集群的session共享<br/>
nginx：反向代理服务器，用来调度多台tomcat<br/>
h2:内存数据库，用于测试<br/>

开发环境
====
jdk1.8+mysql5.7.22+tomcat8.5.32+IDEA<br/>

项目部署
====
第一次部署项目<br/>
1.修改ssm-rs\resources目录下db.properties的数据库账号密码信息<br/>
2.启动redis服务端，修改ssm-rs\resources\spring-config目录下spring-shiro.xml中redis的连接信息，没设置密码的话auth留空<br/>
2.创建数据库train_db并执行根目录下的train_db.sql数据库脚本<br/>
3.进入到ssm-build目录下，执行clean install -Dmaven.test.skip=true，对整个项目进行构建<br/> 
4.启动ssm-rs项目，浏览器访问http://localhost/ssm-rs/swagger-ui.html<br/>

项目模块
====
ssm-build <br/>
项目聚合模块,可以进入该项目目录，对整个项目进行构建。<br/>
mvn clean install -Dmaven.test.skip=true <br/>

ssm-parent<br/>
父模块，其他模块会继承该模块，引入公共的依赖<br/>

ssm-model<br/>
模型层模块，提供各种POJO。包括与数据库表对应的模型、传输模型等。提供给service层(ssm-cs)、controller层(ssm-rs)。<br/>

ssm-commons<br/>
包含各种工具类<br/>

ssm-cs<br/>
service层和dao层，提供具体的业务逻辑和数据库访问，需要依赖ssm-model模块，并提供出来给ssm-rs模块调用<br/>

ssm-rs<br/>
controller层，提供RESTFul接口。<br/>

个人博客
====
http://119.29.59.101/blog/index.action<br/>

联系方式
====
lruojing@foxmail.com<br/>

need your help
====
如果我的分享让客官从中受益了,打发一点服务器租赁费给小的吧<br/>

坑点
========
1、如果你要把User类当做凭证，那么记得把你的User类实现序列化接口
2、实现缓存时的cache名字如果key不为String类型，不要去拼接他生成新的带前缀的key，序列化破坏单例后导致缓存失效，因为key不一致（ShiroRedisCache类）
