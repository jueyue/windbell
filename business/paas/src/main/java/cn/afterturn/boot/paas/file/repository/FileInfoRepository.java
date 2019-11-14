        /**
 * Copyright 2017-2019 JueYue (qrb.jueyue@foxmail.com)
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
package cn.afterturn.boot.paas.file.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.afterturn.boot.paas.file.model.FileInfoModel;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FileInfo持久化
 *
 * @author JueYue
 * @Date 2019-11-14 11:14:44
 */
@Repository
public interface FileInfoRepository extends BaseMapper<FileInfoModel>{

}
