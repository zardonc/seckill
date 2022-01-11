package org.seckill.controller;

import org.seckill.zk_manager.lockinfo.LockerByZk;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/testzk")
public class ZkTestController {

    @Resource
    private LockerByZk locker;

    @GetMapping("/lock")
    public Boolean getLock(@RequestParam String path) {
        locker.acquireDistributedLock(path);
        return Boolean.TRUE;
    }

    @GetMapping(value = "/release")
    private boolean releaseLock(@RequestParam String path) {
        locker.releaseDistributedLock(path);
        return true;
    }
}
