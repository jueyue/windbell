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
package com.wupaas.boot.paas.notice.service.impl;

import com.wupaas.boot.core.business.base.service.BaseServiceCacheImpl;
import com.wupaas.boot.paas.notice.model.NoticeModel;
import com.wupaas.boot.paas.notice.model.NoticeTemplateModel;
import com.wupaas.boot.paas.notice.repository.NoticeRepository;
import com.wupaas.boot.paas.notice.service.INoticeService;
import com.wupaas.boot.paas.notice.thirdservice.ISmsSendClient;
import com.wupaas.boot.paas.notice.thirdservice.SmsSendClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 通知表服务实现
 *
 * @author JueYue
 * @Date 2019-08-29 10:40:45
 */
@Service("noticeService")
public class NoticeServiceImpl extends BaseServiceCacheImpl<NoticeRepository, NoticeModel> implements INoticeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private SmsSendClientFactory smsSendClientFactory;

    @Override
    public Boolean send(NoticeTemplateModel templateModel, NoticeModel model, Map<String, Object> data) {
        //TenantEntity tenant = tenantFacade.getTenantByTenantId(model.getTenantId()).getData();
        model.setTemplateId(templateModel.getId());
        model.setStartTime(new Date());
        model.setChannel(templateModel.getChannelCode());
        ISmsSendClient client = smsSendClientFactory.get(templateModel.getChannelCode());
        boolean        send   = client.send(model.getAddress(), templateModel.getThirdTemplateCode(), "悟耘科技", data, model.getContent());
        model.setEndTime(new Date());
        model.setStatus(send ? "2" : "3");
        model.setTitle(templateModel.getName());
        this.save(model);
        return true;
    }
}
