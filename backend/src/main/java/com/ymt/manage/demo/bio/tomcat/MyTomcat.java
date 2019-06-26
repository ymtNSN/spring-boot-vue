package com.ymt.manage.demo.bio.tomcat;

import com.ymt.manage.demo.bio.http.MyRequst;
import com.ymt.manage.demo.bio.http.MyResponse;
import com.ymt.manage.demo.bio.http.MyServlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/22
 */
public class MyTomcat {
    private int port = 8080;
    private ServerSocket serverSocket;
    private Map<String, MyServlet> servletMap = new HashMap<>();

    private Properties webxml = new Properties();

    public static void main(String[] args) {
        new MyTomcat().start();
    }

    public void start() {
        init();
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket client = serverSocket.accept();
                process(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(Socket client) throws Exception {
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();

        MyRequst requst = new MyRequst(is);
        MyResponse response = new MyResponse(os);

        String url = requst.getUrl();

        if (servletMap.containsKey(url)) {
            servletMap.get(url).service(requst, response);
        } else {
            response.write("404 Not found");
        }
        os.flush();
        os.close();

        is.close();
        Thread.sleep(500);
        client.close();
    }

    private void init() {
        try {

            String WEB_INFO = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INFO + "web.properties");

            webxml.load(fis);

            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".className");
                    MyServlet o = (MyServlet) Class.forName(className).newInstance();
                    servletMap.put(url, o);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
