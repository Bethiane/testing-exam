package rw.ac.rca.termOneExam.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;
import rw.ac.rca.termOneExam.utils.CustomExceptions;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @Mock
    private ICityRepository cityRepositoryMock;

    @InjectMocks
    private City cityService;

//    @Test
//    public void getAllCities(){
//        when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City(UUID.fromString(""),"Rwanda",100,200),
//                new City(UUID.fromString(""),"Rwanda",100,1000)));
//        assertEquals(130,cityService.getWeather());
//    }

    @Test
    public void getAll_success() {
        when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City(1, "New Dawn", 200,350),
                new City(2, "Actros", 250,300)));

        Assert.assertEquals("Actros", cityService.getName());
    }


    @Test
    public void findById_success() {
        when(cityRepositoryMock.findById(1L)).thenReturn(Optional.of(new City(1, "New Dawn", 200,350)));
        Assert.assertEquals("New Dawn", cityRepositoryMock.findById(1L).get().getName());
    }


    @Test
    public void findById_fail()  throws CustomExceptions {
        when(cityRepositoryMock.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(CustomExceptions.class, () -> cityRepositoryMock.findById(1L));
        Assert.assertEquals("City with this id not found", exception.getMessage());
    }

//    @Test
//    public void create_success() {
//        when(cityRepositoryMock.save(any(City.class))).thenReturn(new City(1, "Actros", 200,350));
//
//        Assert.assertEquals("Actros", cityService.save(new City()).getName());
//    }

}
