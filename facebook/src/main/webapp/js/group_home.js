

	var displayTemp=function(commentText,post_id){
		// console.log(post_id);
		commentCollapse=document.getElementById("tempDiv_"+post_id);
		var ccMedia = document.createElement("DIV");
		ccMedia.style.marginTop="0px";
		ccMedia.className = "media";
			var ccMedia2 = document.createElement("DIV");
			ccMedia2.className = "media-left";
				var ccMedia3=document.createElement("img");
				ccMedia3.setAttribute('src', sessionStorage.userProfileImgPath);
				ccMedia3.className="media-object";
				// elem.className = "fittodiv";
				ccMedia3.setAttribute("height", "auto");
				ccMedia3.setAttribute("width", "40px");
			ccMedia2.appendChild(ccMedia3);
		ccMedia.appendChild(ccMedia2);
			var ccMedia_2 = document.createElement("DIV");
			ccMedia_2.className="media-body";
				ccMedia3=document.createElement("H5");
				ccMedia3.className="media-heading";
				ccMedia3.style.color="blue";
				ccMedia3.innerHTML="Me";
			ccMedia_2.appendChild(ccMedia3);
				ccMedia4=document.createElement("p");
				ccMedia4.innerHTML=commentText;
			ccMedia_2.appendChild(ccMedia4);
		ccMedia.appendChild(ccMedia_2);
		
		commentCollapse.appendChild(ccMedia);
	};

	var displayComments=function(data,post_id){
			// -----commentCollapsePanel-----------//
		commentCollapse=document.getElementById("commentCollapse_"+post_id);
			var ccMedia = document.createElement("DIV");
			ccMedia.style.marginTop="0px";
			ccMedia.className = "media";
				var ccMedia2 = document.createElement("DIV");
				ccMedia2.className = "media-left";
					var ccMedia3=document.createElement("img");
					ccMedia3.setAttribute('src', data['profileImg']);
					ccMedia3.className="media-object";
					// elem.className = "fittodiv";
					ccMedia3.setAttribute("height", "auto");
					ccMedia3.setAttribute("width", "40px");
				ccMedia2.appendChild(ccMedia3);
			ccMedia.appendChild(ccMedia2);
				var ccMedia_2 = document.createElement("DIV");
				ccMedia_2.className="media-body";
					ccMedia3=document.createElement("H5");
					ccMedia3.className="media-heading";
					ccMedia3.style.color="blue";
					ccMedia3.appendChild(document.createTextNode(data["name"]));
				ccMedia_2.appendChild(ccMedia3);
					ccMedia4=document.createElement("p");
					ccMedia4.appendChild(document.createTextNode(data["commentText"]));
				ccMedia_2.appendChild(ccMedia4);
			ccMedia.appendChild(ccMedia_2);
			
			commentCollapse.appendChild(ccMedia);
	};
			/*
			 * <div class="media-body"> <h4 class="media-heading">John Doe</h4>
			 * <p>Lorem ipsum...</p> </div>
			 */


	var displayLikes=function(data){
		// --------data has id use to redirect to profile page
		var divxx = document.createElement("DIV");
		divxx.style.marginBottom="1em";
		divxx.className = "row";
		{
			var div1 = document.createElement("DIV");
			div1.className = "col-md-2";
			var elem = document.createElement("img");
			elem.setAttribute('src', data['profileImage']);
			// elem.className = "fittodiv";
			elem.setAttribute("height", "auto");
			elem.setAttribute("width", "60em");
			div1.appendChild(elem)
			divxx.appendChild(div1);
			// ---------//
			var div2 = document.createElement("DIV");
			div2.className = "col-md-5";
			// var p=document.createElement("P");
			var t = document.createTextNode(data["name"]);
			// p.appendChild(t);
			div2.append(t);
			divxx.appendChild(div2);
			divxx.appendChild(document.createElement("br"));
			// p.appendChild(document.createElement("br"));
		}
		document.getElementById("likesModalBody").appendChild(divxx);
		// console.log($("#likesModal.modal-body"));
	};

	var displayPeople = function(data,clName,numberOfLikes,numberOfComments) {
		// console.log(clName);
		var newPanel = document.createElement("DIV");
		newPanel.className = "panel panel-default container-fluid";
		
		// -------//
		var newPanelBody = document.createElement("DIV");
		newPanelBody.className = "panel-body padding0";
		{	
			var divxx = document.createElement("DIV");
			divxx.className = "row";
			{
				var div1 = document.createElement("DIV");
				div1.className = "col-md-2";
				var elem = document.createElement("img");
				elem.setAttribute('src', data['picturePath']);
				// elem.className = "fittodiv";
				elem.setAttribute("height", "auto");
				elem.setAttribute("width", "60em");
				div1.appendChild(elem)
				divxx.appendChild(div1);
				// ---------//
				var div2 = document.createElement("DIV");
				div2.className = "col-md-10";
				var p=document.createElement("P");
				var s=document.createElement("SPAN");
				s.className="postName";
				var t = document.createTextNode(data["userName"]);
				s.appendChild(t);
				p.appendChild(s);
				p.appendChild(document.createElement("br"));
				var s=document.createElement("SPAN");
				s.className="datefont";
				var t = document.createTextNode(new Date(Number(data["creationTime"])));
				s.appendChild(t);
				p.appendChild(s);
				div2.appendChild(p)
				// var p=document.createElement("p");
				// p.className="datefont";
				// var t = document.createTextNode(data["creationTime"]);
				// p.appendChild(t);
				// div2.appendChild(p)
				divxx.appendChild(div2);
			}
			newPanelBody.appendChild(divxx);
			var p=document.createElement("P");
			p.style.marginTop="0em";
			var t = document.createTextNode(data["textfield"]);
			p.appendChild(t);
			newPanelBody.appendChild(p);
			if(data["picLocation"]){
				var elem = document.createElement("img");
				elem.setAttribute('src', data['picLocation']);
				elem.className = "fittodiv";
				// elem.setAttribute("height", "auto");
				// elem.setAttribute("width", "100em");
				newPanelBody.appendChild(elem);
			}
			// ---------Like comment share//
			
		}
		newPanel.appendChild(newPanelBody);
		var newPanelFooter = document.createElement("DIV");
		newPanelFooter.className = "panel-footer footer";
		{
				var like=document.createElement("a");
				like.href="#";
				like.className="afooter "+clName;
				like.id="like_"+data["postid"];
				var span=document.createElement("SPAN");
				span.className="glyphicon glyphicon-thumbs-up";
				var likeText=document.createTextNode(" Like ");
				like.appendChild(span);
				like.appendChild(likeText);
				newPanelFooter.appendChild(like);
				
				
				newPanelFooter.appendChild( document.createTextNode( '\u00A0\u00A0\u00A0' ) );
				// ----------comment----------------//
				var comment=document.createElement("a");
				comment.href="#";
				comment.className="afooter";
				comment.id="comment_"+data["postid"];
				var span=document.createElement("SPAN");
				span.className="glyphicon glyphicon-comment";
				var commentText=document.createTextNode(" Comment");
				comment.appendChild(span);
				comment.appendChild(commentText);
				newPanelFooter.appendChild(comment);
				newPanelFooter.appendChild( document.createTextNode( '\u00A0\u00A0\u00A0' ) );
				// --------share------------//
				var share=document.createElement("a");
				share.href="#";
				share.className="afooter";
				share.id="share_"+data["postid"];
				var span=document.createElement("SPAN");
				span.className="glyphicon glyphicon-share-alt";
				var shareText=document.createTextNode(" Share ");
				share.appendChild(span);
				share.appendChild(shareText);
				newPanelFooter.appendChild(share);
		}
		newPanel.appendChild(newPanelFooter);
		
		// --------others Section--------------//
		if(numberOfLikes>-1){
		newPanelFooter = document.createElement("DIV");
		newPanelFooter.className = "panel-footer";
		
		{
			var like=document.createElement("a");
			like.href="#";
			var likeText;
			like.className="likefooter"
			like.id="numberLikes_"+data["postid"];
			if(numberOfLikes>1 && clName=="afooteractive"){
				likeText=document.createTextNode("You and "+(numberOfLikes-1)+" other liked this ");
				like.appendChild(likeText);
				newPanelFooter.appendChild(like);
				newPanelFooter.appendChild(document.createElement("br"));
			}
			else if(numberOfLikes>0 && clName!="afooteractive"){
				likeText=document.createTextNode(""+numberOfLikes+" liked this ");
				like.appendChild(likeText);
				newPanelFooter.appendChild(like);
				newPanelFooter.appendChild(document.createElement("br"));
			}
			else if(numberOfLikes==1 && clName=="afooteractive"){
				likeText=document.createTextNode("You liked this ");
				like.appendChild(likeText);
				newPanelFooter.appendChild(like);
				newPanelFooter.appendChild(document.createElement("br"));
			}
			
			
			
			
			// ---------comment-------//
			var comment=document.createElement("a");
			comment.href="#";
			var commentText;
			comment.className="likefooter"
			comment.id="numberComments_"+data["postid"];
			if(numberOfComments>0){
				commentText=document.createTextNode("View "+(numberOfComments)+" other comments ");
				comment.appendChild(commentText);
			}
			
			newPanelFooter.appendChild(comment);
			
			var tempDiv=document.createElement("DIV");
			tempDiv.id="tempDiv_"+data["postid"];
			tempDiv.style.marginTop="0.5em";
			newPanelFooter.appendChild(tempDiv);
			// --------------collapse------------------//
			var commentCollapse=document.createElement("DIV");
			commentCollapse.className="collapse";
			commentCollapse.id="commentCollapse_"+data["postid"];
			newPanelFooter.appendChild(commentCollapse);
			
			// ------------------collapse to comment----------------//
			
			var commentToCollapse=document.createElement("DIV");
			commentToCollapse.className="collapse";
			commentToCollapse.id="commentToCollapse_"+data["postid"];
			{
				var ccMedia = document.createElement("DIV");
				ccMedia.style.marginTop="1em";
				ccMedia.className = "media";
					var ccMedia2 = document.createElement("DIV");
					ccMedia2.className = "media-left";
						var ccMedia3=document.createElement("img");
						ccMedia3.setAttribute('src', sessionStorage.userProfileImgPath);
						ccMedia3.className="media-object";
						// elem.className = "fittodiv";
						ccMedia3.setAttribute("height", "33px");
						ccMedia3.setAttribute("width", "auto");
					ccMedia2.appendChild(ccMedia3);
				ccMedia.appendChild(ccMedia2);
					var ccMedia_2 = document.createElement("DIV");
					ccMedia_2.className="media-body";
						var cform = document.createElement("FORM");
						cform.id="commentToPost_"+data["postid"];
						var cinp = document.createElement("INPUT");
						cinp.className="form-control";
						cinp.setAttribute("type", "text");
						cinp.name="commentText";
						cinp.id="commentToPost_"+data["postid"]+"_commentText";
						cinp.setAttribute( "autocomplete", "off" ); 
						cform.appendChild(cinp);
					ccMedia_2.appendChild(cform);
				ccMedia.appendChild(ccMedia_2);
			commentToCollapse.appendChild(ccMedia);
			}

			newPanelFooter.appendChild(commentToCollapse);
			
			
		}
		newPanel.appendChild(newPanelFooter);
		}
		document.getElementById('newsFeed').appendChild(newPanel);

	};

	$(document).ready(function() {
		
		// ------------news feed------------------------//
		var modalform2 = $('#onmymind');
		modalform2.submit(function() {
			var fd = new FormData($('#onmymind')[0]);
			console.info(fd);
			$.ajax({
				type : modalform2.attr('method'),
				url : modalform2.attr('action') + "/" + sessionStorage.groupId+"_"+sessionStorage.id,
				data : fd,
				enctype : 'multipart/form-data',
				processData : false, // Important!
				contentType : false,
				cache : false,
				success : function(data) {
					console.log(data);
					if(data=="null"){
						window.location.reload();
					}
					else if (data) {
						document.getElementById('postImageVerifyDiv').innerHTML='<img id="postImageVerify" src='+data+' style="display:none" onError="fPostImageVerify();" onload="window.location.reload();">';
						postImageVerify_Image=data;
						document.getElementById('postImageVerify').src = postImageVerify_Image;
						}
					else
						alert("Try Again");
				}
			});

			return false;
		});
		$('#UserName').html($('#UserName').html() + name);
		$('#newsfeedPicToggle').click(function() {
			$('#newsfeedPic').click();
		});
		document.getElementById('newsfeedPic').onchange = function() {
			$('#fileName').html($('#newsfeedPic').val().split("\\")[2]);
		};
		
		$.ajax({
			type : "get",
			url : "webapi/groupfeed/" + sessionStorage.groupId,
			success : function(data) {
				if (data) {
					//console.log(data);
					for(i=0;i<data.length;++i){
						var post_id=data[i]["postid"];
						var className="afooter";
						var numberOfLikes=0;
						var numberOfComments=0;
						$.ajax({
							type:'get',
							url:'webapi/glcm/likes?post_id='+post_id+'&user_id='+sessionStorage.id,
							async:false,
							success:function(data){
								if(data.split("_")[0]=="true"){
									className="afooteractive";
									
								}
								numberOfLikes=Number(data.split("_")[1]);
							},
							complete:function(){
								$.ajax({
									type:'get',
									url:'webapi/glcm/comments?post_id='+post_id,
									async:false,
									success:function(data){
										numberOfComments=Number(data);
										//console.log("cc"+data);
									},
									complete:function(){
										displayPeople(data[i],className,numberOfLikes,numberOfComments);
									}
								});
								
							}
						});
						
					}
				}
			},
			complete: function(){
				
				$('a[id^=like_]').click(function(e){
					var id=$(this).attr('id').split("_")[1];
					e.preventDefault();
					$.ajax({
						type:'put',
						url:'webapi/glcm/likes?post_id='+($(this).attr('id')).split("_")[1]+'&user_id='+sessionStorage.id,
						async:false,
						success:function(data){
							if(data){
								$('a[id=like_'+id+']').toggleClass('afooteractive');
							}
						}
					});
					//console.log($(this).attr("id"));
				});
				
				$('a[id^=numberLikes_]').click(function(e){
					var id=$(this).attr('id').split("_")[1];
					e.preventDefault();
					$("#likesModalBody").text(" ");
					$("#likesModal").modal();
					$.ajax({
						type:'get',
						url:'webapi/glcm/likes/'+id,
						async:false,
						success:function(data){
							//console.log(data);
							for(i=0;i<data.length;++i){
								//console.log(data[i]);
								displayLikes(data[i]);
							}
						},
						complete:function(){
							$("#likesModal").modal();
						}
						
					});
					//console.log($(this).attr("id"));
				});
				
				$('a[id^=numberComments_]').click(function(e){
					var id=$(this).attr('id').split("_")[1];
					$('#tempDiv_'+id).text(" ");
					$('#commentCollapse_'+id).text(" ");
					$('#commentCollapse_'+id).collapse('toggle');
					e.preventDefault();
					$.ajax({
						type:'get',
						url:'webapi/glcm/comments/'+id,
						async:false,
						success:function(data){
							//console.log(data);
							commentCollapse=document.getElementById("commentCollapse_"+id);
							commentCollapse.appendChild(document.createElement("br"));
							for(i=0;i<data.length;++i){
								//console.log(data[i]);
								displayComments(data[i],id);
							}
						}
						
					});
					//console.log($(this).attr("id"));
				});
				
				$('a[id^=comment_]').click(function(e){
					var id=$(this).attr('id').split("_")[1];
					//$('#commentToCollapse_'+id).text(" ");
					//console.log(id);
					$('#commentToCollapse_'+id).collapse('toggle');
					
					e.preventDefault();
					
					var commentPostId="commentToPost_"+id;
					
					var commentPost = $('#'+commentPostId);
					//console.log(commentPost);
					commentPost.submit(function() {
						
						$.ajax({
							type: 'put',
							url: 'webapi/glcm/comments?post_id='+id+'&user_id='+sessionStorage.id,
							data: commentPost.serialize(),
							success: function (data){
								if(data=="true"){
									displayTemp($('#commentToPost_'+id+'_commentText').val(),id);
									$('#commentToPost_'+id+'_commentText').val('');
								}
								else{
									alert("try again");
								}
								//commentPost.text('');
							}
						});

						return false;
					});
				});
				
				
				$('a[id^=share_]').click(function(){
					//console.log($(this).attr("id"));
				});
			}
		});
		});
		// ----------------------------Create New
		// Panel---------------------------------//

	function fPostImageVerify(){
		$('#newsfeedPost').prop('disabled', true);
		setTimeout(function(){$("#newsfeedPost").html("Uploading Image   ");}, 300); 
		setTimeout(function(){$("#newsfeedPost").html("Uploading Image.  ");}, 600); 
		setTimeout(function(){$("#newsfeedPost").html("Uploading Image.. ");}, 900); 
		setTimeout(function(){$("#newsfeedPost").html("Uploading Image...");}, 1200); 
		setTimeout(function(){document.getElementById('postImageVerify').src = postImageVerify_Image;}, 1200);
	}
	