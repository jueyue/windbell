package cn.afterturn.boot.bussiness.base.controller;

import cn.afterturn.boot.bussiness.base.page.PageInfo;
import cn.afterturn.boot.bussiness.base.service.IBaseService;
import cn.afterturn.boot.bussiness.constant.factory.PageFactory;
import cn.afterturn.boot.bussiness.exception.BootException;
import cn.afterturn.boot.bussiness.model.RequestParams;
import cn.afterturn.boot.bussiness.response.ErrorResponse;
import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.bussiness.response.SuccessResponse;
import cn.afterturn.boot.core.util.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 基础控制类
 *
 * @author by jueyue on 18-7-2.
 */
public abstract class BaseController<S extends IBaseService<T>, T> implements IBaseController<T> {

    protected static SuccessResponse SUCCESS_RESPONSE = new SuccessResponse();


    @Autowired
    private S baseService;


    @Override
    @ApiOperation(value = "查询列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Response<Page<T>> list(@RequestBody RequestParams<T> params, HttpServletRequest request) {
        Page<T> page = PageFactory.init(params);
        QueryWrapper wrapper = new QueryWrapper<>(params.getModel());
        if (params.getMap() != null) {
            handlerWrapper(wrapper, params.getMap(), params);
        }
        baseService.page(page, wrapper);
        return new SuccessResponse(new PageInfo<T>((page)));
    }

    protected void handlerWrapper(QueryWrapper wrapper, Map<String, String> map, RequestParams<T> params) {

        Class<?> clazz = null;
        if (params.getModel() == null) {
            try {
                clazz = params.getClass().getSuperclass().getDeclaredField("model").getDeclaringClass();
            } catch (NoSuchFieldException e) {
                throw new BootException(500, "参数不对");
            }
        } else {
            clazz = params.getModel().getClass();
        }
        //拼装区间
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            if (key.endsWith("_st") && StringUtils.isNotEmpty(map.get(key))) {
                wrapper.ge(getKey(key.substring(0, key.length() - 3), clazz), getStartTime(map.get(key)));
            }
            if (key.endsWith("_ed") && StringUtils.isNotEmpty(map.get(key))) {
                wrapper.le(getKey(key.substring(0, key.length() - 3), clazz), getEndTime(map.get(key)));
            }
        }
        handlerQuery(wrapper, params);
    }

    /**
     * 客户自定义处理查询参数组装
     *
     * @param wrapper
     * @param params
     */
    public void handlerQuery(QueryWrapper wrapper, RequestParams<T> params) {

    }

    protected String getStartTime(String time) {
        if (time.matches("^\\d{4}-\\d{1,2}$")) {
            return time + "-01 00:00:00";
        } else if (time.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return time + " 00:00:00";
        } else if (time.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return time + ":00";
        } else if (time.matches("^\\d{4}-\\d{1,2}-\\d{1,2}T{1}\\d{1,2}:\\d{1,2}:\\d{1,2}.\\d{3}Z$")) {
            String str = time.replace("T", " ").substring(0, time.indexOf("."));
            return str;
        } else {
            return time;
        }
    }

    protected String getEndTime(String time) {
        if (time.matches("^\\d{4}-\\d{1,2}$")) {
            Date date = DateUtil.parse(time, "yyyy-MM");
            date = DateUtil.getLastDateOfMonth(date);
            return DateUtil.getDay(date) + " 23:59:59";
        } else if (time.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return time + " 23:59:59";
        } else if (time.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return time + ":59";
        } else if (time.matches("^\\d{4}-\\d{1,2}-\\d{1,2}T{1}\\d{1,2}:\\d{1,2}:\\d{1,2}.\\d{3}Z$")) {
            time = time.replace("T", " ").substring(0, time.indexOf("."));
            if (time.endsWith("00:00:00")) {
                time = time.replace("00:00:00", "23:59:59");
            }
            return time;
        } else {
            return time;
        }
    }

    protected String getKey(String key, Class<?> clazz) {
        Field field = ReflectUtil.getField(clazz, key);
        TableField tf = field.getAnnotation(TableField.class);
        if (tf != null && StringUtils.isNotEmpty(tf.value())) {
            String str =  tf.value();
            return str;
        }
        return key;
    }


    @Override
    @ApiOperation(value = "新增")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response create(@RequestBody @Valid T model) {
        baseService.save(model);
        return SUCCESS_RESPONSE;
    }

    @Override
    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public Response delete(@PathVariable String id) {
        baseService.removeById(id);
        return SUCCESS_RESPONSE;
    }

    @Override
    @ApiOperation(value = "批量删除")
    @RequestMapping(value = "/deleteBatchIds", method = RequestMethod.POST)
    public Response deleteBatchIds(@RequestBody List<String> ids) {
        baseService.removeByIds(ids);
        return SUCCESS_RESPONSE;
    }

    @Override
    @ApiOperation(value = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(@RequestBody T model) {
        baseService.updateById(model);
        return SUCCESS_RESPONSE;
    }

    @Override
    @ApiOperation(value = "详情ById")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Response<T> detail(@PathVariable String id) {
        return new SuccessResponse<>(baseService.getById(id));
    }

    @Override
    @ApiOperation(value = "详情ByObj")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Response<T> detailByObj(@RequestBody T model) {
        return new SuccessResponse<>(baseService.getOne(model));
    }
}
