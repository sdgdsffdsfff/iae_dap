//初始化加载全部用户信息
function loadUserData () {
	// body...
	$("#dataArea").show();
	$("#tishi").hide();
	$("#success").hide();
	var strHtml = '<tr class="user_header"><td class="u_index"><span>序号</span></td><td class="u_name"><span>用户名</span></td><td class="u_name"><span>权限</span></td><td class="u_status"><span>状态</span></td><td class="u_option"><span>操作</span></td></tr>';
	$.ajax({
		type:"post",
		url:basePath+"cntv/getManageUser.json?type=search",
		dataType: "json",
		success: function(json){
			if(json == null || json == ""){
				$("#dataArea").hide();
				$("#tishi").show();
			}else{
				var obj = json.userInfo;
				if(obj.length>0){
					for (var i = 0; i < obj.length; i++) {
						if(i%2 == 0){
							strHtml += '<tr class="u_odd"><td id="idx'+obj[i].pk+'">'+obj[i].pk+'</td><td><span>'+obj[i].user_name+'</span></td>';
						}else{
							strHtml += '<tr><td id="idx'+obj[i].pk+'">'+obj[i].pk+'</td><td><span>'+obj[i].user_name+'</span></td>';
						}
						if(obj[i].flag == '0'){
							strHtml += '<td><span>管理员</span></td>';
							if(obj[i].status == '0'){
								strHtml += '<td><span>正常</span></td><td class="none_border"></td></tr>'
							}else{
								strHtml += '<td><span>已删除</span></td><td class="none_border"></td></tr>'
							}
						}else{
							strHtml += '<td><span>普通用户</span></td>';
							if(obj[i].status == '0'){
								strHtml += '<td><span>正常</span></td><td class="none_border"><span><a href="###" onclick="resetUser('+obj[i].pk+');">重置密码</a></span><span><a href="###" onclick="deleteUser('+obj[i].pk+',1);">删除</a></span></td></tr>'
							}else{
								strHtml += '<td><span>已删除</span></td><td class="none_border"><span><a href="###" onclick="resetUser('+obj[i].pk+');">重置密码</a></span><span><a href="###" onclick="deleteUser('+obj[i].pk+',0);">恢复</a></span></td></tr>'
							}
						}
						
					}
					$("#userTable").html(strHtml);
				}else{
					$("#dataArea").hide();
					$("#tishi").show();
				}
			}
			var lengthPx = $("table tr").length;
			if(lengthPx!=null&&lengthPx>20){
				lengthPx = lengthPx*40;
				parent.document.getElementById("yonghuguanli").height= lengthPx;
			}else{
				parent.document.getElementById("yonghuguanli").height = 600;
			}
			
		},
		error : function(json) {
			$("#dataArea").hide();
			$("#tishi").show();
			return false;
		}
	});
}
//查询用户信息
function searchUser () {
	// body...
	$("#success").hide();
	var uname = $("#user_name").val();
	var strHtml = '<tr class="user_header"><td class="u_index"><span>序号</span></td><td class="u_name"><span>用户名</span></td><td class="u_name"><span>权限</span></td><td class="u_status"><span>状态</span></td><td class="u_option"><span>操作</span></td></tr>';
	$.ajax({
		type:"post",
		url:basePath+"cntv/getManageUser.json?type=search&user_name="+uname,
		dataType:"json",
		success: function(json){
			if(json == null || json == ""){
				$("#dataArea").hide();
				$("#tishi").show();
			}else{
				//alert(json)
				var obj = json.userInfo;
				if(obj.length>0){
					for (var i = 0; i < obj.length; i++) {
						if(i%2 == 0){
							strHtml += '<tr class="u_odd"><td id="idx'+obj[i].pk+'">'+obj[i].pk+'</td><td><span>'+obj[i].user_name+'</span></td>';
						}else{
							strHtml += '<tr><td id="idx'+obj[i].pk+'">'+obj[i].pk+'</td><td><span>'+obj[i].user_name+'</span></td>';
						}
						if(obj[i].flag == '0'){
							strHtml += '<td><span>管理员</span></td>';
							if(obj[i].status == '0'){
								strHtml += '<td><span>正常</span></td><td class="none_border"></td></tr>'
							}else{
								strHtml += '<td><span>已删除</span></td><td class="none_border"></td></tr>'
							}
						}else{
							strHtml += '<td><span>普通用户</span></td>';
							if(obj[i].status == '0'){
								strHtml += '<td><span>正常</span></td><td class="none_border"><span><a href="###" onclick="resetUser('+obj[i].pk+');">重置密码</a></span><span><a href="###" onclick="deleteUser('+obj[i].pk+',1);">删除</a></span></td></tr>'
							}else{
								strHtml += '<td><span>已删除</span></td><td class="none_border"><span><a href="###" onclick="resetUser('+obj[i].pk+');">重置密码</a></span><span><a href="###" onclick="deleteUser('+obj[i].pk+',0);">恢复</a></span></td></tr>'
							}
						}
					}
					$("#userTable").html(strHtml);
					$("#dataArea").show();
					$("#tishi").hide();
					var lengthPx = $("table tr").length;
					if(lengthPx!=null&&lengthPx>20){
						lengthPx = lengthPx*40;
						parent.document.getElementById("yonghuguanli").height= lengthPx;
					}else{
						parent.document.getElementById("yonghuguanli").height = 600;
					}
					
				}else{
					$("#dataArea").hide();
					$("#tishi").show();
				}
				
			}
		},
		error : function(json){
			$("#dataArea").hide();
			$("#tishi").show();
			return false;
		}
	});
}
//增加用户
function addUser () {
	window.open('addUser.jsp',"_self");
}
//修改用户信息
function resetUser (pk) {
	//window.open('modifyUser.jsp?pk='+pk,"_self");
	if (confirm("确认重置密码？")) {
		$.ajax({
			type:"post",
			dataType:"json",
			url:basePath+"cntv/getResetPassword.json?pk="+pk,
			success: function(json){
				loadUserData();
				$("#success").show();
			},
			error: function(json){
				$("#dataArea").hide();
				$("#tishi").show();
				return false;
			}
		});
	}
}
//删除用户，置用户状态为已删除
function deleteUser (pk, status) {
	$("#success").hide();
	if (confirm("确认删除或恢复用户？")) {
		$.ajax({
			type:"post",
			dataType:"json",
			url:basePath+"cntv/getManageUser.json?type=delete&status="+status+"&pk="+pk,
			success: function(json){
				loadUserData();
			},
			error: function(json){
				$("#dataArea").hide();
				$("#tishi").show();
				return false;
			}
		});
	}
}

