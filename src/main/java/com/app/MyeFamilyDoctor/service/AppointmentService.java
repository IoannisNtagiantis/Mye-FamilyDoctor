package com.app.MyeFamilyDoctor.service;

import com.app.MyeFamilyDoctor.model.AppointmentRequest;
import com.app.MyeFamilyDoctor.model.Citizen;
import com.app.MyeFamilyDoctor.model.Doctor;
import com.app.MyeFamilyDoctor.repository.AppointmentRequestRepository;
import com.app.MyeFamilyDoctor.repository.CitizenRepository;
import com.app.MyeFamilyDoctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRequestRepository appointmentRequestRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public AppointmentRequest createAppointmentRequest(Long citizenId, Long doctorId) throws Exception {
        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new Exception("Citizen not found with id " + citizenId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new Exception("Doctor not found with id " + doctorId));

        if (doctor.getCapacity() >= citizen.getFamilyMembers()) {
            AppointmentRequest appointmentRequest = new AppointmentRequest();
            appointmentRequest.setCitizen(citizen);
            appointmentRequest.setDoctor(doctor);
            appointmentRequest.setStatus("pending");

            doctor.setCapacity(doctor.getCapacity() - citizen.getFamilyMembers());
            doctorRepository.save(doctor);

            return appointmentRequestRepository.save(appointmentRequest);
        } else {
            throw new Exception("Doctor with id " + doctorId + " cannot accept more patients due to capacity limits.");
        }
    }

    public List<AppointmentRequest> findAppointmentsByCitizenIdOrderByDate(Long citizenId) {
        return appointmentRequestRepository.findByCitizenIdOrderByAppointmentDateTimeDesc(citizenId);
    }

    public List<AppointmentRequest> findAppointmentsByDoctorIdOrderByDate(Long doctorId) {
        return appointmentRequestRepository.findByDoctorIdOrderByAppointmentDateTimeDesc(doctorId);
    }

    public AppointmentRequest acceptAppointmentRequest(Long requestId) throws Exception {
        AppointmentRequest request = appointmentRequestRepository.findById(requestId)
                .orElseThrow(() -> new Exception("Appointment request not found with id: " + requestId));
        request.setStatus("accepted");
        return appointmentRequestRepository.save(request);
    }

    public AppointmentRequest rejectAppointmentRequest(Long requestId) throws Exception {
        AppointmentRequest request = appointmentRequestRepository.findById(requestId)
                .orElseThrow(() -> new Exception("Appointment request not found with id: " + requestId));
        request.setStatus("rejected");
        return appointmentRequestRepository.save(request);
    }




}

