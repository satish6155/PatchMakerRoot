<html>
<head>
<link rel="stylesheet"	href="<%=request.getContextPath()%>/resources/assets/style/header.css" />
</head>
<body>
<nav class="navbar navbar-default navbar-expand-xl navbar-light">
		<div class="navbar-header d-flex col">
			<a class="navbar-brand" href="#"><i class="fa fa-cube"></i>SVN.<b>PatchMaker</b></a>
			<button type="button" data-target="#navbarCollapse"
				data-toggle="collapse" class="navbar-toggle navbar-toggler ml-auto">
				<span class="navbar-toggler-icon"></span> <span class="icon-bar"></span>
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
		<!-- Collection of nav links, forms, and other content for toggling -->
		<div id="navbarCollapse"
			class="collapse navbar-collapse justify-content-start">
			<ul class="nav navbar-nav">
				<li class="nav-item"><a href="releaseTracker"
					class="nav-link"><b>Release Register</b></a></li>
			   <li class="nav-item"><a href="#" 
			   		class="nav-link"><b>Patch Maker</b></a></li> 
			   	<li class="nav-item"><a href="http://localhost:1011/PatchMaker/servlet/FileManager"  target="_blank"
			   		class="nav-link"><b>File Browser</b></a></li> 
			</ul>
	<div class="user-options">
			<ul class="nav navbar-nav navbar-right ml-auto">
		
				<li class="nav-item dropdown"><a href="#"
					data-toggle="dropdown" class="nav-link dropdown-toggle user-action">
					<img
						src="<%=request.getContextPath()%>/resources/assets/img/logo.png" class="avatar" alt="User">
						<b><%=session.getAttribute("username")%> </b>
						<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#" class="dropdown-item"><i
								class="fa fa-user-o"></i> Profile</a></li>
		
						<li class="divider dropdown-divider"></li>
						<li><a href="<%=request.getContextPath()%>/logout" class="dropdown-item"><i
								class="fa fa-power-off"></i>Logout</a></li>
					</ul></li>
			</ul>
			</div>
		</div>
	</nav>
	</body>
	</html>