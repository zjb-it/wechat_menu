// package com.feiyu.aop;
//
// import cn.hutool.http.Method;
// import com.alibaba.fastjson.JSON;
// import com.alibaba.fastjson.serializer.ValueFilter;
// import lombok.extern.slf4j.Slf4j;
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Pointcut;
// import org.springframework.stereotype.Component;
// import org.springframework.web.context.request.RequestAttributes;
// import org.springframework.web.context.request.RequestContextHolder;
// import org.springframework.web.context.request.ServletRequestAttributes;
//
//
// /**
//  * Created by zjb on 18-6-29.
//  */
// @Aspect
// @Component
// @Slf4j
// public class LogAop {
//
//     @Pointcut("execution(* com.feiyu.controller..*.**(..))")
//     public void controllerPointcut() {
//
//     }
//
//     @Around(value = "controllerPointcut()")
//     public Object controllerLog(ProceedingJoinPoint joinPoint) {
//         RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//         ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//         HttpServletRequest request = sra.getRequest();
//
//         String url = request.getRequestURL().toString();
//         String method = request.getMethod();
//         String uri = request.getRequestURI();
//         String queryString;
//         if (Method.GET.equals(method)) {
//
//              queryString = request.getQueryString();
//         }else{
//             queryString = JSON.toJSONString(request.getParameterMap(), new ExtValueFilter());
//         }
//         // result的值就是被拦截方法的返回值
//         Object result = null;
//         try {
//             result = joinPoint.proceed();
//             Object obj = result;
//             log.info("请求 url: {} \n method: {}, uri: {}, \n params: {},\n 请求结束，controller的返回值是 {}", url, method, uri, queryString,JSON.toJSONString(obj),new ExtValueFilter());
//         } catch (Throwable throwable) {
//             throwable.printStackTrace();
//         }
//         return result;
//
//     }
//
//     class ExtValueFilter implements ValueFilter{
//
//         @Override
//         public Object process(Object object, String name, Object value) {
//             if (value.toString().length() > 100) {
//                 return null;
//             } else {
//                 return value;
//             }
//         }
//     }
//
//
// }
