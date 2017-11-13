<?php
/* DATOS DEL SERVIDOR */
$contrasna="Carolina21";
$usuario="caroli41_caro";
$host="198.91.81.4";
$base="caroli41_android";

/* CONECTANDO AL SERVIDOR */
$conexion=mysqli_connect($host,$usuario,$contrasna);
if(!$conexion){
	echo "La conexion fallo";
	return;
}
$selectBD=mysqli_select_db($conexion,$base);
if(!$selectBD){
	echo "Base de datos no encontrada";
	return;
}
/*OBTENER DATOS DEL ANDROD POST*/
$nombre=$_POST["nombre"];
$domicilio=$_POST["domicilio"];
$telefono=$_POST["telefono"];
$correo=$_POST["correo"];
/* INSERTAR EN LA BASE*/
$resultadoInsertar=mysqli_query($conexion,"SELECT FROM Persona values(0,'$nombre','$domicilio','$telefono','$correo')");
if($resultadoInsertar){
	 /*SI SE INSERTO*/
}else{
	 /*NO SE INSERTO*/
	echo "ERROR";
}
?>
