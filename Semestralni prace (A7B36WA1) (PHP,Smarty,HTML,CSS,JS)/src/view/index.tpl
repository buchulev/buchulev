<!DOCTYPE html>

<html>
<head>

	<link rel="stylesheet" href="view/css/main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Main Page</title>
</head>



<body>
	{include file="pop_up_menu.tpl"}
	{include file="logo.tpl"}
    {include file="header.tpl"}

	<main>
		<div class="page">
	{include file="search-toolkit.tpl"}
	    <div class="headside">

		
		
		 <span class="topic">News</span>
		</div>
			{include file="news.tpl"}	
			{include file="list_of_pages.tpl"}
		</div>
	</main>
	<footer>

	</footer>
</body>
</html>