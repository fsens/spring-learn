package fsens.control.handlerAdapter;

import fsens.control.handler.MethodHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public interface HandlerAdapter {
    public void hand(HttpServletRequest request,
                     HttpServletResponse response,
                     MethodHandler handler,
                     Map<String, Object> beans);

    public Object[] argumentResolver(HttpServletRequest request,
                                     HttpServletResponse response,
                                     MethodHandler handler,
                                     Map<String, Object> beans);


    public Map<String, Object> getBeansOfType(Map<String, Object> beans, Class type);


}
