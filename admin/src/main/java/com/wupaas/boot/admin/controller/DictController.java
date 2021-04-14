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

import com.wupaas.boot.admin.model.DictModel;
import com.wupaas.boot.admin.service.IDictService;
import com.wupaas.boot.core.business.base.controller.BaseController;
import com.wupaas.boot.core.business.base.page.PageInfo;
import com.wupaas.boot.core.business.constant.factory.PageFactory;
import com.wupaas.boot.core.business.request.RequestParams;
import com.wupaas.boot.core.business.response.Response;
import com.wupaas.boot.core.business.response.SuccessResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wupaas.boot.admin.common.Constant.ADMIN;


/**
 * 字典表控制器
 *
 * @author JueYue
 * @Date 2018-09-06 20:58:18
 */
@Api("字典表")
@RestController
@RequestMapping(ADMIN + "/dict")
public class DictController extends BaseController<IDictService, DictModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictController.class);

    @Autowired
    private IDictService dictService;

    @Override
    @ApiOperation(value = "查询列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Response<Page<DictModel>> list(@RequestBody RequestParams<DictModel> params) {
        Page<DictModel> page = PageFactory.init(params);
        QueryWrapper wrapper = new QueryWrapper<>(params.getModel());
        // 如果查询条件为空,就要求只能查询最顶级字典
        if (params.getModel() == null || params.getModel().getPid() == null) {
            wrapper.isNull("P_ID");
        }
        if (params.getMap() != null) {
            handlerWrapper(wrapper, params.getMap(), params);
        }
        dictService.page(page, wrapper);

        // 加载完所有的子字典
        DictModel temp = new DictModel();
        for (int i = 0; i < page.getRecords().size(); i++) {
            temp.setPid(page.getRecords().get(i).getId());
            page.getRecords().get(i).setSubDict(dictService.list(temp));
        }
        return new SuccessResponse(new PageInfo<>((page)));
    }

    @ApiOperation(value = "查询子字典ByKey")
    @RequestMapping(value = "/subByKey/{key}", method = RequestMethod.GET)
    public Response<List<DictModel>> subByKey(@PathVariable String key) {
        return new SuccessResponse(dictService.list(new QueryWrapper<DictModel>().eq("PKEY", key)));
    }

    @ApiOperation(value = "查询子字典状态")
    @RequestMapping(value = "/subDetail/{key}/{pkey}", method = RequestMethod.GET)
    public Response<DictModel> detail(@PathVariable String key,@PathVariable String pkey ) {
    	DictModel dictModel = new DictModel();
    	dictModel.setKey(key);
    	dictModel.setPkey(pkey);
    	dictModel = dictService.getOne(dictModel);
        return new SuccessResponse(dictModel);
    }
}
