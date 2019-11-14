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
package cn.afterturn.boot.paas.file.service.impl;

import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import cn.afterturn.boot.core.util.MD5Util;
import cn.afterturn.boot.paas.file.model.FileInfoModel;
import cn.afterturn.boot.paas.file.repository.FileInfoRepository;
import cn.afterturn.boot.paas.file.service.IFileInfoService;
import cn.afterturn.boot.paas.file.thirdservice.FileClientFactory;
import cn.afterturn.boot.paas.file.thirdservice.IFileClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Base64;

/**
 * 文件信息服务实现
 *
 * @author JueYue
 * @Date 2019-11-14 11:14:44
 */
@Service("fileInfoService")
public class FileInfoServiceImpl extends BaseServiceCacheImpl<FileInfoRepository, FileInfoModel> implements IFileInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileInfoServiceImpl.class);

    @Autowired
    private FileInfoRepository fileInfoRepository;
    @Autowired
    private FileClientFactory  fileClientFactory;

    @Override
    public String upload(String file, String tenantId) {
        IFileClient   fileClient = fileClientFactory.get();
        FileInfoModel model      = getFile(tenantId);
        fileClient.upload(Base64.getDecoder().decode(file), MD5Util.encrypt(model.getId()));
        return MD5Util.encrypt(model.getId());
    }

    private FileInfoModel getFile(String tenantId) {
        FileInfoModel model = new FileInfoModel();
        model.setChannelId(fileClientFactory.get().getChannelId());
        model.setStatus(1);
        model.setTenantId(tenantId);
        save(model);
        return model;
    }


    @Override
    public String upload(InputStream inputStream, String tenantId) {
        IFileClient   fileClient = fileClientFactory.get();
        FileInfoModel model      = getFile(tenantId);
        fileClient.upload(inputStream, MD5Util.encrypt(model.getId()));
        return MD5Util.encrypt(model.getId());
    }
}
