package fsens.control.servlet;

import fsens.control.handler.HandlerMapping;
import fsens.control.handler.Impl.RequestMappingHandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.net.URL;
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
        scanPackage("fsens.control");
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
        //得到基包的url
        URL url = this.getClass().getClassLoader().getResource("/" + replaceTo(basePackageName));
        //得到基包名（全名）
        String fileStr = url.getFile();
        //创建基包这一文件对象
        File file = new File(fileStr);
        //得到基包下所有与之直接是父子关系的文件和目录（部分名）
        String[] filesStr = file.list();

        for(String name : filesStr){
            File filePath = new File(fileStr + name);

            //如果是目录，就再次进行扫包
            if(filePath.isDirectory()){
                scanPackage(basePackageName + "." + name);
            }
            else {
                //filePath其实是很长的(全名),getName()删去了前缀，即得到xxx.class
                //最终得到诸如fsens.control.annotation.xxx.class的classNames
                classNames.add(basePackageName + "." + filePath.getName());
            }
        }
    }

    private String replaceTo(String packageName){
        //将"."转换成"/"
        return packageName.replaceAll("\\.", "/");
    }


    private void instantiate(){

    }

    private void ioc(){}

}



