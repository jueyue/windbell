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

import com.wupaas.boot.admin.model.LinkUserRoleModel;
import com.wupaas.boot.admin.model.RoleModel;
import com.wupaas.boot.admin.service.ILinkUserRoleService;
import com.wupaas.boot.admin.service.IRoleService;
import com.wupaas.boot.core.business.base.controller.BaseController;
import com.wupaas.boot.core.business.response.Response;
import com.wupaas.boot.core.business.response.SuccessResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wupaas.boot.admin.common.Constant.ADMIN;


/**
 * 角色表控制器
 *
 * @author JueYue
 * @Date 2018-09-05 16:35:32
 */
@Api("角色表")
@RestController
@RequestMapping(ADMIN + "/role")
public class RoleController extends BaseController<IRoleService, RoleModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService         roleService;
    @Autowired
    private ILinkUserRoleService linkUserRoleService;

    @ApiOperation(value = "保存权限")
    @RequestMapping(value = "/saveAuth/{roleId}", method = RequestMethod.POST)
    public Response saveAuth(@PathVariable String roleId, @RequestBody List<String> menuIds) {
        roleService.saveAuth(roleId,menuIds);
        return SUCCESS_RESPONSE;
    }
    @ApiOperation(value = "查看角色下的用户")
    @RequestMapping(value = "/getUserByRole", method = RequestMethod.POST)
    @ResponseBody
    public Response getUserByRole(@RequestParam String roleId) {
        return new SuccessResponse(linkUserRoleService.count(new QueryWrapper<>(new LinkUserRoleModel(null,roleId))));
    }


}
