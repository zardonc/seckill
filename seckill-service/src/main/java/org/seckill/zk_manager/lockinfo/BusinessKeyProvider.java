package org.seckill.zk_manager.lockinfo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.seckill.zk_manager.annotation.LockKey;
import org.seckill.zk_manager.annotation.ZkLock;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: 获取用户定义业务key
 * 〈〉
 *
 * @Param:
 * @Return:
 * @Author: lang
 */
@Component
public class BusinessKeyProvider {
    public static final String LOCK_NAME_SEPARATOR = "/";

    private final ParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    private final ExpressionParser parser = new SpelExpressionParser();

    public String getKeyName(ProceedingJoinPoint joinPoint, ZkLock zkLock) {
        List<String> keyList = new ArrayList<>();
        Method method = getMethod(joinPoint);
        //获取方法zkLock注解上的自定义keys
        List<String> definitionKeys = getSpelDefinitionKey(zkLock.keys(), method, joinPoint.getArgs());
        keyList.addAll(definitionKeys);
        List<String> parameterKeys = getParameterKey(method.getParameters(), joinPoint.getArgs());
        keyList.addAll(parameterKeys);
        return StringUtils.collectionToDelimitedString(keyList, "", LOCK_NAME_SEPARATOR, "");
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return method;
    }

    /**
     * 功能描述: 获取zklock注解上的自定义keys
     * 〈〉
     *
     * @Param:
     * @Return:
     * @Author: lang
     */
    private List<String> getSpelDefinitionKey(String[] definitionKeys, Method method, Object[] parameterValues) {
        List<String> definitionKeyList = new ArrayList<>();
        for (String str : definitionKeyList) {
            if (str != null && !str.isBlank()) {
                EvaluationContext context = new MethodBasedEvaluationContext(null, method , parameterValues, nameDiscoverer);
                String key = parser.parseExpression(str).getValue(context).toString();
                definitionKeyList.add(key);
            }
        }
        return definitionKeyList;
    }

    /**
     * 功能描述: 获取LockKey上的内容
     * 〈〉
     * @Param:
     * @Return:
     * @Author: lang
     */
    private List<String> getParameterKey(Parameter[] parameters, Object[] parameterValues) {
        List<String> parameterKey = new ArrayList<>();
        //遍历参数
        for (int i = 0; i < parameters.length; i++) {
            //参数上带有注解
            if (parameters[i].getAnnotation(LockKey.class) != null) {
                LockKey keyAnnotation = parameters[i].getAnnotation(LockKey.class);
                //注解的内容不为空
                if (keyAnnotation.value().isEmpty()) {
                    parameterKey.add(parameterValues[i].toString());
                } else {
                    StandardEvaluationContext context = new StandardEvaluationContext(parameterValues[i]);
                    String key = parser.parseExpression(keyAnnotation.value()).getValue(context).toString();
                    parameterKey.add(key);
                }
            }
        }
        return parameterKey;
    }
}
