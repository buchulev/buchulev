<?php

require_once ('header.php');
require_once ('functions.php');
require_once ('db_connection.php');

session_start();
$page_number = 0;

/*
 * dostaneme aktualni url + GET hodnoty
 */
$actual_url = basename($_SERVER['REQUEST_URI']);

/*
 * Auomaticky wrap'uje každy vstup do šablony pomoci htmlspecialchars
 */

$smarty -> escape_html = true;

/*
 *  Kontrola je-li uzivatel byl zalogovan
 */

if (isset($_SESSION['user'])) {
	$smarty -> assign('user', $_SESSION['user']);
} else {
	$smarty -> assign('user', NULL);
}

/*
 * Vyhledavani her na ktere byli udelane recenze . Podle pismena kterym se začina nazev hry
 */

if (isset($_GET['letter'])) {

	if (isset($_GET['page_number'])) {
		$page_number = $_GET['page_number'];
	} else {
		$page_number = 1;
	}

	$smarty -> assign('number_of_pages', getNumberOfPagesGL($dbconn, $_GET['letter']));
	$smarty -> assign('games', getGamesByLetter($dbconn, $_GET['letter'], $page_number));

}

/*
 * Vyhledavani her na ktere byli udelane recenze . Podle kličevych řetezců v nazvu her
 */

else {

	if (isset($_GET['page_number'])) {
		$page_number = $_GET['page_number'];
	} else {
		$page_number = 1;
	}

	if (isset($_GET['word'])) {
		$smarty -> assign('number_of_pages', getNumberOfPagesGW($dbconn, $_GET['word']));
		$smarty -> assign('games', getGamesByWord($dbconn, $_GET['word'], $page_number));
	} else {

	}

}

if(isset($_GET['word']) == false && isset($_GET['letter']) == false) {
header("Location:index.php");
exit;
}


$smarty -> assign('actual_url', $actual_url);

/*
 * Rendering stranky
 */

$smarty -> display('games.tpl');
?>