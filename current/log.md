----------------------------------------------------------------------------------------------------------------
https://182.92.243.145/svn/erp   //项目svn
http://erp.yifeng-dl.com/	 //项目网址
192.168.1.55
http://localhost:8083
http://ump.yifeng-dl.com
https://192.168.1.2:8006  服务器地址  
https://192.168.1.3:8006  服务器地址107    ip：192.168.1.17 内网
d:/ftp 文件夹下

外网
IFengLoginUrl = http://182.92.243.145:8081
IFengUrl = http://182.92.243.145:8083 

马继开
IFengLoginUrl = http://192.168.1.55:8082
IFengUrl = http://192.168.1.51:8083

内网
IFengLoginUrl = http://192.168.1.55:8083
IFengUrl = http://192.168.1.17:8083

/getWxSeriesDataAmount.ajax   统计图表

通过‘系统管理’查看开发使用哪个网页

https://192.168.1.3:8006  远程终端 root/!QAZ2wsx 


javaee开发
web 终端：192.168.1.16 开始-->附件-->远程桌面-->左 密码106-->206

数据库xml文件查找
attendanceSumService -->attendanceSum Ctrl+shift+R --> AttendanceSumExtMapper.xml

List<String> yearList = expenseAccountStatisticsService.getYearAccountStatistics();
对应接口实现类：ExpenseAccountStatisticsServiceImpl
this.expressAccountExtMapper.getYearAccountStatistics();找到xml文件，对应是erp-core-src包下的xml文件
getYearAccountStatistics对应在xml文件中的id,必须是getYearAccountStatistics名



单独方法（供其它sql调用）
<sql id="Where_Clause">
    <where>
    	<if test="conditions.month != null and conditions.month != ''">
    		and t_attendance_sum_month =#{conditions.month}
    	</if>
    	<if test="conditions.year != null and conditions.year != ''">
    		and t_attendance_sum_year =#{conditions.year}
    	</if>
    	<if test="conditions.userId!= null and conditions.userId != ''">
    		and user_id =#{conditions.userId}
    	</if>
    	    
    </where>
 </sql>
 
---------------------------------------------------------------------------------------------------------------
 [ftp服务器搭建]
1.在管理中添加用户名 记住名字和密码
2.在windows中开启ftp服务和iis服务
3.管理工具-->iis管理器，添加ftp站点
4.ssl选无，身份验证（基本），授权（所有用户），权限（读，写）
5.在防火墙中添加ftp过滤

[用户名：root](密码：123456)<192.168.1.18>
-----------------------------------------------------------------------------------------------------------------
Gson gson = new Gson();
JavaBean jsonjava = gson.fromJson(results, JavaBean.class);
Log.e("GSON", jsonjava.getCustomerId());

eclipse快捷键
Ctrl+k:自动查找下一个字段
调试:Ctrl+shift+i
-------------------------------------------------------------------------------------------------------------------
postgre 
端口 5432
密码123456


	select b.mon,b.ye,COALESCE(sum(jz),0) as jz1
		,COALESCE(sum(cz),0) as cz1
		,COALESCE(sum(jz),0)-COALESCE(sum(cz),0) as ce 
		from (
			SELECT(Case when a.classify ='023001' then a.sm end)as jz,
			(Case When a.classify ='023002'then a.sm end)as cz,
				a.mon,a.ye
		from
			(SELECT  classify ,EXTRACT(MONTH from billing_time) as mon,
			EXTRACT(YEAR from billing_time) as ye,
			"sum"("sum") as sm 
	    from t_express_account te
			where 1=1
      <if test="conditions.type!= null and conditions.type!= ''">
         and type =#{conditions.type}
      </if>
	     and to_char(create_time,'yyyy') = #{conditions.year} 
	     GROUP BY classify ,mon,ye
	     ORDER BY sm ASC) a
      ) b
	GROUP BY b.mon,b.ye
	ORDER BY b.mon ASC
	
	
	SELECT classify ,EXTRACT(MONTH from billing_time) as month,EXTRACT(YEAR from billing_time) as year,"sum"("sum") from t_express_account
     where 1=1
     <if test="conditions.type!= null and conditions.type!= ''">
         and type =#{conditions.type}
      </if>
     <if test="conditions.classify!= null and conditions.classify != ''">
    		 and classify =#{conditions.classify}
     </if>
     and to_char(billing_time,'yyyy') = #{conditions.year} 
     GROUP BY classify ,month,year
     ORDER BY month ASC
----------------------------------------------------------------------------------------------------------------------------------------------
翼峰企业邮箱地址 http://mail.yifeng-dl.com/
 8cb65d793df4457cae60484e6973e2d5
 
 http://192.168.1.53:8081/setRoles/loadRoleTree.ajax
 
 

 
 [{"checked":false,"children":[{"checked":false,"id":"0813dc3fe2fa40b5955a77fa95c0e345","parentId":"01edb238b51846a0bd0e3865755993dc","sortorder":0,"text":"系统管理员"}],"id":"01edb238b51846a0bd0e3865755993dc","sortorder":0,"state":"open","text":"管理员"},{"checked":false,"children":[{"checked":false,"id":"a6509c7f484b482ab979aff844acbd2f","parentId":"4d24e45a24ff4ae1859137d0c9db17e9","sortorder":0,"text":"BOSS"},{"checked":false,"id":"f8dc2c2f6ece4f38a8df43ab4d4a4c5d","parentId":"4d24e45a24ff4ae1859137d0c9db17e9","sortorder":0,"text":"项目经理"}],"id":"4d24e45a24ff4ae1859137d0c9db17e9","sortorder":0,"state":"open","text":"管理人员"},{"checked":false,"children":[{"checked":false,"id":"0e039933cb7249489c9c840e3aa4f6cd","parentId":"69ee0804f30841ac86204c0b55caff96","sortorder":0,"text":"物流业务员"},{"checked":false,"id":"8cb65d793df4457cae60484e6973e2d5","parentId":"69ee0804f30841ac86204c0b55caff96","sortorder":0,"text":"财务人员"},{"checked":false,"id":"a63711b079154ccba4d4aff08d65b484","parentId":"69ee0804f30841ac86204c0b55caff96","sortorder":0,"text":"开发人员"}],"id":"69ee0804f30841ac86204c0b55caff96","sortorder":0,"state":"open","text":"员工"}]
 
 
 
 http://i.yifeng-dl.com/setRoles/loadRoleTree.ajax
 
 http://i.yifeng-dl.com/identify/index.jhtml?clientUrl=http://erp.yifeng-dl.com/setRoles/loadRoleTree.ajax
 
 
 http://ump.yifeng-dl.com/setRoles/loadRoleTree.ajax?sessionid=3bll5tranb7p26sh94g2rhs3
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 