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

import cn.afterturn.boot.admin.model.SequenceModel;
import cn.afterturn.boot.admin.service.ISequenceService;
import cn.afterturn.boot.bussiness.base.controller.BaseController;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.bussiness.response.SuccessResponse;
import cn.afterturn.boot.facade.admin.ISequenceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 序列管理控制器
 *
 * @author JueYue
 * @Date 2018-09-18 11:12:33
 */
@Api("序列管理")
@RestController
@RequestMapping("/sequence")
public class SequenceController extends BaseController<ISequenceService, SequenceModel> implements ISequenceFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceController.class);

    @Autowired
    private ISequenceService sequenceService;

    @Override
    @ApiOperation(value = "获取下一个值")
    @RequestMapping(value = "/getNext/{name}", method = RequestMethod.GET)
    public Response<String> getNext(@PathVariable String name) {
        return new SuccessResponse<>(sequenceService.getNext(name));
    }

}