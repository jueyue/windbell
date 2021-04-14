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
package com.wupaas.boot.paas.notice.service;

import com.wupaas.boot.core.business.base.service.IBaseService;
import com.wupaas.boot.paas.notice.model.NoticeTemplateModel;

import java.util.List;


/**
 * 通知模板服务
 *
 * @author JueYue
 * @Date 2019-08-16 17:09:52
 */
public interface INoticeTemplateService extends IBaseService<NoticeTemplateModel> {

    /**
     * 查询所有正常状态的模板
     * @return
     */
    List<NoticeTemplateModel> allTemplate();
}
