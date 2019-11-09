package cn.afterturn.boot.paas.im.thirdservice.dingtalk.clients;

import cn.afterturn.boot.paas.im.thirdservice.dingtalk.config.DingTalkConfiguration;
import cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.DingTalkDeptModel;
import cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.DingTalkBaseResultModel;
import cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.dept.DingTalkDeptListResultModel;
import cn.afterturn.boot.paas.im.thirdservice.dingtalk.model.result.dept.DingTalkDeptParentResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 部门管理
 *
 * @author by jueyue on 19-7-17.
 */
@FeignClient(name = "dingTalkDeptClient", contextId = "dingtalk", url = "https://oapi.dingtalk.com/department", configuration = DingTalkConfiguration.class)
public interface IDingTalkDeptClient {


    /**
     * 创建部门
     *
     * @param dept
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    DingTalkBaseResultModel create(@RequestBody DingTalkDeptModel dept);

    /**
     * 更新部门
     *
     * @param dept
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    DingTalkBaseResultModel update(@RequestBody DingTalkDeptModel dept);

    /**
     * 删除部门
     *
     * @param id 部门id (注：不能删除根部门；当部门里有员工，或者部门的子部门里有员工，也不能删除)
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    DingTalkBaseResultModel delete(@RequestParam("id") String id);

    /**
     * 获取子部门ID列表
     *
     * @param id 父部门id。根部门的话传1
     * @return DingTalkDeptListResultModel
     **/
    @RequestMapping(value = "/list_ids", method = RequestMethod.GET)
    DingTalkDeptListResultModel listIds(@RequestParam("id") String id);

    /**
     * 获取部门列表
     *
     * @param lang        通讯录语言（默认zh_CN，未来会支持en_US）
     * @param fetch_child 是否递归部门的全部子部门，ISV微应用固定传递false
     * @param id          父部门id（如果不传，默认部门为根部门，根部门ID为1）
     * @return DingTalkDeptListResultModel
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    DingTalkDeptListResultModel list(@RequestParam("lang") String lang, @RequestParam("fetch_child") String fetch_child, @RequestParam("id") String id);

    /**
     * 获取部门详情
     *
     * @param id   部门id
     * @param lang 通讯录语言(默认zh_CN，未来会支持en_US)
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    DingTalkBaseResultModel get(@RequestParam("id") String id, @RequestParam("lang") String lang);

    /**
     * 查询部门的所有上级父部门路径
     *
     * @param id 希望查询的部门的id，包含查询的部门本身
     * @return DingTalkDeptListResultModel
     **/
    @RequestMapping(value = "/list_parent_depts_by_dept", method = RequestMethod.GET)
    DingTalkDeptListResultModel listParentDeptsByDept(@RequestParam("id") String id);

    /**
     * 查询指定用户的所有上级父部门路径
     *
     * @param userId 希望查询的用户的id
     * @return DingTalkBaseResultModel
     **/
    @RequestMapping(value = "/list_parent_depts", method = RequestMethod.GET)
    DingTalkDeptParentResultModel listParentDepts(@RequestParam("userId") String userId);
}