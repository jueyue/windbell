package com.wupaas.boot.paas.file.thirdservice;

import com.wupaas.boot.paas.common.enums.ChannelEnum;
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
        ChannelEnum channelEnum = ChannelEnum.to(channelId);
        switch (channelEnum) {
            case QI_NIU:
                return fileClientOfQiNiu;
        }
        return fileClientOfQiNiu;
    }


}
