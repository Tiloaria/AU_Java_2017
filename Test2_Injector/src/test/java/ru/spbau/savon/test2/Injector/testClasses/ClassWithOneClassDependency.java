package ru.spbau.savon.test2.Injector.testClasses;

public class ClassWithOneClassDependency {

    public ClassWithoutDependencies dependency;

    public ClassWithOneClassDependency(ClassWithoutDependencies dependency) {
        this.dependency = dependency;
    }
}