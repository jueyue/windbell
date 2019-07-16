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
package cn.afterturn.boot.facade.admin;

import cn.afterturn.boot.bussiness.response.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 资源管理接口
 *
 * @author JueYue
 * @Date 2019-07-06 17:09:00
 */
@FeignClient(value = "resourceFacade", contextId = "lemur-admin", path = "/resource")
public interface IResourceFacade {

    /**
     * 查询用户在该产品下的所有权限
     *
     * @param productCode
     * @param userId
     * @return
     */
    @ApiOperation(value = "查询用户产品")
    @GetMapping(value = "/queryUserPermissions/{productCode}/{userId}")
    public Response<List<String>> queryUserPermissions(@PathVariable("productCode") String productCode, @PathVariable("userId") String userId);

}
