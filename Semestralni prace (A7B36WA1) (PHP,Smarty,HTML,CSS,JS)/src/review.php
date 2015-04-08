<?php
session_start();

require_once ('header.php');
require_once ('functions.php');
require_once ('db_connection.php');

if (isset($_SESSION['user']))  {
	$smarty -> assign('user', $_SESSION['user']);
}
else {
	$smarty->assign('user',NULL);
}

/*
 * GET id recenze
 */
if (isset($_GET['review_id'])) {
	
	/*
	 *  Pošleme data do šablony
	 */
	$smarty -> assign('review', getReviewById($dbconn, $_GET['review_id']));
}

/*
 * Rendering stranky
 */
$smarty -> display('review.tpl');
?>