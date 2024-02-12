package com.app.MyeFamilyDoctor.service;

import com.app.MyeFamilyDoctor.model.Doctor;
import com.app.MyeFamilyDoctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findDoctorsByPostalCode(int postalCode) {
        return doctorRepository.findByPostalCode(postalCode);
    }

}
