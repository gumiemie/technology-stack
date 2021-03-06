#开闭原则
##开闭原则的定义
开闭原则（Open Closed Principle OCP）由勃兰特・梅耶 提出,他在1988年的著作《面向对象软件构造》(Object Oriented Software Construction)
中提出：软件实体应该对扩展开放，对修改关闭(Software entities should be open for extension,but closed for modification)。
这里的软件实体包含以下几个部分：
1. 项目中划分的功能模块
2. 类与接口
3. 方法

开闭原则的含义是：当应用的需求改变时，在不修改软件实体的原代码或二进制代码的基础上，可以扩展新的模块，使其满足新的需求

##开闭原则的作用
开闭原则是面向对象程序设计的终极目标,它使软件实体具有一定的灵活性和适应性同时具备稳定性和延伸性,具体来说,作用如下：

1. 对软件测试的影响
软件遵守开闭原则的话，软件测试时需对扩展的代码进行测试，因为原有代码还可以正常运行。

2. 可以提高代码的可复用性
粒度越小，被复用的可能性就越大;在面向对象的程序设计中，根据原子和抽象编程可提高代码的复用性。

3. 可以提高软件的可维护性
遵守开闭原则的软件，其稳定性高和可延续性强，从而易于扩展和维护

##开闭原则的实现方法

可以通过"抽象约束，封闭变化" 来实现开闭原则,即通过接口或抽象类定义一个相对稳定的抽象层，而将相同的可变因素封装在相同的具体实现中
因为抽象灵活性好，适应性广，只要抽象的合理，可以基本保持软件架构的稳定。而从软件中易变的细节可以从抽象派生的来实现类进行扩展，
当软件需要变化时，只需要根据需求来扩展新的实现类就可以了。

