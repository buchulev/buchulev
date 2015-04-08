<?php



require_once ('header.php');
require_once ('db_connection.php');
require_once ('functions.php');
session_start();

$error ="";
/*
 * Proměne pro zachovavani uzivatelskeho vstupu
 */

$text = "";
$title = "";
$name = "";

/*
 * Kontrola je-li uzivatel zalogovan
 */

if ($_SESSION['user'] == NULL) {
	header("Location:index.php");
	exit ;
}

/*
 * Odolnost reload'u stranky/dvojitemu odesilani
 */

if (isset($_POST['submit'])) {
	
    $nickname = $_SESSION['user']['nickname'];
	$text = $_POST['text'];
	$title = $_POST['title'];
	$name = $_POST['name'];

	/*
	 * Přidani nove recenze
	 */
	if(createReview($dbconn, $_POST,$nickname)){
     header("Location:index.php");
	 exit ;	
	}
	
	else {
	$error.="Review with this name already exitst";	
	}
	
}

/*
 * Kontrola pomoci specialchars/Nastaveni vstupu do template souboru
 */
$smarty -> assign('error', htmlspecialchars($error, ENT_QUOTES));
$smarty -> assign('text', htmlspecialchars($text, ENT_QUOTES));
$smarty -> assign('title', htmlspecialchars($title, ENT_QUOTES));
$smarty -> assign('name', htmlspecialchars($name, ENT_QUOTES));

/*
 * Rendering stranky
 */

$smarty -> display('add_review.tpl');
?>