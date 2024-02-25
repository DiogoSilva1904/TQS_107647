package deti.tqs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(MockitoExtension.class)
public class StocksPortofolioTest {
    @InjectMocks
    StocksPortofolio sp;

    @Mock
    IStockmarketService stockMarket;

    @Test
    public void testAddStock() {
        Stock s = new Stock("A", 10);
        sp.addStock(s);
        when(stockMarket.lookUpPrice("AAPL")).thenReturn(10.0);
        assertThat(stockMarket.lookUpPrice("AAPL"), equalTo(10.0));
        verify(stockMarket, times(1)).lookUpPrice("AAPL");
    }
}
