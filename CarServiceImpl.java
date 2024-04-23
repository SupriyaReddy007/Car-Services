package com.example.Carservicecompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Carservicecompany.exception.ResourceNotFoundException;
import com.example.Carservicecompany.model.CarService;
import com.example.Carservicecompany.repository.CarServiceRepository;

@Service
public class CarServiceImpl implements CarServiceService{
	
	@Autowired
	CarServiceRepository CSR;

	@Override
	public CarService saveCarService(CarService CarService){
		 //TODO Auto-generated method stub
		
		return CSR.save(CarService);
	}

	@Override
	public CarService getCarService(int id) {
		// TODO Auto-generated method stub
		CSR.findById(id).orElseThrow(
					()
					-> new ResourceNotFoundException ("ID not found"));
		
		return CSR.findById(id).get();
	}

	@Override
	public CarService updateCarService(int id, CarService CarService) {
		// TODO Auto-generated method stub
		CarService Car = CSR.findById(id).orElseThrow(()-> new ResourceNotFoundException ("ID not found"));

		if(Car.getId()==0 || Car.getCarmake()==null || Car.getModelname()==null || Car.getRegistrationNumber()==null ||
				Car.getChassisNumber()==0 || Car.getKnownissues()==null || Car.getCost()==0 ||
					Car.getGivendate()==null || Car.getExpecteddeliverydate()==null) 
				//Car.getCreateddatetime()==null || Car.getUpdateddatetime()==null 
						throw new ResourceNotFoundException("invalid update");
		
		Car.setId(CarService.getId());
		Car.setCarmake(CarService.getCarmake());
		Car.setModelname(CarService.getModelname());
		Car.setRegistrationNumber(CarService.getRegistrationNumber());
		Car.setChassisNumber(CarService.getChassisNumber());
		Car.setKnownissues(CarService.getKnownissues());
		Car.setCost(CarService.getCost());
		Car.setGivendate(CarService.getGivendate());
		Car.setExpecteddeliverydate(CarService.getExpecteddeliverydate());
				

		return CSR.save(Car);
	}
	@Override
	public boolean deleteCarServiceById(int id) {
		// TODO Auto-generated method stub
		CarService Car=CSR.findById(id).orElseThrow(()-> new ResourceNotFoundException ( "ID not found"));
		CSR.deleteById(id);
		System.out.println("the delete is:"+id);
		return true;
	}

	@Override
	public List<CarService> showAll() {
		// TODO Auto-generated method stub
		return CSR.findAll();
	}

	

	
}
