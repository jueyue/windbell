package cn.afterturn.boot.paas.common.client;

/**
 * @author jueyue on 19-11-14.
 */
public interface IClient {

    /**
     * 获取当前使用的三方
     *
     * @return
     */
    public String getChannelId();
}
