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
package com.wupaas.boot.paas.notice.controller;

import com.wupaas.boot.bussiness.base.controller.BaseController;
import com.wupaas.boot.bussiness.response.Response;
import com.wupaas.boot.bussiness.response.SuccessResponse;
import com.wupaas.boot.core.cache.CacheKey;
import com.wupaas.boot.core.cache.RedisKit;
import com.wupaas.boot.paas.common.enums.StatusEnum;
import com.wupaas.boot.paas.common.exception.PaasBizException;
import com.wupaas.boot.paas.common.exception.PaasBizExceptionEnum;
import com.wupaas.boot.paas.notice.controller.entity.NoticeRequestEntity;
import com.wupaas.boot.paas.notice.controller.entity.enums.NoticeTypeEnum;
import com.wupaas.boot.paas.notice.model.NoticeModel;
import com.wupaas.boot.paas.notice.model.NoticeTemplateModel;
import com.wupaas.boot.paas.notice.service.INoticeService;
import com.wupaas.boot.paas.notice.service.INoticeTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.wupaas.boot.paas.common.Constant.PAAS;


/**
 * 通知表控制器
 *
 * @author JueYue
 * @Date 2019-08-29 10:40:45
 */
@Api("通知表")
@RestController
@RequestMapping(PAAS + "/notice")
public class NoticeController extends BaseController<INoticeService, NoticeModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private INoticeService         noticeService;
    @Autowired
    private INoticeTemplateService noticeTemplateService;

    @ApiOperation(value = "发送消息")
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public Response send(NoticeRequestEntity data) {
        NoticeModel model = new NoticeModel();
        BeanUtils.copyProperties(data, model);
        NoticeTemplateModel templateModel = noticeTemplateService.getOne(new NoticeTemplateModel(data.getTemplateCode()));
        if (templateModel == null) {
            LOGGER.error("短信模板不存在: TemplateId : %s  ", templateModel.getCode());
            throw new PaasBizException(PaasBizExceptionEnum.REQUEST_NULL);
        }
        if (!StatusEnum.NORMAL.getCode().equals(templateModel.getStatus())) {
            LOGGER.error("短信模板已经停用: TemplateId : %s  , TemplateName", templateModel.getCode(), templateModel.getName());
            throw new PaasBizException(PaasBizExceptionEnum.REQUEST_NULL);
        }
        model.setContent(getContent(templateModel.getContent(), data.getData()));
        return new SuccessResponse(noticeService.send(templateModel, model, data.getData()));
    }

    @ApiOperation(value = "发送验证码")
    @RequestMapping(value = "/sendVerificationCode/{tenantId}/{templateCode}/{mobile}", method = RequestMethod.GET)
    public Response sendVerificationCode(@PathVariable String tenantId, @PathVariable String templateCode, @PathVariable String mobile) {
        // 一分钟内不再发送验证码,默认返回成功
        if (RedisKit.exists(CacheKey.get("VerificationCode").append(templateCode).append(mobile).toString())
                || RedisKit.getExpire(CacheKey.get("VerificationCode").append(templateCode).append(mobile).toString()) > 230) {
            return SUCCESS_RESPONSE;
        }
        NoticeRequestEntity data = new NoticeRequestEntity();
        data.setTemplateCode(templateCode);
        data.setAddress(mobile);
        data.setTenantId(tenantId);
        data.setType(NoticeTypeEnum.MSG.getCode());
            Map map = new HashMap();
            map.put("code", RandomStringUtils.randomNumeric(6));
        RedisKit.put(CacheKey.get("VerificationCode").append(templateCode).append(mobile).toString(), map.get("code"), 5 * 60);
        data.setData(map);
        return send(data);
    }

    private String getContent(String content, Map<String, Object> data) {
        while (content.contains("{{")) {
            String sourceCode = content.substring(content.indexOf("{{") + 2, content.indexOf("}}"));
            String code       = sourceCode.trim();
            if (!data.containsKey(code)) {
                LOGGER.error("发送短信参数缺失: code : %s ", code);
                throw new PaasBizException(PaasBizExceptionEnum.REQUEST_NULL);
            }
            content = content.replaceAll(sourceCode, data.get(code) + "");
            content = content.replaceFirst("\\{\\{", "");
            content = content.replaceFirst("}}", "");
        }
        return content;
    }
}