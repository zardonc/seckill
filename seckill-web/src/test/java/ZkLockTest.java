import org.apache.curator.framework.CuratorFramework;
import org.junit.jupiter.api.Test;
import org.seckill.SecondKillApplication;
import org.seckill.zk_manager.lockinfo.LockerByZk;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = SecondKillApplication.class)
public class ZkLockTest {
    String path1 = "testLock1";
    String path2 = "testLock2";
    @Resource
    private LockerByZk locker;
    @Resource(name = "myCuratorFramework")
    private CuratorFramework curatorFramework;

    @Test
    void testLocker() {
        locker.acquireDistributedLock(path1);
        locker.acquireDistributedLock(path2);
        locker.releaseDistributedLock(path1);
        locker.releaseDistributedLock(path2);
    }
}
