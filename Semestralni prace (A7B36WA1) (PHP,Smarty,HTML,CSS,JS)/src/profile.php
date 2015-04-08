<?php
session_start();


require_once ('header.php');
require_once ('functions.php');
require_once ('db_connection.php');

/*
 * Auomaticky wrap'uje každy vstup do šablony pomoci htmlspecialchars
 */
$smarty -> escape_html = true;

/*
 *  Kontrola je-li uzivatel byl zalogovan
 */
if (isset($_SESSION['user'])) {

	$smarty -> assign('user', $_SESSION['user']);
	/*
	 * Rendering stranky
	 */
	$smarty -> display('profile.tpl');
} else {

	header("Location:login.php");
}
?>