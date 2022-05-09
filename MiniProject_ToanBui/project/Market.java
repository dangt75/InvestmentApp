import java.util.ArrayList;
import java.util.Iterator;

public class Market {
    private ArrayList<Stock> list_of_stock;

    public Market(){
        list_of_stock= new ArrayList<>();
    }
    
    //Method that return the text information about normal and premium stock for market
    public String getListOfStock(String text){
        Iterator<Stock> it = list_of_stock.iterator();
        while (it.hasNext()){
            Stock stock = it.next();
            text += "Grade: "+stock.getGrade() + "  ";
            text += "Price: "+stock.getPrice() + "  ";
            text += "Amount: "+stock.getCurrentAmount() + "\n" + "\n";
        }
        return text;
    }

    //Getter method that return the array list of stock
    public ArrayList<Stock> getListOfStock(){
        return list_of_stock;
    }
    
    public void addStock(Stock s){
        list_of_stock.add(s);
    }
}
