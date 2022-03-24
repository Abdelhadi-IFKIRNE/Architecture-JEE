package com.example.mapping_relations_2.sevices;

import com.example.mapping_relations_2.entity.consultation;
import com.example.mapping_relations_2.entity.medcin;
import com.example.mapping_relations_2.entity.patient;
import com.example.mapping_relations_2.entity.rendezVous;


public interface Ihopital {
    patient savePatient( patient patient);
    consultation saveConsultation( consultation consultation);
    medcin saveMedcin( medcin medcin);
   rendezVous saveRendezVous( rendezVous rendezVous);
}
