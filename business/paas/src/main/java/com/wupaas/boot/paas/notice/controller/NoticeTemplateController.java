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
package com.wupaas.boot.paas.notice.controller;

import com.wupaas.boot.core.business.base.controller.BaseController;
import com.wupaas.boot.core.business.response.Response;
import com.wupaas.boot.core.business.response.SuccessResponse;
import com.wupaas.boot.paas.notice.model.NoticeTemplateModel;
import com.wupaas.boot.paas.notice.service.INoticeTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wupaas.boot.paas.common.Constant.PAAS;


/**
 * 通知模板控制器
 *
 * @author JueYue
 * @Date 2019-08-16 17:09:52
 */
@Api("通知模板")
@RestController
@RequestMapping(PAAS + "/notice/template")
public class NoticeTemplateController extends BaseController<INoticeTemplateService, NoticeTemplateModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeTemplateController.class);

    private static final String START_STR = "{{";
    private static final String END_STR   = "}}";

    @Autowired
    private INoticeTemplateService noticeTemplateService;

    @ApiOperation(value = "查询名称列表")
    @RequestMapping(value = "/allList", method = RequestMethod.POST)
    public Response<List<NoticeTemplateModel>> allList() {
        return new SuccessResponse(noticeTemplateService.allTemplate());
    }


    @ApiOperation(value = "获取解析内容")
    @RequestMapping(value = "/getParseDetail/{id}", method = RequestMethod.POST)
    public Response<Map<String, Object>> getParseDetail(@PathVariable(name = "id") String id) {
        NoticeTemplateModel model = noticeTemplateService.getById(id);
        Map<String, Object> map   = new HashMap<>();
        map.put("content", model.getContent());
        List<String> keys    = new ArrayList<>();
        String       content = model.getContent();
        while (content.contains(START_STR)) {
            keys.add(content.substring(content.indexOf(START_STR) + 2, content.indexOf(END_STR)));
            content = content.replaceFirst("\\{\\{", "");
            content = content.replaceFirst("\\}\\}", "");
        }
        map.put("keys", keys);
        return new SuccessResponse(map);
    }


}
