<!DOCTYPE html>

<html>
<head>

	<link rel="stylesheet" href="view/css/main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Logi In Page</title>
</head>


<body>
    
	{include file="logo.tpl"}
    {include file="header.tpl"}
    <div class="login_menu">
    <form method="POST" action="login.php">
    <div>
     <label for="nickname"><span class="fontawesome-user"></span></label>
     <input type="text" id="nickname" value="{$nickname}" name="nickname" tabindex="1"/>
    </div>
    <div>
     <label for="password"><span class="fontawesome-lock"></span></label>
    <input id="password" type="password"  name="password" tabindex="2"/>
    </div>
    <div>
    <input value="Log in" name="submit" type="submit"/>
    </div>
    </form>
    <div class="error_space">
     {$error}
     </div>
     </div>
     
      

   
    
    
    

</body>






</html>