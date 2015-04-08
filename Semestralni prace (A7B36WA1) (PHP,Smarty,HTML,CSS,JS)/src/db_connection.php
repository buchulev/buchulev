<?php
/*
 *  Pripojeni do databaze
 */
try {
$dbconn = new PDO('mysql:host=localhost;dbname=buchulev','buchulev','a7b36wa1');
}
catch (PDOException $e) {
    print "Error!: " . $e->getMessage() . "</br>";
    die();
}
?>