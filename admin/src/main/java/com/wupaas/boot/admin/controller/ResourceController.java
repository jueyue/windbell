/**
 * Copyright 2017-2019 JueYue (qrb.jueyue@foxmail.com)
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

import com.wupaas.boot.admin.model.ResourceModel;
import com.wupaas.boot.admin.service.IResourceService;
import com.wupaas.boot.bussiness.base.controller.BaseController;
import com.wupaas.boot.bussiness.request.RequestParams;
import com.wupaas.boot.bussiness.response.Response;
import com.wupaas.boot.bussiness.response.SuccessResponse;
import com.wupaas.boot.web.iview.IViewTree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 资源管理控制器
 *
 * @author JueYue
 * @Date 2019-07-06 17:09:00
 */
@Api("资源管理")
@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController<IResourceService, ResourceModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private IResourceService resourceService;

    @Override
    public void handlerQuery(QueryWrapper wrapper, RequestParams<ResourceModel> params) {
        if (Boolean.parseBoolean(params.getMap().get("parentIsNull"))) {
            wrapper.isNull("P_ID");
        }

    }

    @ApiOperation(value = "保存菜单权限")
    @RequestMapping(value = "/saveMenuAuth/{menuId}", method = RequestMethod.POST)
    public Response saveAuth(@PathVariable String menuId, @RequestBody List<String> resourceIds) {
        resourceService.saveAuth(menuId,resourceIds);
        return SUCCESS_RESPONSE;
    }

    @ApiOperation(value = "获取机构树")
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    public Response<List<IViewTree>> tree(@RequestParam String menuId) {
        List<IViewTree> list = resourceService.getTreeByMenuId(menuId);
        return new SuccessResponse(list);
    }

    @ApiOperation(value = "查询用户访问权限")
    @GetMapping(value = "/queryUserPermissions/{productCode}/{userId}")
    public Response<List<String>> queryUserPermissions(@PathVariable String productCode, @PathVariable String userId) {
        List<String> access = resourceService.getAllByUserId(userId, productCode);
        return new SuccessResponse(access);
    }

}