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
	<link href="<?php echo base_url(); ?>css/dataTables.bootstrap4.min.css" type="text/css" rel="stylesheet" />
	<link href="<?php echo base_url(); ?>css/responsive.bootstrap4.min.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div class="">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col-md-7">
								<h4 class="card-title">LISTA DE RAZAS</h4>
							</div>
							<div class="col-md-5">
								<a class="btn btn-danger"  href="<?php echo base_url(); ?>index.php/Sistema/salir"><i class="fa fa-sign-out" aria-hidden="true"></i> <?php echo "".$this->session->userdata('nombre'); ?>  CERRAR SESION</a>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="card">
									<div class="card-body">
										<table id="tablausuarios" class="table table-striped table-bordered  table-sm dt-responsive " cellspacing="0" width="100%">
											<thead>
												<tr>
													<th>RAZA</th>
													<th>OPCIONES</th>
												</tr>
											</thead>
											<tbody>
												<?php
												if ($RAZAS) {
													foreach ($RAZAS as $filas => $valores) {
														?>
														<tr>
															<td colspan=""><?php echo $valores->nombre_raza; ?> </td>
															<td colspan="">
																<div class="btn-group btn-block">
																	<button type="button" class="btn btn-primary btn-block dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
																		<i class="fa fa-bars" aria-hidden="true"></i> OPCIONES
																	</button>
																	<div class="dropdown-menu">
																		<a class="dropdown-item" href="<?php echo base_url(); ?>index.php/Sistema/editar_raza/<?php  echo $valores->idrazamascota;?>"><i class="fa fa-pencil-square-o colorEditar" aria-hidden="true"></i> EDITAR INFORMACION</a>
																	</div>
																</div>
															</td>
														</tr>
														<?php
													}
												}else {
												}
												?>
											</tbody>
										</table>
									</div>
								</div>
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
<script type="text/javascript" src="<?php echo base_url(); ?>js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<?php echo base_url(); ?>js/dataTables.bootstrap4.min.js"></script>
<script type="text/javascript" src="<?php echo base_url(); ?>js/dataTables.responsive.min.js"></script>
<script type="text/javascript" src="<?php echo base_url(); ?>js/responsive.bootstrap4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#tablausuarios').DataTable({
		"language": {
			"url": "<?php echo base_url(); ?>js/datatables/usuarios.json"
		},
		"order": [[ 4, "desc" ]]
	});
} );
</script>
</html>
