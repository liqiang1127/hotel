<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" " />

<title>酒店列表</title>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.tabfont01 {
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}

.font051 {
	font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}

.font201 {
	font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}

.topA{
	background: #fff;
    border: 1px solid #C28F27;
    padding: 2px 2px 0px 2px;
    margin: 4px;
    border-width: 1px 3px 1px 3px;
    font-size: 12px;
    color: #A74300;
}

html {
	overflow-x: auto;
	overflow-y: auto;
	border: 0;
}
-->
</style>

<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		
			<tr>
				<form name="fom" id="fom" method="post" action="${pageContext.request.contextPath}/service!queryCheckin.action">
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="62" background="${pageContext.request.contextPath}/images/nav04.gif">
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="21"><img src="${pageContext.request.contextPath}/images/ico07.gif" width="20"
											height="18" />&nbsp;&nbsp;
											</td>
											<td width="538">查看内容：房间号： <input name="room_no"
											type="text" size="20" /> &nbsp; &nbsp;&nbsp;
											<input name="Submit4" type="submit" class="right-button02" value="查 询" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				</form>
			</tr>
			
			<tr>
				<td><table id="subtree1" style="DISPLAY:" width="100%"
						border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><table width="95%" border="0" align="center"
									cellpadding="0" cellspacing="0">
									<tr>
										<td height="40" class="font42">
											<table width="70%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
												<tr class="CTitle">
													<td height="22" colspan="8" align="center"
														style="font-size: 16px">结账退房单详细</td>
												</tr>
												<tr bgcolor="#EEEEEE" align="center" >
													<td colspan="8"  align="center" height="22" >客房基本信息</td>
												</tr>
												<tr bgcolor="#FFFFFF" height="40" align="center">
													<td width="15%">结款单编号</td>
													<td width="10%">${checkinOrder.id }</td>
													<td width="15%">房间编号</td>
													<td  width="10%">${room.id }</td>
													<td width="15%">房间号</td>
													<td width="10%">${room.no }</td>
													<td width="15%">房间类型</td>
													<td width="10%">${room.roomKindStr }</td>
												</tr>
												<tr bgcolor="#FFFFFF" height="40" align="center">
													<td>入住日期</td>
													<td>${checkinOrder.inDateStr }</td>
													<td>离店日期</td>
													<td>${checkinOrder.leaveDateStr }</td>
													<td>已付费用</td>
													<td>${checkinOrder.paidMoney}</td>
													<td>总费用</td>
													<td>${checkinOrder.totalCost }</td>
												</tr>
												</table>
												<table width="70%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
												<tr bgcolor="#EEEEEE" align="center" >
													<td colspan="4"  align="center" height="22" >住客信息</td>
												</tr>
												<tr bgcolor="#FFFFFF" height="40" align="center">
													<td style="width: 20%" >姓名</td>
													<td style="width: 10%">性别</td>
													<td style="width: 40%">身份证号码</td>
													<td style="width: 30%">联系电话</td>
												</tr>
												<c:forEach var="guest" items="${guests }">
												<tr bgcolor="#FFFFFF" align="center">
													<td height="20">${guest.name }</td>
													<td>${guest.gender =='0'?'男':'女'}</td>
													<td>${guest.IDCardNo}</td>
													<td>${guest.mobile}</td>
												</tr>
												</c:forEach>
												</table >
												<table width="70%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
												<tr bgcolor="#EEEEEE" align="center" >
													<td colspan="4"  align="center" height="22" >服务信息</td>
												</tr>
												<tr bgcolor="#FFFFFF" align="center"  align="center">
													<td width="5%" height="30">编号</td>
													<td width="15%">日期</td>
													<td width="7%">费用（元）</td>
													<td width="30%">备注</td>
												</tr>
												<c:forEach var="busineseFlow" items="${busineseFlows }" >
												<tr bgcolor="#FFFFFF" align="center">
													<td height="20">${busineseFlow.id }</td>
													<td>${busineseFlow.createDateStr }</td>
													<td>${busineseFlow.cost}</td>
													<td>${busineseFlow.remark}</td>
												</tr>
												</c:forEach>
										</table>
											<form name="fom" id="fom" method="post" action="${pageContext.request.contextPath}/service!checkout.action">
												<input type="hidden" name="checkinOrder_id" value="${checkinOrder.id }">
													<table width="70%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
												<tr bgcolor="#FFFFFF" align="center">
													<td width="30%">剩余费用(元)<input id="remainingCost" value="${remainingCost }" type="hidden"></td>
													<td width="20%" >${remainingCost }</td>
													<td nowrap align="right" >支付方式:</td>
												<td><select name="payMethod" style="width: 300px"  id="payMethod">
														<option value="0" >现金支付</option>
														<option value="1" >在线支付</option>
												</select><span
													class="red"> *</span><button type="button" onclick="newPayment()">发起支付宝付款</button></td>
													<td colspan="2"><input name="Submit4" type="submit" class="right-button02" value="结账" /></td>
												</tr>
												</table>
											</form>
										</td>
									</tr>
									
									<tr><h2 style="color: red; text-align: center;">${errorInfo }</h2></tr>
								</table>
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="6"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1"
											height="1" /></td>
									</tr>
								</table>
							</td>
						</tr>
						</table>
						<table width="70%" border="0" cellpadding="4"
												cellspacing="1" bgcolor="#464646" class="newfont03" align="center">
						<tr style="display:none;" class="payNoRow"  bgcolor="#FFFFFF" align="center">
							<td align="right" width="10%">支付单编号:</td>
							<td><input name="outTradeNo" 
								class="text" style="width: 300px" value="" id="outTradeNo" readonly="readonly"/><span
								class="red"> *</span></td>
							<td colspan="4"><img src="" id="cede"  rowspan="3"></td>
						</tr>
						<tr class="payNoRow" style="display:none;" bgcolor="#FFFFFF" align="center">
							<td align="right" width="10%">支付单状态:</td>
							<td><input name="payState" 
								class="text" style="width: 300px" value="未支付" id="payState" readonly="readonly"/><span
								class="red"> *</span>
								</td>
							<td><button type="button" onclick="queryPay()">更新支付状态</button></td>
							</tr>
					</table>
</body>
<script type="text/javascript">
function newPayment(){
	var selected = $('#payMethod').val();
	 if(selected=='1'){
		var padMoney = $('#remainingCost').val();
		var data = {
				padMoney:padMoney,
				content:"结款支付",
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
</html>