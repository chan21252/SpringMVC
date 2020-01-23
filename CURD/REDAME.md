# SpringMVC Demo：员工信息增删改查
---

## 接口定义

restful风格。

- 查询用户列表 GET /emps
- 添加用户 POST /emp
- 更新用户 PUT /emp/{id}
- 删除用户 DELETE /emp/{id}

## 基本结构

1. DispatcherServlet处理接口请求
2. 调用控制类对应的处理方法，返回结果视图
3. 视图解析器解析模型和视图，转发到结果页面

## 知识点

1. @RequestMapping：映射URL和处理方法，可以指定参数和方法。
2. @PathVariable：URL中的变量传入处理方法参数。
3. @ModelAttribute：
    1. 带有该注解的方法会在处理方法之前被调用，可以向模型Map中添加模型对象。
    2. 带有该注解的处理方法参数，请求参数会替换模型Map中对应模型对象的属性，然后将模型对象传入参数
4. 存储模型对象：
    1. 处理方法、@ModelAttribute注解的方法参数列表添加Map类型，可以在方法中添加模型对象。
    2. @SessionAttributes：指定模型对象的作用域是session域，默认request。
5. 视图
    1. ModelAndView：所有的处理方法的返回值都会被封装为ModelAndView对象。
    2. 视图处理器：
        1. DispatcherServlet的配置文件中定义视图处理器
        2. ord属性可以定义顺序，默认InternalResourceViewResolver
        3. 如果使用了JSTL，Spring4会转为JSTLResolver
6. 静态资源处理：\<mvc:default-servlet-handler/>，没有被映射的URL会自动寻找静态资源。
7. SpringMVC form标签：
    1. ModeAttribute属性：表单属性注入模型Map中同名的对象中
8. HiddenHttpMethodFilter：表单中定义name="_method"字段，value可以是DELTE或PUT，将POST请求转为对应请求。
9. 请求参数绑定流程
    1. webDataBinderFactory实例，根据request对象和要绑定数据的对象，创建DataBinder对象
    2. DataBinder调用ConversionService组件进行数据类型转换、格式校验，将请求参数注入到绑定对象中。
    3. DataBinder调用Validator组件进行参数合法性校验，生成数据绑定结果BindingData对象。
    4. BindingData的对象再传入处理方法。
10. 自定义类型转换器
    1. 实现ConversionService接口，作为IOC容器的组件。
    2. 通过 ConversionServiceFactoryBean的converters属性注册自定义的类型转换器。
    3. 通过\<mvc:annotation-driven conversion-service="自定义类型转换器组件"/>使用服务。
