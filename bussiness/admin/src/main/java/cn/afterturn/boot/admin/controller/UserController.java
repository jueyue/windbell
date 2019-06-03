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

import cn.afterturn.boot.admin.model.UserModel;
import cn.afterturn.boot.admin.service.IUserService;
import cn.afterturn.boot.bussiness.auth.JwtUtil;
import cn.afterturn.boot.bussiness.base.controller.BaseController;
import cn.afterturn.boot.bussiness.response.ErrorResponse;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.bussiness.response.SuccessResponse;
import cn.afterturn.boot.core.util.ToolUtil;
import cn.afterturn.boot.facade.admin.IUserFacade;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 用户管理控制器
 *
 * @author JueYue
 * @Date 2018-09-06 20:36:08
 */
@Api("用户管理")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<IUserService, UserModel> implements IUserFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private IUserService userService;

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody @ApiParam(value = "{\"account\":登录用户,\"password\":密码(md5小写)}") Map<String, String> map) {
        String    account  = map.get("account");
        String    password = map.get("password");
        Wrapper   wrapper  = new QueryWrapper<UserModel>().eq("account", account);
        UserModel user     = userService.getOne(wrapper);
        if (user == null) {
            return new ErrorResponse(401, "未找到改用户");
        }
        Subject               currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token       = new UsernamePasswordToken(account, ToolUtil.getPassword(password, user.getSalt()));
        currentUser.login(token);
        map.put("userId", user.getId());
        map.put("token", JwtUtil.sign(user.getId(), getAllRoles(user), user.getName(), jwtSecret));
        return new SuccessResponse(map);
    }

    @ApiOperation("登出")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response logout() {
        SecurityUtils.getSubject().logout();
        return new SuccessResponse();
    }

    private String getAllRoles(UserModel user) {
        return null;
    }

    @RequestMapping(value = "/userInfo/{userId}", method = RequestMethod.GET)
    public Response userInfo(@PathVariable String userId) {
        UserModel user = userService.getById(userId);
        return new SuccessResponse(user);
    }

}