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
package com.wupaas.boot.admin.service.impl;

import com.wupaas.boot.admin.model.ProductModel;
import com.wupaas.boot.admin.repository.ProductRepository;
import com.wupaas.boot.admin.service.IProductService;
import com.wupaas.boot.bussiness.base.service.BaseServiceCacheImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 产品配置服务实现
 *
 * @author JueYue
 * @Date 2018-09-05 16:40:10
 */
@Service("productService")
public class ProductServiceImpl extends BaseServiceCacheImpl<ProductRepository, ProductModel> implements IProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

}
