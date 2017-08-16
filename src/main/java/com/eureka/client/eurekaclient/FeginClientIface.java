package com.eureka.client.eurekaclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author 许洪荣
 * @date 2017/8/15
 */
//名字与配置文件的
@FeignClient(value = "eureka.server.provider")
public interface FeginClientIface {

    @GetMapping(value = "/eureka/testAdd/{id}")
    int add(@PathVariable(value = "id") Integer id);

    @GetMapping(value = "/eureka/testStr")
    String testStr(@RequestParam(value = "userName") String userName,@RequestParam(value = "address") String address);

    @PostMapping(value = "/eureka/addUser")
    void addUser(UserInfo userInfo);
}
