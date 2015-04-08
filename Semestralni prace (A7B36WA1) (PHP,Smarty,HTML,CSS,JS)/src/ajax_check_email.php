<?php
/*
 * php soubor pro kontrolu pomoci ajax'u
 */


require_once ('db_connection.php');

/*
 *  Kontrolujeme je-li tenoto email uz v systemu
 */
$query = $dbconn -> prepare('Select email from website_user where email=:email');
$query -> bindParam(':email', $_POST['email']);
$query -> execute();

if ($row = $query -> fetch()) {
	echo 'e';
} else
	echo '';
  ?>
