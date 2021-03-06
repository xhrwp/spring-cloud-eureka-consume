package com.eureka.client.eurekaclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Feign是一个声明式Web Service客户端。
 * 使用Feign能让编写Web Service客户端更加简单,
 * 它的使用方法是定义一个接口，然后在上面添加注解，同时也支持JAX-RS标准的注解。
 * Feign也支持可拔插式的编码器和解码器。
 * Spring Cloud对Feign进行了封装，使其支持了Spring MVC标准注解和HttpMessageConverters。
 * Feign可以与Eureka和Ribbon组合使用以支持负载均衡。
 * Ribbon是一个客户端的负载均衡器，可以提供很多HTTP和TCP的控制行为。Feign已经使用了Ribbon，所以使用了@FeignClient，Riboon也同样被应用了。
 * 如果使用resttemplate 需要单独写@LoadBalanced 对restemplate进行bean注入
 *
     * @Bean
     @LoadBalanced
     RestTemplate restTemplate() {
        return new RestTemplate();
     }

     @Bean
     public IRule ribbonRule() {
        return new RandomRule();//这里配置策略，和配置文件对应
     }
 * @author 许洪荣
 * @date 2017/8/15
 */
//注意value=服务提供方配置文件的spring.application.name
@FeignClient(value = "spring-eureka-server-provider")
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
