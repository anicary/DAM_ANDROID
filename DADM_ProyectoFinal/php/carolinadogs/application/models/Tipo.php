<?php
class Tipo extends CI_Model {
  public function __construct()
  {
    parent::__construct();
  }
  public function getTipos( )
  {
    $DBcon = $this->load->database('default', TRUE);
    $query=$DBcon->query("SELECT * FROM tipo_mascota");
    if ($query->num_rows() > 0) {
        return $query->result();
    } else {
        return false;
    }
  }
}
