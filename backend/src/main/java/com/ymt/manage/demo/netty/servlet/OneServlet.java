package com.ymt.manage.demo.netty.servlet;

import com.ymt.manage.demo.netty.http.MyRequst;
import com.ymt.manage.demo.netty.http.MyResponse;
import com.ymt.manage.demo.netty.http.MyServlet;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/22
 */
public class OneServlet extends MyServlet {

    @Override
    protected void doPost(MyRequst requst, MyResponse response) throws Exception {
        response.write("This is One Servlet");
    }

    @Override
    public void doGet(MyRequst requst, MyResponse response) throws Exception {
        doPost(requst,response);
    }
}
