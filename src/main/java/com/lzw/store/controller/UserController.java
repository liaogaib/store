package com.lzw.store.controller;

import com.lzw.store.entity.User;
import com.lzw.store.service.IUserService;
import com.lzw.store.service.ex.InsertException;
import com.lzw.store.service.ex.UsernameDuplitedException;
import com.lzw.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: store
 * @BelongsPackage: com.lzw.store.controller
 * @Author: lzw
 * @CreateTime: 2022-04-12 21:35
 * @Description: 用户模块控制层类
 */
@RestController // @Controller+@ResponseBody
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    /*@RequestMapping("reg")
    //@ResponseBody //表示此方法的响应结果以json格式进行数据的响应给到前端
    public JsonResult<Void> reg(User user){
        // 创建响应结果对象
        JsonResult<Void> result = new JsonResult<>();
        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UsernameDuplitedException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        } catch (InsertException e) {
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }
        return result;
    }*/
    /**
     * 1.接受数据方式：请求处理方法的参数列表设置为pojo类型来接收前端的数据，
     *  SpringBoot会将前端的url地址中的参数名和pojo类的属性名进行比较，如
     *  果这两个名称相同，则将值注入到pojo中对应的属性上
     */

    /**
     *
     * @param user
     * @return
     */

    @RequestMapping("reg")
    //@ResponseBody //表示此方法的响应结果以json格式进行数据的响应给到前端
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    /**
     * 约定大雨配置：开发思想来完成，省略大量的配置甚至注解的编写
     * 2.接受数据方式：请求处理方法的参数列表设置为非pojo类型
     *  SpringBoot会直接将请求的参数名和方法的参数名直接进行比较。如果名称
     *  相同则自动完成值的依赖注入
     */
    /**
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password){
        User data = userService.login(username, password);
        return new JsonResult<User>(OK,data);
    }
}
