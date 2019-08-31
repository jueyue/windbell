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
package cn.afterturn.boot.paas.msg.controller;

import cn.afterturn.boot.bussiness.base.controller.BaseController;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.facade.paas.msg.INoticeFacade;
import cn.afterturn.boot.facade.paas.msg.model.NoticeEntity;
import cn.afterturn.boot.paas.msg.model.NoticeModel;
import cn.afterturn.boot.paas.msg.service.INoticeService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 通知表控制器
 *
 * @author JueYue
 * @Date 2019-08-29 10:40:45
 */
@Api("通知表")
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController<INoticeService, NoticeModel> implements INoticeFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private INoticeService noticeService;

    @Override
    public Response send(NoticeEntity data) {
        return null;
    }
}