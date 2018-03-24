/**
 * 
 */


	var displayInvitationMembers=function(data){
	//console.log(data);
	var newPanel = document.createElement("DIV");
	newPanel.className = "panel panel-default";
	var newPanelBody = document.createElement("DIV");
	newPanelBody.className = "panel-body";
	{	var newDiv = document.createElement("DIV");
		newDiv.className = "col-md-3";
		var elem = document.createElement("img");
		elem.setAttribute('src',data['profileImage']);
		elem.className="fittodiv";
		//elem.setAttribute("height", "auto");
		//elem.setAttribute("width", "100em");
		newDiv.appendChild(elem);
		newPanelBody.appendChild(newDiv);
		//----//
		var newDiv = document.createElement("DIV");
		newDiv.className = "col-md-4";
		var h5 = document.createElement("H5");
		var h5text = document.createTextNode(data['name']); 
		h5.appendChild(h5text);
		newDiv.appendChild(h5);
		var h6 = document.createElement("H6");
		var h6text = document.createTextNode(data['email']); 
		h6.appendChild(h6text);
		newDiv.appendChild(h6);
		var h6 = document.createElement("H6");
		var h6text = document.createTextNode(data['DOB']); 
		h6.appendChild(h6text);
		newDiv.appendChild(h6);
		newPanelBody.appendChild(newDiv);
		//----//
		
		var newDiv = document.createElement("DIV");
		newDiv.className = "col-md-5";
		var btn = document.createElement("BUTTON");	// suggest
		btn.className="btn btn-primary pull-right edit"
		btn.id="inviteMember_"+data["id"];
		var t = document.createTextNode("Invite");    
		btn.appendChild(t); 
		newDiv.appendChild(btn);

		
		//----//
		newPanelBody.appendChild(newDiv);
	}
	document.getElementById('inviteMembersModalBody').appendChild(newPanelBody);
};
	
	var displayLikes=function(data){
		//--------data has id use to redirect to profile page
		var divxx = document.createElement("DIV");
		divxx.style.marginBottom="1em";
		divxx.className = "row";
		{
			var div1 = document.createElement("DIV");
			div1.className = "col-md-2";
			var elem = document.createElement("img");
			elem.setAttribute('src', data['user_piclocation']);
			//elem.className = "fittodiv";
			elem.setAttribute("height", "auto");
			elem.setAttribute("width", "60em");
			div1.appendChild(elem)
			divxx.appendChild(div1);
			//---------//
			var div2 = document.createElement("DIV");
			div2.className = "col-md-5";
			//var p=document.createElement("P");
			var t = document.createTextNode(data["name"]);
			//p.appendChild(t);
			div2.append(t);
			divxx.appendChild(div2);
			divxx.appendChild(document.createElement("br"));
			//p.appendChild(document.createElement("br"));
		}
		document.getElementById("likesModalBody").appendChild(divxx);
		//console.log($("#likesModal.modal-body"));
	};
	
	var displayInterestedGoing=function(data){
		//--------data has id use to redirect to profile page
		console.log(data);
		var divxx = document.createElement("DIV");
		divxx.style.marginBottom="1em";
		divxx.className = "row";
		{
			var div1 = document.createElement("DIV");
			div1.className = "col-md-2";
			var elem = document.createElement("img");
			elem.setAttribute('src', data['profileImage']);
			//elem.className = "fittodiv";
			elem.setAttribute("height", "auto");
			elem.setAttribute("width", "60em");
			div1.appendChild(elem)
			divxx.appendChild(div1);
			//---------//
			var div2 = document.createElement("DIV");
			div2.className = "col-md-5";
			//var p=document.createElement("P");
			var t = document.createTextNode(data["name"]);
			//p.appendChild(t);
			div2.append(t);
			divxx.appendChild(div2);
			divxx.appendChild(document.createElement("br"));
			//p.appendChild(document.createElement("br"));
		}
		document.getElementById("likesModalBody").appendChild(divxx);
		//console.log($("#likesModal.modal-body"));
	};
	
	
//----------------------document ready-------------------------------//	
$(document).ready(function() {
		
	var update = $('#update');
	update.submit(function() {
		console.info(update.serialize());
		$.ajax({
			type : 'put',
			url : 'webapi/events/'+sessionStorage.event_id,
			data : update.serialize(),
			success : function(data) {
				console.log(data);
				if(data=="false"){
					alert("END DATE CAN'T BE BEFORE START DATE");
				}
				else
					window.location.reload();
			}
		});

		return false;
	});
	
	var summary = $('#summary');
	summary.submit(function() {
		console.info(summary.serialize());
		$.ajax({
			type : 'post',
			url : 'webapi/eventadd/updateSummary/'+sessionStorage.event_id,
			data : summary.serialize(),
			success : function(data) {
				if(data=="true"){
					window.location.reload();
				}
				else{
					alert("try again");
				}
			}
		});

		return false;
	});
	
	$('#inviteMembersButton').click(function(){
		$('#inviteMembersModalBody').html(' ');
		//console.log($('#inviteMembers').val());
		//console.log(sessionStorage.id);
		$.ajax({
			type:"get",
			url: "webapi/eventadd/inviteFriends?event_id="+sessionStorage.event_id+"&member_id="+sessionStorage.id+"&searchval="+$('#inviteMembers').val(),
			async:false,
			success: function(data){
				console.log(data);
				for(i=0;i<data.length;++i){
					//console.log(data[i]);
					 displayInvitationMembers(data[i]);
				}
			},
			complete:function(){
				$('#inviteMembersModal').modal();
				$('button[id^="inviteMember_"]').click(function(){
				var bid2=this.id.split('_')[1];
				//console.log(this.id);
					$.ajax({
						type:"post",
						url: "webapi/eventadd/eventinvitation?event_id="+sessionStorage.event_id+"&member_id="+bid2+"&from_id="+sessionStorage.id,
						success: function(data){
							console.log(data);
							if(data=="true"){
								//console.log($('button[id=inviteMember_'+bid2+']').attr('id'));
								$('button[id=inviteMember_'+bid2+']').html("Invited")
								.prop('disabled',true);
								//$('button[id=unfriend_'+bid.split("_")[1]+']').remove();
								
							}
						}
					});
				});
				
			}
		});
	});
	
	var displayEvents = function(data) {
		var x;//console.log(data);
		if(data['end_time']==""){
			x="All time";
		}
		else{
			x=String(new Date(Number(data['end_time']))).replace("GMT+0530 (IST)","")
		}
		//console.log(data);
		document.getElementById('selectedEvent').innerHTML='<div class="panel panel-default">'+
				'<div class="panel-body">'+
						'<h2 style="color:red;text-align:center;display:none;" id="whenEventEnds">EVENT ENDED</h2>'+
						'<img src="'+data['piclocation']+'" class="fittodiv">'+
					'</div>'+
				'</div>'+
				'<div class="panel-footer">'+
					'<div class="row">'+
						'<div class="col-md-12">'+
							'<div class="col-md-3">'+
								'<h5 align="center" style="color:red">'+String(new Date(Number(data['start_time']))).split(" ")[1]+'</h5>'+
								'<h2 align="center" style="color:blue">'+String(new Date(Number(data['start_time']))).split(" ")[2]+'</h2>'+
							'</div>'+
							'<div class="col-md-9">'+
								'<h2>'+data['event_name']+'</h2>'+
								'<h5 id="hostedBy">HOSTED BY: '+data['admin_id']+'</h5>'+
							'</div>'+
						'</div>'+
					'</div>'+
					'<br>'+
					'<div class="container-fluid row" id="mychoice">'+
						'<div id="forAdmin">'+	
							'<button class="btn btn-default" id="interested_'+sessionStorage.id+'">'+
							'+<span class="glyphicon glyphicon-star"></span> May Be'+
							'</button>'+
							'<button class="btn btn-default" id="going_'+sessionStorage.id+'">'+
								'<span class="	glyphicon glyphicon-ok"></span> Going'+
							'</button>'+
							'<button class="btn btn-default" id="no_'+sessionStorage.id+'">'+
							'<span class="	glyphicon glyphicon-remove"></span> No'+
							'</button>'+
						'</div>'+
						'<button class="btn btn-default pull-right" id="edit" data-toggle="modal" data-target="#editModal" style="display:none">'+
						'<span class="	glyphicon glyphicon-edit"></span> Edit'+
						'</button>'+
					'</div>'+
				'</div>'+
				
				'<div class="panel-footer">'+
					'<div class="row">'+
						'<div>'+
							'<h4>'+
								'<span class="glyphicon glyphicon-time"></span> '+
								String(new Date(Number(data['start_time']))).replace("GMT+0530 (IST)","")+
								' - '+
								x+
								'</h4>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<div class="panel-footer">'+
					'<div class="row">'+
						'<div>'+
							'<h4>'+
								'<span class="glyphicon glyphicon-map-marker">LOCATION: </span> '+
								 data['location']+
								'</h4>'+
						'</div>'+
				
					'</div>'+
				'</div>'+
				
				'<div class="panel-footer">'+
				'<div class="row">'+
					'<div>'+
						'<h4>'+
							'<span class="glyphicon glyphicon-file">DETAILS :</span> '+data['event_detail']+
							'</h4>'+
					'</div>'+
				
				'</div>'+
				'</div>'+
				
				'<div class="panel-footer">'+
					'<div class="row">'+
						'<div>'+
							'<h2>'+
							'<a href="#" class="likefooter" id="numberGoing_data"></a>'+
								'</h2>'+'<h2>'+
							'<a href="#" class="likefooter" id="numberInterested_data"></a>'+
								'</h2>'+
						'</div>'+
				
					'</div>'+
				'</div>';

	};



	$.ajax({
					type : "get",
					url : "webapi/events/" + sessionStorage.event_id,
				success : function(res) {
						//console.log("here i am!!!");
						console.log(res);
						for (i = 0; i < res.length; i++) {
							displayEvents(res[i]);
							$.ajax({
								type:'post',
								url:'webapi/profile/'+res[i]['admin_id'],
								async:false,
								success:function(data){
									$('#hostedBy').html('HOSTED BY: '+data['name']);
								}
							});
							console.log(res[i]['admin_id']);
							
							if(res[i]['admin_id']==sessionStorage.id){
								$('#edit').css('display', 'block');
								$('#onmymind').css('display', 'block');
								console.log(res[i]["end_time"]);
								console.log(Number(new Date().getTime()));
								console.log(Number(new Date().getTime())+19800000);
								if(res[i]['end_time']=="" || Number(res[i]['end_time'])>Number(new Date().getTime()))
									$('#summary').css('display', 'hidden');
								else{
									$('#summary').css('display', 'block');
									if(res[i]['summary']&&res[i]['summary']!="")
										$('#eventSummary').html(res[i]['summary']);
									$('#edit').css('display', 'none');
									$('#onmymind').css('display', 'none');
								}
								
								$('#forAdmin').html("YOU'RE THE ADMIN");
							}
							if(res[i]['end_time']!="" && Number(res[i]['end_time'])<Number(new Date().getTime())){
								$('#whenEventEnds').css('display','block');
								$('#mychoice').css('display','none');
								$('#showSummary').css('display','block');
								if(res[i]['summary']&&res[i]['summary']!="")
									$('#eventSummary').html(res[i]['summary']);
								$("#inviteMembersBlockShow").css('display','none');
								
							}
						}

						$("button[id^='interested_']").click(function() {
											//console.log("kuku");
											var member_id = $(this).attr('id').split("_")[1];
											//window.location.href="groupHome.html";
											//			console.log(($(this).attr('id')).split("_")[1]);
											//console.log(member_id);
											$.ajax({
														type : "put",
														url : "webapi/eventadd/EventResponse?event_id="
																+ sessionStorage.event_id+ "&member_id="+ member_id+"&status=1",
														async:false,
														success : function(data) {
															//console.log(data);
															if (data == 'true') {
																console	.log("dis is bcoz of mudita");
																/*	$('button[id='+ bid+ ']').html("Joined")
																	.addClass("btn-success").removeClass("btn-info")
																	.prop('disabled',true);
																 */
																//$("button[id^='going_']").prop('disabled',true);
																$("button[id^='interested_']").addClass("btn-success").prop('disabled',true);
																$("button[id^='going_']").removeClass("btn-success").prop('disabled',false);
																$("button[id^='no_']").removeClass("btn-success").prop('disabled',false);

															}
														}
													});
										});

						$("button[id^='going_']").click(function() {
											//console.log("kuku");
											var member_id = $(this).attr('id').split("_")[1];
											//window.location.href="groupHome.html";
											//			console.log(($(this).attr('id')).split("_")[1]);
											//console.log(member_id);
											$.ajax({
												type : "put",
												url : "webapi/eventadd/EventResponse?event_id="
														+ sessionStorage.event_id+ "&member_id="+ member_id+"&status=2",
												async:false,
												success : function(data) {
													//console.log(data);
													if (data == 'true') {
														console	.log("dis is bcoz of mudita");
														/*	$('button[id='+ bid+ ']').html("Joined")
															.addClass("btn-success").removeClass("btn-info")
															.prop('disabled',true);
														 */
														//$("button[id^='going_']").prop('disabled',true);
														$("button[id^='going_']").addClass("btn-success").prop('disabled',true);
														$("button[id^='interested_']").removeClass("btn-success").prop('disabled',false);
														$("button[id^='no_']").removeClass("btn-success").prop('disabled',false);

													}
												}
											});
										});
						$("button[id^='no_']").click(function() {
							//console.log("kuku");
							var member_id = $(this).attr('id').split("_")[1];
							//window.location.href="groupHome.html";
							//			console.log(($(this).attr('id')).split("_")[1]);
							//console.log(member_id);
							$.ajax({
								type : "put",
								url : "webapi/eventadd/EventResponse?event_id="
										+ sessionStorage.event_id+ "&member_id="+ member_id+"&status=3",
								//	async:false,
								success : function(data) {
									//console.log(data);
									if (data == 'true') {
										console	.log("dis is bcoz of mudita");
										$("button[id^='no_']").addClass("btn-success").prop('disabled',true);
										$("button[id^='interested_']").removeClass("btn-success").prop('disabled',false);
										$("button[id^='going_']").removeClass("btn-success").prop('disabled',false);

									}
								}
							});
						});
					},
	complete:function(){
		
	$.ajax({
		type:'get',
		url:'webapi/eventadd/checkresponse?event_id='+sessionStorage.event_id+"&member_id="+sessionStorage.id,
		success:function(data){
			console.log(data);
			if(Number(data)==1){
				$("button[id^='interested_']").addClass("btn-success").prop('disabled',true);
				$("button[id^='going_']").removeClass("btn-success").prop('disabled',false);
				$("button[id^='no_']").removeClass("btn-success").prop('disabled',false);
			}
			if(Number(data)==2){
				$("button[id^='going_']").addClass("btn-success").prop('disabled',true);
				$("button[id^='interested_']").removeClass("btn-success").prop('disabled',false);
				$("button[id^='no_']").removeClass("btn-success").prop('disabled',false);
			}
			if(Number(data)==3){
				$("button[id^='no_']").addClass("btn-success").prop('disabled',true);
				$("button[id^='interested_']").removeClass("btn-success").prop('disabled',false);
				$("button[id^='going_']").removeClass("btn-success").prop('disabled',false);
			}
		}
	});

		$.ajax({
			type : "get",
			url : "webapi/count/going/" + sessionStorage.event_id,
			success : function(data) {
				if (data) {
					console.log(data);
					$('#numberGoing_data').html(data+" Going");
					$('#numberGoing_data').click(function(){
						$.ajax({
							type:'get',
							url:'webapi/count/listgoing/'+sessionStorage.event_id,
							success:function(data){
								$('#likesModalBody').html('');
								console.log(data);
								for(var i=0;i<data.length;++i){
									displayInterestedGoing(data[i]);
								}
								$('#likesModal').modal();
							}
						});
						return false;
					});
				}
		}
		});
		$.ajax({
			type : "get",
			url : "webapi/count/interested/" + sessionStorage.event_id,
			success : function(data) {
				if (data) {
					console.log(data);
					$('#numberInterested_data').html(data+" Interested");
					$('#numberInterested_data').click(function(){
						$.ajax({
							type:'get',
							url:'webapi/count/listinterested/'+sessionStorage.event_id,
							success:function(data){
								$('#likesModalBody').html('');
								console.log(data);
								for(var i=0;i<data.length;++i)
									displayInterestedGoing(data[i]);
								
								$('#likesModal').modal();
							}
						});
						return false;
					});
					
				}
		}
		});
	}
	});
});