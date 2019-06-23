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

import cn.afterturn.boot.admin.model.MenuModel;
import cn.afterturn.boot.admin.service.IMenuService;
import cn.afterturn.boot.bussiness.base.controller.BaseController;
import cn.afterturn.boot.bussiness.model.RequestParams;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.bussiness.response.SuccessResponse;
import cn.afterturn.boot.core.support.BeanKit;
import cn.afterturn.boot.facade.admin.IMenuFacade;
import cn.afterturn.boot.web.iview.IViewTree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 菜单表控制器
 *
 * @author JueYue
 * @Date 2018-10-12 14:51:42
 */
@Api("菜单表")
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController<IMenuService, MenuModel> implements IMenuFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "获取机构列表")
    @RequestMapping(value = "/treeList", method = RequestMethod.POST)
    public Response<List<MenuModel>> treeList(@RequestBody RequestParams<MenuModel> params) {
        QueryWrapper wrapper = new QueryWrapper<>(params.getModel());
        if (params.getMap() != null) {
            handlerWrapper(wrapper, params.getMap(), params);
        }
        if (BeanKit.isAllFieldNullNoTransient(params.getModel())) {
            wrapper.isNull("P_ID");
        }
        wrapper.orderByAsc("NUM");
        List<MenuModel> list = menuService.tree(wrapper);
        return new SuccessResponse(list);
    }


    @ApiOperation(value = "获取机构树")
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    public Response<List<IViewTree>> tree(@RequestParam String roleId) {
        List<IViewTree> list = menuService.getTreeByRoleId(roleId);
        return new SuccessResponse(list);
    }

}