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
				switch($cell->type){
					case "HomeTile":
						$draw->setFillColor($colors[$cell->owner]);
                                        	$draw->rectangle($cellid * 100, $rowid * 100, $cellid * 100 + 100, $rowid * 100 + 100);
						$draw->setFillColor('black');
						break;
					case "HintTile":
						$draw->setFillColor('gold');
						$draw->setStrokeColor('purple');
						$draw->circle($cellid * 100 + 50, $rowid * 100 + 50, $cellid * 100 + 25, $rowid * 100 + 25);
						$draw->annotation($cellid * 100 + 45, $rowid * 100 + 55, "H");
						$draw->setStrokeColor('black');
                                        	$draw->setStrokeWidth(1);
						$draw->setFillColor('black');
						break;
					case "BrokenRobotTile":
						$draw->setFillColor('black');
                                                $draw->rectangle($cellid * 100, $rowid * 100, $cellid * 100 + 100, $rowid * 100 + 100);
						break;
					case "ConveyorTile":
						switch($cell->rotation){
							case "R0DEG":
								$draw->line($cellid * 100 + 50, $rowid * 100, $cellid * 100 + 50, $rowid * 100 + 100);
								$draw->line($cellid * 100, $rowid * 100 + 50, $cellid * 100 + 50, $rowid * 100);
								$draw->line($cellid * 100 + 100, $rowid * 100 + 50, $cellid * 100 + 50, $rowid * 100);
								break;
							case "R180DEG":
								$draw->line($cellid * 100 + 50, $rowid * 100, $cellid * 100 + 50, $rowid * 100 + 100);
								$draw->line($cellid * 100, $rowid * 100 + 50, $cellid * 100 + 50, $rowid * 100 + 100);
								$draw->line($cellid * 100 + 100, $rowid * 100 + 50,  $cellid * 100 + 50, $rowid * 100 + 100);
								break;
							case "R90DEG":
								$draw->line($cellid * 100, $rowid * 100 + 50, $cellid * 100 + 100, $rowid * 100 + 50);
								$draw->line($cellid * 100 + 50, $rowid * 100, $cellid * 100 + 100, $rowid * 100 + 50);
								$draw->line($cellid * 100 + 50, $rowid * 100 + 100, $cellid * 100 + 100, $rowid * 100 + 50);
								break;
							case "R270DEG":
								$draw->line($cellid * 100, $rowid * 100 + 50, $cellid * 100 + 100, $rowid * 100 + 50);
								$draw->line($cellid * 100, $rowid * 100 + 50, $cellid * 100 + 50, $rowid * 100);
								$draw->line($cellid * 100, $rowid * 100 + 50, $cellid * 100 + 50, $rowid * 100 + 100);
								break;
						}
						break;
				}

				if(isset($cell->occupier)){
					$draw->setStrokeColor($colors[$cell->occupier]);
					$draw->setStrokeWidth(5);
					$draw->line($cellid * 100, $rowid * 100, $cellid * 100 + 100, $rowid * 100 + 100);
					$draw->line($cellid * 100 + 100, $rowid * 100, $cellid * 100, $rowid * 100 + 100);
					$draw->setStrokeColor('black');
					$draw->setStrokeWidth(1);
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

		$image = new Imagick();
		$image->newImage(100* $norows + 1, 100* $nocols + 1 + 20, new ImagickPixel('white'));
		$drawIm = new ImagickDraw();
		$drawIm->composite(imagick::COMPOSITE_DEFAULT, 0, 20, 100* $norows + 1, 100* $nocols + 1, $im);
		$drawIm->annotation(10, 14, "Frame #$frameid");
		$image->drawImage($drawIm);

		$image->setImageFormat("png");
		file_put_contents("frames/frame$frameid.png", $image);
	}
?>
