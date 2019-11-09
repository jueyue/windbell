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
package cn.afterturn.boot.admin.service.impl;

import cn.afterturn.boot.admin.model.UserAuthModel;
import cn.afterturn.boot.admin.repository.UserAuthRepository;
import cn.afterturn.boot.admin.service.IUserAuthService;
import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import cn.afterturn.boot.core.util.ToolUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户秘钥服务实现
 *
 * @author JueYue
 * @Date 2019-11-09 14:01:51
 */
@Service("userAuthService")
public class UserAuthServiceImpl extends BaseServiceCacheImpl<UserAuthRepository, UserAuthModel> implements IUserAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthServiceImpl.class);

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public boolean save(UserAuthModel model) {
        model.setSalt(RandomStringUtils.randomAlphanumeric(8));
        if (StringUtils.isEmpty(model.getPassword())) {
            model.setPassword(model.getAccount());
        }
        model.setPassword(ToolUtil.getPassword(model.getPassword(), model.getSalt()));
        return super.save(model);
    }
}
