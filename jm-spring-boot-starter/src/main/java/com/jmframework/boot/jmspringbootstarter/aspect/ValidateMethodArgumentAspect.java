package com.jmframework.boot.jmspringbootstarter.aspect;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <h1>ValidateMethodArgumentAspect</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-07-06 12:17
 **/
@Slf4j
@Aspect
@Component
public class ValidateMethodArgumentAspect {
    private final Validator validator;

    public ValidateMethodArgumentAspect() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Define pointcut. Pointcut is a predicate or expression that matches join points. In
     * ValidateMethodArgumentAspect, we need to cut any method annotated with `@ValidateArgument` only.
     * <p>
     * More detail at: <a href="https://howtodoinjava.com/spring-aop/aspectj-pointcut-expressions/">Spring aop aspectJ
     * pointcut expression examples</a>
     */
    @Pointcut("@annotation(com.jmframework.boot.jmspringbootstarter.annotation.ValidateArgument)")
    public void validateMethodArgumentPointcut() {
    }

    /**
     * Before handle method's argument. In this phrase, we're going to take some logs.
     * <p>
     * `@Before` annotated methods run exactly before the all methods matching with pointcut expression.
     *
     * @param joinPoint a point of execution of the program
     */
    @Before("validateMethodArgumentPointcut()")
    public void beforeMethodHandleArgument(JoinPoint joinPoint) {
        log.info("Method           : {}#{}",
                 joinPoint.getSignature().getDeclaringTypeName(),
                 joinPoint.getSignature().getName());
        log.info("Argument         : {}", joinPoint.getArgs());
    }

    /**
     * Around annotated method processes argument. Around advice can perform custom behavior before and after the
     * method invocation.
     *
     * @param proceedingJoinPoint the object can perform method invocation
     * @return any value (may be void) that annotated method returned
     */
    @Around("validateMethodArgumentPointcut()")
    public Object aroundMethodHandleArgument(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("=========================== METHOD'S ARGUMENT VALIDATION START ===========================");
        Object[] args = proceedingJoinPoint.getArgs();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
        List<Integer> argumentIndexes = new ArrayList<>();
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            int paramIndex = ArrayUtil.indexOf(parameterAnnotations, parameterAnnotation);
            for (Annotation annotation : parameterAnnotation) {
                if (annotation instanceof Valid) {
                    argumentIndexes.add(paramIndex);
                }
            }
        }
        for (Integer index : argumentIndexes) {
            Set<ConstraintViolation<Object>> validates = validator.validate(args[index]);
            if (CollectionUtil.isNotEmpty(validates)) {
                log.error("Method's argument is not valid: {}", validates);
                String message = String.format("Argument validation failed: %s", validates);
                log.info("Method           : {}#{}",
                         proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                         proceedingJoinPoint.getSignature().getName());
                log.info("Argument         : {}", args);
                log.info("Validation result: {}", message);
                throw new IllegalArgumentException(message);
            }
        }
        log.info("Validation result: Validation passed");
        return proceedingJoinPoint.proceed();
    }

    /**
     * `@After` annotated methods run exactly after the all methods matching with pointcut expression.
     */
    @After("validateMethodArgumentPointcut()")
    public void afterMethodHandleArgument() {
        log.info("============================ METHOD'S ARGUMENT VALIDATION END ============================");
    }

    /**
     * `@AfterThrowing` annotated methods run after the method (matching with pointcut expression) exits by throwing an
     * exception.
     *
     * @param joinPoint a point of execution of the program
     * @param e         exception that controller's method throws
     */
    @AfterThrowing(pointcut = "validateMethodArgumentPointcut()", throwing = "e")
    public void afterThrowingException(JoinPoint joinPoint, Exception e) {
        log.info("Signature      : {}", joinPoint.getSignature().toShortString());
        log.info("Exception Info : {}", e.toString());
        log.info("==================== METHOD'S ARGUMENT VALIDATION END WITH EXCEPTION =====================");
    }
}
