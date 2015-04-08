<!DOCTYPE html>

<html>
<head>

	<link rel="stylesheet" href="view/css/main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Profile Page</title>
</head>



<body>
	{include file="pop_up_menu.tpl"}
	{include file="logo.tpl"}
    {include file="header.tpl"}
	<main>
	<div class="page">
	<div class="profile">
		<div>Nickname : {$user[0]}</div>
		<div>Firstname : {$user[1]}</div>
		<div>Email : {$user[4]}</div>
		<div>Phone number : {$user[3]}</div>
		</div>
</div>
	</main>
	<footer>

	</footer>

		
</body>






</html>