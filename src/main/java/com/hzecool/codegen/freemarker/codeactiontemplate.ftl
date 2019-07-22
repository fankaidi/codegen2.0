<#include "common.ftl">
/*
 * 文件名称: ${_cname}Controller.java
 * 版权信息: Copyright 2001-${_year} ${_company}. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: ${_uname}
 * 修改日期: ${_time}
 * 修改内容: 
 */
package ${_actionpkg};

import ${_cfullname};
import ${_servicefullname};
import ${_daofullname};
import java.util.List;
import com.hzecool.bizfdn.action.EcAction;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * ${_description}控制器入口
 * http://localhost/ctx/${_clname}
 * @author ${_uname} created on ${_time}
 * @since 
 */
@Service
public class ${_viewactionname} extends EcAction<${obj.pkJavaType}, ${_cname}, ${_daoname}, ${_servicename}>{
    
	@Resource
    private ${_servicename} service;
    
    @Override
    protected ${_servicename} getService() {
        return service;
    }    

    @Override
    protected Class<${_cname}> getEntityClass() {
        return ${_cname}.class;
    }

    @Override
    protected Type getEntityType() {
        return new TypeToken<${_cname}>(){}.getType();
    }

    @Override
    protected Type getEntityListType() {
        return new TypeToken<List<${_cname}>>(){}.getType();
    }
}
