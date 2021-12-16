package rw.ac.rca.termOneExam.controller;


import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.service.CityService;
import rw.ac.rca.termOneExam.utils.APICustomResponse;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {
	
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void GetAllCities(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange("/api/cities/all",HttpMethod.GET,entity,String.class);
        assertNotNull(response.getBody());
    }


    @Test
    public void GetByIdSuccess(){
        City city = restTemplate.getForObject("/api/cities/id/101", City.class);
        System.out.println(city.getName());
        assertNotNull(city);
    }

    @Test
    public void getById_404() {
        ResponseEntity<APICustomResponse> city =
                this.restTemplate.getForEntity("/api/cities/id/110", APICustomResponse.class);
        City city1 = new City(110,"Kg",20,50);

        assertTrue(city.getStatusCodeValue()==404);
        assertFalse(city.getBody().isStatus());
        assertEquals("City not found " + city1.getId(), city.getBody().getMessage());
    }

    @Test
    public void create_Success(){
        City city = new City();
        city.setName("Nyamagabe");
        city.setWeather(18.43);

        ResponseEntity<City> createResponse = restTemplate.postForEntity("/api/cities/add",city,City.class);
        assertNotNull(createResponse);
        assertNotNull(createResponse.getBody());
    }




//
//    @Test
//    public void getAll_testSuccess() throws JSONException {
//        String response= this.restTemplate.getForObject("/api/cities/all", String.class);
//                JSONAssert.assertEquals("[{name: \"Nairobi\"},{weather:23}, {fahrenheit:26}]",response,false);
//    }
//
//    @Test
//    public void getById_testSuccess() {
//        City city = this.restTemplate.getForObject("/api/cities/all/1",City.class);
//       assertEquals("Nairobi",city.getName());
//    }
//
//    @Test
//    public void getById_404()  {
//        ResponseEntity<City> response = this.restTemplate.getForEntity("/api/cities/all/1",City.class);
//        System.out.println("The response"+response);
//        assertEquals(404,response.getStatusCodeValue());
//    }
//
////    @Test
////    public void create_success()  {
////        City city = new City(2,"Nairobi",29.0,29.0);
////        ResponseEntity<City> response = this.restTemplate.postForEntity("/api/cities/add",city, String.class);
////
////        assertEquals(201,response.getStatusCodeValue());
////        assertEquals("Nairobi",response.getBody().getName());
////    }
//
//    @Test
//    public void create_400()  {
//        City city = new City(2,"Nairobi",29.0,29.0);
//        ResponseEntity<String> response = this.restTemplate.postForEntity("/api/cities/add",city, String.class);
//
//        assertEquals(400,response.getStatusCodeValue());
//        assertEquals("City already existe",response.getBody());
//    }
//
//    @Test
//    public void update_success()  {
//        City city = new City(2,"Somalia",29.0,29.0);
//        ResponseEntity<City> response = this.restTemplate.exchange("/api/cities/update", HttpMethod.PUT, new HttpEntity<>(city),City.class);
//
//        assertEquals(response.getBody().getName(),"Somalia");
//        assertEquals(response.getStatusCode(),HttpStatus.CREATED);
//    }
//
//    @Test
//    public void delete_Success() {
//        ResponseEntity<City> response = restTemplate.exchange("", HttpMethod.DELETE, null, City.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }


}
