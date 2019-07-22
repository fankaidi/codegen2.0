/*
 * 文件名称: Temlate.java
 * 版权信息: Copyright 2001-2012 ZheJiang Collaboration Data System Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: huangwb
 * 修改日期: 2012-3-1
 * 修改内容: 
 */
package com.hzecool.codegen.util;

/**
 * 
 * @author <a href="mailto:huangwb@zjcds.com">huangwb</a> created on 2012-3-1
 * @since DE6.0
 */
public enum TemlateEnum {
    TEMPCODEFILENAME(0,"codetemplate.ftl","model",".java",true),
    TEMPDAOFILENAME(1,"codedaotemplate.ftl","dao","Dao.java",true),
    TEMPACTIONTESTFILENAME(2,"codeactiontesttemplate.ftl","view","ActionTest.java",true),
//    TEMPMAPPERFILENAME(3,"mappertemplate.ftl","xml","Mapper.xml",false),
    TEMPMAPPERFILENAME(3,"mappertemplate.ftl","dao","Mapper.xml",true),
    TEMPSERVICEFILENAME(4,"codeservicetemplate.ftl","service","Service.java",true),//原来是接口，现在变成了新版的servcie实现类
    TEMPSERVICEIMPLFILENAME(5,"codeserviceimpltemplate.ftl","service.impl","ServiceImpl.java",true),//废弃
    TEMSPRINGFILENAME(6,"springservicetemplate.ftl","spring",".service.xml",false),
    TEMPQUERYVIEWFILENAME(7,"codequeryactiontemplate.ftl","view","QueryAction.java",true),
    TEMPSAVEVIEWFILENAME(8,"codesaveactiontemplate.ftl","view","SaveAction.java",true),
    TEMPSTUTSFILENAME(9,"strutstemplate.ftl","struts",".web.xml",false),
    TEMPCONTROLLFILENAME(10,"codeactiontemplate.ftl","action","Action.java",true),
    TEMPDAOTESTFILENAME(11,"codedaotesttemplate.ftl","","ServiceTest.java", true);
    
    private int tmpNo;
    private String tmpName;
    private String pkgSux;
    private String suffix;
    private Boolean isJavaFile;
    
    TemlateEnum(int tmpNo,String tmpName,String pkgSux,String suffix,Boolean isJavaFile) {
    	this.tmpNo = tmpNo;
    	this.tmpName = tmpName;
    	this.suffix = suffix;
    	this.pkgSux = pkgSux;
    	this.isJavaFile = isJavaFile;
    }

	public int getTmpNo() {
		return tmpNo;
	}

	public void setTmpNo(int tmpNo) {
		this.tmpNo = tmpNo;
	}

	public String getTmpName() {
		return tmpName;
	}

	public void setTmpName(String tmpName) {
		this.tmpName = tmpName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setPkgSux(String pkgSux) {
		this.pkgSux = pkgSux;
	}

	public String getPkgSux() {
		return pkgSux;
	}

	public void setIsJavaFile(Boolean isJavaFile) {
		this.isJavaFile = isJavaFile;
	}

	public Boolean getIsJavaFile() {
		return isJavaFile;
	}
    
}
