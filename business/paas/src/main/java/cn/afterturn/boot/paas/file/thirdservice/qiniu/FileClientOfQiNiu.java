package cn.afterturn.boot.paas.file.thirdservice.qiniu;

import cn.afterturn.boot.paas.common.enums.ChannelEnum;
import cn.afterturn.boot.paas.common.exception.BizException;
import cn.afterturn.boot.paas.common.exception.BizExceptionEnum;
import cn.afterturn.boot.paas.file.thirdservice.IFileClient;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * @author jueyue on 19-11-14.
 */
@Slf4j
@Component("fileClientOfQiNiu")
public class FileClientOfQiNiu implements IFileClient {

    private Configuration cfg = new Configuration(Region.region2());

    @Value("${file.qiniu.accesskey}")
    private String accesskey;
    @Value("${file.qiniu.secretkey}")
    private String secretkey;
    @Value("${file.qiniu.bucket}")
    private String bucket;

    private Auth auth;

    private UploadManager uploadManager;

    @PostConstruct
    public void init() {
        /**
         * 生成上传凭证
         */
        auth = Auth.create(accesskey, secretkey);
        uploadManager = new UploadManager(cfg);

    }

    @Override
    public String upload(byte[] file, String fileName) {
        String token = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(file, fileName, token);
            if (response.isOK()) {
                return response.reqId;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        throw new BizException(BizExceptionEnum.UPLOAD_ERROR);
    }

    @Override
    public String upload(InputStream is, String fileName) {
        String token = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(is, fileName, token, null, null);
            if (response.isOK()) {
                return response.reqId;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        throw new BizException(BizExceptionEnum.UPLOAD_ERROR);
    }

    @Override
    public String getChannelId() {
        return ChannelEnum.QI_NIU.getName();
    }
}
