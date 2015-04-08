<?php

require_once ('header.php');
require_once ('functions.php');
require_once ('db_connection.php');

session_start();
/*
 * Auomaticky wrap'uje každy vstup do šablony pomoci htmlspecialchars
 */
$smarty->escape_html = true;

/*
 * Na index.php se zobrazuji noviny/aktuality
 * Default'ně chceme aby zobrazili nejnovejši noviny => nastavime cislo stranky na 1
 */ 
 
 /*
  *  druhej argument funkce getNewsByPageNumber určuje sql limit zaznamu na stranku
  */
if(isset($_GET['page_number'])) {
	$smarty -> assign('news', getNewsByPageNumber($dbconn,5,$_GET['page_number']));
}

else {
	$smarty -> assign('news', getNewsByPageNumber($dbconn,5,1));
}

/*
 * Kontrola administratorských prav
 */
if (isset($_SESSION['user'])) 
{
	$smarty -> assign('user', $_SESSION['user']);
	
}

else {
	$smarty -> assign('user', NULL);
}

	

/*
 * funkce getNews se vrati pocet stranek potřeba na celkovy počet novin v DB
 * defoltne podle sql limit = 5, da se změnit jen přimo v function.php
 */
 
$smarty -> assign('number_of_pages', getNews($dbconn));

/*
 * Rendering stranky
 */
 
$smarty -> display('index.tpl');


?>