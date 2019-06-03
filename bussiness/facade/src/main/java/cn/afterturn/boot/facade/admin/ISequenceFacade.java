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
package cn.afterturn.boot.facade.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.afterturn.boot.bussiness.response.Response;

/**
 * 序列管理接口
 *
 * @author JueYue
 * @Date 2018-09-18 11:12:33
 */
@FeignClient(value = "sequenceFacade")
public interface ISequenceFacade {

    /**
     * 获取下一个值
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/getNext/{name}", method = RequestMethod.GET)
    public Response<String> getNext(@PathVariable String name);

}
