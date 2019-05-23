package com.jing.pojo;

import java.util.List;

/**
 * @ClassName:UserVO
 * @Description TODO
 * @author:RanMoAnRan
 * @Date:2019/5/22 20:52
 * @Version 1.0
 */
public class UserVO {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserVO [users=" + users + "]";
    }
}
