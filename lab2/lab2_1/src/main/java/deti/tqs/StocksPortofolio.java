package deti.tqs;
import java.util.ArrayList;
import org.mockito.Mock;
public class StocksPortofolio{


    public ArrayList<Stock> stocks = new ArrayList<Stock>();


    public double TotalValue(){
        return 0.00;
    }

    public void addStock(Stock s){
        stocks.add(s);
    }


}
