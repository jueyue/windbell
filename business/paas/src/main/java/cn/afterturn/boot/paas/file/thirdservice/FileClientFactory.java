package cn.afterturn.boot.paas.file.thirdservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 文件服务
 *
 * @author jueyue on 19-11-14.
 */
@Component
public class FileClientFactory {

    @Value("${file.channelId}")
    private String channelId;

    @Resource(name = "fileClientOfQiNiu")
    private IFileClient fileClientOfQiNiu;

    /**
     * 获取当期使用的三方服务
     *
     * @return
     */
    public IFileClient get() {
        switch (channelId) {
            case "qiuniu":
                return fileClientOfQiNiu;
        }
        return fileClientOfQiNiu;
    }


    /**
     * 获取当前使用的三方
     *
     * @return
     */
    public String getChannelId() {
        return channelId;
    }
}
