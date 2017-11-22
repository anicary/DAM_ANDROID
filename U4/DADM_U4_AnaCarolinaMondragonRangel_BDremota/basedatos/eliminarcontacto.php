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
$idPersona=$_POST["idPersona"];
/* INSERTAR EN LA BASE*/
$resultado=mysqli_query($conexion,"DELETE FROM Persona WHERE idPersona=$idPersona;");
if($resultado){
}else{
	 /*NO SE INSERTO*/
	echo "ERROR";
}


?>
