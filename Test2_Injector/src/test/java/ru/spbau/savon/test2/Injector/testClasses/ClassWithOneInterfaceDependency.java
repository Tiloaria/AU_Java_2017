package ru.spbau.savon.test2.Injector.testClasses;

public class ClassWithOneInterfaceDependency {

    public Interface dependency;

    public ClassWithOneInterfaceDependency(Interface dependency) {
        this.dependency = dependency;
    }
}