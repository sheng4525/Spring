主要内容：Spring Boot 2基础知识、异常处理、测试、CORS配置、Actuator监控，集成springfox-swagger生成JSON API文档；利用Swagger UI、Postman进行Rest API测试；Angular基础知识、国际化、测试、NZ-ZORRO；Angular与Spring Boot、Spring Security、JWT集成的方法；Spring Boot、Angular集成Sonar、Jenkins等。

本文主要参考了Rich Freedman先生的博客"Integrating Angular 2 with Spring Boot, JWT, and CORS"，使用了部分代码(tour-of-heroes-jwt-full)，博客地址请见文末参考文档。前端基于Angular官方样例Tour of Heroes。完整源码请从github下载：heroes-api, heroes-web 。

技术堆栈
Spring Boot 2.1.6.RELEASE
Spring Security
Spring Data
Spring Actuator
JWT
Springfox Swagger 2.9.2
Angular 8.0
测试工具： Postman
代码质量检查： Sonar
CI： Jenkins
推荐IDE： IntelliJ IDEA、WebStorm/Visual Studio Code

Java代码中使用了lombok注解，IDE需安装lombok插件。

Spring Boot
创建Spring Boot App
创建Spring Boot项目最简易的方式是使用SPRING INITIALIZR
输入Group、Artifact，选择Dependency（Web、JPA、Security、Actuator、H2、PostgreSQL、Lombok）后，点击Generate the project，会生成zip包。下载后解压，编辑POM文件，添加java-jwt和springfox-swagger。我们选用了两个数据库H2、PostgreSQL，分别用于开发、测试环境，将其修改到两个profile dev和prod内。完成的POM文件如下：


