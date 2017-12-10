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
  public function actualizarMactoa($ic,$datos)
  {
    $DB2 = $this->load->database('default', TRUE);
    $DB2->where("idmascota",$ic);
    $DB2->update('mascota',$datos);
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
    $query=$DBcon->query("SELECT u.nombre as nombreu,u.idusuarios,u.perfil_foto,ma.nombre,ma.idmascota,ma.edad,ma.foto_mas,ma.megusta,ma.nomegusta,ma.sexo,ma.tipo_mascota_idtipo_mascota,ma.razamascota_idrazamascota
       FROM mascota as ma,mascota_usuarios as mu,usuarios as u where mu.usuarios_idusuarios!= $idusuarios and mu.mascota_idmascota=ma.idmascota and mu.usuarios_idusuarios=u.idusuarios");
    if ($query->num_rows() > 0) {
      return $query->result();
    } else {
      return false;
    }
  }
  public function obtenerCorazon($idmascota)
  {
    $DBcon = $this->load->database('default', TRUE);
    $query=$DBcon->query("SELECT megusta FROM mascota where idmascota=$idmascota");
    if ($query->num_rows() > 0) {
      return $query->result();
    } else {
      return false;
    }
  }
  public function obtenerLike($idmascota)
  {
    $DBcon = $this->load->database('default', TRUE);
    $query=$DBcon->query("SELECT nomegusta FROM mascota where idmascota=$idmascota");
    if ($query->num_rows() > 0) {
      return $query->result();
    } else {
      return false;
    }
  }
  public function subirCorazon($mascota,$datos)
  {
    $DB2 = $this->load->database('default', TRUE);
    $DB2->where('idmascota', $mascota );
    $DB2->update('mascota',$datos);
  }
  public function subirLike($mascota,$datos)
  {
    $DB2 = $this->load->database('default', TRUE);
    $DB2->where('idmascota', $mascota );
    $DB2->update('mascota',$datos);
  }
}
