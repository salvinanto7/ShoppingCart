<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<link rel='stylesheet'
	href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'
	integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T'
	crossorigin='anonymous' />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.css">

<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>

<style>
.body{
	background-image : url("cover.jpg");
	background-repeat: no-repeat;
	background-size: cover;
}
</style>

</head>
<body>
	<section id="navbar">

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="index.jsp">Shopping Cart</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item active"><a class="nav-link"
						href="index.jsp">Home <span class="sr-only">(current)</span>
					</a></li>
					<c:if test="${sessionScope.email!=null}">
						<form action="controller"
							class="d-flex align-items-ceter justify-content-center">
							<input type="hidden" class="nav-items" name="userAction"
								value="cart">
							<button type="submit" class="bg-light"
								style="background-color: none; border: none; margin: auto">Cart</button>
						</form>
					</c:if>
					<form action="controller"
						class="d-flex align-items-ceter justify-content-center">
						<input type="hidden" class="nav-items" name="userAction"
							value="products">
						<button type="submit" class="bg-light"
							style="background-color: none; border: none; margin: auto">Products</button>
					</form>
				</ul>
				<div class='dropdown show ml-auto'>
					<a class='btn btn-secondary dropdown-toggle' href='#' role='button'
						id='dropdownMenuLink' data-toggle='dropdown' aria-haspopup='true'
						aria-expanded='false'> Account </a>

					<div class='dropdown-menu ml-auto'
						aria-labelledby='dropdownMenuLink'>
						<a class='dropdown-item' href="register.jsp">Register</a> <a
							class='dropdown-item' href='login.jsp'> Login </a>
					</div>
				</div>
			</div>
		</nav>

	</section>
	
	<section>
	
		<div class="continer my-5 py-5 d-flex align-items-center jsutify-content-center">
			<div class="col-md-12">
				<div class="my-5 py-5">
					<h1>Welcome to Shopping Cart</h1>
					<form action="controller" method="post">
						<input type="hidden" class="nav-item" name="userAction" value="products">
						<button class="btn btn-success" type="submit" >See All Products</button>
					</form>
				</div>
			</div>
		
		</div>
	
	</section>


	<script
		src='https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js'
		integrity='sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1'
		crossorigin='anonymous'></script>
	<script
		src='https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js'
		integrity='sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM'
		crossorigin='anonymous'></script>
</body>
</html>