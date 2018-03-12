package ru.spbau.savon.test2.Injector;

import org.junit.Test;
import ru.spbau.savon.test2.Injector.testClasses.ClassWithOneClassDependency;
import ru.spbau.savon.test2.Injector.testClasses.ClassWithOneInterfaceDependency;
import ru.spbau.savon.test2.Injector.testClasses.ClassWithoutDependencies;
import ru.spbau.savon.test2.Injector.testClasses.InterfaceImpl;

import java.util.Collections;

import static org.junit.Assert.*;

public class TestInjector {

    @Test
    /**
     * Injector should initialize class without dependencies.
     */
    public void withoutDependencies()
            throws Exception {
        Object object = Injector.initialize("ru.spbau.savon.test2.Injector.testClasses.ClassWithoutDependencies", Collections.emptyList());
        assertEquals(true, object instanceof ClassWithoutDependencies);
    }

    @Test
    /**
     * Injector should initialize class with one class dependency.
     */
    public void withOneClassDependency()
            throws Exception {
        Object object = Injector.initialize(
                "ru.spbau.savon.test2.Injector.testClasses.ClassWithOneClassDependency",
                Collections.singletonList("ru.spbau.savon.test2.Injector.testClasses.ClassWithoutDependencies")
        );
        assertEquals(true, object instanceof ClassWithOneClassDependency);
        ClassWithOneClassDependency instance = (ClassWithOneClassDependency) object;
        assertEquals(true, instance.dependency != null);
    }

    @Test
    /**
     * Injector should initialize class with one interface dependency.
     */
    public void oneInterfaceDependency()
            throws Exception {
        Object object = Injector.initialize(
                "ru.spbau.savon.test2.Injector.testClasses.ClassWithOneInterfaceDependency",
                Collections.singletonList("ru.spbau.savon.test2.Injector.testClasses.InterfaceImpl")
        );
        assertEquals(true, object instanceof ClassWithOneInterfaceDependency);
        ClassWithOneInterfaceDependency instance = (ClassWithOneInterfaceDependency) object;
        assertEquals(true, instance.dependency instanceof InterfaceImpl);
    }
}
