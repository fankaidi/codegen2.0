<#include "common.ftl">
/*
 * 文件名称: ${_cname}Dao.java
 * 版权信息: Copyright 2001-${_year} ${_company}. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: ${_uname}
 * 修改日期: ${_time}
 * 修改内容: 
 */
package ${_daopkg};
import co.kensure.annotation.MyBatisRepository;
import co.kensure.frame.JSBaseDao;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import ${_cfullname};

/**
 * ${_description}Dao接口类
 * 
 * @author ${_uname} created on ${_time}
 * @since 
 */
 @MyBatisRepository
public interface ${_daoname} extends JSBaseDao<${_cname}> {
	
	
	public ${_cname} selectOne(Long id);
	
	public List<${_cname}> selectByIds(Collection<Long> ids);
	
	public List<${_cname}> selectAll();
	
	public List<${_cname}> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(${_cname} obj);
	
	public boolean insertInBatch(List<${_cname}> objs);
	
	
	public boolean update(${_cname} obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
