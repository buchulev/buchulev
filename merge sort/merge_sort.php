<?php

/**
 * function merge_sort
 *
 * @param $array pole
 * @param $sestupne chcete li sestupne řazení - TRUE, jinak(vzestupně) - FALSE
 * @return $array seřatene pole
 * @author Levan Buchukuri
 */
function merge_sort($array, $sestupne) {

	if (count($array) <= 1) {
		return $array;
	}

	$a_length = count($array);

	$mid_index = (int)($a_length / 2);

	$left_a = array();
	$right_a = array();

	for ($i = 0; $i < $mid_index; $i++) {
		$left_a[] = $array[$i];

	}

	for ($i = $mid_index; $i < $a_length; $i++) {
		$right_a[] = $array[$i];

	}

	$left_a = merge_sort($left_a, $sestupne);
	$right_a = merge_sort($right_a, $sestupne);

	return merge($left_a, $right_a, $sestupne);

}

/**
 *
 * function merge
 *
 * @param $left_a levá čast pole
 * @param $right_a pravá čast pole
 * @return $array vrati sloučene, seřazene pole
 * @author Levan Buchukuri
 */
function merge($left_a, $right_a, $sestupne) {

	$array = array();
	
	//index na iterace pro vysledne pole
	$array_index = 0;
	//index na iterace pro prave pole
	$l_index = 0;
	//index na iterace pro leve pole
	$r_index = 0;

	while ($l_index < count($left_a) && $r_index < count($right_a)) {
        
		//když $sestupne = TRUE
		if ($sestupne) {
			if ($left_a[$l_index] > $right_a[$r_index]) {

				$array[$array_index] = $left_a[$l_index];
				$l_index++;
				$array_index++;

			} else {
				$array[$array_index] = $right_a[$r_index];
				$r_index++;
				$array_index++;
			}
		} 
		//Jinak
		else {
			if ($left_a[$l_index] < $right_a[$r_index]) {

				$array[$array_index] = $left_a[$l_index];
				$l_index++;
				$array_index++;

			} else {
				$array[$array_index] = $right_a[$r_index];
				$r_index++;
				$array_index++;
			}

		}
	}
	//To co zustalo v levem pole
	while ($l_index < count($left_a)) {

		$array[$array_index] = $left_a[$l_index];
		$array_index++;
		$l_index++;

	}
    //To co zustalo v pravem pole
	while ($r_index < count($right_a)) {
		$array[$array_index] = $right_a[$r_index];
		$array_index++;
		$r_index++;

	}

	return $array;
}

/*
 *  Testovani
 */

$test_array = array();

for ($i = 0; $i < 50; $i++) {
	$test_array[$i] = $i;
}

shuffle($test_array);
var_dump($test_array);

echo "<br>/";

$sorted_array = merge_sort($test_array, FALSE);
var_dump($sorted_array);
?>