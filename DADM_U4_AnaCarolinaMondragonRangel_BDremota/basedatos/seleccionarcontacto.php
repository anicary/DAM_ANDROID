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
$resultado=mysqli_query($conexion,"SELECT * from Persona;");
if($resultado){
	 /*SI SE INSERTO*/
	 $datos = array();
	 $i=0;
	 while($renglon = mysqli_fetch_array($resultado))
	 {
			 $datos[$i] = $renglon;
			 $i++;
	 }
	 echo json_encode($datos);
}else{
	 /*NO SE INSERTO*/
	echo "ERROR";
}


?>
