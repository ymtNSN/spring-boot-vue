package com.ymt.manage.web.admin;

import com.ymt.manage.base.BaseController;
import com.ymt.manage.base.BaseReq;
import com.ymt.manage.base.RespEntity;
import com.ymt.manage.domain.mysql.entity.AdminUrlEntity;
import com.ymt.manage.domain.mysql.repo.AdminUrlRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/4/30
 */
@Api(tags = {"1、admin管理部分"}, description = "admin管理部分接口")
@RequestMapping("/web/admin")
@RestController
public class WebAdminController extends BaseController {

    @Autowired
    private AdminUrlRepo adminUrlRepo;

    @ApiOperation(value = "所有admin_url")
    @RequestMapping(value = "/url", method = RequestMethod.GET)
    public RespEntity<List<AdminUrlEntity>> findAll(BaseReq req) {
        RespEntity<List<AdminUrlEntity>> respEntity = RespEntity.One(req);
        respEntity.data = adminUrlRepo.findAll();
        return respEntity;
    }
}
