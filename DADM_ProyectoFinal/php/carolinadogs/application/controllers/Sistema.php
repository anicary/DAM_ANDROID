<?php
defined('BASEPATH') OR exit('No direct script access allowed');
class Sistema extends CI_Controller {
	function __construct() {
		parent::__construct();
		$this->load->model('Usuarios');
		$this->load->helper(array('url', 'form'));
		$this->load->library(array('session', 'form_validation'));
	}
	public function index()
	{
		$this->load->view('inicio');
	}
	public function inicio_sesion()
	{
		$correo_usuario = $this->input->post('correo');
		$contrasena= sha1($this->input->post('contrasena'));
		$datos=$this->Usuarios->loginusuario($correo_usuario,$contrasena);
		$this->Usuarios->actualizarultima_sesion($datos[0]->idusuarios,"".date('Y-m-d H:i:s'));
		redirect(base_url().'index.php/Sistema/menu_sistema');
	}
	public function menu_sistema()
	{
		$this->load->view('inicio');
	}
	public function registro_usuario()
	{
		if($this->input->post('nombre')!=""){
			$datos= array(
				'nombre' => $this->input->post('nombre'),
				'apellidos' => $this->input->post('apellidos'),
				'correo' => $this->input->post('correo'),
				'contrasena' => sha1($this->input->post('contrasena')),
				'sexo' =>"F",
				'tipo' => 2,
				'activo' => 1,
				'fecha_creacion' => date('Y-m-d H:i:s'),
				'estado' => "Nayarit",
				'municipio' =>"Tepic"
			);
			$correo=$this->input->post('correo');
			$contrasena=sha1($this->input->post('contrasena'));
			$this->Usuarios->insertarUsuario($datos);
			$datos=$this->Usuarios->loginusuario($correo,$contrasena);
			$this->Usuarios->actualizarultima_sesion($datos[0]->idusuarios,"".date('Y-m-d H:i:s'));
			echo json_encode($datos);
		}
		// $datos=$this->Usuarios->loginusuario('caro@mail.com',sha1("Carolina21"));
		// $this->Usuarios->actualizarultima_sesion($datos[0]->idusuarios,"".date('Y-m-d H:i:s'));
		// echo json_encode($datos);
	}
}
