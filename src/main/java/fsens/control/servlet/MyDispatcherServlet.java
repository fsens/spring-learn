package fsens.control.servlet;

import fsens.control.handler.HandlerMapping;
import fsens.control.handler.Impl.RequestMappingHandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * MyDispatcherServlet本质上也是一个servlet;继承HttpServlet,实现更丰富的功能
 */
public class MyDispatcherServlet extends HttpServlet {
    //创建装className的容器
    List<String> classNames = new ArrayList<String>();
    //创建bean容器
    Map<String, Object> beans = new HashMap<String, Object>();
    //创建handlerMapping
    Map<String, Object> handlerMap = new HashMap<String, Object>();

    /**
     * @see javax.servlet.Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException{
        //1.我们要根据一个基本包进行扫描，扫描里面的子包以及子包下的类
        scanPackage("");
        //2、把扫描出来的类进行实例化
        //beans只有@Controller和@Service标注了的实例
        instantiate();
        //3、依赖注入，把service层的实例注入到controller
        ioc();
        //4、建立一个path与method的映射关系
        HandlerMapping handlerMapping = new RequestMappingHandlerMapping();
        handlerMapping.init(handlerMap, beans);
    }

    private void scanPackage(String basePackageName){

    }

    private void instantiate(){}

    private void ioc(){}

}



