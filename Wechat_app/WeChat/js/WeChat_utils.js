window.ip={
	serverip : "http://172.20.10.4:8888/"
}
 
window.url={
	//注册的url
	register_url : ip.serverip + "user/register",
	login_url : ip.serverip + "user/login",
	upload_Image_url : ip.serverip + "res/uploadImage",
	getUserByUsername_url : ip.serverip + "user/getUserByUsername",
	friendApply_url : ip.serverip + "friend/addFriendApply",
	getMyFriendApplyList_url : ip.serverip + "friend/getMyFriendApplyList",
	updateFriendApplyStatus_url : ip.serverip + "friend/updateFriendApplyStatus",
	getFriendListById_url : ip.serverip + "friend/getFriendListById",
	findUserById_url : ip.serverip + "user/findUserById"
}

window.util={
	ajax:function(param){
		//显示等待的圈
		plus.nativeUI.showWaiting();
		mui.ajax(param.url,{
			data:param.data, 
			dataType:'json',//服务器返回json格式数据
			type:'post',//HTTP请求类型	               
			success:function(data){
				plus.nativeUI.closeWaiting();
				param.success(data);
			},
			error:function(xhr,type,errorThrown){
				plus.nativeUI.closeWaiting();
				//异常处理；
				plus.nativeUI.toast("服务端出现异常");
			}
		});
	},
	setUser:function(user){ //保存用户到数据库(cookie)
		plus.storage.setItem("login_user",JSON.stringify(user));
	},
	getUser:function(){
		return JSON.parse(plus.storage.getItem("login_user"));
	},
	
	setFriendList:function(friendList){ //保存好友信息到数据库
		plus.storage.setItem("friendList",JSON.stringify(friendList));
	},
	getFriendList:function(){
		return JSON.parse(plus.storage.getItem("friendList"));
	},
	
}