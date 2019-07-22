<#include "common.ftl">
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${_daofullname}"><!--dsName="" saasAspect=""-->
	<!-- <cache readOnly="true"/> -->
    <sql id="allFields">
    	<#list obj.props as prop> 
		<#if prop.columnName != prop.name>
		${prop.columnName} as ${prop.name}<#if prop_has_next>,</#if>
		<#else>
		${prop.columnName}<#if prop_has_next>,</#if>
		</#if>
		</#list>
    </sql>
    
	<sql id="insertFields">
    	<#list obj.props as prop> 
		${prop.columnName}<#if prop_has_next>,</#if>
		</#list>
    </sql>
	
	<insert id="insert" parameterType="${_cfullname}">
		insert into ${obj.tableName} (<include refid="insertFields"/>)
		values (
		<#list obj.props as prop> 
			#${_brack}${prop.name}}<#if prop_has_next>,</#if>
		</#list>
		)
	</insert>
	
	<insert id="insertInBatch" parameterType="${_cfullname}">
		insert into ${obj.tableName} (<include refid="insertFields"/>) values 
		<foreach collection="list" item="item" index="index" separator=",">
			(
			<#list obj.props as prop> 
				#${_brack}item.${prop.name}}<#if prop_has_next>,</#if>
			</#list>
			)
		</foreach>
	</insert>   		
	
    <sql id="whereClause">
	<#list obj.props as prop> 
		<#if obj.primaryKey != prop.name>
		<if test="${prop.name} != null">			
			<#if prop.name = "createdDate">
			<#if (prop_index > 1)>and </#if>${prop.columnName} &gt;= #${_brack}${prop.name}}
			<!--and ${prop.columnName} &lt;= ${r"#{createdDate_fan1}"} -->
			<#else>
			<#if (prop_index > 1)>and </#if>${prop.columnName} = #${_brack}${prop.name}}			
			</#if>			
		</if>
		</#if>
	</#list>
	</sql>
    
	<select id="selectOne" resultType="${_cfullname}" parameterType="${obj.pkJavaType}" >
		select <include refid="allFields"/> 
		  from ${obj.tableName} 
		 where ${obj.jdbcPK} = #${_brack}${obj.primaryKey}} 
	</select>
	
	<select id="selectByIds" parameterType="List" resultType="${_cfullname}">
		select <include refid="allFields"/> from ${obj.tableName} 
		where ${obj.jdbcPK} in
		<foreach collection="list" item="item" open="(" close=")" separator=",">
			${r"#{item}"}
		</foreach>
	</select>
		
	<select id="selectAll" resultType="${_cfullname}">
		select <include refid="allFields"/> 
		from ${obj.tableName} 
	</select>

	<select id="selectCount" resultType="long">
		select count(*) from ${obj.tableName} 
	</select>
	
	<select id="selectCountByWhere" parameterType="Map" resultType="Long">
		select count(*) from ${obj.tableName} 
		<where>
		    <include refid="whereClause"/>
		</where>
	</select>

	<select id="selectByWhere" parameterType="Map" resultType="${_cfullname}">
		select <include refid="allFields"/>
		from ${obj.tableName} 
		<where>
		    <include refid="whereClause"/>
		</where>				
		<if test="orderby != null">
			ORDER BY ${r"${orderby}"}
		</if>
	</select>
	
	<sql id="setComn">		
		<#list obj.props as prop> 
		<#if obj.primaryKey != prop.name>
		<if test="${prop.name} != null">
			${prop.columnName} = #${_brack}${prop.name}}<#if prop_has_next>,</#if>
		</if>
		</#if>
		</#list>
	</sql>
	
	<update id="update" parameterType="${_cfullname}">
		 update ${obj.tableName} 
		    <set>
		    	<include refid="setComn"/>
			</set>
		  where ${obj.jdbcPK} = #${_brack}${obj.primaryKey}}
	</update>
	
	<update id="updateByMap" parameterType="Map">
		 update ${obj.tableName} 
		    <set>
		    	<include refid="setComn"/>
			</set>
		  where ${obj.jdbcPK} = #${_brack}${obj.primaryKey}}
	</update>
	
	<delete id="delete">
		delete from ${obj.tableName} where ${obj.jdbcPK} = #${_brack}${obj.primaryKey}}
	</delete>
	
	<delete id="deleteMulti" parameterType="List">
		delete from ${obj.tableName}
		where ${obj.jdbcPK} in
		<foreach collection="list" item="item" open="(" close=")" separator=",">
			${r"#{item}"}
		</foreach>
	</delete>
	
	<delete id="deleteByWhere" parameterType="Map">
		delete from ${obj.tableName} 
		<where>
		    <include refid="whereClause"/>
		</where>
	</delete>	

</mapper>    
