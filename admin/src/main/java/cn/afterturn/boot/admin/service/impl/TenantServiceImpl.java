package cn.afterturn.boot.admin.service.impl;

import cn.afterturn.boot.admin.model.DeptModel;
import cn.afterturn.boot.admin.model.LinkTenantProductModel;
import cn.afterturn.boot.admin.model.TenantModel;
import cn.afterturn.boot.admin.model.UserModel;
import cn.afterturn.boot.admin.repository.TenantRepository;
import cn.afterturn.boot.admin.service.*;
import cn.afterturn.boot.bussiness.base.service.BaseServiceCacheImpl;
import cn.afterturn.boot.core.cache.CacheKey;
import cn.afterturn.boot.core.cache.RedisKit;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * 服务实现
 *
 * @author
 * @Date 2018-09-03 23:21:00
 */
@DependsOn("springContextHolder")
@Service("tenantService")
public class TenantServiceImpl extends BaseServiceCacheImpl<TenantRepository, TenantModel> implements ITenantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantServiceImpl.class);

    @Autowired
    private TenantRepository          tenantRepository;
    @Autowired
    private ISequenceService          sequenceService;
    @Autowired
    private IUserService              userService;
    @Autowired
    private IDeptService              deptService;
    @Autowired
    private ILinkTenantProductService linkTenantProductService;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean save(TenantModel entity) {
        //Step 1 创建一个租户
        // 创建一个租后编号
        String tenantId = "TE" + sequenceService.getNext("TENANT_NO", 8);
        entity.setTenantId(tenantId);
        entity.setStatus("1");
        tenantRepository.insert(entity);
        //Step 2 创建部门
        DeptModel dept = getDeptModel(entity);
        dept.setTenantId(tenantId);
        deptService.save(dept);
        //Step 3 创建管理员
        userService.save(getUserModel(entity, dept));
        //Step 4 关键默认的产品 -- 系统管理
        LinkTenantProductModel link = new LinkTenantProductModel();
        link.setProCode("1001");
        link.setStatus(1);
        link.setStartTime(new Date());
        // 默认一年
        link.setEndTime(DateUtils.addYears(new Date(), 1));
        link.setTenantId(tenantId);
        linkTenantProductService.save(link);

        // 同步redis数据
        RedisKit.put(CacheKey.get("admin").append("tenant").append(entity.getTenantId()).append("appType").toString(), entity.getPaas());
        return true;
    }

    private DeptModel getDeptModel(TenantModel entity) {
        DeptModel dept = new DeptModel();
        dept.setFullName(entity.getName());
        dept.setNum(1);
        dept.setSimpleName(entity.getName());
        dept.setTenantId(entity.getTenantId());
        dept.setCrtUserId(entity.getCrtUserId());
        return dept;
    }

    private UserModel getUserModel(TenantModel entity, DeptModel dept) {
        UserModel user = new UserModel();
        user.setAccount(entity.getPhone());
        user.setIsAdmin(1);
        user.setDeptId(dept.getId());
        user.setPhone(entity.getPhone());
        user.setName(entity.getLinkman());
        user.setPassword(entity.getPhone());
        user.setTenantId(entity.getTenantId());
        return user;
    }

    @Override
    public boolean paasInit(String id) {
        TenantModel tenant = getById(id);
        // 部门
        List<DeptModel> tree = deptService.tree(new QueryWrapper<DeptModel>().eq("TENANT_ID", id));
        deptService.batchToPaas(tree);
        // 角色

        //用户


        return false;
    }

    @PostConstruct
    public void init() {
        List<TenantModel> list = list();
        list.forEach(tenantModel ->
                RedisKit.put(CacheKey.get("admin").append("tenant").append(tenantModel.getTenantId()).append("appType").toString(), tenantModel.getPaas())
        );
    }


}
