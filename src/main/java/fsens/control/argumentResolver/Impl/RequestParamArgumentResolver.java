package fsens.control.argumentResolver.Impl;

import fsens.control.argumentResolver.ArgumentResolver;
import fsens.control.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


@argumentResolver("requestParamArgumentResolver")
public class RequestParamArgumentResolver implements ArgumentResolver {
    public boolean support(Class<?> type, int paramIndex, Method method){
        //位置：annotations[i][j],第i个参数，第j个注解实例，整体表示一个注解实例
        Annotation[][] an = method.getParameterAnnotations();

        Annotation[] paramAns = an[paramIndex];

        for (Annotation paramAn : paramAns){
            //当前Class对象如果是参数Class对象的父类，父接口，或者是相同，都会返回true。
            if(myRequestParam.class.isAssignableFrom(paramAn.getClass())){
                return true;
            }
        }
        return false;
    }


    public Object argumentResolver(HttpServletRequest request,
                                   HttpServletResponse response,
                                   Class<?> type,
                                   int paramIndex,
                                   Method method) {
        //位置：annotations[i][j],第i个参数，第j个注解实例，整体表示一个注解实例
        Annotation[][] an = method.getParameterAnnotations();

        Annotation[] paramAns = an[paramIndex];

        for (Annotation paramAn : paramAns){
            if(myRequestParam.class.isAssignableFrom(paramAn.getClass())){
                myRequestParam mrp = (myRequestParam) paramAn;

                String value = mrp.value();
                String str = request.getParameter(value);
                    if(mrp.required()&&(str != "name" || str != "sex" || str != "age")){
                        try{
                            response.getWriter().write("未输入参数");
                            //如果这样，就抛出异常，表示未找到该资源
                            throw new ServletException();
                        }catch (IOException e){
                            e.printStackTrace();
                        } catch (ServletException e) {
                            e.printStackTrace();
                        }
                    }
                    return str;
                }
            }
        return null;
        }
    }

