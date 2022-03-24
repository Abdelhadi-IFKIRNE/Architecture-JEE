package com.example.mapping_relations_2.sevices;

import com.example.mapping_relations_2.entity.consultation;
import com.example.mapping_relations_2.entity.medcin;
import com.example.mapping_relations_2.entity.patient;
import com.example.mapping_relations_2.entity.rendezVous;
import com.example.mapping_relations_2.repository.RendezVousRepository;
import com.example.mapping_relations_2.repository.consultationsRepository;
import com.example.mapping_relations_2.repository.madcinRepository;
import com.example.mapping_relations_2.repository.patientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class hopitalImpl implements Ihopital{

    private RendezVousRepository rendezVousRepository;
    private consultationsRepository consultationsRepository;
    private patientRepository patientRepository;
    private madcinRepository madcinRepository;

    public hopitalImpl(RendezVousRepository rendezVousRepository, com.example.mapping_relations_2.repository.consultationsRepository consultationsRepository, com.example.mapping_relations_2.repository.patientRepository patientRepository, com.example.mapping_relations_2.repository.madcinRepository madcinRepository) {
        this.rendezVousRepository = rendezVousRepository;
        this.consultationsRepository = consultationsRepository;
        this.patientRepository = patientRepository;
        this.madcinRepository = madcinRepository;
    }

    @Override
    public patient savePatient(patient patient) {
        patient.setId(UUID.randomUUID().toString());
        return patientRepository.save(patient);
    }

    @Override
    public consultation saveConsultation(consultation consultation) {
        return consultationsRepository.save(consultation);
    }

    @Override
    public medcin saveMedcin(medcin medcin) {
        return madcinRepository.save(medcin);
    }

    @Override
    public rendezVous saveRendezVous(rendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }
}
