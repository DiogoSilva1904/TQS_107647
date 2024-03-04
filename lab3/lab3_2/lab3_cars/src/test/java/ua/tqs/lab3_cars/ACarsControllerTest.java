package ua.tqs.lab3_cars;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;


@WebMvcTest(CarsController.class)
public class ACarsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarsService carsService;

    @BeforeEach
    public void startup()  throws Exception{
    }

    @Test
    void testPostCar() throws Exception{
        Car car = new Car("opel", "corsa");
        when( carsService.save(Mockito.any()) ).thenReturn( car);
        mvc.perform(
                post("/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("corsa")));

        verify(carsService, times(1)).save(Mockito.any());

    }

    @Test
    public void testGetAllCars() throws Exception {
        Car car1 = new Car("opel", "corsa");
        Car car2 = new Car("renault", "clio");
        List<Car> allCars = Arrays.asList(car1, car2);
        when(carsService.getAllCars()).thenReturn(allCars);
        mvc.perform(get("/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].model", is(car1.getModel())))
                .andExpect(jsonPath("$[1].model", is(car2.getModel())));
        verify(carsService, times(1)).getAllCars();
    }

    @Test
    public void testGetCarById() throws Exception {
        Car car = new Car("opel", "corsa");
        when(carsService.getCarDetails(anyLong())).thenReturn(car);
        mvc.perform(get("/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is(car.getModel())));
        verify(carsService, times(1)).getCarDetails(anyLong());
    }

    @Test
    public void testEditCar() throws Exception {
        Car car = new Car("opel", "corsa");
        car.setModel("astra");
        when(carsService.getCarDetails(anyLong())).thenReturn(car);
        when(carsService.save(any())).thenReturn(car);
        mvc.perform(put("/cars/1").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is("astra")));
        verify(carsService, times(1)).getCarDetails(anyLong());
        verify(carsService, times(1)).save(any());
    }

    @Test
    public void testDeleteCar() throws Exception {
        Car car = new Car("opel", "corsa");
        when(carsService.getCarDetails(anyLong())).thenReturn(car);
        mvc.perform(delete("/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(carsService, times(1)).getCarDetails(anyLong());
        verify(carsService, times(1)).deleteCar(anyLong());
    }

}
