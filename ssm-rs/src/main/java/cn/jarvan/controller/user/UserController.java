package cn.jarvan.controller.user;

import cn.jarvan.dao.user.RoleMapper;
import cn.jarvan.dao.user.UserMapper;
import cn.jarvan.model.Prize;
import cn.jarvan.model.user.User;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <b><code>UserController</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2018/7/25 9:59.
 *
 * @author liuruojing
 * @since ssm 0.1.0
 */
@RestController
@RequestMapping("/v1.0")
@Api(tags = "用户相关接口")
public class UserController {
   /**
   *
   *roleMapper.
   *
   *@since ${PROJECT_NAME} 0.1.0
   */
    @Autowired
    RoleMapper roleMapper;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "登录成功"),
            @ApiResponse(code = 500, message = "internal server error"),
            @ApiResponse(code = 404, message = "账号不存在"),
            @ApiResponse(code = 400, message = "账号或者密码错误")})
    public final ResponseEntity<?> login(@ApiParam(value = "username", required = true) @RequestParam(value = "username", required = true) String username,
                                         @ApiParam(value = "password", required = true) @RequestParam(value = "password", required = true) String password) {
        Subject currentuser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(false);
        try {
            currentuser.login(token);
            List<String> roles = roleMapper.selectRoleByUserId(((User) currentuser.getPrincipal()).getUserId());
            return new ResponseEntity<>(roles, HttpStatus.OK);//登录成功
        } catch (UnknownAccountException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//账号不存在
        } catch (AuthenticationException e) {
           e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);//Z帐号或密码错误
        }
    }
    /**
     *
     * 未登录拦截跳转接口 提供给shiro调用.
     *
     * @author liuruojing
     * @param
     * @return
     * @since ${PROJECT_NAME} 0.1.0
     */
    @RequestMapping(value = "/unlogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "登录拦截", notes = "未登录拦截后供shiro调用的跳转接口")
    @ApiResponses(value = {@ApiResponse(code = 401, message = "未登录")})
    public ResponseEntity<?> unlogin()
    {
        Map<String,String> map=new HashMap<>();
        map.put("msg","请先登录");
        return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
    }

    /**
     *
     * 未授权拦截跳转接口 提供给shiro调用.
     *
     * @author liuruojing
     * @param
     * @return
     * @since ${PROJECT_NAME} 0.1.0
     */
    @RequestMapping(value = "/unauthorized", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "授权拦截", notes = "未授权拦截后供shiro调用的跳转接口")
    @ApiResponses(value = {@ApiResponse(code = 401, message = "未授权")})
    public ResponseEntity<?> unauthorized()
    {
        Map<String,String> map=new HashMap<>();
        map.put("msg","抱歉,您没有权限访问");
        return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
    }
}