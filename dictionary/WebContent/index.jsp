<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>dictionary</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

      <script type="text/javascript" src="js/jquery1.11.1.min.js"></script>

</head>

	<body>	
	
	<h1>Dictionary server</h1>
	
	
	<div class="search_div">  
	
	<input type="text" name="txt" id="txt" value="" />
	<input type="button" name="tijiao" id="tijiao" value="submit" />
	<br>
	<span id="result"></span>
	
	</div>
		
	
		

		
	 </body>
	 
<script type="text/javascript">

 $(function(){
	 
	 $.ajax({
			type:"post",
			url:"SelectServlet",
			data:{},
			error:function(){},
	    	timeout:1000,
			success:function(data){
				$("#context").html("");
				$("#context").append(data);
			}
		});
	 
	 
	 $("#tijiao").click(function(event){
		 tijiao();
		});
	 $("#delete").click(function(event){
		 var txt=$("#deletetxt").val();
		 
			$.ajax({
				type:"post",
				url:"DeleteServlet",
				data:{"txt":txt},
				error:function(){},
		    	timeout:1000,
				success:function(data){
					alert(data);
					reflash();
				}
			});
		});
	 $("#add").click(function(event){
		 var code=$("#addcode").val();
		 var definition=$("#adddefinition").val();
		 
			$.ajax({
				type:"post",
				url:"AddServlet",
				data:{"code":code,"definition":definition},
				error:function(){},
		    	timeout:1000,
				success:function(data){
					alert(data);
					reflash();
				}
			});
		});
 });
 function tijiao(){
	
	 
	 var txt=$("#txt").val();
	 
		$.ajax({
			type:"post",
			url:"RunServlet",
			data:{"txt":txt},
			error:function(){},
	    	timeout:1000,
			success:function(data){
				//alert(data);
				$("#result").html("Response:"+data);
			}
		});
 }
 
 
 function reflash(){
	 $.ajax({
			type:"post",
			url:"SelectServlet",
			data:{},
			error:function(){},
	    	timeout:1000,
			success:function(data){
				$("#context").html("");
				$("#context").append(data);
			}
		});
 }
</script>		
	
  
</html>
