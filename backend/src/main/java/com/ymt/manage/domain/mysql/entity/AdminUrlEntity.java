package com.ymt.manage.domain.mysql.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

/**
 * 后台管理系统对数据有变动的url
 *
 * @author yaojunguang
 * create time: Tue Apr 30 11:30:05 CST 2019.
 */

@Entity
@Table(name = "admin_url")
@ApiModel(description = "<a href='/web/swift/AdminUrlEntity' target=_blank>后台管理系统对数据有变动的url</a>")
public class AdminUrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "url")
    @ApiModelProperty("请求url")
    private String url;

    @Basic
    @Column(name = "name")
    @ApiModelProperty("接口名称")
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
