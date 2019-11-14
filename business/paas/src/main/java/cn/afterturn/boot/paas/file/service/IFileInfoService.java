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
package cn.afterturn.boot.paas.file.service;

import cn.afterturn.boot.bussiness.base.service.IBaseService;
import cn.afterturn.boot.paas.file.model.FileInfoModel;

import java.io.InputStream;


/**
 * 文件信息服务
 *
 * @author JueYue
 * @Date 2019-11-14 11:14:44
 */
public interface IFileInfoService extends IBaseService<FileInfoModel> {

    /**
     * 上传文件
     *
     * @param file
     * @param tenantId
     * @return
     */
    String upload(String file, String tenantId);

    /**
     * 上传文件
     *
     * @param inputStream
     * @param tenantId
     * @return
     */
    String upload(InputStream inputStream, String tenantId);
}
