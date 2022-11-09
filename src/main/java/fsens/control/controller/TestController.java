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
    public void queryName(HttpServletRequest request, HttpServletResponse response,
                      @myRequestParam("info")String name){
        try {
            PrintWriter pw = response.getWriter();
            String result = queryService.query(name);
            pw.write(result);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
