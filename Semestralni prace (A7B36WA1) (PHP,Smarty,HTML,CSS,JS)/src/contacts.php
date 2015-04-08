<?php

require_once ('header.php');
require_once ('functions.php');
require_once ('db_connection.php');

session_start();


/*
 *  Kontrola je-li uzivatel byl zalogovan
 */

if (isset($_SESSION['user'])) {
	$smarty -> assign('user', $_SESSION['user']);
} else {
	$smarty -> assign('user', NULL);
}





/*
 * Rendering stranky
 */

$smarty -> display('contacts.tpl');
?>