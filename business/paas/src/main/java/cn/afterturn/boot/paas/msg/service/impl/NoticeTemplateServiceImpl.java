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
package cn.afterturn.boot.paas.msg.service.impl;

import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import cn.afterturn.boot.paas.msg.model.NoticeTemplateModel;
import cn.afterturn.boot.paas.msg.repository.NoticeTemplateRepository;
import cn.afterturn.boot.paas.msg.service.INoticeTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 通知模板服务实现
 *
 * @author JueYue
 * @Date 2019-08-16 17:09:52
 */
@Service("noticeTemplateService")
public class NoticeTemplateServiceImpl extends BaseServiceCacheImpl<NoticeTemplateRepository, NoticeTemplateModel> implements INoticeTemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeTemplateServiceImpl.class);

    @Autowired
    private NoticeTemplateRepository noticeTemplateRepository;

}
