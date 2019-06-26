package com.ymt.manage.demo.bio.servlet;

import com.ymt.manage.demo.bio.http.MyRequst;
import com.ymt.manage.demo.bio.http.MyResponse;
import com.ymt.manage.demo.bio.http.MyServlet;

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
