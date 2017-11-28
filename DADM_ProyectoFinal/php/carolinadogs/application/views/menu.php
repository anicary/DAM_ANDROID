<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>PET WORLD MENU</title>
	<link rel="shortcut icon" href="<?php echo base_url(); ?>images/tec.ico">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
	<meta name="description" content="Sistema para el seguimiento en el aula del instituto tecnologico de tepic.">
	<meta name="author" content="Fernando Manuel Avila Cataño">
	<meta name="theme-color" content="#FFFFFF">
	<meta name="msapplication-navbutton-color" content="#FFFFFF">
	<meta name="apple-mobile-web-app-status-bar-style" content="white">
	<link href="<?php echo base_url(); ?>css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="<?php echo base_url(); ?>css/font-awesome.css" type="text/css" rel="stylesheet" />
	<link href="<?php echo base_url(); ?>css/animate.css" type="text/css" rel="stylesheet" />
	<link href="<?php echo base_url(); ?>css/fontello.css" type="text/css" rel="stylesheet" />
	<link href="<?php echo base_url(); ?>css/perritoslogin.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col-md-9">
								<h4 class="card-title"><i class="fa fa-server" aria-hidden="true"></i> MENU ADMINISTRACION</h4>
							</div>
							<div class="col-md-3">
								<a class="btn btn-danger"  href="<?php echo base_url(); ?>index.php/Sistema/salir"><i class="fa fa-sign-out" aria-hidden="true"></i> CERRAR SESION</a>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<a href="<?php echo base_url(); ?>index.php/Sistema/usuarios">
									<div class="card">
										<div class="card-body">
											<i class="fa fa-users" aria-hidden="true"></i> VER USUARIOS REGISTRADOS
										</div>
									</div></a>
								</div>
								<div class="col-md-6">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</body>
	<script type="text/javascript" src="<?php echo base_url(); ?>js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="<?php echo base_url(); ?>js/tether.min.js"></script>
	<script type="text/javascript" src="<?php echo base_url(); ?>js/popper.min.js"></script>
	<script type="text/javascript" src="<?php echo base_url(); ?>js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<?php echo base_url(); ?>js/jquery.validate.js"></script>
	</html>
