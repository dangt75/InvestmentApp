public abstract class Stock {
     private String grade;
     private int price; //Price for one stock
     private int CurrentAmount; // Attribute for amount of stock in the market
     private int amount_in_account; // Attribute for amount of stock in client portfolio

     // Constructor to create stock information in client portfolio
     public Stock(){
         this.amount_in_account= 0;
     }
     
     //Constructor to create new stock in market
     public Stock(String grade, int  CurrentAmount, int price){
         this.CurrentAmount = CurrentAmount;
         this.grade = grade;
         this.price= price;
     }

     public void sellOut(int amount){
        this.CurrentAmount-= amount;
     }
     public void buyIn(int amount){
        this.CurrentAmount+= amount;
     }

     public void increase(int amount){
        this.amount_in_account+= amount;
     }
     public void decrease(int amount){
        this.amount_in_account-= amount;
     }

     public String getGrade(){
         return this.grade;
     }

     public int getPrice(){
         return this.price;
     }

     public void setAccAmount(int number){
         this.amount_in_account= number;
    }

     public int getAccAmount(){
         return this.amount_in_account;
     }

     public void setCurrentAmount(int number){
        this.CurrentAmount= number;
   }

     public int getCurrentAmount(){
        return this.CurrentAmount;
    }

    // Abstract method which have different behaviour based on different stock grade
    public abstract int getValuation(int amount);
}
