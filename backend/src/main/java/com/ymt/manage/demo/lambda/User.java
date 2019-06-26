package com.ymt.manage.demo.lambda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/6/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder =true)
public class User {
    //姓名
    private String name;
    //年龄
    private Integer age;
    //性别
    private Integer sex;
    //所在省市
    private String address;
}
