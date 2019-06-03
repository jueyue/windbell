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

import cn.afterturn.boot.admin.model.SequenceModel;
import cn.afterturn.boot.admin.repository.SequenceRepository;
import cn.afterturn.boot.admin.service.ISequenceService;
import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 序列管理服务实现
 *
 * @author JueYue
 * @Date 2018-09-18 11:12:33
 */
@Service("sequenceService")
public class SequenceServiceImpl extends BaseServiceCacheImpl<SequenceRepository, SequenceModel> implements ISequenceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceServiceImpl.class);

    @Autowired
    private SequenceRepository sequenceRepository;

    @Override
    public String getNext(String name) {
        return sequenceRepository.getNext(name);
    }

    @Override
    public String getNext(String name, int length) {
        String seq = getNext(name);
        String zero = getZero(length - seq.length());
        return zero + seq;
    }

    private String getZero(int length) {
        switch (length) {
            case 9:
                return "000000000";
            case 8:
                return "00000000";
            case 7:
                return "0000000";
            case 6:
                return "000000";
            case 5:
                return "00000";
            case 4:
                return "0000";
            case 3:
                return "000";
            case 2:
                return "00";
            case 1:
                return "0";
        }
        return "";
    }
}
