<?php

require_once('smarty/libs/Smarty.class.php');

/*
 * Inicializace smarty
 */
$smarty = new Smarty();

/*
 *  Inicializace slozky pro sablony/compilace
 *  Budeme šablony renderovat pomoci $smarty->display("jmeno_sablony.tpl");
 */
 
$smarty->setTemplateDir('view');
$smarty->setCompileDir('tmp');

?>