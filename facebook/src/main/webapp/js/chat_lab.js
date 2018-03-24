var giveInfoOnHover = function(data2, sports) {
	var title;
	title = "<b>Email: </b><p>" + data2["email"] + "</p><b>Born On: </b><p>"
			+ data2["DOB"] + "</p>";
	if (data2["mobile"])
		title += "<b>MobileNo.: </b><p>" + data2["mobile"] + "</p>";
	if (data2["hometown"])
		title += "<b>Lives in: </b><p>" + data2["hometown"] + "</p>";
	title += "<b>Sports Of Interest: </b><p>" + sports + "</p>";
	return title;
};

var displayChat = function(data1, data2, sports) {
	// console.log(post_id);
	commentCollapse = document.getElementById("chatPanelBody");
	// var newA = document.createElement('a');
	// newA.setAttribute('href',"javascript:register_popup('narayan-prusty',
	// 'Narayan Prusty');");
	var ccMedia = document.createElement("DIV");
	// ccMedia.style.whiteSpace="pre-line";
	ccMedia.style.marginTop = "1em";
	ccMedia.className = "media";
	var ccMedia2 = document.createElement("DIV");
	ccMedia2.className = "media-left";
	var ccMedia3 = document.createElement("img");
	ccMedia3.setAttribute('src', data1["profileImage"]);
	ccMedia3.className = "media-object";
	// elem.className = "fittodiv";
	ccMedia3.setAttribute("height", "auto");
	ccMedia3.setAttribute("width", "40px");
	ccMedia3.setAttribute('title', giveInfoOnHover(data2, sports));
	ccMedia3.setAttribute('data-placement', 'left');
	ccMedia3.setAttribute('data-html', 'true');
	ccMedia3.id = "chat_" + data1["id"];
	ccMedia2.appendChild(ccMedia3);
	ccMedia.appendChild(ccMedia2);
	var ccMedia_2 = document.createElement("DIV");
	ccMedia_2.className = "media-body";
	ccMedia3 = document.createElement("a");
	ccMedia3.className = "media-heading";
	ccMedia3.style.color = "grey";
	ccMedia3.innerHTML = data1["name"] + " ";
	ccMedia3.id = "chatbox_" + data1["id"];
	ccMedia3.onclick = function() {
		register_popup(data1["id"], data1["name"]);
	};
	var temp = document.createElement("span");
	temp.className = "badge glyphicon glyphicon-envelope"
	temp.id = "unread_" + data1["id"];
	ccMedia_2.appendChild(ccMedia3);
	ccMedia_2.appendChild(temp);
	ccMedia.appendChild(ccMedia_2);
	commentCollapse.appendChild(ccMedia);
};

// ------------function end-------------------//
// ------------------document ready---------------//

$(document).ready(function() {

	$('#chatPanel').css('height', $(window).height() - 50);
	$('#chatPanel').css('overflow-y', 'scroll');

	var getGroupMembers = function(id) {
		$.ajax({
			type : "get",
			// make sure you respect the same origin policy with this url:
			// http://en.wikipedia.org/wiki/Same_origin_policy
			// dataType: "text",
			url : "webapi/friends/"+id,

			success : function(res) {
				// console.log(res);
				var len = res.length;
				var data2;
				for (i = 0; i < len; i++) {
					var sid = res[i]["id"];
					// console.log(res[i]);
					$.ajax({
						type : "post",
						url : "webapi/profile/" + res[i]["id"],
						async : false,
						success : function(data) {
							data2 = data;
						},
						complete : function() {
							$.ajax({
								type : "get",
								url : "webapi/sportsofinterest/" + sid,
								async : false,
								success : function(data) {
									displayChat(res[i], data2, data);
									// console.log(data);
								}
							});
						}
					});
				}
			},
			complete : function() {
				$('[id^="chat_"]').hover(function() {
					$(this).tooltip();
				});
			}
		});
	};

});
