package fsens.control.argumentResolver;

import fsens.control.handler.MethodHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public interface ArgumentResolver {
    public boolean support(Class<?> type, int paramIndex, Method method);

    public Object argumentResolver(HttpServletRequest request,
                                   HttpServletResponse response,
                                   Class<?> type,
                                   int paramIndex,
                                   Method method);
}
