package rw.ac.rca.termOneExam.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.service.CityService;
import rw.ac.rca.termOneExam.utils.APICustomResponse;
import rw.ac.rca.termOneExam.utils.APIResponse;

@RestController
@RequestMapping("/api/cities")
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getById(@PathVariable(name = "id") long id) {

		Optional<City> city = cityService.getById(id);

		if (city.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(city.get());
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new APICustomResponse(false, "City not found with id " + id));
	}

	@GetMapping("/all")
	public List<City> getAll() {

		return cityService.getAll();
	}

	@PostMapping("/add")
	public ResponseEntity<?> saveItem(@RequestBody CreateCityDTO dto) {

		if (cityService.existsByName(dto.getName())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new APICustomResponse(false, "City name " + dto.getName() + " is registered already"));
		}
		City city = cityService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(city);
	}

//	@PutMapping("/update")
//	public ResponseEntity<?> editItem(@RequestParam UUID id, @RequestBody CreateCityDTO dto){
//
//		City city = cityService.update(id,dto);
//		if(city !=null){
//			return ResponseEntity.status(HttpStatus.CREATED).body(city);
//		}
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(false,"City not found"));
//
//	}
//
//	@DeleteMapping("/delete")
//	public ResponseEntity<?> deleteItem(@RequestParam UUID id){
//		City city = cityService.delete(id);
//		return  ResponseEntity.ok(city);
//	}


}
