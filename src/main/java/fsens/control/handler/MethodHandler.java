package fsens.control.handler;

import java.lang.reflect.Method;


/**
 * url与方法映射的Handler
 */
public class MethodHandler {

   private String url;

   private Method method;


   public MethodHandler(String url, Method method){
       this.url = url;
       this.method = method;
   }

   public void setMethod(Method method){
       this.method = method;
   }


   public Method getMethod(){
       return method;
   }


   public String getUrl(){
       return this.url;
   }

   public boolean isSupport(String url){
       if(url == this.url){
       return true;
       }
    return false;
   }


}
