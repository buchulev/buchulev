<?php

/*
 * php soubor pro kontrolu pomoci ajax'u
 */


require_once ('db_connection.php');

/*
 *  Kontrolujeme je-li tenoto nickname uz v systemu
 */

$query = $dbconn -> prepare('Select nickname from website_user where nickname=:nickname');
$query -> bindParam(':nickname', $_POST['nickname']);
$query -> execute();
if ($row = $query -> fetch()) {
	echo 'e';
}

?>