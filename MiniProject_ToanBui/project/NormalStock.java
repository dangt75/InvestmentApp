public class NormalStock extends Stock {
    // The amount of normal stock that each client would have initially
    private final int INITIAL_AMOUNT = 3000;

    public NormalStock() {
        super();
        this.setAccAmount(INITIAL_AMOUNT);
    }

    public NormalStock(String grade, int CurrentAmount, int price){
        super(grade, CurrentAmount, price);  
    }

    @Override
    public int getValuation(int amount) {
        return amount*this.getPrice();
    }
}
