package deti.tqs;

public class Stock {
    public String label = null;
    public Integer quantity = null;

    public Stock(String lab,Integer quant){
        this.label= lab;
        this.quantity= quant;
    }

    public String getLabel(){
        return this.label;
    }

    public Integer getQuantity(){
        return this.quantity;
    }

    public void setLabel(String label){
        this.label=label;
    }

    public void setQuantity(Integer quant){
        this.quantity = quant;
    }



}
