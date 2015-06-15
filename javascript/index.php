<html><head>
<title>Raphael Test</title>    


<script src="raphael.js" mce_src="raphael.js" type="text/javascript" charset="utf-8"></script>
<script src="jquery-1.11.3.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>


<body>
<div>
<?php
$imgSize = getimagesize("finger.jpg");
//echo "width =".$imgSize[0];
?>


<script language="javascript" type="text/javascript">
var deletePoint = false;
var newPoints =[];
//creates drawable canvas and loads and scales the image.
<?php
echo("var paper = Raphael(30, 30,". $imgSize[0] . ", " . $imgSize[1].");");
echo ("var img = paper.image('finger.jpg', 30,30,". $imgSize[0] . ", " . $imgSize[1].");");
?>
var rectangles = paper.set();
img.scale(.5);

function setListeners(rect) {
	rectangles[rect].node.onclick = function () {
		if(document.getElementById("delete").checked){
			rectangles[rect].remove();
		}
	}
	rectangles[rect].node.drag(dragStart(),dragging(e),dragEnd(e));
}

function dragStart(){
}
function dragging(e){
	rectangles[rect].attr("x", e.clientX, "y", e.clientY);
}
function dragEnd(e){
	rectangles[rect].attr("x", e.clientX, "y", e.clientY);
}
//Adds listerner to img If clicked creates square
img.node.onclick = function (e) {
	if(!document.getElementById("delete").checked){
		var x = e.clientX;
		var y = e.clientY;
		rectangles.push(paper.rect(x,y,15,15));
		rectangles[rectangles.length-1].attr("fill", "#f00");
		rectangles[rectangles.length-1].attr("stroke", "#fff");
		setListeners(rectangles.length-1);
		var arrayText="test";
		for (var i = 0; i < newPoints.length(); i++) {
			arrayText= arrayText + newPoints[i] + "\n";
		}
		printf("test");
		document.getElementById("array").value= arrayText;
	}
}

//var img = paper.image("finger.jpg",10 50,500,500);

</script>
</div>
<div>
<form action="submitted.php" method="GET">
Delete<input type="checkbox" id ="delete" value = "Delete">
<input type ="submit" value = "Submit">
</form>
</div>
</body></html>
