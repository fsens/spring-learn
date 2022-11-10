package fsens.control.controller;

import fsens.control.annotation.*;
import fsens.control.service.MyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@myController
@myRequestMapping("/fsens")
public class TestController {

    //指定注入queryService实例
    @myQualifier("queryService")
    private MyService queryService;

    @myRequestMapping("/query")
    public void queryInfo(HttpServletRequest request, HttpServletResponse response,
                      @myRequestParam(value = "info")String info){
        try {
            PrintWriter pw = response.getWriter();
            String result = queryService.query(info);
            pw.write(result);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
