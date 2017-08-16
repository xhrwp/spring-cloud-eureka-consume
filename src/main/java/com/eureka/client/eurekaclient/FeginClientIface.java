package com.eureka.client.eurekaclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author 许洪荣
 * @date 2017/8/15
 */
//注意value=服务提供方配置文件的spring.application.name
@FeignClient(value = "eureka.server.provider")
public interface FeginClientIface {

    @GetMapping(value = "/eureka/testAdd/{id}")
    int add(@PathVariable(value = "id") Integer id);

    /**
     * 单独参数需要指定　value="userName" 否则服务会报错
     * @param userName
     * @param address
     * @return
     */
    @GetMapping(value = "/eureka/testStr")
    String testStr(@RequestParam(value = "userName") String userName,@RequestParam(value = "address") String address);

    @PostMapping(value = "/eureka/addUser")
    void addUser(UserInfo userInfo);
}
