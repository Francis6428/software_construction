# Blog-7 软件构造课程Lab4实验总结
---
## 一些总结

  实验4的主要目的就是实现对于实验3的健壮性的优化和DEBUG四个小程序。首先说一下自己对于实验4的一些理解。

  在实验3里我们写了一个有关于图的ADT，但是更多的考虑的是正确性，在实验4里需要增加考虑健壮性。举个例子来看，就是我们转变了身份角色，从一个"航天器"的程序员，变成一个应用程序的程序员，我们不能再对用户报以“你这都输不对，别用我们的软件了！”这样的回复，而是转为“诶上帝你看，你是不是这个意思”。但是由于在实验3中对于健壮性的考虑可以说是几乎没有，表现在我们以前所有的实验中都没有`@throws`这个关键字出现。我们对于所有的正确性的保证都是建立在`assert`类似的语句之上的。

  这其实对于我们后边再写实验4来说，阻力很大。但是，也不是不能写。我选择了以一种比较麻烦的方式来实现这其中的正确性检验，就是我们尽可能的将所有的判断分散开。换句话就是，下移抛出的位置；并且对于实验中要求的需要重新更换输入文件的`Exception`来说，不在原地处理，而是抛出到一个足够下层(相对于调用栈而言)，再处理。这样的做法相对于另一种比较合理的做法，我仅仅在`GraphFactory`中处理这些异常而言，可能更加难以实现，尤其是在当初设计的时候没有考虑相关异常的情况下，这就导致我前期写实验4写到崩溃。但是有好处，我可以在脱离`GraphFactory`的情况下也能够发现这种错误，而不是仅仅依靠于`GraphFactory`中一些特判。

  另一点就是设计测试用例，感觉100%的覆盖度其实真的很难实现。举个例子说，在我们的`Edge`类中有这么一个函数就是`addVertices(List<Vertex> vertices)`，这个由于我不能直接使用外来的`reference`来调用边内的顶点，所以我使用了`clone`这么一个函数。基于此，编译器会强制我检查`CloneNotSupportedException`这个异常，以保证没有实现`Clone`接口的类调用的时候不会出错。但是，由于我在参数中使用的`Vertex`的顶点类均实现了`Clone`接口，这就导致我尝试传入其他的参数，会无法通过静态编译，而如果不能抛出这个异常，覆盖率就达不到100%。

  所以在实验报告的最后，Mr.Wang也提出了这个问题，对此这里我阐释一下我的看法，就是覆盖率仅仅是一种衡量标准，它反应的是代码的覆盖度而不是分支的覆盖度，我们达到了100%的覆盖度只能说明我们的测试比较完全，但不一定没有遗漏；相对的，我们没有100%的覆盖度也不能说明我们测试一定不好。不论如此，测试优先和回归测试等等方法都能够帮助我们尽量来实现在开发阶段发现问题，这**才是我们设计测试最终的目的，即检验我们的正确性和健壮性**。

  ## 写在实验后
  对于Lab3、Lab4和Lab5，给我的一种感觉是实验的想法是好的，但在实现上总会有或多或少的一些问题存在。这些问题，很大程度上是由于我Lab3写的次，没有达到要求。更多的一些问题在于越靠近最后，实验的密集程度和考试的压力增大让我们没有再像开学初那么充足的精力来实现很细致的细节，Blog也有很久没有更新了，对于Lab3改的时间太久了，下次再写吧。第一年，感觉难免有问题，希望我们留给下一级的同学们的火炬能够照亮他们的路吧。