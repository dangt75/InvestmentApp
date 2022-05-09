public class PremiumStock extends Stock{
    // The amount of premium stock that each client would have initially
    private final int INITIAL_AMOUNT = 1000;

    public PremiumStock(){
        super();
        this.setAccAmount(INITIAL_AMOUNT);
    }

    public PremiumStock(String grade, int currentAmount, int price){
        super(grade, currentAmount, price);
    }

    // Premium stock will be doubled the price of itself when buy/sell
    @Override
    public int getValuation(int amount) {
        return 2*(amount*this.getPrice());
    }  
}