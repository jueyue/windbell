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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wupaas.boot.admin.model.DeptModel;
import com.wupaas.boot.admin.service.IDeptService;
import com.wupaas.boot.admin.service.ISequenceService;
import com.wupaas.boot.core.business.base.controller.BaseController;
import com.wupaas.boot.core.business.request.RequestParams;
import com.wupaas.boot.core.business.response.Response;
import com.wupaas.boot.core.business.response.SuccessResponse;
import com.wupaas.boot.core.support.BeanKit;
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

import static com.wupaas.boot.admin.common.Constant.ADMIN;


/**
 * 部门表控制器
 *
 * @author JueYue
 * @Date 2018-09-18 15:25:41
 */
@Api("部门表")
@RestController
@RequestMapping(ADMIN + "/dept")
public class DeptController extends BaseController<IDeptService, DeptModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private IDeptService     deptService;
    @Autowired
    private ISequenceService sequenceService;

    @Override
    protected Response handlerCreate(DeptModel model) {
        if (model.getPid() == null) {
            model.setDeptCode(sequenceService.getNext("DEPT_CODE", 6));
        } else {
            DeptModel pdept = deptService.getById(model.getPid());
            model.setDeptCode(sequenceService.getNext("DEPT_CODE_" + pdept.getDeptCode(), 4));
        }
        return super.handlerCreate(model);
    }

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
