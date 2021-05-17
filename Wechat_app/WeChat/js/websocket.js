var websocket;

window.ws={
	init:function(param){
		initWebsocket(param);
	}
}
var flag=false;

function setifRconn(){
	ifRconn=false;
}
var ifRconn=true;
function initWebsocket(param){
				if(window.WebSocket){
					websocket=new WebSocket("ws://172.20.10.4:8084/");
					websocket.onopen = function(){
						console.info("重连"+flag);
						ifRconn=true;
						if(flag){
							console.info("重连成功")
							plus.nativeUI.toast("重连成功");
							clearTimeout(closerReConn);
						}
						flag=false;
						sendHeart();
						closerConn();
						param.onopen();
					};
					
					websocket.onclose = function(){
						clearInterval(sendHearTime);
						if(ifRconn){
							reConn();
						}
						param.onclose();
					};
					 
					websocket.onmessage = function(resp){
						var data=resp.data;
						if(data == "heart"){
							console.info(data);
							clearTimeout(closerConnTime); //清除定时关闭器
							closerConn();
						}else{
							param.onmessage(resp);
							// $("#showMsg").append("<span '>服务端: "+data+"</span><br>")
						}
						
					};
				}else{
					alert("不支持websocket")
				}
}

function sendMsg(){
				var msg=$("#content").val();
				$("#showMsg").append("<span '>我: "+msg+"</span><br>")		
				websocket.send(msg);
}
function sendObj(obj){
	websocket.send(JSON.stringify(obj));
}
var sendHearTime;
function sendHeart(){
				sendHearTime = setInterval(function(){
					var data={"type":2};
					websocket.send(JSON.stringify(data));
				},5000);
}
		
function closeConn(){
		ifRconn=false;
		websocket.close();
		console.info("关闭连接");
}		
			
var closerConnTime;
function closerConn(){
				closerConnTime = setTimeout(function(){
					websocket.close();
				},10000);
}
			
var closerReConn;
function reConn(){
	closerReConn=setTimeout(function(){
					plus.nativeUI.toast("服务器故障,正在重新连接");
					flag=true;
					ws.init({
						onopen:function(){ 
							console.log("客户端连接")
							plus.device.getInfo({  
								success:function(e){  
									var user=util.getUser();
									var param={"did":e.uuid,"type":1,"user_id":user.id}; 
									sendObj(param);
								}, 
							});
						},
						onclose:function(){   
							console.log("2")
						},
						onmessage:function(resp){ 
							console.log("3"+resp.data);
							var data=JSON.parse(resp.data);
							if(data.type == 6){ //挤下线
								//把本机用户删除
								plus.storage.removeItem("login_user");
								plus.storage.removeItem("friendList");
								//跳转到登录页面
								var cpage=plus.webview.currentWebview();
								plus.nativeUI.toast("您的账号已在其他设备登录，如果非本人操作，请及时修改密码");
								plus.webview.open("login.html","login.html");
								cpage.close();
							}
						}
					})
				},10000);
}
			