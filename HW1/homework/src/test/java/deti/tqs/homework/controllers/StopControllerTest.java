package deti.tqs.homework.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Arrays;

import deti.tqs.homework.models.Route;
import deti.tqs.homework.models.Stop;
import deti.tqs.homework.services.RouteService;
import deti.tqs.homework.services.StopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(StopController.class)
public class StopControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StopService stopService;

    @MockBean
    RouteService routeService;

    Stop stop1,stop2,stop3,stop4,stop5,stop6,stop7,stop8,stop9,stop10;
    Route route1,route2;

    @BeforeEach
    void setUp() {
        stop1 = new Stop();
        stop1.setName("Aveiro");
        stop1.setStopOrder(1);
        stop2 = new Stop();
        stop2.setName("Coimbra");
        stop2.setStopOrder(2);
        stop3 = new Stop();
        stop3.setName("Leiria");
        stop3.setStopOrder(3);
        stop4 = new Stop();
        stop4.setName("Lisboa");
        stop4.setStopOrder(4);
        stop5 = new Stop();
        stop5.setName("Faro");
        stop5.setStopOrder(5);
        stop6 = new Stop();
        stop6.setName("Porto");
        stop6.setStopOrder(1);
        stop7 = new Stop();
        stop7.setName("Braga");
        stop7.setStopOrder(2);
        stop8 = new Stop();
        stop8.setName("Viana do Castelo");
        stop8.setStopOrder(3);
        stop9 = new Stop();
        stop9.setName("Vila Real");
        stop9.setStopOrder(4);
        stop10 = new Stop();
        stop10.setName("Viseu");
        stop10.setStopOrder(5);
        route1 = new Route();
        route1.setStops(Arrays.asList(stop1,stop2,stop3,stop4,stop5));
        route2 = new Route();
        route2.setStops(Arrays.asList(stop6,stop7,stop8,stop9,stop10));
        when(stopService.getStopById(1L)).thenReturn(stop1);
        when(stopService.getAllStops()).thenReturn(Arrays.asList(stop1,stop2,stop3,stop4,stop5,stop6,stop7,stop8,stop9,stop10));

    }

    @Test
    public void testGetStopById() throws Exception {
        mockMvc.perform(get("/stops/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Aveiro")))
                .andExpect(jsonPath("$.stopOrder", is(1)));
    }

    @Test
    public void testGetAllStops() throws Exception {
        mockMvc.perform(get("/stops"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    public void testSaveStop() throws Exception {
        stop1.setRoute(route1);
        when(stopService.saveStop(any(Stop.class))).thenReturn(stop1);
        mockMvc.perform(post("/stops")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.toJson(stop1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Aveiro")))
                .andExpect(jsonPath("$.stopOrder", is(1)));
        verify(stopService).saveStop(any(Stop.class));
    }
}
