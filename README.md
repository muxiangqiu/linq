# linq
语言集成查询工具

## 简介
linq是基于JPA实现的。可以很简单的和spring-data-jpa和spring-boot集成。通过它，我们可以实现数据表的增删除改查。
## 目标与特色
### 目标
简化JPA相关API，增强代码的可读性，提示开发效率。对于一个mis系统，可能90%以上的代码都是围绕着数据的操作，优化数据操作API，对提升项目开发效率是非常有效的。
### 特色
* 智能适配多数据源
* 极简设计
* 方法链式调用
* 结构化API设计
#### 智能适配多数据源
  
当你对实体类进行增删改查的时候，不需要关心实体类是属于哪一个数据源，这些对用户是完全透明，内部会智能帮我们判断。
   
#### 极简设计
数据密集性系统，往往90%以上的代码是数据库操作有关。所以简化API是极为重要。首先，所以的API入口只有一个，且通过一个名字极短的工具类提供-JpaUitl。看如下例子(数据的增删改)：
以前的话
```java
	@Transactional
	public void oldSave(List<User> users) {
	  EntityManager em = ......
	  for (User user : users) {
	    em.persist(user)
	  }
	}
```
现在的话
```java
	@Transactional
	public void save(List<User> users) {
	  JpaUtil.persist(users);
	}
```

#### 方法链式调用
为结构化查询体功能条件。代码更连贯，增强可读性。
如下(分页+过滤栏+部分字段查询+固定条件+排序)：
```java
	@Transactional(readOnly = true)
	public Page<User> load(Pageable pageable, String deptId) {
		JpaUtil
		  .linq(User.class)
		  .select("id", "name", "age")
                  .addIf(deptId)
		    .equal("deptId", deptId)
	          .endIf()
		  .gt("age", 18)
		  .or()
		    .isTrue("married")
		    .and()
		      .ge("salary", 5000)
		      .le("salary", 2000)
		    .end()
		  .end()
		  .desc("createAt", "name")
		  .paging(pageable);
		/**********************************************************************************************************************************************
		 * 当deptId为空时，近似于：select id, name, age from user where arg > 18 and (married=1 or salary >= 5000 and salay <= 2000 order by createat desc, name desc)   
		 * 当deptId不为空时，近似于：select id, name, age from user where deptid = xxx arg > 18 and (married=1 or salary >= 5000 and salay <= 2000 order by createat desc, name desc)
         ********************************************************************************************************************************************/
	}
```

#### 结构化API设计
当我们查询条件很复杂的时候，基于JPA Criteria API 构造条件，简直是场灾难。API不直观，完全没有可读性。而结构化的API能很大程度改善这个问题，因为它的整体代码结构与SQL相仿，单比SQL更优雅，条件的代码书写像语法树一样。如下（递归条件定义）：

```java
	...
    ...
    .equal ...
    .ge ...
    .or()
      .equal ...
      .ge ...
      .and()
        .equal ...
        .ge ...
    ...
    ...
```
## 示例
1. 查询所有数据
```java
	JpaUtil.linq(User.class).list();
    或者
	JpaUtil.findAll(User.class);
```
2. 按固定条件查询数据
```java
	JpaUtil
          .linq(User.class)
          .equal("age", 18)
          .ge("salary", 5000)
          .or()
      	    .isTrue("married")
            .equal("deptId", "001")
          .end()
          .list();
      /******************************************************************
	   * 近似于：select * from user where arg = 18 and salay >= 5000 and (married=1 or deptid = '001')   
       ******************************************************************/  
```

3. 按动态条件查询数据
```java
	JpaUtil
    	  .linq(User.class)
    	  .exists(DeptUser.class)
    	  	.equal("deptId", "001")
    	  	.equalProperty("userId", "id")
    	  .end()
          .addIf(xxx)
            .equal("age", 18)
          .endIf()
          .select("id", "name")
          .list();
```

4. 查询部分实体属性
```java
	JpaUtil
    	  .linq(User.class)
          .select("id", "name")
          .list();
```

5. 查询所有数据
```java
	JpaUtil.linq(User.class).list();
    或者
	JpaUtil.findAll(User.class);
```

6. 分页查询数据
```java
	JpaUtil.linq(User.class).paging(pageable);
```

7. 查询一条数据
```java
	User user = JpaUtil.linq(User.class).equal("id", "001").findOne();
    或者
	User user = JpaUtil.getOne(User.class, "001");
```

8. 查询数据条数
```java
	Long count = JpaUtil.linq(User.class).count();
```

9. 判断数据存在
```java
	boolean isExists = JpaUtil.linq(User.class).exists();
```

10. 标准持久化数据
```java
	JpaUtil.persist(user);
```

11. 标准批量持久化数据
```java
	JpaUtil.persist(users);
```

12. 标准更新数据
```java
	JpaUtil.merge(user);
```

14. 标准批量更新数据
```java
	JpaUtil.merge(users);
```

15. 标准删除数据
```java
	JpaUtil.remove(user);
```

16. 标准批量删除数据
```java
	JpaUtil.remove(users);
```

17. 条件批量更新数据
```java
	JpaUtil
    	  .linu(User.class)
    	  .le("age", 18)
          .set("salary", 0)
          .update();
```

18. 条件批量删除数据
```java
	JpaUtil
    	  .lind(User.class)
    	  .le("age", 18)
          .delete();
```


