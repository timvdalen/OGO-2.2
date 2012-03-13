<?php
	//Read input
	$input = array();
	$line = readline("");
	while($line != "EOF"){
		$input[] = $line;
		$line = readline("");
	}

	//Draw frames
	foreach($input as $frame){
		$object = json_decode($frame);
		$snapshot = $object->snapshot;
		$frameid = $object->framecount;
		$im = new Imagick();
		$im->newImage(330, 330, new ImagickPixel('white'));
		$draw = new ImagickDraw();
		$draw->setStrokeColor( new ImagickPixel( 'black' ) );
		$rowid = 0;
		foreach($snapshot as $row){
			$cellid = 0;
			foreach($row as $cell){
				$draw->line($cellid * 100 + $cellid * 10, $rowid * 100 + $rowid * 10 , $cellid * 100 + 100 + $cellid * 10, $rowid * 100 + 100 + $rowid * 10);
				$cellid++;
			}
			$rowid++;
		}
		$im->drawImage( $draw );
		$im->setImageFormat("png");
		file_put_contents("frames/frame$frameid.png", $im);
	}
?>
