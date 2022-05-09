/**
* ECS414U - Object Oriented Programming
* Queen Mary University of London, 2021/22.
* MiniProject- Dang Toan Bui - Student ID: 210289895
* The app for investment trading of multiple clients to the stock market
*/

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JLabel;

public class InvestmentApp extends Frame{

    /*
     * Printing messages to the text area.
     */
    private static TextArea infoArea = new TextArea("Investment Trading App");

    public static void print(String text){
	infoArea.setText(text);
    }    
    //---

    
    private Agent agent;
    private Panel clientButtonsPanel;


    /**
     * This method prints the names of all clients in alphabetical order.
     */
    public void printClients(){
	String text = agent.getListOfClientNames();
	print(text);
    }

	/**
     * This method prints the information of all stocks in market.
     */
    public void printMarketInfo(){
	String text =agent.getMarketInfo();
	print(text);
	}

    /**
     * This method prints the information of a client.
     */
    public void printClientInfo(int index){
	String text = agent.getClientInfo(index);
	print(text);	
    }
    
    /**
     * This method takes all neccessary steps to add a client
     */
    public void addClient(String name, int age){
	agent.addClient(new Client(name, age));
	


	// Adding button in the client panel that will indicate the client number
	int numClients = agent.getNumberOfClients();
	Button btn = new Button("Client " +numClients);
	btn.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			printClientInfo(numClients - 1);
		}
	});	
	clientButtonsPanel.add(btn);	
	this.setVisible(true); 
    }

	/**
	 *  This method takes all the necessary steps when a client is removed.
	 */
	public void removeClient(int clientNumber){
	agent.removeClient(clientNumber);
	 
	clientButtonsPanel.remove(clientNumber-1);

	this.setVisible(true);
	}
    
    public InvestmentApp(){

	this.agent = new Agent();
	this.setLayout(new FlowLayout());
	
	// Print client list button
        Button reportButton=new Button("Print client list");
	reportButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evt) {
			printClients();
		}
	});
	this.add(reportButton); 

	// View market button

	    Button marketButton=new Button("View market");
	marketButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evt) {
			printMarketInfo();
		}
	});
	this.add(marketButton); 
    

	// Add client button
        Button addClientButton=new Button("Add client");
	addClientButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evt) {		
		    Prompt acp = new Prompt();
			TextField userText = new TextField(10);
			JLabel nameClient= new JLabel("Client name");
			TextField ageText = new TextField(10);
			JLabel ageClient= new JLabel("Client age");
			nameClient.setLabelFor(userText);
			acp.add(userText);
			acp.add(nameClient);
			acp.add(ageText);
			acp.add(ageClient);
			acp.addSubmitListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try{
						int clientAge = Integer.parseInt(ageText.getText());
					    if(clientAge< 18){
						print("Sorry the client is not old enough to play.");
					    }
					    else{
						addClient(userText.getText(), clientAge);
					    }
					}
					catch(NumberFormatException ex){
						print("Please input correct information in each input box");
					}
				}
			});
		    
		    acp.activate();
		}
	});

    this.add(addClientButton);
		
	// Remove client button
	    Button removeClientButton = new Button("Remove client");
	removeClientButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			Prompt rcp = new Prompt();
			TextField userText = new TextField(10);
			JLabel indexClient = new JLabel("Client number");
			
			indexClient.setLabelFor(userText);
			rcp.add(userText);
			rcp.add(indexClient);
            rcp.addSubmitListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int clientNumber = Integer.parseInt(userText.getText());
					removeClient(clientNumber);
	
				}
			});



			rcp.activate();
		}
	});

		this.add(removeClientButton);

	
	// Buy/Sell Stock button
        Button transferButton = new Button("Buy/Sell Stock");
	transferButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evt) {
			Prompt aca = new Prompt();
			TextField usernameText = new TextField(10);
			TextField amountText = new TextField(10);
			TextField gradeText = new TextField(10);
            TextField statement = new TextField(10);
            
			// Constructing new Java Label objects
            JLabel nameClient = new JLabel("Client name");
			JLabel money= new JLabel("Amount transfer");
			JLabel type= new JLabel("Grade type");
			JLabel request= new JLabel("Buy/Sell");

			nameClient.setLabelFor(usernameText);
			money.setLabelFor(amountText);
			type.setLabelFor(gradeText);
			request.setLabelFor(statement);

			aca.add(usernameText);
			aca.add(amountText);
			aca.add(nameClient);
			aca.add(money);
			aca.add(gradeText);
			aca.add(statement);
			aca.add(type);
			aca.add(request);

			aca.addSubmitListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					try{
					int amount= Integer.parseInt(amountText.getText());
					if(statement.getText().equals("Buy")){
						if(!agent.buyStock(usernameText.getText(), gradeText.getText(), amount)){
							print("Sorry! The client is not in the list.");
						}
						else{
							print("The transaction is successful");
						}
					}
					else if(statement.getText().equals("Sell")){	
						if(!agent.sellStock(usernameText.getText(), gradeText.getText(), amount)){
							print("Sorry! The client is not in the list.");
						}
						else{
							print("The transaction is successful");	
						}		
					}
					else{
						print("Please input the correct statement (Buy or Sell)");
					}
				    }
					catch(NumberFormatException ex){
						print("Please input correct information in each input box");
					} 
					catch(BalanceException ex) {
						print("Your account do not have enough balance to buy stock");
					}
					catch(GradeTypeException ex){
                        print("Please input the correct Stock grade type (Normal or Premium)");
					}
					catch(StockAmountException ex){
                        print("Sorry you do not have enough stock amount to sell!");
					}	
				}
			});
			aca.activate();
		}
	});
	
	this.add(transferButton);

	
	// Output console
	infoArea.setEditable(false);
	this.add(infoArea);

	// Client button panel
	clientButtonsPanel = new Panel();
	clientButtonsPanel.setLayout(new GridLayout(0,1));
	clientButtonsPanel.setVisible(true);
	this.add(clientButtonsPanel);

	
	

	// This is just so the X button closes our app
	WindowCloser wc = new WindowCloser();
        this.addWindowListener(wc); 

	this.setSize(500,500);
	this.setLocationRelativeTo(null); // Centers the window on the screen
	this.setVisible(true);

	// Write the market info initially out to the file market.txt
	 try{
		BufferedWriter writer = new BufferedWriter(new FileWriter("market.txt"));
		writer.write(agent.getMarketInfo());
        writer.close();
	 }
	 catch(Exception ex){
		ex.printStackTrace();
	 }
    }
    
    public static void main(String[] args){
	 new InvestmentApp();
    }
}
