package cn.afterturn.boot.paas.file.thirdservice;

import cn.afterturn.boot.paas.common.client.IClient;

import java.io.InputStream;

/**
 * @author jueyue on 19-11-14.
 */
public interface IFileClient extends IClient {

    /**
     * 上传
     *
     * @param file
     * @param fileName
     * @return
     */
    public String upload(byte[] file, String fileName);

    /**
     * 上传
     *
     * @param is
     * @param fileName
     * @return
     */
    public String upload(InputStream is, String fileName);
}
