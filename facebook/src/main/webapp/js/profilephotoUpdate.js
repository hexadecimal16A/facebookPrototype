var profileImgTemp="a.jpg";
	function doSomething() {
	    document.getElementById('modalProfileImg').src=profileImgTemp;
	    document.getElementById('profilePhotoImg').src = profileImgTemp;
	    document.getElementById('UserHomeImg').src= profileImgTemp;
	} 
	
	$(document).ready(function(){
		document.getElementById("profilePhotoId").value = sessionStorage.id;
		
		var modalform2 = $('#myModal2Form');
		modalform2.submit(function() {
			var fd = new FormData($('#myModal2Form')[0]);
			//console.log("hello");
		    //console.log(fd);
			$.ajax({
				type : modalform2.attr('method'),
				url : modalform2.attr('action'),
				data : fd,
				enctype: 'multipart/form-data',
		        processData: false,  // Important!
		        contentType: false,
		        cache: false,
				success : function(data) {
					if(data){
						profileImgTemp=data;
						document.getElementById('modalProfileImg').src=profileImgTemp;
						document.getElementById('profilePhotoImg').src = profileImgTemp;
						//setTimeout(location.reload.bind(location),8000);
					}
					else{
						alert("Try Again");
					}
				}
			});

			return false;
		});
	});