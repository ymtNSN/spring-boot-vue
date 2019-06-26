package com.ymt.manage.demo.bio.http;

import javax.xml.transform.Source;
import java.io.InputStream;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/22
 */
public class MyRequst {

    private String method;
    private String url;

    public MyRequst(InputStream is) {
        try {
            String content = "";
            byte[] buffer = new byte[1024];
            int len = 0;
            if ((len = is.read(buffer)) > 0) {
                content = new String(buffer, 0, len);
            }

            System.out.println(content);

            String line = content.split("\\n")[0];
            String arr[] = line.split("\\s");

            this.method = arr[0];

            this.url = arr[1].split("\\?")[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
