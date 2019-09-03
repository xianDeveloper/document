#### 1 什么是spring ioc 和aop
        ioc : 通俗来说就是控制反转；IOC只是一个概念或是说是设计思想，在java开发中，IOC 控制反
            转意味着将你设计好的对象交给容器控制，而不是在你的对象内部直接控制。因此实际上是指获取对象依赖的方式发生了反转，都交给了Spring IOC容器去做
        DI   dependency injection  依赖注入    是组件直接依赖关系由容器这运行期间决定：由容器动态的将某个依赖关系注入到组件之中
        总结： IOC 是在表述容器的核心设计思想，而DI 则表达了容器的主要实现方式，两者从不同角度来描述了SpringIOC 容器 实际上是指向相同的内容


#### 2  IOC和DI 的意义
        
        依赖注入的目的并非是为软件系统带来更多的功能，而是为了提高组件重用的频率，通过依赖注入机制，我们只需要通过简单的配置，而无需任何代码就可以指定目标需要的资源，完成自身的业务逻辑，而不需要要关心具体的资源来自何处，由谁实现    


        由原先的高度耦合的代码 交由Spring容器来控制之后，spring 创建好的对象A，B，C 存到容器中 当A对象需要使用B时 就从容器中取出交给A使用，具体是什么时候创建的，如何创建的 A对象都不需要关系 ，只需要拿来就用


        总结： 控制反转IOC inversion of control 是说创建对象的控制权 进行转移，以前由对象的主动创建  交付给第三方 IOC容器  IOC 容器就是一个对象工厂 你需要时什么 我就给你创建什么，有了IOC 之后 原先的依赖关系就没有了 都依赖于IOC容器 通过IOC 容器来建立他们之间的关系 ， 统一管理 统一调度  解耦合 

#### Spring 中 AOP 的应用场景、Aop 原理、好处？

      答：AOP 用来封装横切关注点，具体可以在下面的 场景中使用: Authentication 权限、Caching缓存、Contextpassing内容传递、Errorhandling错误处理 Lazy loading 懒加载、Debugging 调试、logging,tracing,profilingandmonitoring 记录跟踪优化 校准、Performanceoptimization 性能优化、Persistence持久化、Resourcepooling 资源池、 Synchronization 同步、Transactions事务

        原理：AOP 是面向切面编程，是通过代理的方式为程序添加统一功能，集中解决一些公 共问题。

        好处：1.各个步骤之间的良好隔离性 2.源代码无关性

####  Spring 中 IOC 的作用与原理？对象创建的过程。

        答：当某个角色需要另外一个角色协助的时候，在传统的程序设计过程中，通常由调用 者来创建被调用者的实例。但在 spring 中创建被调用者的工作不再由调用者来完成，因此称 为控制反转。创建被调用者的工作由 spring 来完成，然后注入调用者。

        IOC 本质上是一个容器，已 MAP 对 IOC 简单举例，服务器加载配置文件，由 xml 文档 解析工具读取 bean 的 ID，获取 class，使用反射创建对象，以 K-V 的形式存入 MAP，K 是 ID， V 是反射创建的对象。获取对象可以调用 context.getBean（K）的方式。

####  介绍 spring 框架

        它是一个 full-stack 框架，提供了从表现层到业务层再到持久层的一套完整的解决方案。我 们在项目中可以只使用 spring 一个框架，它就可以提供表现层的 mvc 框架，持久层的 Dao 框架。它的两大核心 IoC 和 AOP 更是为我们程序解耦和代码简洁易维护提供了支持。

####  Spring 常见创建对象的注解？

        答：@Component@Controller@Service@Repository

####  Spring 中用到的设计模式

        答：简单工厂、工厂方法、单例模式、适配器、包装器、代理、观察者、策略、模板方法 详细介绍自己查了。

####  Spring 的优点？

    （1）降低了组件之间的耦合性，实现了软件各层之间的解耦

    （2）可以使用容易提供的众多服务，如事务管理，消息服务等

    （3）容器提供单例模式支持

    （4）容器提供了 AOP 技术，利用它很容易实现如权限拦截，运行期监控等功能

    （5）容器提供了众多的辅助类，能加快应用的开发

    （6）spring 对于主流的应用框架提供了集成支持，如 hibernate，JPA，Struts 等

    （7）spring 属于低侵入式设计，代码的污染极低

    （8）独立于各种应用服务器

    （9）spring 的 DI 机制降低了业务对象替换的复杂性

    （10）Spring 的高度开放性，并不强制应用完全依赖于 Spring，开发者可以自由选择 spring 的部分或全部

####  SpringBean 的作用域之间有什么区别？

    Spring 容器中的 bean 可以分为 5 个范围。所有范围的名称都是自说明的，但是为了避 免混淆，还是让我们来解释一下：

    singleton：这种 bean 范围是默认的，这种范围确保不管接受到多少个请求，每个容器 中只有一个 bean 的实例，单例的模式由 beanfactory 自身来维护。

    prototype：原形范围与单例范围相反，为每一个 bean 请求提供一个实例。黑马程序员

    request：在请求 bean 范围内会每一个来自客户端的网络请求创建一个实例，在请求完成以 后，bean 会失效并被垃圾回收器回收。

    Session：与请求范围类似，确保每个 session 中有一个 bean 的实例，在 session 过期后， bean 会随之失效。

    global-session：global-session 和 Portlet 应用相关。当你的应用部署在 Portlet 容器中工 作时，它包含很多 portlet。如果你想要声明让所有的 portlet 共用全局的存储变量的话，那 么这全局变量需要存储在 global-session 中。全局作用域与 Servlet 中的 session 作用域效果相同。

####  Spring 管理事务有几种方式？

    答：有两种方式：

    （1）编程式事务，在代码中硬编码。(不推荐使用)

    （2）声明式事务，在配置文件中配置（推荐使用）声明式事务又分为两种：

    a、基于 XML 的声明式事务

    b、基于注解的声明式事务

#### spring 中自动装配的方式有哪些？

    （1）No：即不启用自动装配。

    （2）byName：通过属性的名字的方式查找 JavaBean 依赖的对象并为其注入。比如说类 Computer 有个属性 printer，指定其 autowire 属性为 byName 后，Spring IoC 容器会在配置文 件中查找 id/name 属性为 printer 的 bean，然后使用 Seter 方法为其注入。

    （3）byType：通过属性的类型查找 JavaBean 依赖的对象并为其注入。比如类 Computer 有个属性 printer，类型为 Printer，那么，指定其 autowire 属性为 byType 后，SpringIoC 容 器会查找 Class 属性为 Printer 的 bean，使用 Seter 方法为其注入。

    （4）constructor：通 byType 一样，也是通过类型查找依赖对象。与 byType 的区别在于 它不是使用 Seter 方法注入，而是使用构造子注入。

    （5）autodetect：在 byType 和 constructor 之间自动的选择注入方式。

    （6）default：由上级标签<beans>的 default-autowire 属性确定。

#### spring 中的核心类有那些，各有什么作用？

    BeanFactory：产生一个新的实例，可以实现单例模式

    BeanWrapper：提供统一的 get 及 set 方法

    ApplicationContext:提供框架的实现，包括 BeanFactory 的所有功能

#### Bean 的调用方式有哪些？

    答：有三种方式可以得到 Bean 并进行调用：

    （1）使用 BeanWrapper

        HelloWorld hw=new HelloWorld();

        BeanWrapper bw=new BeanWrapperImpl(hw);

        bw.setPropertyvalue(”msg”,”HelloWorld”);

        System.out.println(bw.getPropertyCalue(”msg”));


    （2）使用 BeanFactory

            InputStream is=new FileInputStream(”config.xml”);

            XmlBeanFactory factory=new XmlBeanFactory(is);

            HelloWorld hw=(HelloWorld) factory.getBean(”HelloWorld”);

            System.out.println(hw.getMsg());

    （3）使用 ApplicationConttext

            ApplicationContext actx=new FleSystemXmlApplicationContext(”config.xml”);

            HelloWorld hw=(HelloWorld) actx.getBean(”HelloWorld”);

            System.out.println(hw.getMsg());

####  什么是 IOC，什么又是 DI，他们有什么区别？

        答：依赖注入 DI 是一个程序设计模式和架构模型，一些时候也称作控制反转，尽管在 技术上来讲，依赖注入是一个 IOC 的特殊实现，依赖注入是指一个对象应用另外一个对象来 提供一个特殊的能力，例如：把一个数据库连接已参数的形式传到一个对象的结构方法里 面而不是在那个对象内部自行创建一个连接。

        控制反转和依赖注入的基本思想就是把类的依 赖从类内部转化到外部以减少依赖 应用控制反转，对象在被创建的时候，由一个调控系统内所有对象的外界实体，将其所 依赖的对象的引用，传递给它。也可以说，依赖被注入到对象中。所以，控制反转是，关 于一个对象如何获取他所依赖的对象的引用，这个责任的反转。

        ####  spring 有两种代理方式：

        （1）若目标对象实现了若干接口，spring 使用 JDK 的 java.lang.reflect.Proxy 类代理。

        优点：因为有接口，所以使系统更加松耦合

        缺点：为每一个目标类创建接口

        （2）若目标对象没有实现任何接口，spring 使用 CGLIB 库生成目标对象的子类。

        优点：因为代理类与目标类是继承关系，所以不需要有接口的存在。

        缺点：因为没有使用接口，所以系统的耦合性没有使用 JDK 的动态代理好。

#### springMVC 的流程？

        （1）用户发送请求至前端控制器 DispatcherServlet

        （2）DispatcherServlet 收到请求调用 HandlerMapping 处理器映射器。

        （3）处理器映射器根据请求 url 找到具体的处理器，生成处理器对象及处理器拦截器(如果 有则生成)一并返回给 DispatcherServlet。

        （4）DispatcherServlet 通过 HandlerAdapter 处理器适配器调用处理器

        （5）执行处理器(Controller，也叫后端控制器)。

        （6）Controller 执行完成返回 ModelAndView

        （7）HandlerAdapter 将 controller 执行结果 ModelAndView 返回给 DispatcherServlet

        （8）DispatcherServlet 将 ModelAndView 传给 ViewReslover 视图解析器

        （9）ViewReslover 解析后返回具体 View

        （10）DispatcherServlet 对 View 进行渲染视图（即将模型数据填充至视图中）。

        （11）DispatcherServlet 响应用户
#### Springmvc 的优点

        （1）它是基于组件技术的.全部的应用对象,无论控制器和视图,还是业务对象之类的都是java 组件.并且和 Spring 提供的其他基础结构紧密集成.

        （2）不依赖于 ServletAPI(目标虽是如此,但是在实现的时候确实是依赖于 Servlet 的)

        （3）可以任意使用各种视图技术,而不仅仅局限于 JSP4.支持各种请求资源的映射策略5.它应是易于扩展的

