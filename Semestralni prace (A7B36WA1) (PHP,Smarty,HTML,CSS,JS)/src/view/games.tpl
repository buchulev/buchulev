<!DOCTYPE html>
<html>
<head>

	<link rel="stylesheet" href="view/css/main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Reviews on games</title>
</head>



<body>
	{include file="pop_up_menu.tpl"}
	{include file="logo.tpl"}
    {include file="header.tpl"}
	<main>
		<div class="page">
		{include file="search-toolkit.tpl"}
		<h2>Results</h2>
		<div class="games">
		
		<table>
		<thead>
		<tr>
		<th>Review Title</th>
		<th>Game Name</th>
		</tr>
		</thead>
		<tbody>
		
		{foreach $games as $game}
<tr>
		
			<td><a href="review.php?review_id={$game[2]}">{$game[3]} </a></td><td>  {$game[0]}</td>  
	</tr>		
			{/foreach}
			</tbody>
			</table>
			</div>
			
			
			{include file="game_list_of_pages.tpl"}
			</div>
	
		
	</main>
	<footer>

	</footer>

		
</body>






</html>
