# Spock 学习笔记

* [Java Testing with Spock](https://github.com/kkapelon/java-testing-with-spock) - 源码

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
## Where

### 5.1 SimpleTabularSpec
````
参数测试
|   表示一列
||  表示输入参入与输出参数的分隔符
eg : 3 | 2 || 5
adder.add(first,second)==sum 第一个参数first为3，第二个参数second为2，sum为5
Groovy is an optionally typed language；不需要声明参数类型
test:com.sillyhat.groovy.chapter4.params.SimpleTabularSpec
````

### 5.2 ImageNameValidatorSpec
````
test:com.sillyhat.groovy.chapter4.params.ImageNameValidatorSpec
````

### 5.2 SingleColumnSpec
````
Spock数据表的另一个角落案例是它们必须至少有两列。 如果您正在编写一个只有一个参数的测试，则必须在第二列中使用“填充符”
eg: "screenshot.bmp" || _
test:com.sillyhat.groovy.chapter4.params.SingleColumnSpec
````

### 5.3 LifecycleDataSpec
````
where 声明周期
test:com.sillyhat.groovy.chapter4.lifecycle.LifecycleDataSpec
````

### 5.4 UnrollDataSpec
````
@Unroll 清晰的指出多次测试结果
test:com.sillyhat.groovy.chapter4.lifecycle.UnrollDataSpec
````

### 5.5 UnrollDiscountSpec
````
@Unroll 可以标记在class上
test:com.sillyhat.groovy.chapter4.lifecycle.UnrollDiscountSpec
````



### 2.1 TestCustomer
不连接数据库，直接返回查询结果
test:com.sillyhat.groovy.chapter2.customer.TestCustomer


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
````