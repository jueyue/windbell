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
package cn.afterturn.boot.admin.service.impl;

import cn.afterturn.boot.admin.model.UserModel;
import cn.afterturn.boot.admin.repository.UserRepository;
import cn.afterturn.boot.admin.service.IUserService;
import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import cn.afterturn.boot.core.util.MD5Util;
import cn.afterturn.boot.core.util.ToolUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户管理服务实现
 *
 * @author JueYue
 * @Date 2018-09-06 20:36:08
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceCacheImpl<UserRepository, UserModel> implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean save(UserModel entity) {
        entity.setSalt(RandomStringUtils.randomAlphanumeric(8));
        entity.setPassword(ToolUtil.getPassword(entity.getPassword(),entity.getSalt()));
        entity.setStatus(1);
        // TODO 同步微信,钉钉
        return super.save(entity);
    }
}
