package org.seckill.zk_manager.lockinfo;

/**
 * 功能描述: zk锁基本信息
 * 〈〉
 *
 * @Param:
 * @Return:
 * @Author: lang
 */
public class LockInfo {
    /**
     * 临时目录分隔符
     */
    public final static String SEPARATOR_CHARACTER = "/";
    /**
     * 当前锁节点
     */
    private String node;
    /**
     * 当前锁 前一节点
     */
    private String lastNode;
    /**
     * 当前锁的索引路径
     */
    private String lockPath;

    public LockInfo() {
    }

    public LockInfo(String lockPath) {
        this.lockPath = lockPath;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public void setLastNode(String lastNode) {
        this.lastNode = lastNode;
    }

    public void setLockPath(String lockPath) {
        this.lockPath = lockPath;
    }

    /**
     * 获取当前节点位置路径
     *
     * @return
     */
    public String getNodePath() {
        return lockPath + SEPARATOR_CHARACTER + node;
    }

    /**
     * 获取当前前一个节点位置路径
     *
     * @return
     */
    public String getLastNodePath() {
        return lockPath + SEPARATOR_CHARACTER + lastNode;
    }
}
