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
package cn.afterturn.boot.facade.paas.msg;

import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.facade.paas.msg.model.NoticeRequestModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 通知模板接口
 *
 * @author JueYue
 * @Date 2019-08-16 17:09:52
 */
@FeignClient(value = "noticeFacade", contextId = "lemur-paas", path = "/notice")
public interface INoticeFacade {

    /**
     * 发送消息
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "发送消息")
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public Response send(@RequestBody NoticeRequestModel data);

}
