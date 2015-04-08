<?php


require_once ('header.php');
require_once ('db_connection.php');
require_once ('functions.php');

session_start();


/*
 * Proměne pro zachovavani uzivatelskeho vstupu
 */

$text = "";
$topic = "";

/*
 * Kontrola administratorských prav
 */

if ($_SESSION['user'] == NULL || $_SESSION['user']['isadmin'] == false) {
	header("Location:index.php");
	exit ;
}

/*
 * Odolnost reload'u stranky/ dvojitemu odesilani
 */

if (isset($_POST['submit'])) {

	$text = $_POST['text'];
	$topic = $_POST['topic'];

	/*
	 * Přidani nove zaznamu do aktualit/novin
	 */

	addNews($dbconn, $_POST, $_SESSION['user']['nickname']);

	header("Location:index.php");
}

/*
 * Kontrola pomoci specialchars/Nastaveni vstupu do template souboru
 */

$smarty -> assign('text', htmlspecialchars($text, ENT_QUOTES));
$smarty -> assign('topic', htmlspecialchars($topic, ENT_QUOTES));

/*
 * Rendering stranky
 */
$smarty -> display('add_news.tpl');
?>