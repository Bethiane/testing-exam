package rw.ac.rca.termOneExam.utils;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
    public class CityUtilTest {

        @Mock
        private ICityRepository cityRepositoryMock;

        List<City> mock = mock(List.class);


        @Test
        public void noCityWithWeather_success(){
            Boolean WithWeatherGreater40 = false;
            when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City("Kigali",10),new City("Musanze",10)));

            for (City city: cityRepositoryMock.findAll())
                if(city.getWeather() > 40)
                    WithWeatherGreater40 = true;
                assertEquals(false, WithWeatherGreater40);
        }

        @Test
        public void noCityWithWeather_404(){
            Boolean WithWeatherGreater40 = false;

            when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City("Kigali",10),
                    new City("Nairobi",10), new City("Cyanika",50)));
             for (City city: cityRepositoryMock.findAll())
                if(city.getWeather() > 40)
                    WithWeatherGreater40 = true;
                assertEquals(true, WithWeatherGreater40);

        }



    @Test
    public void MockingTest() {

        ArrayList<City> arrayListMock =  mock(ArrayList.class);
        assertEquals(0,arrayListMock.size());
        arrayListMock.add(new City("Gisagara",30));
        arrayListMock.add(new City("Nyamagabe",20));
        assertEquals(0,arrayListMock.size());
        when(arrayListMock.size()).thenReturn(5);
        assertEquals(5,arrayListMock.size());

    }


        @Test
        public void SpyTest() {

            ArrayList<City> arrayListSpy =  spy(ArrayList.class);

            arrayListSpy.add(new City("Kigali",30));
            assertEquals(1,arrayListSpy.size());
            arrayListSpy.add(new City("Kamonyi",30));
            arrayListSpy.add(new City("Ruhango",20));
            assertEquals(3,arrayListSpy.size());
            when(arrayListSpy.size()).thenReturn(5);
            assertEquals(5,arrayListSpy.size());
            arrayListSpy.add(new City("Nyamagabe",30));
            assertEquals(5,arrayListSpy.size());
        }

    @Test
    public void KigaliAndMusanze_success(){
        Boolean containsCityMUSANZE = false;
        Boolean containsCityKIGALI = false;

        when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City("Kigali",25),
                new City("Musanze",13)));
        for (City city: cityRepositoryMock.findAll()){

            if(city.getName().equals("Kigali") ) {
                containsCityKIGALI = true;
            }
            if(city.getName() == "Musanze") {
                containsCityMUSANZE = true;
            }

        }

        assertEquals(true, containsCityKIGALI);
        assertEquals(true, containsCityMUSANZE);

    }


//
//    private ArrayList<City> spy(Class<ArrayList> arrayListClass) {
//    }




}
