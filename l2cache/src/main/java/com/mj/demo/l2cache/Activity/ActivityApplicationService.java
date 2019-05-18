package com.mj.demo.l2cache.Activity;

import com.reger.l2cache.cache.annotation.L2Cache;
import org.springframework.stereotype.Service;

@Service
public class ActivityApplicationService {


    @L2Cache(key="'test.id.'+#id",timeOut=2000)
    public Object findById(int id) {
        System.err.println("进入缓存执行的方法");
        return "hello word ! findById " +id;
    }
}
