package cn.afterturn.boot.bussiness.base.controller;

import cn.afterturn.boot.bussiness.model.RequestParams;
import cn.afterturn.boot.bussiness.response.Response;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 包含了Excel导入,导出,预览等EasyPoi集成服务
 *
 * @author by jueyue on 18-7-4.
 */
public interface IBaseExcelController<T> extends IBaseController<T> {

    /**
     * 导出Excel
     *
     * @param params
     * @param request
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public Response<String> exportExcel(@RequestBody RequestParams<T> params, HttpServletRequest request);

    /**
     * 导出Excel
     *
     * @param params
     * @param request
     * @return
     */
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    public Response<String> preview(@RequestBody RequestParams<T> params, HttpServletRequest request);

    /**
     * 模板Excel
     *
     * @param param 参数类型
     * @return
     */
    @RequestMapping(value = "/exportTemplate", method = RequestMethod.GET)
    public Response<String> exportExcel(T param);

    /**
     * 导入Excel
     *
     * @param fileId 文件ID
     * @param param
     */
    @RequestMapping(value = "/import/{fileId}", method = RequestMethod.GET)
    public void importExcel(@PathVariable String fileId, T param);
}
