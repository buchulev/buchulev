<!DOCTYPE html>

<html>
<head>

	<link rel="stylesheet" href="view/css/main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Add News</title>
</head>


<body>
	{include file="logo.tpl"}
    {include file="header.tpl"}
    <div class="add_news_menu">
    <form method="POST" action="add_news.php">
    <h2>Add News</h2>
   <div>Mandatory fields*</div>
    <label for="topic" >Topic*</label><input tabindex="1" type="text" id="topic" name="topic" value="{$topic}" pattern="{literal}.{0,100}{/literal}" required title="Should not  be longer than 100 letters"/>
   <label for="text" class="text_label">Text*</label> <textarea id="text" tabindex="2" name="text" cols="40"  pattern="{literal}.{0,10000}{/literal}" required title="Should not  be longer than 10000 letters">{$text}</textarea>
    <input type="submit" name="submit" value="add"/>
    </form>
     
     
    
     <div class="error_space">
 
     </div>

    </div>

		
</body>
</html>