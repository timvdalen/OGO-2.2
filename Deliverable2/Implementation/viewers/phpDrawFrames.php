<?php
ini_set('memory_limit', '512M');
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
		print_r($object);
		$snapshot = $object->snapshot;
		$frameid = $object->framecount;
		$norows = count($snapshot);
		$nocols = count($snapshot[0]);
		$im = new Imagick();
		$im->newImage(50* $norows + 1, 50* $nocols + 1, new ImagickPixel('white'));
		$draw = new ImagickDraw();
		$draw->setStrokeColor(new ImagickPixel('black'));
		$rowid = 0;
		foreach($snapshot as $row){
			$cellid = 0;
			foreach($row as $cell){
				//print_r($cell);
				switch($cell->type){
					case "xtremerobotgames.HomeTile":
						$home = new Imagick('images/home.png');
						$hdraw = new ImagickDraw();
						$hdraw->setFillColor($colors[$cell->owner]);
						$hdraw->setStrokeColor($colors[$cell->owner]);
                                        	$hdraw->rectangle(10, 19, 38, 47);
						$home->drawImage($hdraw);
						$draw->composite(imagick::COMPOSITE_DEFAULT, $cellid * 50 + 1, $rowid * 50 + 1, 49, 49, $home);
						break;
					case "xtremerobotgames.HintTile":
						$hint = new Imagick('images/hint.png');
						$draw->composite(imagick::COMPOSITE_DEFAULT, $cellid * 50 + 1, $rowid * 50 + 1, 49, 49, $hint);
						break;
					case "xtremerobotgames.BrokenRobotTile":
						$broken = new Imagick('images/broken.png');
                                                $draw->composite(imagick::COMPOSITE_DEFAULT, $cellid * 50 + 1, $rowid * 50 + 1, 49, 49, $broken);
						break;
					case "xtremerobotgames.ConveyorTile":
						$rot = substr($cell->rotation, 1, -3);
						$conveyor = new Imagick('images/conveyor.png');
						$conveyor->rotateImage(new ImagickPixel('none'), $rot);
                                        	$draw->composite(imagick::COMPOSITE_DEFAULT, $cellid * 50 + 1, $rowid * 50 + 1, 49, 49, $conveyor);
						break;
				}

				if(isset($cell->occupier)){
					$robot = new Imagick('images/robot.png');
					//Draw eyes
					$rdraw = new ImagickDraw();
					$rdraw->setStrokeColor($colors[$cell->occupier->id]);
					$rdraw->setStrokeWidth(1);
					$rdraw->line(18, 14, 21, 14);
					$rdraw->line(26, 14, 29, 14);
					$rdraw->line(17, 15, 21, 15);
					$rdraw->line(26, 15, 30, 15);
					$rdraw->line(17, 16, 21, 16);
					$rdraw->line(26, 16, 30, 16);
					$robot->drawImage($rdraw);
					$rot = substr($cell->occupier->rotation, 1, -3);
					$robot->rotateImage(new ImagickPixel('none'), 180);
					$draw->composite(imagick::COMPOSITE_DEFAULT, $cellid * 50 + 1, $rowid * 50 + 1, 48, 48, $robot);
				}
				$draw->line($cellid * 50, $rowid * 50 , $cellid * 50 + 50, $rowid * 50);
				$draw->line($cellid * 50, $rowid * 50 + 50, $cellid * 50 + 50, $rowid * 50 + 50);
				$draw->line($cellid * 50, $rowid * 50, $cellid * 50, $rowid * 50 + 50);
				$draw->line($cellid * 50 + 50, $rowid * 50,  $cellid * 50 + 50, $rowid * 50 + 50);
				//$im->annotateImage($draw, $cellid * 50 + 35,  $rowid * 50 + 35, 45, "r: $rowid, c: $cellid");
				$cellid++;
			}
			$rowid++;
		}
		$im->drawImage( $draw );

		$image = new Imagick('images/arena.png');
		//$image->newImage(50* $norows + 1, 50* $nocols + 1 + 20, new ImagickPixel('white'));
		$drawIm = new ImagickDraw();
		$drawIm->composite(imagick::COMPOSITE_DEFAULT, 200, 198, 50* $norows + 1, 50* $nocols + 1, $im);
		$drawIm->setFont("arial.ttf");
		$drawIm->setFontSize(33);
		$drawIm->annotation(290, 110, "Turn #$frameid");
		$drawIm->setFontSize(22);
		$drawIm->annotation(290, 135, "This match is brought to you by:");
		$drawIm->annotation(290, 155, "Sanders Frozen Fishstick Company");
		$drawIm->setFontSize(17);
		$drawIm->annotation(290, 180, "Keeping it fine, since '09!");
		$image->drawImage($drawIm);

		$image->setImageFormat("png");
		file_put_contents("frames/frame$frameid.png", $image);

		if(isset($object->won)){
			$won = $object->won;
			
		}
	}
?>
