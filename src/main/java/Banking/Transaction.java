package Banking;

/**
 * Created with IntelliJ IDEA.
 * User: All_in_one
 * Date: 6/13/13
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction {
    long timestamp;
    String numAcc;
    double amount;
    String des;

    public long getTime() {
        return this.timestamp;  //To change body of created methods use File | Settings | File Templates.
    }
    Transaction(String numAcc, long timestamp, double amount, String des){
        this.numAcc = numAcc;
        this.timestamp = timestamp;
        this.amount = amount;
        this.des = des;
    }

    public double getBalance() {
        return this.amount;  //To change body of created methods use File | Settings | File Templates.
    }

    public String getDes() {
        return this.des;  //To change body of created methods use File | Settings | File Templates.
    }

    public int getNumTransaction() {
        return 0;  //To change body of created methods use File | Settings | File Templates.
    }
}
