<?php


require_once ('header.php');
require_once ('functions.php');
require_once ('db_connection.php');

$error = "";
$salt ="";

session_start();

/*
 * Proměne pro zachovavani uzivatelskeho vstupu
 */

$nickname = "";

/*
 * Odolnost reload'u stranky/ dvojitemu odesilani
 */

if (isset($_POST['submit'])) {

	if (empty($_POST['nickname']) || empty($_POST['password'])) {

		$error .= "One or both fields are empty";
	} else {
		$nickname = $_POST['nickname'];
		$password = $_POST['password'];

		$query = $dbconn -> prepare("SELECT salt FROM website_user WHERE nickname=:nickname");
		$query -> bindParam(':nickname', $_POST['nickname']);
		$query -> execute();

		if ($row = $query -> fetch()) {
			$salt = $row[0];


		} else {

		}

		$password_salted = cryptPassword($_POST['password'], $salt);

		$query1 = $dbconn -> prepare("Select * From website_user where nickname=:nickname and password=:password");
		$query1 -> bindParam(':nickname', $_POST['nickname']);
		$query1 -> bindParam(':password', $password_salted);
		$query1 -> execute();

		if ($row1 = $query1 -> fetch()) {
			$_SESSION['user'] = $row1;
			header('Location: index.php');
			exit ;

		} else
			$error .= "Wrong nickname or password";
	}

}

/*
 * Kontrola pomoci specialchars/Nastaveni vstupu do template souboru
 */

$smarty -> assign('nickname', htmlspecialchars($nickname, ENT_QUOTES));
$smarty -> assign('error', $error);
/*
 * Rendering stranky
 */
$smarty -> display("login.tpl");
?>