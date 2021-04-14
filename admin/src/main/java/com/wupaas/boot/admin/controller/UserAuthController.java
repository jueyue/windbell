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
package com.wupaas.boot.admin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wupaas.boot.admin.model.UserAuthModel;
import com.wupaas.boot.admin.service.IUserAuthService;
import com.wupaas.boot.bussiness.base.controller.BaseController;
import com.wupaas.boot.bussiness.response.ErrorResponse;
import com.wupaas.boot.bussiness.response.Response;
import com.wupaas.boot.bussiness.response.SuccessResponse;
import com.wupaas.boot.bussiness.security.jwt.JwtUtil;
import com.wupaas.boot.core.util.ToolUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.wupaas.boot.admin.common.Constant.ADMIN;


/**
 * 用户账户控制器
 *
 * @author JueYue
 * @Date 2019-11-09 14:01:51
 */
@Api("用户秘钥")
@RestController
@RequestMapping(ADMIN + "/userAuth")
public class UserAuthController extends BaseController<IUserAuthService, UserAuthModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthController.class);

    @Autowired
    private IUserAuthService userAuthService;

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody @ApiParam(value = "{\"account\":登录用户,\"password\":密码(md5小写),\"webType\":产品编码,\"type\":用户类型}") Map<String, String> map) {
        String        account  = map.get("account");
        String        password = map.get("password");
        String        webType  = map.get("webType");
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
        map.put("token", JwtUtil.createToken(user.getName(), webType));
        return new SuccessResponse(map);
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

    @ApiOperation(value = "修改账号密码", httpMethod = "GET")
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public Response modifyPassword(@ApiParam("用户账号（电话）：phone") @RequestParam String account,
                                   @ApiParam("类型") @RequestParam String type,
                                   @ApiParam("旧密码：oldPassword") @RequestParam String oldPassword,
                                   @ApiParam("新密码：newPassword") @RequestParam String newPassword) {
        Response      res;
        Wrapper       wrapper = new QueryWrapper<IUserAuthService>().eq("account", account).eq("type", type);
        UserAuthModel user    = userAuthService.getOne(wrapper);
        if (user == null) {
            res = new ErrorResponse(-200, "用户不存在,或停用 不支持修改密码");
            res.setSuccess(false);
        } else if (!user.getPassword().equalsIgnoreCase(ToolUtil.getPassword(oldPassword, user.getSalt()))) {
            return new ErrorResponse(301, "账号或密码不对");
        } else {
            user.setPassword(ToolUtil.getPassword(newPassword, user.getSalt()));
            userAuthService.updateById(user);
            res = SUCCESS_RESPONSE;
        }
        return res;

    }

    @RequestMapping(value = "/updateAuthUser", method = RequestMethod.POST)
    public Response updateAuthUser(@RequestBody UserAuthModel user) {
        if (userAuthService.updateByUserId(user)) {
            return SUCCESS_RESPONSE;
        }
        return new ErrorResponse(301, "未找到数据");
    }
}