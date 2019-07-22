/*
 * 文件名称: ChangePackage.java
 * 版权信息: Copyright 2013-2015 chunchen technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: zhangyz
 * 修改日期: 2015-2-4
 * 修改内容: 
 */
package com.hzecool.codegen.refact;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import org.apache.commons.lang3.StringUtils;

/**
 * 包重构工具插件
 * @author zhangyz created on 2015-2-4
 */
public class ChangePackage implements ProcessPlugIn {

    @Override
    public void process(File origionFile, BufferedReader reader, RefactParam param) throws Exception {
        File destFile = new File(origionFile.getParentFile(), origionFile.getName() + ".tmp"); 
        OutputStreamWriter ws = new OutputStreamWriter(new FileOutputStream(destFile), param.getDestCode());
        BufferedWriter writer = new BufferedWriter(ws);            
        boolean changed = false;
        do {
            String line = reader.readLine();   
            if (line == null)
                break;
            if (line.length() >= param.getMinLen() && param.getOrigionPackName() != null 
                    && param.getOrigionPackName().length > 0) {
                line = StringUtils.replaceEach(line, 
                        param.getOrigionPackName(), param.getDestPackName());
                changed = true;
            }
            writer.write(line);
            writer.write("\r\n");
        }
        while (true);            
        writer.close();
        reader.close();
        if (changed) {
        	origionFile.delete();
        	destFile.renameTo(origionFile);
        	System.out.println(origionFile.getName() + "处理成功!");    
        }
        else {
        	destFile.delete();
        	System.out.println(origionFile.getName() + "无需处理!");  
        }
    }

    @Override
    public void processAfter(File origionFile, RefactParam param)  throws Exception {
        //改名
        //origionFile.delete();
    }

    @Override
    public void processAllAfter(RefactParam param) {
        System.out.println("全部处理结束!请修改包目录路径名...................");
    }    

}
