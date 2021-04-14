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
package com.wupaas.boot.admin.repository;

import com.wupaas.boot.admin.model.UserModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User持久化
 *
 * @author JueYue
 * @Date 2018-09-06 20:36:08
 */
@Repository
public interface UserRepository extends BaseMapper<UserModel> {
    /**
     *
     * @param roleName
     * @param tenantId
     * @return
     */
    List<UserModel> getUserByRole(@Param("roleName") String roleName,@Param("tenantId") String tenantId);
}
