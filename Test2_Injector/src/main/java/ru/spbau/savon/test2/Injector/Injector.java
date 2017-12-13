package ru.spbau.savon.test2.Injector;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Have method injector which, create and initialize object of rootClassName.
 */
public class Injector {
    private static List<String> classes;

    private static Class<?> findSolutionClass(Class<?> root) throws Exception {
        Class<?> result = null;

        for (String currClass : classes) {
            Class<?> candidate = Class.forName(currClass);
            if (!root.isAssignableFrom(candidate)) {
                continue;
            }
            if (result != null) {
                System.out.println("It's impossible to uniquely implement a class");
                throw new AmbiguousImplementationException();
            }
            result = candidate;
        }

        if (result == null) {
            throw new ImplementationNotFoundException();
        }

        return result;
    }

    private static Object initializeStep(String rootClassName, Map<String, Object> cntx) throws Exception {
        Class<?> solution = findSolutionClass(Class.forName(rootClassName));
        String solutionName = solution.getName();

        if (cntx.containsKey(solutionName)) {
            Object instance = cntx.get(solutionName);
            if (instance == null) {
                throw new InjectionCycleException();
            }
            return instance;
        }

        cntx.put(solutionName, null);

        Constructor<?> constructor = solution.getConstructors()[0];
        List<Object> params = new ArrayList<>();

        for (Class paramType : constructor.getParameterTypes()) {
            params.add(initializeStep(paramType.getName(), cntx));
        }

        Object result = constructor.newInstance(params.toArray());
        cntx.put(solutionName, result);
        return result;

    }

    /**
     * Create and initialize object of rootClassName class using classes from
     * implementationClassNames.
     */
    public static Object initialize(String rootClassName, List<String> implementationClassNames) throws Exception {
        classes = new ArrayList<>(implementationClassNames);
        classes.add(rootClassName);
        return initializeStep(rootClassName, new HashMap<>());
    }
}