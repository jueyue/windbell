package cn.afterturn.boot.bussiness.base.controller;

import cn.afterturn.boot.bussiness.request.RequestParams;
import cn.afterturn.boot.bussiness.response.Response;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 基础RestController
 *
 * @author by jueyue on 18-7-2.
 */
@RestController
public interface IBaseController<T> {

    /**
     * 分页查询数据
     *
     * @param params
     * @param request
     * @return
     */
    @ApiOperation(value = "列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Response<Page<T>> list(@RequestBody RequestParams<T> params, HttpServletRequest request);

    /**
     * 新增对象
     *
     * @param model
     * @return
     */
    @ApiOperation(value = "新增")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response create(@RequestBody @Valid T model);

    /**
     * 删除对象
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public Response delete(@PathVariable String id);

    /**
     * 批量删除对象
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除")
    @RequestMapping(value = "/deleteBatchIds", method = RequestMethod.POST)
    public Response deleteBatchIds(@RequestBody List<String> ids);

    /**
     * 修改对象
     *
     * @param model
     * @return
     */
    @ApiOperation(value = "更新")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(T model);

    /**
     * 查询对象详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "详情ById")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Response<T> detail(@PathVariable String id);

    /**
     * 查询对象详情
     *
     * @param model
     * @return
     */
    @ApiOperation(value = "详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Response<T> detailByObj(T model);
}
