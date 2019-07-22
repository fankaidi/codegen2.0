<#include "common.ftl">
/*
 * 文件名称: ${_cname}ServiceImpl.java
 * 版权信息: Copyright 2001-${_year} ${_company}. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: ${_uname}
 * 修改日期: ${_time}
 * 修改内容: 
 */
package ${_servicepkg};

import ${_daofullname};
import ${_cfullname};
import ${_servicefullname};

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import co.kensure.frame.JSBaseService;


/**
 * ${_description}服务实现类
 * @author ${_uname} created on ${_time}
 * @since 
 */
@Service
public class ${_servicename} extends JSBaseService{
	
	@Resource
	private ${_daoname} dao;
    
    
    public ${_cname} selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<${_cname}> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<${_cname}> selectAll(){
		return dao.selectAll();
	}
	
	public List<${_cname}> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(${_cname} obj){
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<${_cname}> objs){
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(${_cname} obj){
		return dao.update(obj);
	}
    
    public boolean updateByMap(Map<String, Object> params){
		return dao.updateByMap(params);
	}
    
    
	public boolean delete(Long id){
		return dao.delete(id);
	}	
	
    public boolean deleteMulti(Collection<Long> ids){
		return dao.deleteMulti(ids);
	}
    
    public boolean deleteByWhere(Map<String, Object> parameters){
		return dao.deleteByWhere(parameters);
	}
    
    
  

}
