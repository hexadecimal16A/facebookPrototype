var giveInfoOnHover=function(data2,sports){
	var title;
	title="<b>Email: </b><p>"+data2["email"]+"</p><b>Born On: </b><p>"+data2["DOB"]+"</p>";
	if(data2["mobile"])
			title+="<b>MobileNo.: </b><p>"+data2["mobile"]+"</p>";
	if(data2["hometown"])
		title+="<b>Lives in: </b><p>"+data2["hometown"]+"</p>";
	title+="<b>Sports Of Interest: </b><p>"+sports+"</p>";
	return title;
};

var displayChat=function(data1,data2,sports){
		//console.log(post_id);
		commentCollapse=document.getElementById("chatPanelBody");
		//var newA = document.createElement('a');
		//newA.setAttribute('href',"javascript:register_popup('narayan-prusty', 'Narayan Prusty');");
		var ccMedia = document.createElement("DIV");
		//ccMedia.style.whiteSpace="pre-line";
		ccMedia.style.marginTop="1em";
		ccMedia.className = "media";
			var ccMedia2 = document.createElement("DIV");
			ccMedia2.className = "media-left";
				var ccMedia3=document.createElement("img");
				ccMedia3.setAttribute('src', data1["profileImage"]);
				ccMedia3.className="media-object";
				//elem.className = "fittodiv";
				ccMedia3.setAttribute("height", "auto");
				ccMedia3.setAttribute("width", "40px");
				ccMedia3.setAttribute('title',giveInfoOnHover(data2,sports));
				ccMedia3.setAttribute('data-placement','left');
				ccMedia3.setAttribute('data-html','true');
				ccMedia3.id="chat_"+data1["id"];
			ccMedia2.appendChild(ccMedia3);
		ccMedia.appendChild(ccMedia2);
			var ccMedia_2 = document.createElement("DIV");
			ccMedia_2.className="media-body";
				ccMedia3=document.createElement("a");
				ccMedia3.className="media-heading";
				ccMedia3.style.color="grey";
				ccMedia3.innerHTML=data1["name"]+" ";
				ccMedia3.id="chatbox_"+data1["id"];
				ccMedia3.onclick = function(){register_popup(data1["id"], data1["name"]);};
				var temp=document.createElement("span");
				temp.className="badge glyphicon glyphicon-envelope"
				temp.id="unread_"+data1["id"];
			ccMedia_2.appendChild(ccMedia3);
			ccMedia_2.appendChild(temp);
		ccMedia.appendChild(ccMedia_2);
		commentCollapse.appendChild(ccMedia);
	};
	
	
	//------------function end-------------------//
	//------------------document ready---------------//
	
	$(document).ready(function(){
		
		
		$('#chatPanel').css('height',$(window).height()-50);
		$('#chatPanel').css('overflow-y','scroll');
		
		$.ajax({
			type : "get",
			// make sure you respect the same origin policy with this url:
			// http://en.wikipedia.org/wiki/Same_origin_policy
			//dataType: "text",
			url : "webapi/friends/"
					+ sessionStorage.id,

			success : function(res) {
				//console.log(res);
				var len=res.length;
				var data2;
				for (i = 0; i < len; i++) {
					var sid=res[i]["id"];
					//console.log(res[i]);
					$.ajax({
						type:"post",
						url:"webapi/profile/"+res[i]["id"],
						async:false,
						success:function(data){
							data2=data;
						},
						complete:function(){
							$.ajax({
								type : "get",
								url : "webapi/sportsofinterest/" +sid,
								async:false,
								success : function(data) {
									displayChat(res[i],data2,data);
									//console.log(data);
								}
							});
						}
					});
				}
			},
			complete:function(){
				$('[id^="chat_"]').hover(function(){
					$(this).tooltip();  
				} );
			}
		});
		
	});
	
	function createDiv(message,cl,id1,id2){
		var Div_1 = document.createElement('div');
		Div_1.className = cl;
		var para=document.createElement('p');
		var node = document.createTextNode(message);
		para.appendChild(node);
		Div_1.appendChild(para);
		var element = document.getElementById(id1);
		element.appendChild(Div_1);
		var objDiv = document.getElementById(id1);
		objDiv.scrollTop = objDiv.scrollHeight;
	};
	
	function executeQuery() {
		var dataTemp=null;
		$.ajax({
			type:'get',
			url : 'webapi/chats/'+sessionStorage.id,
			success : function(data) {
				dataTemp=data;
				//console.log(data);
				if(data){
					//console.log(data);
					for(i=0;i<data.length;++i){
						if($('#'+data[i].from_id).length>0){
							createDiv(data[i]['message'],"alert alert-danger test2 pull-right","popup_messages_"+data[i].from_id,data[i].from_id);
							$('#unread_'+data[i].from_id).html('');
						}
						else{
								$('#unread_'+data[i].from_id).html(' ');
						}
					}
				}
			},
			complete:function(){
				if(dataTemp)
				for(i=0;i<dataTemp.length;++i){
					if($('#'+dataTemp[i].from_id).length>0){
						$.ajax({
							type:'get',
							url: 'webapi/chats/makeRead/'+dataTemp[i].chat_id
						});
					}
				}
			}
		});
		setTimeout(executeQuery, 3000); // you could choose not to continue on failure...
	};
	
	executeQuery();
	
	function loadOldChat(id){
		$.ajax({
			type:'get',
			url : 'webapi/chats/oldChat/'+id+"_"+sessionStorage.id,
			success : function(data) {
				$('#popup_messages_'+id).html(' ');
				for(i=0;i<data.length;++i){
					if(Number(data[i].from_id)==Number(sessionStorage.id)){
						createDiv(data[i]['message'],"alert alert-info test1 pull-left","popup_messages_"+id,id);
					}
					else
						createDiv(data[i]['message'],"alert alert-danger test2 pull-right","popup_messages_"+id,id);
				}
			}
		});
	}
	//this function can remove a array element.
	Array.remove = function(array, from, to) {
	    var rest = array.slice((to || from) + 1 || array.length);
	    array.length = from < 0 ? array.length + from : from;
	    return array.push.apply(array, rest);
	};

	//this variable represents the total number of popups can be displayed according to the viewport width
	var total_popups = 0;

	//arrays of popups ids
	var popups = [];

	//this is used to close a popup
	function close_popup(id)
	{
	    for(var iii = 0; iii < popups.length; iii++)
	    {
	        if(id == popups[iii])
	        {
	            Array.remove(popups, iii);
	            
	            //document.getElementById(id).style.display = "none";
	            $( "#"+id ).remove();
	            calculate_popups();
	            
	            return;
	        }
	    }   
	}

	//displays the popups. Displays based on the maximum number of popups that can be displayed on the current viewport width
	function display_popups()
	{
	    var right = 220;
	    
	    var iii = 0;
	    for(iii; iii < total_popups; iii++)
	    {
	        if(popups[iii] != undefined)
	        {
	            var element = document.getElementById(popups[iii]);
	            element.style.right = right + "px";
	            right = right + 320;
	            element.style.display = "block";
	        }
	    }
	    
	    for(var jjj = iii; jjj < popups.length; jjj++)
	    {
	        var element = document.getElementById(popups[jjj]);
	        element.style.display = "none";
	    }
	}

	
	//creates markup for a new popup. Adds the id to popups array.
	function register_popup(id, name)
	{
	    for(var iii = 0; iii < popups.length; iii++)
	    {   
	        //already registered. Bring it to front.
	        if(id == popups[iii])
	        {
	            Array.remove(popups, iii);
	        
	            popups.unshift(id);
	            
	            calculate_popups();
	            
	            
	            return;
	        }
	    }               
	    
	    var element = '<div class="popup-box chat-popup" id="'+ id +'">';
	    element = element + '<div class="popup-head">';
	    element = element + '<div class="popup-head-left">'+ name +'</div>';
	    element = element + '<div class="popup-head-right"><a href="javascript:loadOldChat(\''+ id +'\');">Load older chats </a> <a href="javascript:close_popup(\''+ id +'\');"> &#10005;</a></div>';
	    element = element + '<div style="clear: both"></div></div><div  id="popup_messages_'+id+'" class="popup-messages"></div>';
	    element = element +'<div>';
	    element = element + '<form id="chatSend_'+id+'"><input class="form-control" id="chatText_'+id+'" name="chatText" type="text" style="width:100%;margin-bottom:0px"  autocomplete="off"/></form></div></div>';
	    
	    $('body').append(element); 
	    var chatForm = $('#chatSend_'+id);
		chatForm.submit(function() {
			//console.log(chatForm.serialize());
			$.ajax({
				type : 'post',
				url :  'webapi/chats/'+id+"_"+sessionStorage.id,
				data : chatForm.serialize(),
				success : function(data) {
					//console.log(data);
					createDiv($('#chatText_'+id).val(),"alert alert-info test1 pull-left","popup_messages_"+id,id);
					$('#chatText_'+id).val('');
				}
			});

			return false;
		});
	    popups.unshift(id);
	    calculate_popups();
	    
	}

	//calculate the total number of popups suitable and then populate the toatal_popups variable.
	function calculate_popups()
	{
	    var width = window.innerWidth;
	    if(width < 540)
	    {
	        total_popups = 0;
	    }
	    else
	    {
	        width = width - 200;
	        //320 is width of a single popup box
	        total_popups = parseInt(width/320);
	    }
	    
	    display_popups();
	    
	}

	//recalculate when window is loaded and also when window is resized.
	window.addEventListener("resize", calculate_popups);
	window.addEventListener("load", calculate_popups);
