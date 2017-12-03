<?php
class Mascotas extends CI_Model {
  public function __construct()
  {
    parent::__construct();
  }

  public function insertarMascotas($datos)
  {
    $DB2 = $this->load->database('default', TRUE);
    $DB2->insert('mascota',$datos);
  }

  public function obtenerUltmas()
  {
    $DBcon = $this->load->database('default', TRUE);
    $query=$DBcon->query("SELECT MAX(idmascota) as maximo FROM mascota");
    if ($query->num_rows() > 0) {
      return $query->result();
    } else {
      return false;
    }
  }
  public function insertarMascotasRelacion($datos)
  {
    $DB2 = $this->load->database('default', TRUE);
    $DB2->insert('mascota_usuarios',$datos);
  }
  public function obtenerMascoasUsuario($idusuarios)
  {
    $DBcon = $this->load->database('default', TRUE);
    $query=$DBcon->query("SELECT * FROM mascota as ma,mascota_usuarios as mu where mu.usuarios_idusuarios=$idusuarios and mu.mascota_idmascota=ma.idmascota");
    if ($query->num_rows() > 0) {
      return $query->result();
    } else {
      return false;
    }
  }
  function borrarUSUARIO($usuario) {
    $DB2 = $this->load->database('default', TRUE);
    $DB2->where('idusuarios', $usuario );
    $DB2->delete('usuarios');
  }
  public function borrarmascota($mascota,$usuario)
  {
    $DB2 = $this->load->database('default', TRUE);
    $DB2->where('usuarios_idusuarios', $usuario );
    $DB2->where('mascota_idmascota', $mascota );
    $DB2->delete('mascota_usuarios');

    $DB2->where('idmascota', $mascota );
    $DB2->delete('mascota');
  }
  public function cargarMacotasExternas($idusuarios)
  {
    $DBcon = $this->load->database('default', TRUE);
    $query=$DBcon->query("SELECT * FROM mascota as ma,mascota_usuarios as mu where mu.usuarios_idusuarios!= $idusuarios and mu.mascota_idmascota=ma.idmascota");
    if ($query->num_rows() > 0) {
      return $query->result();
    } else {
      return false;
    }
  }
}
