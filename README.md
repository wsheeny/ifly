<div align="center">
    <h2>elegant-admin</h2>
    <p>
        一款基于SpringBoot构建的智慧社区系统，与<a href="https://github.com/1020317774/rhapsody" target="_blank">rhapsody</a>搭配使用，效果更佳。
    </p>
    <p>
        <a href="https://github.com/1020317774/elegant-admin/stargazers"><img alt="GitHub release" src="https://img.shields.io/github/release/1020317774/elegant-admin?style=flat-square&logo=Ren'py"></a>
        <a href="https://github.com/1020317774/elegant-admin/blob/main/LICENSE"><img alt="GitHub license" src="https://img.shields.io/github/license/1020317774/elegant-admin"></a>
        <a href="https://github.com/1020317774/elegant-admin/blob/main/LICENSE"><img alt="GitHub license" src="https://img.shields.io/github/release/jgthms/bulma?style=flat-square&logo=Bulma"></a>
    </p>
    <p>
        <a href="./README.md">简体中文</a>
        ·
        <a href="./README_EN.md">English</a>
    </p>
</div>

## 技术栈

| 技术 | 版本 |说明 |
| --- | --- | --- |
|Spring Boot|2.4.0|容器+MVC框架|
|Spring Security|5.1.4|认证和授权框架|
|JWT|0.9.0|JWT登录支持|
|Mysql|8.0.22|关系型数据库|
|MyBatis|3.4.6|ORM框架|
|MyBatis-Plus|3.4.1|MB增强工具|
|MyBatisGenerator|1.3.3|数据层代码生成|
|Flyway|7.1.1| Java数据库移植框架|
|Swagger-UI|2.9.2|文档生产工具|
|Elasticsearch|7.6.2|搜索引擎|
|RabbitMq|3.7.14|消息队列|
|Redis|5.0|分布式缓存|
|Druid|1.1.10|数据库连接池|
|Lombok|1.18.6|简化对象封装工具|
|Hutool|5.5.1|Java工具类库|
|FastJson|1.2.73|JSON解析库|
|JustAuth|1.15.8|第三方授权登录的工具类库|

……

## 安装指导

- 克隆
```java
git clone https://github.com/1020317774/elegant-admin.git
```

- 修改`application.properties`选择环境

- 修改多环境配置中的redis参数和数据库

- 启动`BootApplication`

- 访问[`http://127.0.0.1:10000`](http://127.0.0.1:10000)

## 版本预览

![首页面](src/main/resources/preview/index.png)

![活动](src/main/resources/preview/activity.png)

![摸鱼](src/main/resources/preview/moyu.png)

![详情页](src/main/resources/preview/topic-detail.png)

![评论](src/main/resources/preview/footer.png)

![专栏](src/main/resources/preview/column.png)

![专栏详情](src/main/resources/preview/column-detail.png)

![登录](src/main/resources/preview/login.png)

![注册](src/main/resources/preview/register.png)

![夜间](src/main/resources/preview/dark.png)

![swagger](src/main/resources/preview/swagger.png)



## 版权

Code copyright 2020 QQ:1020317774. Code released under [the MIT license](https://github.com/jgthms/bulma/blob/master/LICENSE).
