<?php
require_once ('header.php');
session_start();

  
 /*
  *  1. Vymazeme user'a z SESSION(odhlašeni)
  *  2 .Redirect
  */
  
 
 
$_SESSION['user'] = NULL;
header("Location:index.php");
?>