/*
 * 文件名称: ObjSqlFactory.java
 * 版权信息: Copyright 2001-2012 ZheJiang Collaboration Data System Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: huangwb
 * 修改日期: 2012-3-1
 * 修改内容: 
 */
package com.hzecool.codegen.source.sqlfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hzecool.codegen.bean.ArgInfo;
import com.hzecool.codegen.bean.Column;
import com.hzecool.codegen.bean.Obj;
import com.hzecool.codegen.source.DefaultObjFactory;
import com.hzecool.codegen.source.ObjFactory;
import com.hzecool.codegen.util.CodeGenUtil;


/**
 * 
 * @author <a href="mailto:huangwb@zjcds.com">huangwb</a> created on 2012-3-1
 * @since DE6.0
 */
public class ObjSqlFactory implements ObjFactory {
    
    Logger logger = LogManager.getLogger(getClass());

    private static String PRIMARYKEY = "PRIMARY KEY";
    private static String FOREIGNKEY = "FOREIGN KEY";
    
    @Override
    public Obj getObj(ArgInfo argInfo) {
    	String filePath = argInfo.getSqlFilePath();
        InputStream sqlIn = CodeGenUtil.readStream(filePath);
        BufferedReader br = SqlFileReadHelper.readSql(sqlIn);
        String sql = "";
        try {
            sql = SqlFileReadHelper.readSql(br);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("末获取到表名称",e);
        }
        finally {
            try {
                br.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        String tableName = captureTableName(sql);
        if (StringUtils.isNotBlank(tableName)) {
            argInfo.setTableName(tableName);
        }
        List<Column> columns = parseSql(sql);
        
        return new DefaultObjFactory().genObj(argInfo, columns);
    }

    private List<Column> parseSql(String sql) {
        List<Column> props = new ArrayList<Column>();
        String fbracket = "(";
        String lbracket = ")";
        int fBracketPos = 0;
        int lBracketPos = 0;
        fBracketPos = sql.indexOf(fbracket);
        lBracketPos = sql.lastIndexOf(lbracket);
        String subStr = sql.substring(fBracketPos+1,lBracketPos);
        java.io.StringReader reader = new java.io.StringReader(subStr);
        java.io.BufferedReader br = new java.io.BufferedReader(reader);
        
        String[] temps = null;
        Boolean isPrimary = false;
        String primaryKey = capturePrimarykey(subStr);
        do {
        //String[] lines = subStr.split(",");
        //for (String line : lines) {
        	String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
        	if (line == null)
        		break;
        	
            isPrimary = false;
            line = line.trim();
            if (line.length() == 0)
            	continue;
            if (line.endsWith(",")){
            	line = line.substring(0,line.length()-1);
            }
            
            if(line.toUpperCase().contains(PRIMARYKEY) && line.endsWith(",")) {
                isPrimary = true;
            } else if(line.toUpperCase().contains(PRIMARYKEY) || line.toUpperCase().contains(FOREIGNKEY)) {
                continue;
            }
            temps = StringUtils.splitByWholeSeparator(line, " ");// line.split(" ");
            if(temps.length > 1) {
                String colName = CodeGenUtil.trimMySql(temps[0]);                
                if (StringUtils.containsIgnoreCase(primaryKey, colName)) {
                    isPrimary = true;
                }
                String colType = temps[1].replaceAll("\\(.*?\\)", "");
                int length = parserLength(temps[1]);
                if ((colType.equalsIgnoreCase("BIGINT") || colType.equalsIgnoreCase("BIGSERIAL")) && length <= 0)
                	length = 20;//这样后面可以为long，补丁
                
                Column column = new Column(colName, colType, isPrimary, length);
                column.setComment(parserComment(temps));
                props.add(column);
            } 
        }
        while(true);
        return props;
    }
    
    /**
     * 解析字段注释，如： COMMENT '分成id'
     * @return
     * @author zhangyz created on 2014-4-25
     */
    private String parserComment(String[] temps) {
        for (int ii = 0; ii < temps.length; ii++) {
            if ("COMMENT".equalsIgnoreCase(temps[ii])) {
                String comment = "";
                for (ii = ii + 1; ii < temps.length; ii++) {
                	String part = temps[ii];

                	part = CodeGenUtil.trimMySql(part, ",");
                    comment += part + " ";
                }
                comment = comment.trim();
                comment = CodeGenUtil.trimMySql(comment, "'");
                return comment;                
            }
        }        
        /*String hint = temps[temps.length - 2];//取倒数第二个
        if ("COMMENT".equalsIgnoreCase(hint)) {
            String comment = temps[temps.length - 1];
            comment = CodeGenUtil.trimMySql(comment, "'");
            return comment;
        }*/
        return null;
    }
    
    /**
     * 解析表达式中的字段长度
     * @param token 如：numeric(8)
     * @return 若没有则返回0，代表没有长度
     * @author zhangyz created on 2014-4-25
     */
    private int parserLength(String token) {
        int length = 0;
        int begin = token.indexOf("(");
        int end = token.indexOf(")", begin + 1);
        if (end > begin && end > 1) {
            String strlen = token.substring(begin + 1, end);
            if (strlen != null && strlen.length() > 0) {
                try {
                	String[] lens = StringUtils.splitByWholeSeparator(strlen, ",");
                    length = Integer.parseInt(lens[0]);
                }
                catch(Exception ex) {
                    System.out.println("解析" + token + "中的字段长度失败:" + ex.getMessage());                            
                }
            }
        }
        return length;
    }
    
    /**
     * 获取主键
     * 
     * @param line
     * @return
     */
    private String capturePrimarykey(String line) {
        line = line.trim();
        if(line.toUpperCase().contains(PRIMARYKEY)) {
        	int start = line.toUpperCase().indexOf(PRIMARYKEY) + PRIMARYKEY.length();
        	int end = line.indexOf(",", start);
        	//start and end to capture the primary key line
        	if (end > 0) {
        		return line.substring(start,end);
        	}
        	return line.substring(start);
        }
        return "";
    }
    
    private String captureTableName(String sql) {
        String bracket = "(";
        int fBracketPos = 0;
        fBracketPos = sql.indexOf(bracket);
        String preStr = sql.substring(0, fBracketPos);
        preStr = preStr.trim();
        
        if (!StringUtils.containsIgnoreCase(preStr, "TABLE")) {
            throw new RuntimeException("末获取到表名称");
        }
        String[] words = preStr.split(" ");
        if (words.length > 0) {
            String ret = words[words.length-1];
            ret = CodeGenUtil.trimMySql(ret);
            return ret;
        }
        throw new RuntimeException("末获取到表名称");
    }
    
}
