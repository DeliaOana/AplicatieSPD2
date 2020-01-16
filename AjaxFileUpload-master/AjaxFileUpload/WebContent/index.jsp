<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prelucrare imagini</title>
<script src="jquery-1.8.2.js"></script>
<script src="jquery.ajaxfileupload.js"></script>

<style type="text/css">
.centered {
	width: 100%;
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}
</style>
</head>
<body>
	<form>
		<div class="centered">
			<h2 style="text-align: center;">Aplicatie de prelucrare imagini</h2>
			<input type="file" name="datafile" /><br />
			<div id="upload" style="display: none;">Uploading..</div>
		</div>


		<table align="center">
			<tr>
				<td><input type="radio" id="filtru1" name="filtru"
					value="grayscale" checked><span>Grayscale</span></td>
			</tr>
			<tr>
				<td><input type="radio" id="filtru2" name="filtru"
					value="sepia"><span>Sepia</span></td>
			</tr>
			<tr>
				<td><input type="radio" id="filtru3" name="filtru"
					value="green"><span>Green</span></td>
			</tr>
			<tr>
				<td><input type="radio" id="filtru4" name="filtru"
					value="negative"><span>Negative</span></td>
			</tr>
		</table>
	</form>

<script type="application/javascript">
	$(document).ready(function() {
		//initFileUploadParam();
		$("input[name='filtru']").on('click', function(event){
			initFileUploadParam();
		});
		
		function initFileUploadParam(){
			$('input[type="file"]').ajaxfileupload({
			    valid_extensions : ['jpg'],
			    params: {
		    		filtru: $("input[name='filtru']:checked").val()
				},
				action : 'UploadFile',
				onComplete : function(response) {
					$('#upload').hide();
					alert("Imaginea a fost salvata!");
				},
				onStart : function() {
					$('#upload').show();
				}
			});
		}
	});
</script>
</body>
</html>