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
11. \<mvc:annotation-driven/>：
    1. 自动注册：RequestMappingHandlerMapping、RequestMappingHandlerAdapter、ExceptionHandlerExceptionResolver三个bean
    2. 支持ConversionService对表单参数进行类型转换
    3. 支持使用 @NumberFormat annotation、@DateTimeFormat –
       注解完成数据类型的格式化
12. @initBinder：对webDataBinder初始化。
13. 参数校验：
    1. 引入BeanValidation.jar（依赖jboss-logging），hibernate-validator.jar
    2. 实体类的参数上添加校验注解，比如@NotNull，@Past，@Email...
    3. 方法参数之前使用@Valid，SpringMVC会在请求参数绑定到方法对象后，进行校验
    4. 检验结果保存在方法BinderResult类型的参数
    5. annotation-driven会默认装配好一个LocalValidatorFactoryBean
    6. 需校验的Bean对象和其绑定结果对象或错误对象时成对出现的，它们之间不允许声明其他的入参
14. json处理：
    1. 引入阿里巴巴fastjson.jar包
    2. annotation-driven配置message-converters，装配com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter
    3. handler处理方法添加@RequestBody注解
15. 国际化：
    1. LocaleChangeInterceptor：
        1. 获取name=local的请求参数
        2. 解析local参数值为Local对象
        3. 获取LocalResolver对象
    2. SessionLocalResolver
        1. 将Local对象放入Session中
        2. 从Session中获取Local对象
    3. 应用程序使用Local对象
16. 文件上传：
    1. 文件上传由MultipartSolver组件支持
    2. SpringMVC的CommonsMultiPartResolver实现了上传组件，依赖commons fileupload
    3. handler方法入参传入MultiPartFile对象，实现上传
17. 拦截器：
    1. 实现HandlerInterceptor接口
    2. SpringMVC配置拦截器：\<mvc:interceptor>
    3. 拦截器方法调用时机：
        1. 执行handler方法前
        2. 渲染视图前
        3. 完成处理后
    4. 多个拦截器调用顺序：
        1. pre1-》pre2-》..-》preN-》handler-》postN-》...-》post2-》post1-》after2-》after1
        2. preHandler返回true的拦截器一定要执行afterCompetition方法