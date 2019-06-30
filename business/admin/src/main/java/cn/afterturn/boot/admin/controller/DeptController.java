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

import cn.afterturn.boot.admin.model.DeptModel;
import cn.afterturn.boot.admin.service.IDeptService;
import cn.afterturn.boot.bussiness.base.controller.BaseController;
import cn.afterturn.boot.bussiness.request.RequestParams;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.bussiness.response.SuccessResponse;
import cn.afterturn.boot.core.support.BeanKit;
import cn.afterturn.boot.facade.admin.IDeptFacade;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 部门表控制器
 *
 * @author JueYue
 * @Date 2018-09-18 15:25:41
 */
@Api("部门表")
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController<IDeptService, DeptModel> implements IDeptFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private IDeptService deptService;

    @ApiOperation(value = "获取机构")
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    public Response<List<DeptModel>> tree(@RequestBody RequestParams<DeptModel> params, HttpServletRequest request) {
        QueryWrapper wrapper = new QueryWrapper<>(params.getModel());
        if (params.getMap() != null) {
            handlerWrapper(wrapper, params.getMap(), params);
        }
        if (BeanKit.isAllFieldNullNoTransient(params.getModel())) {
            wrapper.isNull("P_ID");
        }
        List<DeptModel> list = deptService.tree(wrapper);
        return new SuccessResponse(list);
    }

}