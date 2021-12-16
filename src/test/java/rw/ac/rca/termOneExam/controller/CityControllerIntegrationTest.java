package rw.ac.rca.termOneExam.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {
	
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAll_testSuccess() throws JSONException {
        String response= this.restTemplate.getForObject("/api/cities/all", String.class);
                JSONAssert.assertEquals("[{name: \"Nairobi\"},{weather:23}, {fahrenheit:26}]",response,false);
    }

    @Test
    public void getById_testSuccess() {
        City city = this.restTemplate.getForObject("/api/cities/all/1",City.class);
       assertEquals("Nairobi",city.getName());
    }

    @Test
    public void getById_404()  {
        ResponseEntity<City> response = this.restTemplate.getForEntity("/api/cities/all/1",City.class);
        System.out.println("The response"+response);
        assertEquals(404,response.getStatusCodeValue());
    }

//    @Test
//    public void create_success()  {
//        City city = new City(2,"Nairobi",29.0,29.0);
//        ResponseEntity<City> response = this.restTemplate.postForEntity("/api/cities/add",city, String.class);
//
//        assertEquals(201,response.getStatusCodeValue());
//        assertEquals("Nairobi",response.getBody().getName());
//    }

    @Test
    public void create_400()  {
        City city = new City(2,"Nairobi",29.0,29.0);
        ResponseEntity<String> response = this.restTemplate.postForEntity("/api/cities/add",city, String.class);

        assertEquals(400,response.getStatusCodeValue());
        assertEquals("City already existe",response.getBody());
    }

    @Test
    public void update_success()  {
        City city = new City(2,"Somalia",29.0,29.0);
        ResponseEntity<City> response = this.restTemplate.exchange("/api/cities/update", HttpMethod.PUT, new HttpEntity<>(city),City.class);

        assertEquals(response.getBody().getName(),"Somalia");
        assertEquals(response.getStatusCode(),HttpStatus.CREATED);
    }

    @Test
    public void delete_Success() {
        ResponseEntity<City> response = restTemplate.exchange("", HttpMethod.DELETE, null, City.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
