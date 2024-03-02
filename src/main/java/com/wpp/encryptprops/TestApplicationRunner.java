package com.wpp.encryptprops;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wpp.encryptprops.db.User;
import com.wpp.encryptprops.db.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class TestApplicationRunner implements ApplicationRunner {
    @Autowired
    private UserDao userDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = userDao.selectOne(new QueryWrapper<User>().lambda().eq(User::getUid, 1));
        System.out.println("email=" + user.getEmail());
    }
}
