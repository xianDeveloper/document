前言：Springboot 和SpringCloud的 使用其实是很简单的几个注解就可以搞定 剩下的都是MVC 模板来实现代理就行(其他的整合 如整合redis  Kafka 暂且不表)现在讨论一下面试中有可能会问道的问题SpringBoot的工作原理 是如何实现少配置便可以运行的

首先要明白这3个注解的作用：  有错误的地方希望指出 

1.     文件参考： 注解从Spring3.0，@Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器。   @Configuration的使用 ：https://www.cnblogs.com/duanxz/p/7493276.html 
2.     SpringBoot之@EnableAutoConfiguration注解  https://blog.csdn.net/zxc123e/article/details/80222967(注意文章中这一          句重要的话： @Import(AutoConfigurationImportSelector.class)，借助AutoConfigurationImportSelector，                  @EnableAutoConfiguration可以帮助SpringBoot应用将所有符合条件的@Configuration配置都加载到当前SpringBoot创建并使用的IoC容器。
        借助于Spring框架原有的一个工具类：SpringFactoriesLoader的支持，@EnableAutoConfiguration可以智能的自动配置功效才得以大功告成！
        版权声明：本文为CSDN博主「zxc123e」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/zxc123e/article/details/80222967)
3.  Spring Boot学习笔记1：Spring, Spring Boot中的@Component 和@ComponentScan注解用法介绍    https://blog.csdn.net/Lamb_IT/article/details/80918704



注意第2句话中  "在AutoConfigurationImportSelector类中可以看到通过 SpringFactoriesLoader.loadFactoryNames()
把 spring-boot-autoconfigure.jar/META-INF/spring.factories中每一个xxxAutoConfiguration文件都加载到容器中，spring.factories文件里每一个xxxAutoConfiguration文件一般都会有下面的条件注解:
————————————————
版权声明：本文为CSDN博主「zxc123e」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/zxc123e/article/details/80222967 "

启动原理总结：https://blog.csdn.net/dm_vincent/article/details/77619752
#####  我的理解 
我的理解就是 
1.  通过@ComponentScan注解进行bean的扫描 
2.  然后 :@EnableAutoConfiguration 通过 工具类：SpringFactoriesLoader中 SpringFactoriesLoader.loadFactoryNames()的方法把 获去各种名称 META-INF/spring.factories 的自动配置文件  并且去加载文件 
3.  @Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器。
