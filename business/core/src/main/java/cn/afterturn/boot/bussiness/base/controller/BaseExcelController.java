package cn.afterturn.boot.bussiness.base.controller;

import cn.afterturn.boot.bussiness.base.service.IBaseService;
import cn.afterturn.boot.bussiness.constant.factory.PageFactory;
import cn.afterturn.boot.bussiness.request.RequestParams;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.bussiness.response.SuccessResponse;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelXorHtmlUtil;
import cn.afterturn.easypoi.excel.entity.ExcelToHtmlParams;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 基础带有报表服务的控制层
 *
 * @author by jueyue on 18-7-4.
 * @since 1.0
 */
public class BaseExcelController<S extends IBaseService<T>, T> extends BaseController<S, T> implements IBaseExcelController<T> {

    @Autowired
    protected S baseService;

    @Override
    @ApiOperation(value = "导出Excel")
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public Response<String> exportExcel(@RequestBody RequestParams<T> params, HttpServletRequest request) {
        Page<T> page = PageFactory.init(params);
        page.setSize(Integer.MAX_VALUE);
        QueryWrapper wrapper = new QueryWrapper<>(params.getModel());
        handlerWrapper(wrapper, params.getMap(), params);
        baseService.page(page, wrapper);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), params.getModel().getClass(), page.getRecords());

        return new SuccessResponse<>();
    }

    @Override
    @ApiOperation(value = "预览Excel")
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    public Response<String> preview(@RequestBody RequestParams<T> params, HttpServletRequest request) {
        Page<T> page = PageFactory.init(params);
        page.setSize(Integer.MAX_VALUE);
        QueryWrapper wrapper = new QueryWrapper<>(params.getModel());
        handlerWrapper(wrapper, params.getMap(), params);
        baseService.page(page, wrapper);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), params.getModel().getClass(), page.getRecords());
        return new SuccessResponse<>(ExcelXorHtmlUtil.excelToHtml(new ExcelToHtmlParams(workbook)));
    }

    @Override
    @ApiOperation(value = "导出模板")
    @RequestMapping(value = "/exportTemplate", method = RequestMethod.GET)
    public Response<String> exportExcel(T param) {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), param.getClass(), new ArrayList<>());
        return new SuccessResponse<>();
    }

    @Override
    @ApiOperation(value = "导入Excel")
    @RequestMapping(value = "/import/{fileId}", method = RequestMethod.GET)
    public void importExcel(@PathVariable String fileId, T param) {

    }
}
