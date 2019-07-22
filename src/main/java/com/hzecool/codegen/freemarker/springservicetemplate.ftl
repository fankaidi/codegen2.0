<#include "common.ftl">
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
 	http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
    http://www.springframework.org/schema/tx 
    http://www.springframework.org/schema/tx/spring-tx-2.5.xsd
    http://www.springframework.org/schema/aop 
    http://www.springframework.org/schema/aop/spring-aop-2.5.xsd
    http://www.springframework.org/schema/context 
    http://www.springframework.org/schema/context/spring-context-2.5.xsd">

	<bean id="${_daouname}"
		class="org.mybatis.spring.mapper.MapperFactoryBean">
		<property name="sqlSessionFactory" ref="sqlSessionFactory" />
		<property name="mapperInterface" value="${_daofullname}" />
	</bean>
	
	<bean id="${_serviceuname}"
		class="${_serviceimplfullname}">
		<property name="dao" ref="${_daouname}" />
	</bean> 

</beans>
