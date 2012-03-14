<?php
	//Player settings
	$colors = array("blue", "red", "yellow");

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
		$norows = count($snapshot);
		$nocols = count($snapshot[0]);
		$im = new Imagick();
		$im->newImage(100* $norows + 1, 100* $nocols + 1, new ImagickPixel('white'));
		$draw = new ImagickDraw();
		$draw->setStrokeColor(new ImagickPixel('black'));
		$rowid = 0;
		foreach($snapshot as $row){
			$cellid = 0;
			foreach($row as $cell){
				//print_r($cell);
				if(isset($cell->occupier)){
					$draw->setStrokeColor($colors[$cell->occupier]);
					$draw->setStrokeWidth(5);
					$draw->line($cellid * 100, $rowid * 100, $cellid * 100 + 100, $rowid * 100 + 100);
					$draw->line($cellid * 100 + 100, $rowid * 100, $cellid * 100, $rowid * 100 + 100);
					$draw->setStrokeColor('black');
					$draw->setStrokeWidth(1);
				}
				if($cell->type == "HomeTile"){
					$draw->setFillColor($colors[$cell->owner]);
                                        $draw->rectangle($cellid * 100, $rowid * 100, $cellid * 100 + 100, $rowid * 100 + 100);
					$draw->setFillColor('black');
				}

				$draw->line($cellid * 100, $rowid * 100 , $cellid * 100 + 100, $rowid * 100);
				$draw->line($cellid * 100, $rowid * 100 + 100, $cellid * 100 + 100, $rowid * 100 + 100);
				$draw->line($cellid * 100, $rowid * 100, $cellid * 100, $rowid * 100 + 100);
				$draw->line($cellid * 100 + 100, $rowid * 100,  $cellid * 100 + 100, $rowid * 100 + 100);
				$im->annotateImage($draw, $cellid * 100 + 35,  $rowid * 100 + 35, 45, "r: $rowid, c: $cellid");
				$cellid++;
			}
			$rowid++;
		}
		$im->drawImage( $draw );
		$im->setImageFormat("png");
		file_put_contents("frames/frame$frameid.png", $im);
	}
?>
