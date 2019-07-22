<#include "common.ftl">
/*
 * 文件名称: ${_cname}.java
 * 版权信息: Copyright 2001-${_year} ${_company}. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: ${_uname}
 * 修改日期: ${_time}
 * 修改内容: 
 */
package ${_cpkg};

import co.kensure.frame.BaseInfo;;

/**
 * ${_description}对象类
 * @author ${_uname} created on ${_time}
 * @since
 */
public class ${_cname} extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	<#list obj.props as prop>	
	/**${prop.comment}*/		
	private ${prop.type} ${prop.name};<#if (prop.comment != '')> </#if>

	</#list>

	<#list obj.props as prop>
	public ${prop.type} get${prop.name?cap_first}() {
		return ${prop.name};
	}

	public void set${prop.name?cap_first}(${prop.type} ${prop.name}) {
		this.${prop.name} = ${prop.name};
	}
	</#list>
}
