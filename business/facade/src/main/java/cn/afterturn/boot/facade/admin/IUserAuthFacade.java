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
import cn.afterturn.boot.facade.admin.entity.AuthUserEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户秘钥接口
 *
 * @author JueYue
 * @Date 2019-11-09 14:01:51
 */
@FeignClient(value = "userAuthFacade", contextId = "lemur-admin", path = "/userAuth")
public interface IUserAuthFacade {

    /**
     * 新增用户
     *
     * @param user
     * @return Response
     */
    @ApiOperation(value = "新增认证用户")
    @PostMapping(value = "/addAuthUser")
    public Response addAuthUser(@RequestBody AuthUserEntity user);

    /**
     * 修改用户
     *
     * @param user
     * @return Response
     */
    @ApiOperation(value = "修改认证用户")
    @PostMapping(value = "/updateAuthUser")
    public Response updateAuthUser(@RequestBody AuthUserEntity user);

}
