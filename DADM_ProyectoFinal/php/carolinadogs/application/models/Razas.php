<?php
class Razas extends CI_Model {
  public function __construct()
  {
    parent::__construct();
  }
  public function getRazas( )
  {
    $DBcon = $this->load->database('default', TRUE);
    $query=$DBcon->query("SELECT * FROM razamascota");
    if ($query->num_rows() > 0) {
        return $query->result();
    } else {
        return false;
    }
  }
  public function getRazasID($id)
  {
    $DBcon = $this->load->database('default', TRUE);
    $query=$DBcon->query("SELECT * FROM razamascota where idrazamascota=$id");
    if ($query->num_rows() > 0) {
        return $query->result();
    } else {
        return false;
    }
  }
  public function getRazasIDTIPO($id)
  {
    $DBcon = $this->load->database('default', TRUE);
    $query=$DBcon->query("SELECT * FROM razamascota where tipo_mascota_idtipo_mascota=$id");
    if ($query->num_rows() > 0) {
        return $query->result();
    } else {
        return false;
    }
  }
  public function actRaza($id,$datos)
  {
    $DB2 = $this->load->database('default', TRUE);
    $DB2->where('idrazamascota', $id );
    $DB2->update('razamascota',$datos);
  }
}
