package com.eureka.client.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author 许洪荣
 * @date 2017/8/15
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 测试段容器和feign
     * @param age1
     * @param age2
     * @return
     */
    @GetMapping(value = "/addAge")
    public int add(@RequestParam int age1, @RequestParam int age2) throws Exception{
        return userService.add(age1,age2);
    }

    @GetMapping(value = "/testStr")
    public String testStr(@RequestParam String userName, @RequestParam String address) throws Exception{
        return userService.addStr(userName,address);
    }

    @PostMapping(value = "/addUser")
    public UserInfo addUser(@RequestBody UserInfo userInfo) {
        userService.addUser(userInfo);
        return userInfo;
    }

}
