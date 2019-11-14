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
package cn.afterturn.boot.facade.paas.file;

import cn.afterturn.boot.bussiness.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文件信息接口
 *
 * @author JueYue
 * @Date 2019-11-14 11:14:44
 */
@FeignClient(value = "fileInfoFacade")
public interface IFileInfoFacade {

    /**
     * base64 格式
     *
     * @param file     base64 文件格式
     * @param fileName 文件名称
     * @param tenantId 客户
     * @return
     */
    public Response upload(@RequestParam(name = "file") String file,
                           @RequestParam(name = "fileName", required = false) String fileName,
                           @RequestParam(name = "tenantId") String tenantId);

}
