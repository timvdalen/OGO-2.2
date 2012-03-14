<?php
	//Read input
	$input = array();
	$line = readline("");
	while($line != "EOF"){
		$input[] = $line;
		$line = readline("");
	}

	//Process afterwards
	/*
	I could do it while reading,
	but this will also work with
	the ImageMagick version.
	*/
	$allframes = array();
	foreach($input as $frame){
		$object = json_decode($frame);
		$allframes[] = $object;
	}
	$stringversion = json_encode($allframes);
	$stringversion = addslashes($stringversion);
	$html = <<<ENDHTML
<html>
	<script type="text/javascript">
		var jsonstring = "$stringversion";
		var frames = JSON.parse(jsonstring);
	</script>
	<!-- Hier de code om de frames te handlen includen -->
	<div id="wrapper">
		<canvas id="draw"></canvas>
	</div>
</html>
ENDHTML;
	file_put_contents("output.html", $html);
?>
