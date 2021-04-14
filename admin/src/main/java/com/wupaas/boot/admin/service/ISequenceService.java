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
package com.wupaas.boot.admin.service;

import com.wupaas.boot.admin.model.SequenceModel;
import com.wupaas.boot.core.business.base.service.IBaseService;


/**
 * 序列管理服务
 *
 * @author JueYue
 * @Date 2018-09-18 11:12:33
 */
public interface ISequenceService extends IBaseService<SequenceModel> {

    /**
     * 获取序列值的下一个值
     *
     * @param name
     * @return
     */
    public String getNext(String name);

    /**
     * 获取序列值的下一个值
     *
     * @param name
     * @param length 长度,不够补齐
     * @return
     */
    public String getNext(String name, int length);

}
