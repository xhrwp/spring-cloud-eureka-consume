package com.eureka.client.eurekaclient;

/**
 * @author 许洪荣
 * @date 2017/8/15
 */
public interface UserService {
    int add(int age1,int age2) throws Exception;
    String addStr(String userName,String address);

    void addUser(UserInfo userInfo);
}
