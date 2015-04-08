<?php


require_once ('header.php');
require_once ('db_connection.php');
require_once ('functions.php');
 
 /*
 * Proměne pro zachovavani uzivatelskeho vstupu
 */
 
$nickname = "";
$firstname = "";
$password = "";
$password_again = "";
$phonenumber = "";
$email = "";

/*
 * Proměna, ktera bude zachovavat vše chybove hlašky 
 */
$error = "";


/*
 * Odolnost reload'u stranky/ dvojitemu odesilani
 */

if (isset($_POST['submit'])) {

	$nickname = $_POST['nickname'];
	$firstname = $_POST['firstname'];
	$password = $_POST['password'];
	$password_again = $_POST['password_again'];
	$phonenumber = $_POST['phonenumber'];
	$email = $_POST['email'];

/*
 * Validace vstupu
 */

	if (empty($_POST['nickname']) || empty($_POST['email']) || empty($_POST['password']) || empty($_POST['password_again'])) {
		
		$error .= "Fill in all mandatory fields";
		
	} else {
		if (strcmp($_POST['password'], $_POST['password_again']) === false) {
			
			$error .= "</br> Passwords are not equal";
			
		} else {
			if (checkNickname($dbconn, $_POST['nickname'])) {
				if (checkEmail($dbconn, $_POST['email'])) {
					
					/*
					 * Když všechno v pořadku => přidam  uzivatele do db
					 */
					 
					createUser($dbconn, $_POST);
					header("Location:index.php");
				} else
					$error .= "</br> Choose another nickname";
			} else {
				$error .= "</br> Choose another nickname";
			}
		}
	}

}
/*
 * Kontrola pomoci specialchars/Nastaveni vstupu do template souboru
 */

$smarty -> assign('error', $error);

$smarty -> assign('nickname', htmlspecialchars($nickname, ENT_QUOTES));
$smarty -> assign('firstname', htmlspecialchars($firstname, ENT_QUOTES));
$smarty -> assign('password', htmlspecialchars($password, ENT_QUOTES));
$smarty -> assign('password_again', htmlspecialchars($password_again, ENT_QUOTES));
$smarty -> assign('email', htmlspecialchars($email, ENT_QUOTES));
$smarty -> assign('phonenumber', htmlspecialchars($phonenumber, ENT_QUOTES));

/*
 * Rendering stranky
 */
$smarty -> display("register.tpl");
?>