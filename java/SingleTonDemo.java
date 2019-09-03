





  
/**
 * 单列模式
 */

//  public static void main(String[] args){}


 public class SingleTonDemo{

    private static SingleTonDemo minstance = null;

    private SingeleTonDemo name() {
        
    }


    public static SingleTonDemo getInstance() {

            if (minstance == null ){

                synchronized(SingleTonDemo.class){

                    if(minstance == null ){
                        minstance = new SingleTonDemo();
                    }
                }
            
            }

            return minstance;
    }

    
    /**
 * 静态内部类
 */

// public class Singleton {
//     private static class SingletonHolder {
//    private static final Singleton INSTANCE = new Singleton();
//     }
//     private Singleton (){}
//     public static final Singleton getInstance() {
//    return SingletonHolder.INSTANCE;
//     }
//   }



/**
 * 双检锁写法
 */
// public class Singleton{  
//     private static Singleton single;    //声明静态的单例对象的变量  
//     private Singleton(){}    //私有构造方法   
  
//     public static Singleton getSingle(){    //外部通过此方法可以获取对象    
//       if(single == null){     
//           synchronized (Singleton.class) {   //保证了同一时间只能只能有一个对象访问此同步块        
//               if(single == null){      
//                   single = new Singleton();          
//           }     
//         }  
//       }    
//       return single;   //返回创建好的对象   
//     }  
//   } 


//   思路很简单，就是我们只需要同步（synchronize）初始化instance的那部分代码从而使代码既正确又很有效率。 
// 这就是所谓的“双检锁”机制（顾名思义）。 
// 很可惜，这样的写法在很多平台和优化编译器上是错误的。

// 原因在于：instance = new Singleton()这行代码在不同编译器上的行为是无法预知的。一个优化编译器可以合法地如下实现instance = new Singleton():

// 1. instance  = 给新的实体分配内存

// 2. 调用Singleton的构造函数来初始化instance的成员变量

// 现在想象一下有线程A和B在调用getInstance，线程A先进入，在执行到步骤1的时候被踢出了cpu。然后线程B进入，B看到的是instance  已经不是null了（内存已经分配），于是它开始放心地使用instance，但这个是错误的，因为在这一时刻，instance的成员变量还都是缺省值，A还没有来得及执行步骤2来完成instance的初始化。

// 当然编译器也可以这样实现：

// 1. temp = 分配内存

// 2. 调用temp的构造函数

// 3. instance = temp

// 如果编译器的行为是这样的话我们似乎就没有问题了，但事实却不是那么简单，因为我们无法知道某个编译器具体是怎么做的，因为在Java的memory model里对这个问题没有定义。

// 双检锁对于基础类型（比如int）适用。很显然吧，因为基础类型没有调用构造函数这一步。


}

