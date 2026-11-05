# Inventory Management System

基于 Spring Boot 的进销存／库存管理 Web 应用，用于学习商品资料、进货、销售、退货及库存联动处理。

## 主要功能

- 商品、供应商、客户资料管理。
- 进货和销售记录的新增、修改、删除、条件查询及分页。
- 进货退货、销售退货及库存数量联动。
- 库存低于预警值时在首页显示提醒。
- 用户、部门、角色、菜单和权限配置。
- 个人资料、头像上传、密码修改、系统公告、登录日志和内存缓存管理。

以上为当前源码包含的功能，仍存在下文列出的已知限制。

## 技术栈

| 部分 | 技术 |
| --- | --- |
| 页面 | HTML、CSS、JavaScript、Layui 2.5.5、jQuery、Thymeleaf |
| 后端 | Java 8、Spring Boot 2.2.1.RELEASE、Spring MVC |
| 数据访问 | MyBatis-Plus 3.2.0、Druid 1.1.20 |
| 认证与权限 | Apache Shiro 1.4.0 |
| 数据库 | MySQL；原始数据库脚本来自 MySQL 5.7.9 |
| 构建与接口文档 | Maven、Swagger 2 |

应用由 Spring Boot 渲染页面，并通过 jQuery 请求业务接口。不是 React 项目，数据库也不是 PostgreSQL。

## 本地运行

### 1. 环境

- JDK 8，确保 `JAVA_HOME` 指向有效的 JDK。
- MySQL，建议先在兼容原脚本的 MySQL 5.7 环境中进行本地验证。
- Maven，或者使用项目自带的 Maven Wrapper。

### 2. 初始化一个空的开发数据库

在 MySQL 客户端或 Navicat 中依次执行：

1. `database/schema.sql`：创建 `warhouse` 数据库及 15 张表。
2. `database/seed-demo.sql`：导入菜单、权限、角色，以及一个新建的演示管理员。

这两个脚本仅用于新的开发数据库，不要在已有业务数据库上重复执行。业务表初始为空，可通过页面录入测试数据。

原始 `sms.sql` 包含用户、客户、供应商等样例资料，保留在本地并通过 `.gitignore` 排除，仓库使用清理后的初始化脚本。

### 3. 配置

PowerShell：

```powershell
Copy-Item src/main/resources/application.example.yml src/main/resources/application.yml
Copy-Item src/main/resources/file.example.properties src/main/resources/file.properties
$env:DB_USERNAME = 'root'
$env:DB_PASSWORD = '填写你自己的数据库密码'
```

如果本地已经有这两个配置文件，请先保留自己的配置，不要直接覆盖。`application.yml` 和 `file.properties` 不上传到 GitHub。

可选环境变量：

| 变量 | 用途 | 默认值 |
| --- | --- | --- |
| `DB_URL` | MySQL JDBC 地址 | 本机 `3306` 端口的 `warhouse` 数据库 |
| `DB_USERNAME` | 数据库用户 | `root` |
| `DB_PASSWORD` | 数据库密码 | 空，需自行设置 |
| `PORT` | HTTP 端口 | `8888` |

上传目录通过 `file.properties` 中的 `filepath` 设置；示例为 `./uploads/`。原始样例商品图片不随仓库提供。

### 4. 启动

```powershell
.\mvnw.cmd spring-boot:run
```

也可使用已安装的 Maven：

```powershell
mvn spring-boot:run
```

打开 [本地登录页面](http://localhost:8888/index.html)。

演示账号仅适用于 `database/seed-demo.sql`：

- 用户名：`admin`
- 密码：`ChangeMe123!`
- 验证码：填写页面显示的图形验证码。

此账号是新建的本地演示账号，不是原始数据库中的账号。使用后请修改密码。

### 5. 打包

```powershell
.\mvnw.cmd -DskipTests package
```

生成 `target/warehouse-0.0.1-SNAPSHOT.jar`。运行现有 Spring Boot 测试需要可连接的数据库和本地配置；跳过测试的打包不代表业务功能已通过测试。

## 项目结构

```text
database/                          清理后的数据库结构与演示初始化数据
src/main/java/com/yeqifu/bus/       商品、进货、销售、退货等业务
src/main/java/com/yeqifu/sys/       用户、权限、公告、日志等系统功能
src/main/resources/templates/     Thymeleaf 页面
src/main/resources/static/        Layui、JavaScript、样式与图片资源
src/main/resources/mapper/        MyBatis XML
src/test/                         基础启动测试与代码生成工具
```

## 当前限制

- 客户删除页面发送 POST，后端仅接受 DELETE，尚需统一。
- 部分库存操作仍需补齐后端数量检查、事务边界及并发扣减保护。
- 菜单及按钮已有权限控制，业务接口的操作权限检查仍需完善。
- 缓存是进程内的 HashMap，并非 Redis；部分数据库更新路径需要同步缓存。
- 库存预警是首页查询显示，尚未接入邮件、短信或实时推送；预警超过五条时的首页显示逻辑需完善。
- 原有依赖版本较旧，尚未进行生产环境安全加固。当前仓库用于学习和本地演示。

## 来源与许可

项目保留了现有源码中的作者标注，以及内置前端模板和第三方资源的许可说明。前端模板许可见 `src/main/resources/static/resources/LICENSE`。整理仓库不代表对已有代码和第三方资源重新声明原创或授予新的许可。

## GitHub 上传与在线运行

本仓库用于托管源码。Spring Boot 和 MySQL 需要运行环境；上传到 GitHub 不会自动产生在线演示网站。
