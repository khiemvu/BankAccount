package Banking;

/**
 * Created with IntelliJ IDEA.
 * User: All_in_one
 * Date: 6/12/13
 * Time: 0:44 PM
 * To change this template use File | Settings | File Templates.
 */
interface BankAccountDAO {
    public void saveAccount(BankAccount capture);
    public BankAccount getAccount(String s);

    public BankAccount findAccount(String accNumber);
}
