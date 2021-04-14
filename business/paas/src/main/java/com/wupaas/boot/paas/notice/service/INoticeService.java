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
package com.wupaas.boot.paas.notice.service;

import com.wupaas.boot.core.business.base.service.IBaseService;
import com.wupaas.boot.paas.notice.model.NoticeModel;
import com.wupaas.boot.paas.notice.model.NoticeTemplateModel;

import java.util.Map;


/**
 * 通知表服务
 *
 * @author JueYue
 * @Date 2019-08-29 10:40:45
 */
public interface INoticeService extends IBaseService<NoticeModel> {

    /**
     * 发送的消息
     *
     * @param templateModel
     * @param model
     * @param data
     * @return
     */
    Boolean send(NoticeTemplateModel templateModel, NoticeModel model, Map<String, Object> data);
}
