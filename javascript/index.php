<html><head>
<title>Raphael Test</title>    
<style type="text/css">
body 
{ 
    background-color:white; 
    text-align:center;
    margin-left:auto;
	margin-right:auto;
    font-family:Arial, Helvetica, sans-serif; 
    font-size:13px; 
    color:#15422f;
}

.labels
{
    color:#15422f;
}
#header
{
	text-align:left; 
	font-size:14px; 
	padding:5px; 
	color:white; 
	width:1000px;
}
#header a
{
    color:White;
}
#header a:hover
{
    text-decoration:underline;
}
#main_content
{
    width:1000px;
    background-color:white;
	border-left-color:#031c36; 
	border-left-style:solid;
	border-right-color:#031c36 ; 
	border-right-style:solid;
    height:auto; 
}
#column1
{
	position: fixed;
    top: 30px;
    right: 5px;
    width:627px; 
	height:auto;
    padding:20px;
	text-align:left;
	background-color:white;
	float:right;
 }
#column2
{
    width:313px; 
	height:auto;
    padding:10px;
	text-align:left;
	float:left; 
	background-color:white; 
	color:black;
}
#column2_interior
{
    width:333px; 
	height:auto;
    text-align:left;
	float:left; 
	background-color:white; 
	color:black;
 }
#column2_content
{
    width:313px; 
	height:auto;
    padding:10px;
	text-align:left;
	float:left
	background-color:white; 
	color:black;
 }
#column2 a:link
{
	color:#124272;
}
#column2 a
{
	color:#124272;
}
#column2 a:hover
{
	text-decoration:underline; 
}
#column2_content a:link
{
	color:#124272;
}
#column2_content a
{
	color:#124272;
}
#column2_content a:hover
{
	text-decoration:underline; 
}
.headline
{
    color:#124272; 
	font-size:20px;
}
.clear
{
	clear: both;
}
</style>

<script src="raphael.js" mce_src="raphael.js" type="text/javascript" charset="utf-8"></script>
<script src="jquery-1.11.3.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>

<body>
<div id="main_content">
<div id="column2">
<?php
$imgSize = getimagesize("finger.jpg");
//echo "width =".$imgSize[0];
?>


<script language="javascript" type="text/javascript">
var deletePoint = false;
var newPoints =[];
//creates drawable canvas and loads and scales the image.
<?php
echo("var paper = Raphael(0, 0,". "700" . ", " . "700".");");
echo ("var img = paper.image('finger.jpg', 0, 0,". "700" . ", " . "700".");");
?>
var rectangles = paper.set();
//img.scale(.5);

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



img.node.onclick = function (e) {
	if(!document.getElementById("delete").checked){
		var x = e.clientX;
		var y = e.clientY;
		rectangles.push(paper.rect(x,y,10,10));
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
<div id="column1">
<form action="submitted.php" method="GET">
Delete<input type="checkbox" id ="delete" value = "Delete">
<input type ="submit" value = "Submit">
</form>
</div>
</div>
</body></html>
