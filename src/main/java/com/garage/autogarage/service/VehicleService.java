package com.garage.autogarage.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garage.autogarage.Exception.BadRequest;
import com.garage.autogarage.Exception.ResourceNotFound;
import com.garage.autogarage.dto.VehicleRequest;
import com.garage.autogarage.dto.VehicleResponse;
import com.garage.autogarage.entity.Customer;
import com.garage.autogarage.entity.Vehicle;
import com.garage.autogarage.repository.CustomerRepository;
import com.garage.autogarage.repository.VehicleRepository;

@Service
public class VehicleService {
	@Autowired
	VehicleRepository repository;
	@Autowired
	CustomerRepository repository2;

	public VehicleResponse addVehicle(VehicleRequest request) {
		if(repository.existsBynumberplate(request.getNumberplate())) {
			throw new BadRequest("Vechile is Exists");
		}
		Customer customer=repository2.findById(request.getCustomerid())
				.orElseThrow(()-> new ResourceNotFound("Customer Not Found"));
		Vehicle vehicle=Vehicle.builder()
				.brand(request.getBrand())
				.model(request.getModel())
				.numberplate(request.getNumberplate())
				.year(request.getYear())
				.Color(request.getColor())
				.customer(customer)
				.build();
		Vehicle savedVehicle=repository.save(vehicle);
		return maptoResponse(savedVehicle);
	}
	
	private VehicleResponse maptoResponse(Vehicle vehicle) {
		return new VehicleResponse(vehicle.getBrand(), 
				vehicle.getModel(),
				vehicle.getYear(), vehicle.getNumberplate(), vehicle.getCustomer().getName());
	}

	public List<VehicleResponse> getAllVehicle() {
		
		return repository.findAll()
				.stream()
				.map(this::maptoResponse)
				.collect(Collectors.toList());
	}

	public VehicleResponse getVehicleByid(Long id) {
		 Vehicle vehicle=repository.findById(id).
				 orElseThrow(()->new ResourceNotFound("Vechile Not Found"));
		return maptoResponse(vehicle);
	}

	public VehicleResponse updateVehicle(Long id, VehicleRequest request) {
		Vehicle vehicle=repository.findById(id).
				 orElseThrow(()->new ResourceNotFound("Vechile Not Found"));
		Customer customer=repository2.findById(request.getCustomerid()).
				orElseThrow(()->new ResourceNotFound("Customer Not Found"));
		vehicle.setBrand(request.getBrand());
		vehicle.setColor(request.getColor());
		vehicle.setModel(request.getModel());
		vehicle.setNumberplate(request.getNumberplate());
		vehicle.setYear(request.getYear());
		vehicle.setCustomer(customer);
		Vehicle updateVehicle=repository.save(vehicle);
		return maptoResponse(updateVehicle);
	}

	public VehicleResponse deleteVehicleByid(Long id) {
		Vehicle vehicle=repository.findById(id).
				 orElseThrow(()->new ResourceNotFound("Vechile Not Found"));
		repository.delete(vehicle);;
		return maptoResponse(vehicle);
	}

	public List<VehicleResponse> getVehicleByCustomerId(Long id) {
		Customer customer=repository2.findById(id).
				orElseThrow(()->new ResourceNotFound("Customer Not Found"));
		return repository.findByCustomer(customer)
				.stream()
				.map(this::maptoResponse)
				.collect(Collectors.toList());
		
	}
}
