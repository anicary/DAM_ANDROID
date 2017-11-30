<?php
class Usuarios extends CI_Model {
  public function __construct()
  {
    parent::__construct();
  }
  public function iniciarsesionm($usuario, $password)
  {
    $DBcon = $this->load->database('default', TRUE);
    $query=$DBcon->query("SELECT * FROM usuarios where usuario='$usuario' and password='$password';");
    if ($query->num_rows() > 0) {
        return $query->result();
    } else {
        return false;
    }
  }
  public function actualizarultima_sesion($usuario,$fechayhora)
  {
    $DBcon = $this->load->database('default', TRUE);
    $DBcon->set('ultima_conexion', $fechayhora);
    $DBcon->where('idusuarios', $usuario);
    $DBcon->update('usuarios');
  }
  public function insertarUsuario($datos)
  {
    $DB2 = $this->load->database('default', TRUE);
    $DB2->insert('usuarios',$datos);
  }
  public function editarUsuario($datos,$id)
  {
    $DB2 = $this->load->database('default', TRUE);
    $DB2->where('idusuarios',$id);
    $DB2->update('usuarios',$datos);
  }
  public function loginusuario($correo,$contrasena)
  {
    $DBcon = $this->load->database('default', TRUE);
    $query=$DBcon->query("SELECT * FROM usuarios where correo='$correo' and contrasena='$contrasena';");
    if ($query->num_rows() > 0) {
        return $query->result();
    } else {
        return false;
    }
  }
  public function verificarCorreo($correo)
  {
    $DBcon = $this->load->database('default', TRUE);
    $query=$DBcon->query("SELECT * FROM usuarios where correo='$correo'");
    if ($query->num_rows() > 0) {
        return $query->result();
    } else {
        return false;
    }
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
