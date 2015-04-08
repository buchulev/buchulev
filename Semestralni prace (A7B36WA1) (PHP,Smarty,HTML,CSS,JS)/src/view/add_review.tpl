<!DOCTYPE html>

<html>
<head>
	
	<link rel="stylesheet" href="view/css/main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Add Review</title>
</head>


<body>
	{include file="logo.tpl"}
    {include file="header.tpl"}
    <div class="add_review_menu">
    <form method="POST" action="add_review.php">
    <h2> Add New Review On Videogame</h2>
    <div>Mandatory fields*</div>
    <div><label for="name">Game Name*</label><input type="text" tabindex="1" id="name" name="name" value="{$name}" pattern="{literal}.{1,100}{/literal}" required title="Should not  be less than 10 or longer than 100 letters"/></div>
    <div><label for="title">Review Title*</label><input tabindex="2" id="title"type="text" name="title" value="{$title}" pattern="{literal}.{1,100}{/literal}" required title="Should not  be less than 10 or longer than 100 letters"/></div>
    <div><label for="text" class="text_label">Text*</label><textarea tabindex="3" id="text" name="text" cols="40"  pattern="{literal}.{100,10000}{/literal}" required title="Should not  be less than 100 or longer than 10000 letters">{$text}</textarea></div>
    <input type="submit" name="submit" value="add"/>
    </form>
    <div class="error_space">{$error}</div>
    </div>
		
</body>
</html>