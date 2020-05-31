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
package com.wupaas.boot.admin.controller;

import com.wupaas.boot.admin.model.AreaModel;
import com.wupaas.boot.admin.service.IAreaService;
import com.wupaas.boot.bussiness.base.controller.BaseController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 地区表控制器
 *
 * @author JueYue
 * @Date 2018-11-12 15:53:51
 */
@Api("地区表")
@RestController
@RequestMapping("/area")
public class AreaController extends BaseController<IAreaService, AreaModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private IAreaService areaService;

}