# Spock 学习笔记

* [Java Testing with Spock](https://github.com/kkapelon/java-testing-with-spock) - 笔记源码

## 1、基础语法

### 1.1 HelloGroovy
````
test:com.sillyhat.groovy.chapter1.HelloGroovy.groovy
````

### 1.2 GettersSettersDemo
````
test:com.sillyhat.groovy.chapter1.GettersSettersDemo
````

### 1.3 GroovyAndJava
````
test:com.sillyhat.groovy.chapter1.GroovyAndJava.groovy
````

### 1.4 GroovyMethod
````
test:com.sillyhat.groovy.chapter1.GroovyMethod.groovy
````

### 1.5 PersonSpec
````
test:com.sillyhat.groovy.chapter1.PersonSpec
````

### 1.6 DefDemoSpec
````
test:com.sillyhat.groovy.chapter1.DefDemoSpec
````

### 1.7 GroovyObjectSpec
````
test:com.sillyhat.groovy.chapter1.GroovyObjectSpec
````

### 1.8 GroovyTruth
````
test:com.sillyhat.groovy.chapter1.GroovyTruth
````

### 1.9 ReadFile
````
test:com.sillyhat.groovy.chapter1.GroovyObjectSpec
````

### 1.10 GroovyClosureSpec
````
test:com.sillyhat.groovy.chapter1.GroovyClosureSpec
````

## 2、基础语法

### 2.1 FireSensorSpec
````
基本block模式
test:com.sillyhat.groovy.chapter2.fire1.FireSensorSpec
````

### 2.2 NuclearReactorSpec
````
Mock 数据方式
test:com.sillyhat.groovy.chapter2.nuclear.NuclearReactorSpec
````

### 2.3 CoolantSensorSpec
````
>>> [prev, current]  第一次查询返回prev,第二次查询返回current
test:com.sillyhat.groovy.chapter2.coolant.CoolantSensorSpec
````

### 2.4 ImprovedCoolantSensorSpec
````
test:com.sillyhat.groovy.chapter2.coolant.ImprovedCoolantSensorSpec
````

## 3、Block

### 3.1 SetupBlockSpec
````
test:com.sillyhat.groovy.chapter3.block.SetupBlockSpec
````

### 3.2 ExpectBlockSpec
````
test:com.sillyhat.groovy.chapter3.block.ExpectBlockSpec
````

### 3.3 CleanupBlockSpec
````
test:com.sillyhat.groovy.chapter3.block.CleanupBlockSpec
````

### 3.4 BasketWeightSpec1
````
test:com.sillyhat.groovy.chapter3.block.BasketWeightSpec1
````

### 3.5 BasketWeightSpec2
````
test:com.sillyhat.groovy.chapter3.block.BasketWeightSpec2
````

### 3.6 BasketWeightDetailedSpec
````
标准
test:com.sillyhat.groovy.chapter3.block.BasketWeightDetailedSpec
````

### 3.7 CommonCleanupSpec
````
public block
test:com.sillyhat.groovy.chapter3.block.CommonCleanupSpec
````

### 3.8 LifecycleSpec
````
生命周期
test:com.sillyhat.groovy.chapter3.lifecycle.LifecycleSpec
````

### 3.9 SharedSpec
````
Spock每个测试方法都会有一个独立的fileds，但在一些特殊的情况下，
需要测试方法之间共享变量，这时候只需要使用 @Shared 即可。
test:com.sillyhat.groovy.chapter3.lifecycle.SharedSpec
````

### 3.10 OldMethodSpec
````
old()方法，允许使用when之前的状态
test:com.sillyhat.groovy.chapter3.lifecycle.OldMethodSpec
````

### 3.11 ToStringSpec
````
重写toString方法可以在异常时，看到更多信息
test:com.sillyhat.groovy.chapter3.lifecycle.ToStringSpec
````

### 3.12 HamcrestMatchersSpec
````
判断List中是否存在某元素的几种方式（Hamcrest）
test:com.sillyhat.groovy.chapter3.structure.HamcrestMatchersSpec
````

### 3.13 GroupingAssertionsSpec
````
分组测试，测试代码过长，使代码可读性变差。alt > grouping > old
test:com.sillyhat.groovy.chapter3.structure.GroupingAssertionsSpec
````
## 4、Where

### 4.1 SimpleTabularSpec
````
参数测试
|   表示一列
||  表示输入参入与输出参数的分隔符
eg : 3 | 2 || 5
adder.add(first,second)==sum 第一个参数first为3，第二个参数second为2，sum为5
Groovy is an optionally typed language；不需要声明参数类型
test:com.sillyhat.groovy.chapter4.params.SimpleTabularSpec
````

### 4.2 ImageNameValidatorSpec
````
test:com.sillyhat.groovy.chapter4.params.ImageNameValidatorSpec
````

### 4.2 SingleColumnSpec
````
Spock数据表的另一个角落案例是它们必须至少有两列。 如果您正在编写一个只有一个参数的测试，则必须在第二列中使用“填充符”
eg: "screenshot.bmp" || _
test:com.sillyhat.groovy.chapter4.params.SingleColumnSpec
````

### 4.3 LifecycleDataSpec
````
where 声明周期
test:com.sillyhat.groovy.chapter4.lifecycle.LifecycleDataSpec
````

### 4.4 UnrollDataSpec
````
@Unroll 清晰的指出多次测试结果
test:com.sillyhat.groovy.chapter4.lifecycle.UnrollDataSpec
````

### 4.5 UnrollDiscountSpec
````
@Unroll 可以标记在class上
test:com.sillyhat.groovy.chapter4.lifecycle.UnrollDiscountSpec
````

### 4.6 BasicPipesSpec
````
data pipes  
区间  20-80 (20..80)
test:com.sillyhat.groovy.chapter4.pipes.BasicPipesSpec
````

### 4.7 BasicPipesSpec
````
test:com.sillyhat.groovy.chapter4.pipes.DerivedValuesSpec
````

### 4.8 BasicPipesSpec
````
test:com.sillyhat.groovy.chapter4.pipes.FileReadingSpec
````

## 5、Stub and Mock

### 5.1 StubsInStubsSpec
````
当测试的Method内部需要使用其他接口时，可以预定义其调用接口的返回值
test:com.sillyhat.groovy.chapter5.stubs.StubsInStubsSpec
````

### 5.2 SimpleStubbingSpec
````
 >>定义返回结果，和指定参数返回结果
test:com.sillyhat.groovy.chapter5.stubs.SimpleStubbingSpec
````

### 5.3 SequenceStubbingSpec
````
>>> 定义第一次查询返回结果，第二次查询返回结果，以此类推
test:com.sillyhat.groovy.chapter5.stubs.SequenceStubbingSpec
````

### 5.4 ArgumentStubbingSpec
````
不论传入参数为什么，都返回指定结果
test:com.sillyhat.groovy.chapter5.stubs.ArgumentStubbingSpec
````

### 5.5 ExceptionStubbingSpec
````
异常处理
test:com.sillyhat.groovy.chapter5.stubs.ExceptionStubbingSpec
````

### 5.6 DynamicStubbingSpec
````
汇总
test:com.sillyhat.groovy.chapter5.stubs.DynamicStubbingSpec
````

### 5.7 OrderMockingSpec
````
调用mock数据的接口存在顺序，并且需要验证调用次数
eg:
1 * creditCardSevice.sale( _, _)   先调用了 1 次creditCardSevice.sale
1 * creditCardSevice.shutdown()    再调用了 1 次creditCardSevice.shutdown
test:com.sillyhat.groovy.chapter5.mocks.OrderMockingSpec
````

### 5.8 CardinalityMockingSpec
````
0 * inventory._   inventory的其他方法都是零次调用
0 * _             类的其他方法被调用零次
_ * inventory.isEmpty() >> false   isEmpty 可以被调用任意次数，但是一定返回false
eg:
1 * creditCardSevice.sale( _, _)   先调用了 1 次creditCardSevice.sale
1 * creditCardSevice.shutdown()    再调用了 1 次creditCardSevice.shutdown
test:com.sillyhat.groovy.chapter5.mocks.OrderMockingSpec
````

### 5.9 ArgumentTypeVerificationSpec
````
2 * inventory.isProductAvailable(!null ,1) >> true   isProductAvailable被调用两次，并且当第一个参数非空，第二个参数为1是，返回true
test:com.sillyhat.groovy.chapter5.mocks.ArgumentTypeVerificationSpec
````

### 5.10 ComplexMockingSpec
````
1 * creditCardSevice.authorize(1550, customer) >>  CreditCardResult.NOT_ENOUGH_FUNDS     
creditCardSevice.authorize被调用1次并且当第一个参数是1550，第二个参数是customer对象时，返回CreditCardResult.NOT_ENOUGH_FUNDS
test:com.sillyhat.groovy.chapter5.mocks.ComplexMockingSpec
````

### 5.11 ArgumentVerificationSpec
````
1 * creditCardSevice.sale({amount -> amount == basket.findOrderPrice()}, { client -> client.vip == false})   
creditCardSevice.sale 被调用1次，并且第一个参数是basket.findOrderPrice()的返回值，第二个参数是一个有vip字段的对象，并且vip是false
test:com.sillyhat.groovy.chapter5.mocks.ArgumentVerificationSpec
````

## 6、Page And Rest Client
### 6.1 GroovyRestClientSpec
````
1 * creditCardSevice.sale({amount -> amount == basket.findOrderPrice()}, { client -> client.vip == false})   
creditCardSevice.sale 被调用1次，并且第一个参数是basket.findOrderPrice()的返回值，第二个参数是一个有vip字段的对象，并且vip是false
test:com.sillyhat.groovy.chapter5.mocks.ArgumentVerificationSpec
````

数据表
数据管道
## 7、Jenkins Test
````
./gradlew groovy-test:test
````

````
Chapter 1 
is a bird’s-eye view of Spock, explaining its position in the Java ecosystem, 
the roles it plays in the testing process, and a brief comparison with JUnit. 
Feel free to skip this chapter if you’re a seasoned Java developer 
and have already written a lot of JUnit tests.

Chapter 2 
is a crash course in the Groovy programming language for Java developers. 
I promised that I don’t assume any Groovy knowledge on your part, 
and this chapter keeps that promise. In it, I specifically focus only
on Groovy features that are useful to Spock tests.
By the end of this chapter, you’ll be fully primed for reading 
and writing the Spock Groovy syntax. If you’re interested 
in learning the whole Groovy package (for writing production code 
and not just unit tests), you can think of this chapter as a stepping stone 
to full Groovy nirvana. If you already know your way around Groovy code 
(and are familiar with closures and expandos), you can safely skip this chapter.

Chapter 3 
demonstrates the three major facets of Spock 
(core testing, parameterized tests, and mocking/stubbing). 
These are presented via a series of testing scenarios for which the Java production 
code is already available and you’re tasked with the unit tests. 
All the examples present that same functionality in both Spock and JUnit/Mockito 
so that you can draw your own conclusions on the readability and clarity of the test code. 
Chapter 3 acts as a hub for the rest of the book, as you can see which facet 
of Spock interests you for your own application.

Chapter 4 
the central chapter of the whole book—shows the individual parts of a Spock unit test (which are called blocks), 
their purpose, significance, and expected structure. This chapter also explains the lifecycle of a Spock test, 
the documentation annotations, and the facilities offered by Spock that affect the readability of a unit test. 
Make sure that you’ve mastered the topics of this chapter before moving on to the rest of the book.

Chapter 5 
focuses on parameterized tests. Parameterized tests are unit tests that always 
test the same scenario with different input and output parameters. 
Depending on your application, you may have one or two parameterized tests (among your vanilla unit tests),
or you may be overwhelmed with parameterized tests of multiple parameter combinations.
Parameterized tests in Spock are a breath of fresh air compared to existing solutions, 
as Spock allows you to directly embed into source code the business description of input/output parameters.

Chapter 6 
focuses on the mocking capabilities of Spock. Unlike other test frameworks, 
Spock has built-in support for creating mocks without the need of an external library.
The way it sets up mocks and instructs them on their expected behavior is 
one of the huge changes that set it apart from its competitors. 
If you’ve already worked with Mockito, 
you’ll truly appreciate the simplicity of Spock mocking. 
Again, depending on your application, you may need mocking in a few special cases or in all your unit tests.

Chapter 7 examines the use of Spock in the full testing lifecycle of an enterprise application. 
Spock can cover trivial plain unit tests, larger integration tests, and even functional tests. 
Several examples (mostly with Spring) show that Spock allows you to reuse your favorite Java testing tools with zero additional effort. 
At the same time, Spock can employ Groovy testing libraries, which may be more appropriate in your specific application.

Chapter 8 builds upon the knowledge of all previous chapters by describing corner cases that need special attention 
in your Enterprise Spock tests. It describes several additional Spock annotations that enable/disable the running of 
a test in a static or dynamic way and then shows you how to refactor large Spock tests with helper methods. 
The chapter finishes with a demonstration of Spock spies, a feature that I explicitly advise you not to use.
````