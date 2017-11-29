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
  public function cargarUsuarios()
  {
    $DBcon = $this->load->database('default', TRUE);
    $query=$DBcon->query("SELECT * FROM usuarios");
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
}
