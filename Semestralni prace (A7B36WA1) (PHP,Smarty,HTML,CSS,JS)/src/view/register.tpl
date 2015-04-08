<!DOCTYPE html>

<html>
<head>

	<link rel="stylesheet" href="view/css/main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>register page</title>
</head>


<body>
    
	{include file="logo.tpl"}
    {include file="header.tpl"}
    
    
    <div class="register_menu">
    <form method="POST" action="register.php">
    <div>Mandatory fields*</div>
    <div><label for="nickname">Nickname*</label><input type="text" id="nickname" tabindex="1" name="nickname" value="{$nickname}" pattern="[a-zA-Z0-9]{literal}{5,20}{/literal}" title="Username should be from 5-20 chars and contain only latin and digits" required/><span class="error_space"></span></div>
     <div><label for="password">Password*</label><input type="text" id="password" tabindex="2" name="password"   pattern="[a-zA-Z0-9]{literal}{5,20}{/literal}" title="Passord should be from 5-20 chars and contain only latin and digits" required/><span class="error_space"></span></div>
     <div><label for="password_again">Confirm Password*</label><input type="text" id="password_again" tabindex="3" name="password_again"    pattern="[a-zA-Z0-9]{literal}{5,20}{/literal}" title="Password should be from 5-20 chars and contain only latin and digits" required/><span class="error_space"></span></div>
     <div><label for="phonenumber">Phonenumber</label><input type="text" id="phonenumber" tabindex="4" name="phonenumber" value="{$phonenumber}"  pattern="[\+]\d{literal}{3}{/literal}[\-]\d{literal}{3}{/literal}[\-]\d{literal}{3}{/literal}[\-]\d{literal}{3}{/literal}" title="phone number like +XXX-XXX-XXX-XXX"/><span class="error_space"></span></div>
    <div><label for="email">Email*</label><input type="email" id="email" tabindex="5" name="email"  value="{$email}" required/><span class="error_space"></span></div>
     <div><label for="firstname">Firstname</label><input type="text" id="firstname" tabindex="6" name="firstname"  value="{$firstname}"/><span class="error_space"></span></div>
    <div><input type="submit" name="submit" value="register"/></div>
   {$error}
    </form>
    </div>


	<script src ="view/js/register.js"></script>
		
</body>
</html>