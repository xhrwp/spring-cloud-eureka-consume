package com.eureka.client.eurekaclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 许洪荣
 * @date 2017/8/15
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private FeginClientIface feginClientIface;

    /**
     * 熔断
     * @param age1
     * @param age2
     * @return
     * @throws Exception
     */
    @Override
    @HystrixCommand(fallbackMethod ="fallback")
    public int add(int age1, int age2) throws Exception {
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
     * 熔断处理
     * @param age1
     * @param age2
     * @return
     */
    int fallback(int age1, int age2){
        return 1000;
    }
}
