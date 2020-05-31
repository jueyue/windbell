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
package cn.afterturn.boot.admin.controller;

import cn.afterturn.boot.admin.model.UserAuthModel;
import cn.afterturn.boot.admin.service.IUserAuthService;
import cn.afterturn.boot.bussiness.base.controller.BaseController;
import cn.afterturn.boot.bussiness.response.ErrorResponse;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.bussiness.response.SuccessResponse;
import cn.afterturn.boot.core.util.ToolUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;


/**
 * 用户秘钥控制器
 *
 * @author JueYue
 * @Date 2019-11-09 14:01:51
 */
@Api("用户秘钥")
@RestController
@RequestMapping("/userAuth")
public class UserAuthController extends BaseController<IUserAuthService, UserAuthModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthController.class);

    @Value("${jwt.secret}")
    private String           jwtSecret;
    @Value("${jwt.expiration}")
    private int              expiration;
    @Autowired
    private IUserAuthService userAuthService;

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody @ApiParam(value = "{\"account\":登录用户,\"password\":密码(md5小写),\"product\":产品编码,\"type\":用户类型}") Map<String, String> map) {
        String        account  = map.get("account");
        String        password = map.get("password");
        String        product  = map.get("product");
        String        type     = map.get("type");
        Wrapper       wrapper  = new QueryWrapper<IUserAuthService>().eq("account", account).eq("type", type);
        UserAuthModel user     = userAuthService.getOne(wrapper);
        if (user == null) {
            return new ErrorResponse(301, "账号或密码不对");
        }
        if (!user.getPassword().equalsIgnoreCase(ToolUtil.getPassword(password, user.getSalt()))) {
            return new ErrorResponse(301, "账号或密码不对");
        }
        if (user.getStatus() != 1) {
            return new ErrorResponse(301, "账号已停用");
        }
        map.put("userId", user.getUserId());
        map.put("token", sign(user.getUserId(), product, user.getName(), jwtSecret));
        return new SuccessResponse(map);
    }

    private String sign(String userId, String product, String userName, String secret) {
        try {
            Date      date      = new Date(System.currentTimeMillis() + expiration);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("userId", userId)
                    .withClaim("userName", userName)
                    .withClaim("product", product)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    @ApiOperation("登出")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response logout() {
        return new SuccessResponse();
    }

    @RequestMapping(value = "/addAuthUser", method = RequestMethod.POST)
    public Response addAuthUser(@RequestBody UserAuthModel user) {
        UserAuthModel userAuthModel = new UserAuthModel();
        userAuthService.save(userAuthModel);
        return SUCCESS_RESPONSE;
    }


    @RequestMapping(value = "/updateAuthUser", method = RequestMethod.POST)
    public Response updateAuthUser(@RequestBody UserAuthModel user) {
        if (userAuthService.updateByUserId(user)) {
            return SUCCESS_RESPONSE;
        }
        return new ErrorResponse(301, "未找到数据");
    }
}