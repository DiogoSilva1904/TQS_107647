package integration;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolverService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class AddressResolverIT {

    AddressResolverService resolver;

    @BeforeEach
    public void startup() {
        ISimpleHttpClient httpClient = new TqsBasicHttpClient();
        resolver = new AddressResolverService(httpClient);
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        Optional<Address> result = resolver.findAddressForLocation(40.63436, -8.65616);
        Address expected = new Address( "Avenida da Universidade", "Aveiro","3810-489", "");
        assertTrue( result.isPresent());
        assertEquals( expected, result.get());

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        Optional<Address> result = resolver.findAddressForLocation( -361,-361);
        assertFalse( result.isPresent());
        
    }

}
