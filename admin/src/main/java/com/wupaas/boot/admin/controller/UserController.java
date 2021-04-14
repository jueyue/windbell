/**
 * Copyright 2017-2018 JueYue (qrb.jueyue@foxmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wupaas.boot.admin.controller;

import com.alibaba.fastjson.JSON;
import com.wupaas.boot.admin.model.UserModel;
import com.wupaas.boot.admin.service.IDeptService;
import com.wupaas.boot.admin.service.IMenuService;
import com.wupaas.boot.admin.service.IUserService;
import com.wupaas.boot.core.business.base.controller.BaseController;
import com.wupaas.boot.core.business.response.Response;
import com.wupaas.boot.core.business.response.SuccessResponse;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wupaas.boot.admin.common.Constant.ADMIN;


/**
 * 用户管理控制器
 *
 * @author JueYue
 * @Date 2018-09-06 20:36:08
 */
@Api("用户管理")
@RestController
@RequestMapping(ADMIN + "/user")
public class UserController extends BaseController<IUserService, UserModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IDeptService deptService;

    @Override
    protected Response handlerCreate(UserModel model) {
        model.setStatus(1);
        model.setTenantId(deptService.getById(model.getDeptId()).getTenantId());
        return super.handlerCreate(model);
    }

    @RequestMapping(value = "/userInfo/{webType}/{userId}", method = RequestMethod.GET)
    public Response userInfo(@PathVariable String userId, @PathVariable(required = false) String webType) {
        UserModel user = userService.getById(userId);
        // 获取所有的菜单权限
        List<String> access = menuService.getAllByUserId(userId, webType);
        user.setAccess(JSON.toJSONString(access));
        return new SuccessResponse(user);
    }


    @RequestMapping(value = "/getUserByRole/{roleName}/{tenantId}", method = RequestMethod.GET)
    public Response getUserByRole(@PathVariable String roleName, @PathVariable String tenantId) {
        return new SuccessResponse(userService.getUserByRole(roleName, tenantId));
    }


}
