package rw.ac.rca.termOneExam.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;
import rw.ac.rca.termOneExam.utils.Exceptions.ResourceNotFOundException;

@Service
public class CityService {

	@Autowired
	private ICityRepository cityRepository;
	
	public Optional<City> getById(long id) {

		Optional<City> city = cityRepository.findById(id);
		if(city.isPresent()) {
			City eachFoundCity = city.get();
			eachFoundCity.setFahrenheit((eachFoundCity.getWeather()*9/5)+32);

			return Optional.of(eachFoundCity);
		}
		return null;
	}

	public List<City> getAll() {
		//    calculating fahrenheit value for every city
		List<City> cities = cityRepository.findAll();
		for(City city:cities){
			city.setFahrenheit((city.getWeather()*9/5)+32);
		}

		return cities;

	}

	public boolean existsByName(String name) {
		
		return cityRepository.existsByName(name);
	}

	public City save(CreateCityDTO dto) {
		City city =  new City(dto.getName(), dto.getWeather());
		return cityRepository.save(city);
	}






//	public City calculateWeather(CreateCityDTO dto){
//		City city = new City(dto.getName(), dto.getWeather());
//
//	}

//	public void update(UUID id, CreateCityDTO cityDTO)  {
//		Optional<City> cityExists = this.cityRepository.findById(id);
//
//		if(cityExists.isPresent()){
//			City city = cityExists.get();
//			city.setName(cityDTO.getName());
//			city.setWeather(cityDTO.getWeather());
//			city.setFahrenheit(cityDTO.getFahrenheit());
//			return cityRepository.save(city);
//		}
//		}
//
//		public City delete(UUID id){
//		cityRepository.findAllById(id)
//				.orElseThrow(()-> new ResourceNotFOundException("City with this is not found" +id));
//		cityRepository.deleteById(id);
//		return null;
//		}

		public boolean existsByname(String name) {
		return cityRepository.existsByName(name);
		}

}
