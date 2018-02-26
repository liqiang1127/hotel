<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增房间</title>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style.css"
	type="text/css" media="all" />
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<style type="text/css">
<!--
.atten {
	font-size: 12px;
	font-weight: normal;
	color: #F00;
}
-->

</style>
</head>

<body class="ContentBody">
<%-- 	<form action="${pageContext.request.contextPath}/room!addRoom.action" method="post" enctype="multipart/form-data" name="fom"
		id="fom" target="mainFrame"> --%>
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">	
				<tr>
					<th class="tablestyle_title">客房预订页面</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width: 100%">
							<!-- <tr>
								<td align="left"><input type="button" name="Submit"
									value="保存" class="button" onclick="alert('保存成功！');" /> <input
									type="button" name="Submit2" value="返回" class="button"
									onclick="window.history.go(-1);" /></td>
							</tr> -->

							<TR>
								<TD width="100%">
									<fieldset style="height: 100%;">
										<legend>客房查询</legend>
											<table width="98%" border="0" align="center" cellpadding="0"
															cellspacing="0">
							<form name="fom" id="fom" method="post" action="${pageContext.request.contextPath}/room!queryRoom.action">
									<tr>
										<td width="21"><img src="${pageContext.request.contextPath}/images/ico07.gif" width="20"
											height="18" /></td>
										<td width="538">查看内容：房间号： <input name="no"
											type="text" size="20"  value="${no}"/> &nbsp; &nbsp;&nbsp;
																						<input type="hidden" value="addReserve" name="fromPage"/>
											楼层： <input name="floor"
											type="text" size="20"  value="${floor}"/> &nbsp; &nbsp;&nbsp;
											房间种类：<select name="roomKind_id">
											<option value= ""  ${roomKind.id==''?'selected':''}>请选择</option>
													<c:forEach items="${roomKindsAll }" var="roomKind">
														<option value="${roomKind.id }"  ${roomKind.id==roomKind_id_b?'selected':''}>${roomKind.name }</option>
														</c:forEach>
												</select>&nbsp; &nbsp;&nbsp;
											<input name="available" type="hidden" value="1"/>
											<input name="Submit4" type="submit" class="right-button02" value="查 询" /></td>
									</tr>
								</form>
								</table>
								<br />
								<table width="100%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr class="CTitle">
													<td height="22" colspan="8" align="center"
														style="font-size: 16px">客房信息列表</td>
												</tr>
												<tr bgcolor="#EEEEEE" align="center" >
													<td width="5%" height="30">编号</td>
													<td width="7%">房间号</td>
													<td width="7%">楼层</td>
													<td width="7%">朝向</td>
													<td width="10%">分机电话</td>
													<td width="10%">状态</td>
													<td width="10%">客房类型</td>
													<td width="10%">可用</td>
												</tr>
												<c:forEach var="room" items="${rooms }">
												<tr bgcolor="#FFFFFF">
													<td height="20">${room.id }</td>
													<td>${room.no}</td>
													<td>${room.floor}</td>
													<td>${room.area}</td>
													<td>${room.telphone}</td>
													<td>${room.state}</td>
													<td>${room.roomKindStr }</td>
													<td>${room.available}</td>
												</tr>
												</c:forEach>
											</table>
										<br />
									</fieldset>
								</TD>
							</TR>
								
							<TR>
								<TD width="100%">
									<fieldset style="height: 100%;">
										<legend>客房预订</legend>
										<form method="post" action="${pageContext.request.contextPath}/reserve!addReserve.action">
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 100%">
											<tr><h2 style="color:red; text-align: center;">${errorInfo }</h2></tr>
											<tr>
												<td nowrap align="right" width="13%">预订人姓名:</td>
												<td width="41%"><input name="resName" class="text"
													style="width: 300px" type="text" size="40"  value="${reser.no} "/> <span
													class="red"> *</span></td>
												<td align="right" width="10%">预订人联系电话:</td>
												<td><input name="resMobile" id="Input22"
													class="text" style="width: 300px" value="${room.floor }"/><span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right">预计入住人数:</td>
												<td><select name="preNumber" style="width: 100px">
														<option value="1" >1</option>
														<option value="2" >2</option>
														<option value="3" >3</option>
														<option value="4">4</option>
														<option value="5" >5</option>
														<option value="6" >6</option>
												</select><span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right">客房种类:</td>
												<td><select name="roomKind_id" style="width: 300px">
												<option value=""  >请选择</option>
													<c:forEach items="${roomKindsAll }" var="roomKind">
														<option value="${roomKind.id }"  ${roomKind.id==room.roomKind_id?'selected':''}>${roomKind.name }</option>
														</c:forEach>
												</select><span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right">预计抵店日期:</td>
												<td><input name="InDate" id="" class="text" type="date"
													style="width: 300px"  value="${room.telphone }"/><span
													class="red"> *</span></td>
													<td nowrap align="right">预计离店日期:</td>
												<td><input name="leaveDate" id="" class="text" type="date"
													style="width: 300px"  value="${room.telphone }"/><span
													class="red"> *</span></td>
											</tr>
											<tr>
													
												<td align="right" width="10%">支付金额:</td>
												<td><input name="paidMoney" 
													class="text" style="width: 300px" value="" id="paidMoney"/><span
													class="red"> *</span></td>
													<td nowrap align="right" >支付方式:</td>
												<td><select name="payMethod" style="width: 300px"  id="payMethod">
														<option value="0" >现金支付</option>
														<option value="1" >在线支付</option>
												</select><span
													class="red"> *</span><button type="button" onclick="newPayment()">发起支付宝付款</button></td>
											</tr>
											
											<tr style="display:none;" class="payNoRow">
												<td align="right" width="10%">支付单编号:</td>
												<td><input name="outTradeNo" 
													class="text" style="width: 300px" value="" id="outTradeNo" readonly="readonly"/><span
													class="red"> *</span></td>
												<td><img src="" id="cede"  rowspan="3"></td>
											</tr>
											<tr class="payNoRow" style="display:none;">
												<td align="right" width="10%">支付单状态:</td>
												<td><input name="payState" 
													class="text" style="width: 300px" value="未支付" id="payState" readonly="readonly"/><span
													class="red"> *</span>
													<button type="button" onclick="queryPay()">更新支付状态</button>
													</td>
											</tr>
											<TR>
												<TD colspan="4" align="center" height="50px"><input
													type="submit" name="Submit" value="保存" class="button"
													 /> <input type="button" name="Submit2"
													value="返回" class="button" onclick="window.history.go(-1);" /></TD>
											</TR>
											<tr>
												<td colspan="4"></td>
											</tr>
										</table>
										</form>
										<br />
									</fieldset>
								</TD>
							</TR>
						</TABLE>
					</td>
				</tr>
			</TABLE>
			</td>
			</tr>
			</table>
		</div>
<script type="text/javascript">
	function newPayment(){
		var selected = $('#payMethod').val();
		 if(selected=='1'){
			var padMoney = $('#paidMoney').val();
			var data = {
					padMoney:padMoney,
					content:"预订支付",
			};
			$.ajax({
				type:"POST",
				url:"${pageContext.request.contextPath}/pay!newPayment.action",
				data:data,
				success:function(data){
					if(data.errorNo==0){
						$(".payNoRow").show();
						$("#cede").attr('src',data.url); 
						$("#outTradeNo").attr("value",data.outTradeNo);
					}
				}
			});
		} 
	}
	
	function queryPay(){
		var outTradeNo = $('#outTradeNo').val();
		var data = {
				outTradeNo:outTradeNo,
		};
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/pay!queryPay.action",
			data:data,
			success:function(data){
				if(data.errorNo==0){
					$("#payState").attr("value","已支付");
				}else{
					$("#payState").attr("value","未支付");
				}
			}
		});
	}
	
	
</script>
</body>
</html>
