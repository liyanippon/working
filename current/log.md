----------------------------------------------------------------------------------------------------------------
https://182.92.243.145/svn/erp   //项目svn
https://182.92.243.145/svn/wphcs //唯品会前台手机端
https://182.92.243.145/svn/wphcs/trunk/src/cms //唯品会后台管理系统

http://erp.yifeng-dl.com/	 //项目网址

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
FTP文件上传：

李龙飞电脑：
阿里云企业邮箱：liyan@yifeng-dl.com/ZAQ!xsw2

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
 http://i.yifeng-dl.com/setRoles/loadRoleTree.ajax
 http://i.yifeng-dl.com/identify/index.jhtml?clientUrl=http://erp.yifeng-dl.com/setRoles/loadRoleTree.ajax
 http://ump.yifeng-dl.com/setRoles/loadRoleTree.ajax?sessionid=3bll5tranb7p26sh94g2rhs3
 
 192.168.1.16:8082/setRoles/loadRoleTree.ajax?sessionid="1pzdkyvclecff196st89v75odu"
 http://192.168.1.16:8082/setRoles/loadRoleTree.ajax?sessionid=%221pzdkyvclecff196st89v75odu%22&userName=%22liudongmei%22
 
 
 http://192.168.1.16:8081/login.jhtml   本地登录
 ----------------------------------------------------------------------------------------------------------------------------------------------
android 最好用的ui框架：https://github.com/wasabeef/awesome-android-ui
仿微信扫码登录：http://blog.csdn.net/xiaanming/article/details/10163203
最全最好用的Android Studio插件整理：http://www.open-open.com/lib/view/open1480329318348.html
给AppCompatActivity的标题栏上加上返回按钮：http://www.open-open.com/lib/view/open1481788218379.html

/getWXAccountsType.ajax //银行账户下拉框

异常全局处理网址：http://blog.csdn.net/cym_lmy/article/details/24704089 
http://www.jb51.net/article/78858.htm
Android 全局异常处理：：http://onewayonelife.iteye.com/blog/1147533

异步任务处理网络请求
http://blog.csdn.net/l631768226/article/details/51858554


考勤统计详细信息：（项目id）
	var projectId = "8f0e04e31b444c8595ea34c4cc630989";//财务项目id liuchao liudongmei(5月之前)
		}else if(userId=='zhangqi'){
			var projectId = "d717f5c7f316439992c28061b03124a0";//物流项目id zhangqi
		}else if(userId=='majikai'||userId=='liyan'){
			var projectId = "b8f9da13246e4f2e9a62a17ff377e3a4";//erp项目id majikai liyan liudongmei(6月后)


定制衬衫svn地址：https://182.92.243.145/svn/wphcs


Struts2 

xml页面跳转传递参数： 

		<!-- 改衣详情 -->
		<action name="/modifydetail/*" class="com.ctm.action.ModifyDetailAction" method="{1}">
			<result name="success">
				/WEB-INF/jsp/modify_detail.jsp
			</result>

			<result name="cancel" type="redirect">
				 showOrderDetails/index?orderNo=${orderNo}&amp;productId=${productId}
			</result>
			 <allowed-methods>cancel</allowed-methods>
		</action>

定制信息struts循环table:

	<div width="89.3%" style="padding-left: 5.3%;height:100% ;padding-right: 5.3%;margin-top:48px;">
		<table style="width:100%;height:100%" >
			<s:iterator value="propList"  status="sta" var="i">
				<tr>
					<td class="td1"><s:property value="#i.propName"/></td>
					<td class="td2"><s:property value="#i.propValue"/></td>
				</tr>
			</s:iterator>
			
		</table>
		<input type="hidden" id="ordermessage" name="ordermessage" value="${orderMessage}"/>
	</div>

productname访问地址
http://localhost:8080/CSDZ/productname/index?productId=1&mode=1&m=webview


html默认背景色：#eee

[工作笔记] 

wifi密码：802.11ac

jquery的写法
<script type="text/javascript">
$(document).ready(function(){
  $("p").click(function(){
  $(this).hide();
  });
});
</script>


struts2学习(二)—action获取表单提交数据的三种方式
http://blog.csdn.net/lvyuan30276/article/details/59638770


JS中怎么使用EL表达式
http://blog.csdn.net/dukangcheng/article/details/50740855
EL表达式只能通过内置对象取值，也就是只读操作

form表单提交

js或jquery
$(document).ready(function(){
	  $("#orderClick").click(function(){
		  $("#form1").attr("action", "${pageContext.request.contextPath}/orderDetails/ordermessage");
		  $("#form1").submit();
	  });
	}); 

&#12288;
他做：量体师，修改地址，定制细节，修改面料，确认订单，订单详情，我的定制，空白页面，我的定制

结算页/列表页/定制属性选择页面/详情页/预约量体页

我的定制，待上门，没加粗
衬衣定制，输入框，间隔线不一致
品牌介绍间隔线
服务信息间隔线，间距，左图标



<div style="width: 100%;height: 30%;padding: 5% 5% 2% 2%;">
			<div style="float:left">
				<img width="45%" height="45%" id="updateBtn" src="${pageContext.request.contextPath}/common/style/images/update.png"/> 
			</div>
			<div style="float:left">
				<s:a value="/login/index?mode=1">
				<!-- <button type="button" style="border:1px solid #dc137d ;background-color: #de3d96;color: #ffffff;" class="mui-btn mui-btn-block btndz ">确认(去定制)</button> -->
				<img width="45%" height="45%" src="${pageContext.request.contextPath}/common/style/images/ensure.png"/> 
				</s:a>
			</div>


<img  width="" height="" alt="" src="${pageContext.request.contextPath}/common/style/images/icon_open_small_blue@2x.png"></img> 	

<div style="padding-left: 10%;width:90%;height:10%;display: inline-block;padding-right:5%;">		
			<div style="display: inline-block;float:left;width:50%">
				<img width="100%" id="updateBtn" src="${pageContext.request.contextPath}/common/style/images/update.png"/> 
			</div>
			<div style="display: inline-block;float:right;width:50%">
				<s:a value="/login/index?mode=1">
				<img width="100%" src="${pageContext.request.contextPath}/common/style/images/ensure.png"/> 
			</s:a>
			</div>
		</div>

vertical-align: middle;纵向垂直居中

802.11ac

衬衣定制

<s:property value="product.name"/>



图片高度
<div id="up"></div>
		<div>
			<img src="${pageContext.request.contextPath}/common/style/images/Bitmap@2x.png" data-preview-src="" data-preview-group="1" width="100%" />
		</div>
document.getElementById("up").style.marginTop = $("#headerDiv").height() + "px";
//控件放到div中，宽高设置100%，调节div






		<!-- 量体师订单 -->
		<action name="/volumedivision/*" class="com.ctm.action.VolumeDivisionOrderAction" method="{1}">
			<result name="success">
				/WEB-INF/jsp/volume_division_order.jsp
			</result>
		</action>
		<!-- 改衣详情 -->
		<action name="/modifydetail/*" class="com.ctm.action.ModifyDetailAction" method="{1}">
			<result name="success">
				/WEB-INF/jsp/modify_detail.jsp
			</result>
		</action>





function phone(){//唤起系统拨号
				
				document.getElementById("telephone").addEventListener('tap',function(){
            	var btnArray=['拨打','取消'];
            	var phone="13693291433";
            	mui.confirm('是否拨打'+phone+'?','提示',btnArray,function(e){
                if(e.index == 0){
                    plus.device.dial(phone,false);
                }else{
                	
                }
            		});
        		});
			};












mui.init({
				swipeBack: false
			});
			(function($) {
				$('.mui-scroll-wrapper').scroll({
					indicators: true //是否显示滚动条
				});
				var html2 = '<ul class="mui-table-view"><li class="mui-table-view-cell">第二个选项卡子项-1</li><li class="mui-table-view-cell">第二个选项卡子项-2</li><li class="mui-table-view-cell">第二个选项卡子项-3</li><li class="mui-table-view-cell">第二个选项卡子项-4</li><li class="mui-table-view-cell">第二个选项卡子项-5</li></ul>';
				var html3 = '<ul class="mui-table-view"><li class="mui-table-view-cell">第三个选项卡子项-1</li><li class="mui-table-view-cell">第三个选项卡子项-2</li><li class="mui-table-view-cell">第三个选项卡子项-3</li><li class="mui-table-view-cell">第三个选项卡子项-4</li><li class="mui-table-view-cell">第三个选项卡子项-5</li></ul>';
				var item2 = document.getElementById('item2mobile');
				var item3 = document.getElementById('item3mobile');
				document.getElementById('slider').addEventListener('slide', function(e) {
					if (e.detail.slideNumber === 1) {
						if (item2.querySelector('.mui-loading')) {
							setTimeout(function() {
								item2.querySelector('.mui-scroll').innerHTML = html2;
							}, 500);
						}
					} else if (e.detail.slideNumber === 2) {
						if (item3.querySelector('.mui-loading')) {
							setTimeout(function() {
								item3.querySelector('.mui-scroll').innerHTML = html3;
							}, 500);
						}
					}
				});
				
			})(mui);
			
			localStorage.setItem('tailor',"1");




showOrderDetails/index?orderNo=201711231000000064&productId=1

<!-- 改衣详情 -->
		<action name="/modifydetail/*" class="com.ctm.action.ModifyDetailAction" method="{1}">
			<result name="success">
				/WEB-INF/jsp/modify_detail.jsp
			</result>
			<result name="cancel" type="chain">
			"showOrderDetails/index?orderNo="+${orderNo}+"&productId="+${productId}</result>			 <allowed-methods>cancel</allowed-methods>
	


	</action>

productname访问地址
http://localhost:8080/CSDZ/productname/index?productId=1&mode=1&m=webview


html默认背景色：#eee


<div class="table-a">
	<table  id="orderTable" class="display" border="0" cellspacing="0" cellpadding="0">
		<!-- <thead> -->
            <tr>
                <td>订单号</td>
                <td>用户ID</td>
				<th>收货人</th>
				<th>收货人手机号码</th>
				<td>sku id</td>
				<td>数量</td>
				<td>支付金额（元）</td>
				<td>下单时间</td>
				<td>状态</td>
            </tr>
        <!-- </thead> -->
	</table>	
	</div>


http://localhost:8080/CSDZ/showOrderDetails/index?orderNo=201711231000000064&productId=1

http://localhost:8080/CSDZ/productname/index?productId=1&mode=1&m=webview


<sql id="Example_Where_Clause">
    <where>
         userId = #{userId}
    </where>





<if test="orderSnArray != null and orderSnArray !='' ">
    		and ord.order_sn in
		<foreach collection="orderSnArray" item="order" index="index" open="("
               separator="," close=")">
			 	'${order}'
		</foreach>
    	</if>


<choose>
    	<when test="orderSn != null and orderSn !='' and orderSn.indexOf(',') >= 0">
			 	
    	</when>
    	<otherwise>
    		and ord.order_sn = ${orderSn}
    	</otherwise>
    	</choose>


			if(orderSn.contains(",")) {
				condition.setOrderSnArray(orderSn.split(","));
			}else {
				String[] str = new String[1];
				str[0]=orderSn;
				condition.setOrderSnArray(str);
			}

http://localhost:8080/cms/getOrderList?stCreateTime=1011400505&etCreateTime=1511530505&page=1&limit=5&mobile=11111111111&orderSn=201711231000000057&orderStatus=1

http://localhost:8080/cms/getOrderList?stCreateTime=1011400505&etCreateTime=1511530505&page=1&limit=5&mobile=11111111111&orderSn=201711231000000057&orderStatus=1&userId=1100022233

<if test="page!=null and limit!=null">  
        		limit ${page},(count-1)/${limit}+1
    	</if>


orderSn.indexOf(',') == 0


	<choose>
    	<when test="orderSn != null and orderSn !='' and orderSn.indexOf(',') >= 0">
			 	and ord.order_sn in (${orderSn})
    	</when>
    	<otherwise>
    			<if test="orderSn != null and orderSn !='' ">
    			and ord.order_sn = ${orderSn}
    			</if>
    	</otherwise>
    	</choose>



if(orderSn.contains(",")) {
				orderSn = orderSn.replaceAll(",","','");
			}

垂直居中demo
http://blog.csdn.net/wolinxuebin/article/details/7615098



INSERT INTO `tshirt`.`ctm_reservation` (`id`, `reservation_no`, `user_id`, `username`, `gender`, `address`, `province`, `city`, `district`, `mobile_phone`, `book_time`, `tailor_no`, `status`, `ctm_props`, `sku_id`, `create_time`, `update_time`, `reservation_type`, `remark`) VALUES ('10', '201711240000000013', '1100022233', '啊啊啊', '1', '阿库拉嗯就咯的', '广东省', '广州市', '荔湾区', '12358695473', NULL, '2015060146902', '1', '{[{\"pid\":\"1\",\"pname\":\"衬衫领子\",\"vid\":\"1\",\"vname\":\"中八领\"},{\"pid\":\"2\",\"pname\":\"袖头\",\"vid\":\"14\",\"vname\":\"圆角单扣\"},{\"pid\":\"3\",\"pname\":\"门襟\",\"vid\":\"19\",\"vname\":\"宽贴门襟\"},{\"pid\":\"4\",\"pname\":\"口袋\",\"vid\":\"22\",\"vname\":\"无\"},{\"pid\":\"5\",\"pname\":\"后背\",\"vid\":\"28\",\"vname\":\"无褶收双省\"},{\"pid\":\"10\",\"pname\":\"纽扣\",\"vid\":\"50\",\"vname\":\"KS021\"}]}', '227', '2017-11-24 18:52:20', '2017-12-11 21:15:19', '0', '');
下完订单订单状态改为7，已签收

http://localhost:8080/CSDZ/volumedivision/index  量体师登录url

日本语教材

http://book.szdnet.org.cn/views/specific/2929/bookDetail.jsp?dxNumber=000008034511&d=9BB0F56A4317930FE23020546C77FA59&fenlei=080406         日本 下 MP3版

http://book.szdnet.org.cn/search?sw=%E5%A4%A7%E6%A3%AE%E5%92%8C%E5%A4%AB&allsw=%23%2Call%E6%97%A5%E6%9C%AC+%E5%A4%A7%E6%A3%AE%E5%92%8C%E5%A4%AB&bCon=&ecode=utf-8&channel=search&Field=all      大森和夫教材
