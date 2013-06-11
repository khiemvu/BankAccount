package Banking;

/**
 * Created with IntelliJ IDEA.
 * User: All_in_one
 * Date: 6/12/13
 * Time: 0:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    private String number;
    private double balance;
    BankAccount(String number){
        this.number = number;

    }
    public String getNumber() {
        return this.number;  //To change body of created methods use File | Settings | File Templates.
    }
    public void setNumber(String number){
        this.number = number;
    }
    public double getBalance(){
        return this.balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }

    public String getMes() {
        return "Account really existed! The balance is: " + this.getBalance();  //To change body of created methods use File | Settings | File Templates.
    }
}
