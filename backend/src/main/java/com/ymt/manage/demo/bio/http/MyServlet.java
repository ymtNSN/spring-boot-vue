package com.ymt.manage.demo.bio.http;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/22
 */
public abstract class MyServlet {

    public void service(MyRequst requst,MyResponse response) throws Exception {
            if("GET".equalsIgnoreCase(requst.getMethod())){
                    doGet(requst,response);
            }else {
                doPost(requst,response);
            }
    }

    protected abstract void doPost(MyRequst requst, MyResponse response) throws Exception;

    public abstract void doGet(MyRequst requst, MyResponse response) throws Exception;

}
