# Hibernate-Demo
Hibernate
连接 GBase 8s数据库测试示例，包含简单的CRUD及分页查询。

### 使用到的环境
Eclipse
JDK-1.8
Hibernate-5.3.12 及对应的GBase 8s Dialect
GBase 8s
JDBC驱动2.0.1a2_2
 
### 源代码结构
```text
/src
|- hibernate.cfg.xml
|- /com/gbasedbt/hibernate/Student.java
|- /com/gbasedbt/hibernate/StudentText.java
```
### 各文件说明

1，hibernate.cfg.xml
> hibernate配置文件，配置数据库连接、dialect等

2，Student.java
> POJO类
> 定义Student类，使用注释方式

3，StudentTest.java
> 测试类
> 包含CRUD定义，执行测试

详情见文档说明
