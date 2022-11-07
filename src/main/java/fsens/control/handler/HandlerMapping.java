package fsens.control.handler;

import java.util.Map;

//可能会有多种handlerMapping,这里定义一个接口，方便扩展
public interface HandlerMapping {

    /**
     *
     * @param handlerMap 是待初始化的映射器
     * @param beans 是bean容器，是初始化handlerMap的依据
     */
    void init(Map<?, ?> handlerMap, Map<?, ?> beans);

}
