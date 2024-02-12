package com.app.MyeFamilyDoctor.service;

import com.app.MyeFamilyDoctor.dto.UserUpdateDto;
import com.app.MyeFamilyDoctor.model.Citizen;
import com.app.MyeFamilyDoctor.model.Doctor;
import com.app.MyeFamilyDoctor.model.Role;
import com.app.MyeFamilyDoctor.model.Users;
import com.app.MyeFamilyDoctor.repository.CitizenRepository;
import com.app.MyeFamilyDoctor.repository.DoctorRepository;
import com.app.MyeFamilyDoctor.repository.RoleRepository;
import com.app.MyeFamilyDoctor.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.MyeFamilyDoctor.dto.UnifiedRegistrationDto;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Users registerNewUser(UnifiedRegistrationDto registrationDto) throws Exception {
        // Βασική διαδικασία εγγραφής όπως παραπάνω
        if (usersRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            throw new Exception("Υπάρχει ήδη χρήστης με αυτό το username.");
        }

        Users newUser = new Users();
        newUser.setUsername(registrationDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        newUser.setEmail(registrationDto.getEmail());

        Role userRole = roleRepository.findByName(registrationDto.getRole())
                .orElseThrow(() -> new Exception("Role not found."));
        newUser.setRoles(new HashSet<>(Set.of(userRole)));

        usersRepository.save(newUser);

        // Διαχείριση ειδικών στοιχείων βάσει ρόλου
        if ("ROLE_DOCTOR".equals(registrationDto.getRole())) {
            Doctor doctor = new Doctor();
            doctor.setName(registrationDto.getName());
            doctor.setLastName(registrationDto.getLastName());
            doctor.setEmail(registrationDto.getEmail());
            doctor.setCapacity(registrationDto.getCapacity());
            doctor.setPostalCode(registrationDto.getDoctorPostalCode());
            doctorRepository.save(doctor);
        } else if ("ROLE_CITIZEN".equals(registrationDto.getRole())) {
            Citizen citizen = new Citizen();
            citizen.setName(registrationDto.getName());
            citizen.setLastName(registrationDto.getLastName());
            citizen.setEmail(registrationDto.getEmail());
            citizen.setAmka(registrationDto.getAmka());
            citizen.setFamilyMembers(registrationDto.getFamilyMembers());
            citizen.setPostalCode(registrationDto.getCitizenPostalCode());
            citizenRepository.save(citizen);
        }

        return newUser;
    }


    public Users changeUserRole(Long userId, String roleName) throws Exception {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found."));
        Role newRole = roleRepository.findByName(roleName)
                .orElseThrow(() -> new Exception("Role not found."));

        user.setRoles(new HashSet<>(Set.of(newRole))); // Θέτει τον νέο ρόλο
        return usersRepository.save(user); // Αποθηκεύει τις αλλαγές
    }




    public Users updateUserDetails(Long userId, UserUpdateDto userUpdateDto) throws Exception {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found with id: " + userId));
        user.setName(userUpdateDto.getName());
        user.setEmail(userUpdateDto.getEmail());
        if (userUpdateDto.getPassword() != null && !userUpdateDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        }
        user.setUsername(user.getUsername());

        if ("ROLE_DOCTOR".equals(userUpdateDto.getRole())) {
            // Υποθέτοντας ότι έχετε μια σχέση OneToOne μεταξύ Users και Doctor
            Doctor doctor = doctorRepository.findByUserId(userId).orElse(new Doctor());
            // Σύνδεση του Doctor με τον User
            doctor.setUser(user);
            doctor.setName(userUpdateDto.getName());
            doctor.setLastName(userUpdateDto.getLastName());
            doctor.setEmail(userUpdateDto.getEmail());
            doctor.setCapacity(userUpdateDto.getCapacity());
            doctor.setPostalCode(userUpdateDto.getPostalCode());
            doctorRepository.save(doctor);
        } else if ("ROLE_CITIZEN".equals(userUpdateDto.getRole())) {
            // Υποθέτοντας ότι έχετε μια σχέση OneToOne μεταξύ Users και Citizen
            Citizen citizen = citizenRepository.findByUserId(userId).orElse(new Citizen());
            // Σύνδεση του Citizen με τον User
            citizen.setUser(user);
            citizen.setName(userUpdateDto.getName());
            citizen.setLastName(userUpdateDto.getLastName());
            citizen.setEmail(userUpdateDto.getEmail());
            citizen.setAmka(userUpdateDto.getAmka());
            citizen.setFamilyMembers(userUpdateDto.getFamilyMembers());
            citizen.setPostalCode(userUpdateDto.getPostalCode());
            citizenRepository.save(citizen);
        }

        return usersRepository.save(user);
    }



}



