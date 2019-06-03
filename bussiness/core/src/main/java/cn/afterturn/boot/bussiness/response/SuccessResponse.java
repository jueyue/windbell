package cn.afterturn.boot.bussiness.response;

import io.swagger.annotations.ApiModel;

/**
 * 返回给前台的成功提示
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:05:22
 */
@ApiModel("返回")
public class SuccessResponse<T> extends Response {

    public SuccessResponse() {
        super.code = 200;
        super.data = "操作成功";
    }

    public SuccessResponse(T data) {
        super.code = 200;
        super.data = data;

    }

}
