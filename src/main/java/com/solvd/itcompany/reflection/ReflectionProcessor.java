package com.solvd.itcompany.reflection;

import com.solvd.itcompany.annotation.ReviewRequired;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ReflectionProcessor {

    public static void analyzeClass(Class<?> targetClass) {
        System.out.println("\nREFLECTION ANALYSIS for Class: " + targetClass.getName());

        // 1. Class Modifiers and Annotations
        System.out.println("\n[Class Info]");
        int classModifiers = targetClass.getModifiers();
        System.out.println("Modifiers: " + Modifier.toString(classModifiers));
        System.out.println("Annotations: " + Arrays.stream(targetClass.getAnnotations())
                .map(a -> "@" + a.annotationType().getSimpleName())
                .collect(Collectors.joining(", ")));

        // 2. Fields
        System.out.println("\n[Fields]");
        for (Field field : targetClass.getDeclaredFields()) {
            System.out.printf("  %s %s %s%n",
                    Modifier.toString(field.getModifiers()),
                    field.getType().getSimpleName(),
                    field.getName());

            // Handle Custom Annotation on Fields
            if (field.isAnnotationPresent(ReviewRequired.class)) {
                ReviewRequired review = field.getAnnotation(ReviewRequired.class);
                System.out.printf("    - ANNOTATION: @ReviewRequired(reviewer=%s, priority=%d)%n", review.reviewer(), review.priority());
            }
        }

        // 3. Constructors
        System.out.println("\n[Constructors]");
        for (Constructor<?> constructor : targetClass.getDeclaredConstructors()) {
            String params = Arrays.stream(constructor.getParameterTypes())
                    .map(Class::getSimpleName)
                    .collect(Collectors.joining(", "));
            System.out.printf("  %s %s(%s)%n",
                    Modifier.toString(constructor.getModifiers()),
                    targetClass.getSimpleName(),
                    params);
        }

        // 4. Methods
        System.out.println("\n[Methods]");
        for (Method method : targetClass.getDeclaredMethods()) {
            String params = Arrays.stream(method.getParameterTypes())
                    .map(Class::getSimpleName)
                    .collect(Collectors.joining(", "));
            String returnType = method.getReturnType().getSimpleName();
            System.out.printf("  %s %s %s(%s)%n",
                    Modifier.toString(method.getModifiers()),
                    returnType,
                    method.getName(),
                    params);

            // Handle Custom Annotation on Methods
            if (method.isAnnotationPresent(ReviewRequired.class)) {
                ReviewRequired review = method.getAnnotation(ReviewRequired.class);
                System.out.printf("    - ANNOTATION: @ReviewRequired(reviewer=%s, priority=%d, date=%s)%n", review.reviewer(), review.priority(), review.date());
            }
        }
    }

    // Create an object and call public methods
    public static Object instantiateAndCallMethod(Class<?> targetClass, Object[] constructorArgs, String methodName, Class<?>[] methodParamTypes, Object[] methodArgs) throws Exception {

        // 1. Instantiate the object (assuming a no-arg constructor for ITCompanyService)
        Constructor<?> constructor = targetClass.getDeclaredConstructor();
        Object instance = constructor.newInstance();
        System.out.println("\n[Reflection Invocation] Successfully instantiated: " + targetClass.getSimpleName());

        // 2. Find the public method
        Method method = targetClass.getMethod(methodName, methodParamTypes);
        System.out.println("[Reflection Invocation] Invoking method: " + method.getName());

        // 3. Call the method
        return method.invoke(instance, methodArgs);
    }
}