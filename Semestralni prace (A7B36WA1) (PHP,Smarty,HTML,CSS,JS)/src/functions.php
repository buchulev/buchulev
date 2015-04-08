<?php

/**
 * @package functions.php
 */


/**
 * function getNews
 *
 * @param PDO $dbconn aktualni pripojeni do db
 * @return fucntion getNumberOfPages pocet stranek potřeba na umisteni všech novin z DB
 * @author buchulev
 */
function getNews($dbconn) {

	$query = $dbconn -> prepare("Select count(id) from news");
	$query -> execute();
	$counter = 0;
	while ($row = $query -> fetch(PDO::FETCH_NUM)) {
		$counter = $row[0];

	}

	return getNumberOfPages($counter, 5);
}

/**
 * function getNewsByPageNumber
 *
 * @return array() $news  Vrati pole všech novin na strance čislo $page_number
 * @param  PDO $dbconn aktualni pripojeni do db
 * @param  int $limit limit novin na strance
 * @param  int $page_number limit novin na strance
 * @author buchulev
 */
function getNewsByPageNumber($dbconn, $limit, $page_number) {
	$news = array();

	$array_counter = 0;

	$query = $dbconn -> prepare("Select * from news ORDER BY news_date DESC LIMIT :o, :limit ");
	$o = ($page_number - 1) * $limit;
	$query -> bindParam(':o', $o,PDO::PARAM_INT);
	$query -> bindParam(':limit', $limit,PDO::PARAM_INT);
	$query -> execute();

	while ($row = $query -> fetch(PDO::FETCH_ASSOC)) {
		$news[$array_counter] = $row;
		$array_counter++;
		

	}
	return $news;

}
/**
 * function getNumberOfPagesGL
 *
 * @return fucntion getNumberOfPages pocet stranek pro recenze 
 * @param  PDO $dbconn aktualni pripojeni do db
 * @param  string $letter pismeno podle ktereho hleda hry
 * @author buchulev
 */
function getNumberOfPagesGL($dbconn, $letter) {
	$array = array();

	$query = $dbconn -> prepare("Select name from game natural join review where name LIKE :n or name LIKE :nl");

	$letterU = $letter . '%';
	$letterL = strtolower($letter) . '%';
	$query -> bindParam(':n', $letterU);
	$query -> bindParam(':nl', $letterL);

	$result = $query -> execute();
	$counter = 0;
	while ($row = $query -> fetch(PDO::FETCH_NUM)) {
		$array[$counter] = $row[0];
		$counter++;

	}

	return getNumberOfPages(count($array), 15);
}
/**
 * function getNumberOfPagesGL
 *
 * @return int $number_of_page pocet stranek pro recenze 
 * @param  PDO $dbconn aktualni pripojeni do db
 * @param  string $word kličevy řetezec pro vyhledavani
 * @author buchulev
 */
function getNumberOfPagesGW($dbconn, $word) {
	$array = array();
	$query = $dbconn -> prepare("Select name from game natural join review where name LIKE :n or name LIKE :nl");

	$wordU = '%' . $word . '%';
	$wordL = '%' . strtolower($word) . '%';

	$query -> bindParam(':n', $wordU);
	$query -> bindParam(':nl', $wordL);

	$result = $query -> execute();
	$counter = 0;

	while ($row = $query -> fetch(PDO::FETCH_NUM)) {
		$array[$counter] = $row[0];
		$counter++;
	}

	return getNumberOfPages(count($array), 15);
}

/**
 * function getGamesByLetter
 *
 * @return array() $array vrati recenze na hry, podle pismenka 
 * @param  PDO $dbconn aktualni pripojeni do db
 * @param  string $letter pismeno podle ktereho hleda hry
 * @param int $page_number čislo stranky
 * @author buchulev
 */
function getGamesByLetter($dbconn, $letter, $page_number) {
	$array = array();

	$query = $dbconn -> prepare("Select * from game inner join review on(game.id = review.game)  where name LIKE :n or name LIKE :nl ORDER BY name ASC LIMIT :o, 15");
	$o = ($page_number - 1) * 15;

	$letterU = $letter . '%';
	$letterL = strtolower($letter) . '%';
	$query -> bindParam(':n', $letterU);
	$query -> bindParam(':nl', $letterL);
	$query -> bindParam(':o', $o,PDO::PARAM_INT);
	$result = $query -> execute();
	$counter = 0;
	while ($row = $query -> fetch(PDO::FETCH_NUM)) {
		$array[$counter] = $row;
		$counter++;

	}

	return $array;
}

/**
 * function getGamesByWord
 *
 * @return array() $array vrati recenze na hry, podle řetezce
 * @param  PDO $dbconn aktualni pripojeni do db
 * @param  string $word kličovy řetezec pro vyhledavani
 * @param int $page_number čislo stranky
 * @author buchulev
 */
function getGamesByWord($dbconn, $word, $page_number) {
	$array = array();
	$query = $dbconn -> prepare("Select * from game  inner join review on(game.id = review.game)  where name LIKE :n or name LIKE :nl ORDER BY name ASC LIMIT :o, 15");
	$o = ($page_number - 1) * 15;

	$wordU = '%' . $word . '%';
	$wordL = '%' . strtolower($word) . '%';

	$query -> bindParam(':n', $wordU);
	$query -> bindParam(':nl', $wordL);
	$query -> bindParam(':o', $o ,PDO::PARAM_INT);

	$result = $query -> execute();
	$counter = 0;

	while ($row = $query -> fetch(PDO::FETCH_NUM)) {
		$array[$counter] = $row;
		$counter++;
		
	}

	return $array;
}
/**
 * function getNumberOfPages
 *
 * @return int $number oběcny počet stranek pro variablini počet elementu a limit
 * @param  int $limit limit elementu na strance
 * @param int $number počet elementu
 * @author buchulev
 */
function getNumberOfPages($number, $limit) {

	if ($number % $limit != 0) {
		$number = (int)$number / $limit + 1;

	} else {
		if ($number == 0) {
			$number = 1;
		} else {
			$number = (int)$number / $limit;
		}
	}
	return (int)$number;
}


/**
 * function ckeckNickname
 *
 * @return boolean je-li uzivatel s takovym nickname'm existuje nebo ne
 * @param  string $nickname jmeno
 * @author buchulev
 */
function checkNickname($dbconn, $nickname) {
	$query = $dbconn -> prepare("SELECT nickname FROM website_user WHERE nickname=:nickname");
	$query -> bindParam(':nickname', $nickname);
	$query -> execute();

	if ($row = $query -> fetch()) {

		return false;
	} else
		return true;
}

/**
 * function ckeckEmail
 *
 * @return boolean je-li uzivatel s takovym email'em existuje nebo ne
 * @param  string $email emailova adressa
 * @author buchulev
 */
function checkEmail($dbconn, $email) {
	$query = $dbconn -> prepare("SELECT nickname FROM website_user WHERE email=:email");
	$query -> bindParam(':email', $email);
	$query -> execute();

	if ($row = $query -> fetch()) {

		return false;
	} else
		return true;
}
/**
 * function createUser
 *
 * @return void zapiše uživatele do db
 * @param  PDO $dbconn aktualni pripojeni do db
 * @param array $data_array uživatelsky vstup
 * @author buchulev
 */
function createUser($dbconn, $data_array) {
	$salt = makeSalt();
	$data_array['password'] = cryptPassword($data_array['password'], $salt);

	$query = $dbconn -> prepare("INSERT INTO website_user(nickname,firstname,password,phonenumber,email,salt) VALUES(:nickname,:firstname,:password,:phonenumber,:email,:salt)");

	$query -> bindParam(':nickname', $data_array['nickname']);

	$query -> bindParam(':firstname', $data_array['firstname']);

	$query -> bindParam(':password', $data_array['password']);

	$query -> bindParam(':phonenumber', $data_array['phonenumber']);

	$query -> bindParam(':email', $data_array['email']);

	$query -> bindParam(':salt', $salt);



	$query -> execute();
	

}
/**
 * function cryptPassword
 *
 * @return function crypt() hash osoleneho hesla 
 * @param  string $salt sul
 * @param string $password heslo
 * @author buchulev
 */
function cryptPassword($password, $salt) {
	return crypt($password, $salt);
}
/**
 * function makeSalt
 *
 * @return string $salt vrati sul 
 * @author buchulev
 */
function makeSalt() {
	$salt = substr(md5(uniqid(rand(), true)), 0, 5);
	return $salt;
}

/**
 * function addNews
 *
 * @return void zapisuje noviny do DB
 * @param  PDO $dbconn aktualni pripojeni do db
 * @param array $data_array uživatelsky vstup
 * @param string $nickname jmeno uzivatele
 * @author buchulev
 */
function addNews($dbconn, $data_array, $nickname) {

	$query = $dbconn -> prepare("INSERT INTO news VALUES(:id,:topic,:text,:date,:nickname)");
	$date = date('Y-m-d');
	$query -> bindParam(':topic', $data_array['topic']);
	$query -> bindParam(':text', $data_array['text']);
	$query -> bindParam(':id', getTableLastId($dbconn, 'news'));
	$query -> bindParam(':date', $date);
	$query -> bindParam(':nickname', $nickname);

	$query -> execute();
}
/**
 * function getTableLastId
 *
 * @return int $result vrati posledni ID v tabulce
 * @param  PDO $dbconn aktualni pripojeni do db
 * @param  string $table nazev tabulky
 * @author buchulev
 */
function getTableLastId($dbconn, $table) {
	$sql = 'Select max(id) from ' . $table;

	$result = 0;
	foreach ($dbconn->query($sql) as $row) {
		$result = $row[0];
	}
	return ($result + 1);
}
/**
 * function getGameId
 *
 * @return int $id  vrati id hry
 * @param  PDO $dbconn aktualni pripojeni do db
 * @param  string $game_name nazev hry
 * @author buchulev
 */
function getGameId($dbconn, $game_name) {
	$id = -1;

	$query = $dbconn -> prepare('Select id from game where name=:name');
	$query -> bindParam(':name', $game_name);
	$result = $query -> execute();
    
	if ($row = $query -> fetch(PDO::FETCH_NUM)) {
		$id = $row[0];
		 
	}
    
	return $id;

}

/**
 * function createReview použiva funkce addReview
 *
 * @return void zapisuje recenze do DB
 * @param  PDO $dbconn aktualni pripojeni do db
 * @param array $data_array uživatelsky vstup
 * @param string $nickname jmeno uzivatele
 * @author buchulev
 */
function createReview($dbconn, $data_array,$nickname) {
if(checkReview($dbconn, $data_array['title'])) {
	if (getGameId($dbconn, $data_array['name']) == -1) {
		addGame($dbconn, $data_array['name']);
	}

	addReview($dbconn, getGameId($dbconn, $data_array['name']), $data_array,$nickname);
return true;
}
else return false;
}
/**
 * function addGame
 *
 * @return void zapis hry do DB
 * @param  PDO $dbconn aktualni pripojeni do db
 * @param  string $game_name nazev hry
 * @author buchulev
 */
function addGame($dbconn, $game_name) {
	$query = $dbconn -> prepare('Insert Into game VALUES(:game_name,:game_id)');
	$query -> bindParam(':game_name', $game_name);
	$query -> bindParam(':game_id', getTableLastId($dbconn, "game"));
	$query -> execute();
}

/**
 * function addReview
 *
 * @return void zapisuje recenze do DB
 * @param  PDO $dbconn aktualni pripojeni do db
 * @param array $data_array uživatelsky vstup
 * @param string $nickname jmeno uzivatele
 * @param int $game_id id hry
 * @author buchulev
 */
function addReview($dbconn, $game_id, $data_array,$nickname) {
	$query = $dbconn -> prepare("Insert Into review VALUES(:id,:title,:text,:game_id,:nickname)");
	$query -> bindParam(':id', getTableLastId($dbconn, "review"));
	$query -> bindParam(':title', $data_array['title']);
	$query -> bindParam(':text', $data_array['text']);
	$query -> bindParam(':game_id', $game_id);
	$query -> bindParam(':nickname', $nickname);
	$query -> execute();
}

/**
 * function checkReview
 *
 * @return boolean je-li recenze s takovym jmenem existuje nebo ne
 * @param  string $review_name jmeno recenze
 * @param  PDO $dbconn aktualni pripojeni do db
 * @author buchulev
 */
 
function checkReview($dbconn,$review_name) {
	$query = $dbconn -> prepare("Select * from review where title=:title");
	$query -> bindParam(':title',$review_name);
	$query -> execute();
	if($row = $query->fetch()) return false;
	else return true;
}

/**
 * function getReviewById
 *
 * @return array() $result vrati recenze podle id
 * @param  PDO $dbconn aktualni pripojeni do db
 * @param  int $id id recenze
 * @author buchulev
 */
function getReviewById($dbconn,$id) {

	$query = $dbconn ->prepare("Select * From review where id=:id");
	$query->bindParam(':id',$id);
	$query->execute();
	
	$result = $query->fetch(PDO::FETCH_NUM);
	return $result;
}
?>