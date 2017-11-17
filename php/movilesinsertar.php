<?php
$modelo =$_POST["modelo"];
$fabricante =$_POST["fabricante"];
$descripcion =$_POST["descripcion"];
$precio =$_POST["precio"];
$ram =$_POST["ram"];

$con = mysqli_connect("localhost","id165952_dadm","quesito02");
if (!$con) {
  echo "ERROR no se pudo conectar con mysql";
  return;
}
$conBD = mysqli_selected_db($con,"id165952_dadm");
if (!$conBD) {
  echo "ERROR no se permitio acceso a BD id165952_dadm";
  return;
}

$SQL ="INSERT INTO MOVILES VALUES('$modelo','$fabricante','descripcion',$precio,$ram)";
$respuesta = mysqli_query($con,$SQL);
if ($respuesta) {
  echo "Se inserto correctamente el registro";
}else{
  echo "ERROR no se pudo insertar el registro";
}
 ?>
