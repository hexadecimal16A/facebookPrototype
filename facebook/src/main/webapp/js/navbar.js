$(document).ready(function() {

		$.ajax({
			type : "post",
			url : "webapi/profile/" + sessionStorage.id,
			success : function(data) {
				sessionStorage.userProfileImgPath=data['profileImage'];
				$('#UserImg').attr('src', data['profileImage']);
				$('#UserName').append(data['name']);
				$('#UserHomeImg').attr('src',data['profileImage']);
				$('#UserHome').append(data['name']);
				//console.log(data);
			}
		});
		
		var form1 = $('#searchform');
		form1.submit(function() {

			$.ajax({
				type : "post",
				url : "webapi/search/",
				data : form1.serialize(),
				success : function(data) {
					window.location.href = "search.html";
				}
			});

			return false;
		});
		
	});