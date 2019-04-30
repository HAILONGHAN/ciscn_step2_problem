<?php
$ua = $_SERVER['HTTP_USER_AGENT'];
if(strpos($ua,"Android")==false)die();
	$target = $_REQUEST[ 'ip' ];
	$target=trim($target);
	$substitutions = array(
		'&'  => '',
		';' => '',
		'|' => '',
		'-'  => '',
		'$'  => '',
		'('  => '',
		')'  => '',
		'`'  => '',
		'||' => '',
	);
	$target = str_replace( array_keys( $substitutions ), $substitutions, $target );
	$cmd = system( 'curl ' . $target );
	echo  "<pre>{$cmd}</pre>";
	
?>
