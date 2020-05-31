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
package com.wupaas.boot.admin.service.impl;

import com.wupaas.boot.admin.model.UserAuthModel;
import com.wupaas.boot.admin.repository.UserAuthRepository;
import com.wupaas.boot.admin.service.IUserAuthService;
import com.wupaas.boot.bussiness.base.service.BaseServiceCacheImpl;
import com.wupaas.boot.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
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
        model.setSalt(new RandomStringGenerator.Builder().withinRange('a', 'Z').build().generate(8));
        if (StringUtils.isEmpty(model.getPassword())) {
            model.setPassword(model.getAccount());
        }
        model.setStatus(1);
        model.setPassword(ToolUtil.getPassword(model.getPassword(), model.getSalt()));
        return super.save(model);
    }

    @Override
    public boolean updateByUserId(UserAuthModel userAuthModel) {
        QueryWrapper wrapper = new QueryWrapper<UserAuthModel>().eq("USER_ID", userAuthModel.getUserId())
                .eq("TYPE", userAuthModel.getType());
        return this.update(userAuthModel, wrapper);
    }
}
