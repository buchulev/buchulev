<!DOCTYPE html>

<html>
<head>

	<link rel="stylesheet" href="view/css/main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Review</title>
</head>



<body>
	{include file="pop_up_menu.tpl"}
	{include file="logo.tpl"}
    {include file="header.tpl"}
	<main>
		<div class="page">
		{include file="search-toolkit.tpl"}
		<div class="review">
		<h2>{$review[1]}</h2>
		<div class="wordwrap">
		{$review[2]}
		</div>
		<div class="review_username">{$review[4]}</div>
		
			</div>
			

			</div>
	
		
	</main>
	<footer>

	</footer>

		
</body>






</html>