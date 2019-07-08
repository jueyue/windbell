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
package cn.afterturn.boot.admin.controller;

import cn.afterturn.boot.admin.model.LinkUserRoleModel;
import cn.afterturn.boot.admin.model.RoleModel;
import cn.afterturn.boot.admin.service.ILinkUserRoleService;
import cn.afterturn.boot.admin.service.IRoleService;
import cn.afterturn.boot.bussiness.base.controller.BaseController;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.bussiness.response.SuccessResponse;
import cn.afterturn.boot.facade.admin.IRoleFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 角色表控制器
 *
 * @author JueYue
 * @Date 2018-09-05 16:35:32
 */
@Api("角色表")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<IRoleService, RoleModel> implements IRoleFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;
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
        LinkUserRoleModel model = new LinkUserRoleModel();
        model.setRoleId(roleId);
        List<LinkUserRoleModel> list =  linkUserRoleService.list(model);
        return list!=null&&list.size()>0?new SuccessResponse(list):new SuccessResponse("error");
    }


}