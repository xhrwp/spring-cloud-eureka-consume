package com.eureka.client.eurekaclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 熔断器
 雪崩效应
 在微服务架构中通常会有多个服务层调用，基础服务的故障可能会导致级联故障，进而造成整个系统不可用的情况，这种现象被称为服务雪崩效应。
 服务雪崩效应是一种因“服务提供者”的不可用导致“服务消费者”的不可用,并将不可用逐渐放大的过程。
 * @author 许洪荣
 * @date 2017/8/15
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private FeginClientIface feginClientIface;

    /**
     * 熔断
     *  HystrixCommand 表明该方法为hystrix包裹，可以对依赖服务进行隔离、降级、快速失败、快速重试等等hystrix相关功能
     该注解属性较多，下面讲解其中几个
     fallbackMethod 降级方法
     commandProperties 普通配置属性，可以配置HystrixCommand对应属性，例如采用线程池还是信号量隔离、熔断器熔断规则等等
     ignoreExceptions 忽略的异常，默认HystrixBadRequestException不计入失败
     groupKey() 组名称，默认使用类名称
     commandKey 命令名称，默认使用方法名
     * 断路器很好理解, 当Hystrix Command请求后端服务失败数量超过一定比例(默认50%),
     * 断路器会切换到开路状态(Open). 这时所有请求会直接失败而不会发送到后端服务. 断路器保持在开路状态一段时间后(默认5秒),
     * 自动切换到半开路状态(HALF-OPEN). 这时会判断下一次请求的返回情况, 如果请求成功, 断路器切回闭路状态(CLOSED), 否则重新切换到开路状态(OPEN).
     * 一旦后端服务不可用, 断路器会直接切断请求链, 避免发送大量无效请求影响系统吞吐量, 并且断路器有自我检测并恢复的能力.
     * @param age1
     * @param age2
     * @return
     * @throws Exception
     */
    @Override
    @HystrixCommand(fallbackMethod ="fallback")
    public int add(int age1, int age2) throws Exception {
        System.out.println("测试123");
        if (age1 == 1) {
            throw new Exception("测试熔断机制");
        }
        return feginClientIface.add(age1 + age2);
    }

    @Override
    public String addStr(String userName, String address) {
        return feginClientIface.testStr(userName,address);
    }

    @Override
    public void addUser(UserInfo userInfo) {
        feginClientIface.addUser(userInfo);
    }

    /**
     * Fallback相当于是降级操作. 对于查询操作, 我们可以实现一个fallback方法, 当请求后端服务出现异常的时候, 可以使用fallback方法返回的值. fallback方法的返回值一般是设置的默认值或者来自缓存.
     * @param age1
     * @param age2
     * @return
     */
    int fallback(int age1, int age2){
        return 1000;
    }
}
