package com.garage.autogarage.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garage.autogarage.Exception.BadRequest;
import com.garage.autogarage.Exception.ResourceNotFound;
import com.garage.autogarage.dto.MechanicRequest;
import com.garage.autogarage.dto.MechanicResponse;
import com.garage.autogarage.entity.Mechanic;
import com.garage.autogarage.entity.MechanicStatus;
import com.garage.autogarage.entity.User;
import com.garage.autogarage.repository.MechanicRepository;
import com.garage.autogarage.repository.UserRepository;

@Service
public class MechanicService {
	@Autowired
	MechanicRepository mechanicRepository;
	@Autowired
	UserRepository userRepository;

	public MechanicResponse addMechanic(MechanicRequest request) {
		if(mechanicRepository.existsByEmail(request.getEmail())) {
			throw new BadRequest("Mechanic exists by email");
		}
		User user=userRepository.findById(request.getUserId())
				.orElseThrow(()-> new ResourceNotFound("User Not Found"));
		Mechanic mechanic=Mechanic.builder()
				.name(request.getName())
				.email(request.getEmail())
				.phone(request.getPhone())
				.specialization(request.getSpecialization())
				.status(request.getStatu())
				.user(user)
				.build();
		Mechanic saveMechanic=mechanicRepository.save(mechanic);
		return maptoResponse(saveMechanic) ;
	}
	public MechanicResponse maptoResponse(Mechanic mechanic) {
		return new MechanicResponse(mechanic.getId(),
				mechanic.getName(),
				mechanic.getEmail(),
				mechanic.getPhone(),
				mechanic.getSpecialization(),
				mechanic.getStatus().toString());
	}
	public List<MechanicResponse> getallMEchanic() {
		
		return mechanicRepository.findAll()
				.stream()
				.map(this::maptoResponse)
				.collect(Collectors.toList());
	}
	public MechanicResponse getMechanicByid(Long id) {
		Mechanic mechanic=mechanicRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFound("Mechnaic Not FOund"));
		
		return maptoResponse(mechanic);
	}
	public MechanicResponse updateMechanic(Long id, MechanicRequest request) {
		 Mechanic mechanic = mechanicRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFound("Mechanic not found"));

	        mechanic.setName(request.getName());
	        mechanic.setEmail(request.getEmail());
	        mechanic.setPhone(request.getPhone());
	        mechanic.setSpecialization(request.getSpecialization());

	        Mechanic updated = mechanicRepository.save(mechanic);
	        return maptoResponse(updated);
	}
	public MechanicResponse deleteMechanic(Long id) {
		 Mechanic mechanic = mechanicRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFound("Mechanic not found with id: " + id));
	        mechanicRepository.delete(mechanic);
		return maptoResponse(mechanic);
	}
	public List<MechanicResponse> getMechanicByAvaiable() {
		return mechanicRepository.findByStatus(MechanicStatus.AVAILABLE)
				.stream()
				.map(this::maptoResponse)
				.collect(Collectors.toList());
	}

}
