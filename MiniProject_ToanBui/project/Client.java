
public class Client extends User{
    private Account account;
    private Stock premStock;
    private Stock normStock;
    private int age;
    
    public Client(String name, int age){
	super(name);
	this.account = new Account();
    this.normStock = new NormalStock();
    this.premStock = new PremiumStock();
    this.age = age;
    }

    public void deposit(int amount){
	this.account.deposit(amount);
    }

    public void withdraw(int amount){
	this.account.withdraw(amount);
    }

    public void buyNormStock(int amount){
        this.normStock.increase(amount);
    }

    public void sellNormStock(int amount){
        this.normStock.decrease(amount);
    }

    public void buyPremStock(int amount){
        this.premStock.increase(amount);
    }

    public void sellPremStock(int amount){
        this.premStock.decrease(amount);
    }

    public int getFunds(){
	return this.account.getBalance();
    }

    public int getAge(){
    return this.age;
    }

    public int getNormStock(){
    return this.normStock.getAccAmount();
    }

    public int getPremStock(){
        return this.premStock.getAccAmount();
    }
}
