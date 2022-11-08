package fsens.control.handlerAdapter.Impl;

import fsens.control.argumentResolver.ArgumentResolver;
import fsens.control.handler.MethodHandler;
import fsens.control.handlerAdapter.HandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class MyHandlerAdapter implements HandlerAdapter {

    /**
     * 执行对应controller的具体方法
     *
     * @param request
     * @param response
     * @param handler
     * @param beans
     */
    public void hand(HttpServletRequest request,
                     HttpServletResponse response,
                     MethodHandler handler,
                     Map<String, Object> beans) {
        //得到实参
        Object[] args = argumentResolver(request, response, handler, beans);
        //得到具体的方法
        Method method = handler.getMethod();
        //得到method的路径
        String path = handler.getUrl();
        //得到method所在的类
        Object instance = beans.get("/" + path.split("/")[1]);
        try {
            method.invoke(instance, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * 解析参数，得到实参
     *
     * @param request
     * @param response
     * @param handler
     * @param beans
     * @return
     */
    public Object[] argumentResolver(HttpServletRequest request,
                                     HttpServletResponse response,
                                     MethodHandler handler,
                                     Map<String, Object> beans) {
        //得到method
        Method method = handler.getMethod();
        //返回参数类型数组
        Class<?>[] paramClazzs = method.getParameterTypes();

        Object[] args = new Object[paramClazzs.length];

        //拿到所有实现了ArgumentResolver这个接口的实现类
        Map<String, Object> argumentResolvers = getBeansOfType(beans, ArgumentResolver.class);

        int paramIndex = 0;
        int i = 0;
        for (Class<?> paramClazz : paramClazzs) {
            for (Map.Entry<String, Object> entry : argumentResolvers.entrySet()) {
                ArgumentResolver ar = (ArgumentResolver) entry.getValue();

                if (ar.support(paramClazz, paramIndex, method)) {
                    args[i++] = ar.argumentResolver(request, response, paramClazz, paramIndex, method);
                }
            }
            paramIndex++;
        }
        return args;
    }


    /**
     * 得到实现了某接口的所有子类
     *
     * @param beans
     * @param intfType
     * @return
     */
    public Map<String, Object> getBeansOfType(Map<String, Object> beans, Class intfType) {
        Map<String, Object> resultBeans = new HashMap<String, Object>();

        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            //Returns:
            //an array of interfaces directly implemented by this class
            Class<?>[] intfs = entry.getValue().getClass().getInterfaces();

            if (intfs.length > 0) {
                for (Class<?> intf : intfs) {
                    //如果有实现intfType接口的，则放入resultBeans中
                    if (intf.isAssignableFrom(intfType)) {
                        resultBeans.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return resultBeans;
    }
}


