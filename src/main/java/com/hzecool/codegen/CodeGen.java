/*
 * 文件名称: CodeGen.java
 * 版权信息: Copyright 2001-2012 ZheJiang Collaboration Data System Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: huangwb
 * 修改日期: 2012-2-16
 * 修改内容: 
 */
package com.hzecool.codegen;

import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
//import org.junit.Test;

//import com.shk.frm.test.CdsBaseTestCase;


import com.hzecool.codegen.bean.ArgInfo;
import com.hzecool.codegen.bean.Obj;
import com.hzecool.codegen.bean.OutputFile;
import com.hzecool.codegen.source.FactoryFactory;
import com.hzecool.codegen.source.ObjFactory;
import com.hzecool.codegen.util.FileWriteUtil;
import com.hzecool.codegen.util.ObjHelper;
import com.hzecool.codegen.util.TmpParseUtil;


/**
 * 代码生成器
 * @author <a href="mailto:huangwb@zjcds.com">huangwb</a> created on 2012-2-16
 * @since DE6.0
 * 
 * 对空值查询条件的处理：
 * <if test="tenantIdNull != null">          
       and tenant_id is null               
   </if>
   
   对空值设置的处理：
   <if test="textNull != null">
       text = null,
   </if>
  
  对查询多id的处理：
  <select id="getByIds" parameterType="List" resultType="Menu">
        select <include refid="allFields"/> from sys_common_menu
        where menu_id in
        <foreach collection="list" item="id"  open="(" separator="," close=")">  
            #{id} 
        </foreach> 
    </select>
     
  以及对多值删除的处理：
      同上
 */
//public class CodeGen  extends CdsBaseTestCase {
public class CodeGen {

    /**
     * @param args
     * @author huangwb created on 2012-2-16 
     * @throws Exception 
     * @since DE6.0 /mfh-comn-server-modules/src/main/java/com/manfen/codegen/gentemplate.xml
     */
    public static void main(String[] args) {
        String filePath = "/com/hzecool/codegen/gentemplate.xml";
        startGenCode(filePath);
        System.out.println("代码成功生成完毕!");
    }

	private static void startGenCode(String filePath) {
		List<ArgInfo> argInfos;
        try {
            argInfos = TmpParseUtil.parseXml(filePath);
            ObjHelper helper = new ObjHelper();
            for (ArgInfo argInfo : argInfos) {
            	try {
            		helper.write(argInfo);
				} catch (Exception e) {
					e.printStackTrace();
				} 
            }
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
    
//    @Test
    public void testGenMapperFromDb() throws Exception {
        String[] strTableName = new String[]{"T_CDS_CODE_VALUE"};
        String[] cName = new String[]{"CodeValue"};
        ArgInfo argInfo = new ArgInfo();
        String pkg = "com.cds.common.syscfg.domain";
        for (int i=0;i<strTableName.length;i++) {
            argInfo.setClassName(cName[i]);
            argInfo.setPkg(pkg);
            argInfo.setTableName(strTableName[i]);
            OutputFile outputFile = new OutputFile();
            outputFile.setClassName(cName[i]);
            outputFile.setOutputType("1111");
            outputFile.setPkg(pkg);
            outputFile.setProjectPath("D:\\gen\\", null);
            argInfo.setOutputInfo(outputFile);
            
            ObjFactory factory = FactoryFactory.createFacotry(FactoryFactory.AryType.METADATA);
            Obj obj = factory.getObj(argInfo);
            Map<String,String> outputMap = argInfo.getOutputInfos();
            for (Map.Entry<String,String> entry : outputMap.entrySet()) {
                writeFile(obj,entry.getKey(),entry.getValue());
            }
        }
    }
    
    public static void writeFile(Object data,String templateName,String filepath) throws Exception{
        FileWriteUtil.writeFile(data,templateName,filepath);
    }
    
}
