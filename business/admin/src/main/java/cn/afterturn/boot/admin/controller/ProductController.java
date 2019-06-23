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
package cn.afterturn.boot.admin.controller;

import cn.afterturn.boot.admin.model.ProductModel;
import cn.afterturn.boot.admin.service.IProductService;
import cn.afterturn.boot.bussiness.base.controller.BaseController;
import cn.afterturn.boot.facade.admin.IProductFacade;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 产品配置控制器
 *
 * @author JueYue
 * @Date 2018-09-05 16:40:10
 */
@Api("产品配置")
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController<IProductService, ProductModel> implements IProductFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private IProductService productService;

}