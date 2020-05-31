        /**
 * Copyright 2017-2018 JueYue (qrb.jueyue@foxmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wupaas.boot.paas.im.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wupaas.boot.paas.im.model.AppInfoConfigModel;

import org.springframework.stereotype.Repository;

        /**
 * AppInfoConfig持久化
 *
 * @author JueYue
 * @Date 2019-06-27 16:40:25
 */
@Repository
public interface AppInfoConfigRepository extends BaseMapper<AppInfoConfigModel>{

}
