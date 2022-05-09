import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Collectors;
public class Agent{

    private ArrayList<Client> clients;
	private Market market;
	
    public Agent(){
	clients = new ArrayList<>();
	market = new Market();
	market.addStock(new NormalStock("Normal", 10000, 10));
	market.addStock(new PremiumStock("Premium", 5000, 20));
    }

	//Self Explained
    public int getNumberOfClients(){
	return clients.size();
    }

	//Method that get a particular client info
    public String getClientInfo(int clientNumber){
    	Client c = clients.get(clientNumber);
	String text = "";
	text += "Name: " + c.getName() + "\n";
	text += "Age: " + c.getAge() + "\n";
	text += "Funds: " + c.getFunds() + "\n";
	text += "Normal Stock: " + c.getNormStock() + "\n";
	text += "Premium Stock: " + c.getPremStock() + "\n";
	return text;
    }
    
	//Method that get the list of client in alphabetical order
    public String getListOfClientNames(){
	ArrayList<Client> sortedClient = (ArrayList<Client>) clients.stream()
	.sorted(Comparator
	.comparing(client -> client.getName())).collect(Collectors.toList()); //Using Java stream to sort the client name in alphabetical order
	String text = "";
	Iterator<Client> it = sortedClient.iterator();
	while (it.hasNext()){
	    Client c = it.next();
	    text += c.getName() + "\n";
	}
	return text;
    }
    
	//Method to return the information of market
	public String getMarketInfo(){
	return market.getListOfStock("");
	}
    
    public void addClient(Client c){
	clients.add(c);
    }

	public void removeClient(int clientNumber){
	clients.remove(clientNumber-1);
	}
   
	//Method to identify the client who want to buy stock
    public boolean buyStock(String clientName, String grade, int amount)throws  BalanceException, GradeTypeException{
	Iterator<Client> it = clients.iterator();
	boolean found = false;	
	while (it.hasNext()){
	    Client c = it.next();
	    if (c.getName().equals(clientName)){
		found = true;
		  if(grade.equals("Normal")){
			if(c.getFunds()<market.getListOfStock().get(0).getValuation(amount)){
				throw new BalanceException(); // The exception is thrown if the user do not have enough account balance to buy stock
			}
			else{
				c.withdraw(market.getListOfStock().get(0).getValuation(amount));
			    c.buyNormStock(amount);
			    market.getListOfStock().get(0).sellOut(amount);
			}
		  }
		  else if(grade.equals("Premium")){
			if(c.getFunds()<market.getListOfStock().get(1).getValuation(amount)){
				throw new BalanceException(); // Explained above
			}
			else{
				c.withdraw(market.getListOfStock().get(1).getValuation(amount));
			    c.buyPremStock(amount);
			    market.getListOfStock().get(1).sellOut(amount);
			}
		  }
		  else{
			  throw new GradeTypeException(); // The Exception is thrown if the user input of Stock grade is incorrect
		  }
	    }
	}
	return found;
    }
 
	//Method to identify the client who want to sell stock
	public boolean sellStock(String clientName, String grade, int amount) throws StockAmountException, GradeTypeException{
		Iterator<Client> it = clients.iterator();
		boolean found = false;	
		while (it.hasNext()){
			Client c = it.next();
			if (c.getName().equals(clientName)){
			found = true;
			    if(grade.equals("Normal")){
			  if(c.getNormStock()<amount){
					throw new StockAmountException(); // The exception is thrown if client do not have enough stock amount to sell
				}
				else{
					c.deposit(market.getListOfStock().get(0).getValuation(amount));
				    c.sellNormStock(amount);
				    market.getListOfStock().get(0).buyIn(amount);
				}
			  }
			  else if(grade.equals("Premium")){
				if(c.getPremStock()<amount){
					throw new StockAmountException(); // Explained above
				}
				else{
					c.deposit(market.getListOfStock().get(1).getValuation(amount));
				    c.sellPremStock(amount);
				    market.getListOfStock().get(1).buyIn(amount);
				}
			  }
			  else{
				throw new GradeTypeException(); // The Exception is thrown if the user input of Stock grade is incorrect
			  }
			}
		}
	return found;
	}
}
