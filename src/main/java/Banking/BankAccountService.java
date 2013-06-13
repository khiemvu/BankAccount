package Banking;

/**
 * Created with IntelliJ IDEA.
 * User: All_in_one
 * Date: 6/12/13
 * Time: 0:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountService {
    private static BankAccountDAO bankAccountDAO;
    public static void setAccountDao(BankAccountDAO bankAccountDAO) {
        //To change body of created methods use File | Settings | File Templates.
        BankAccountService.bankAccountDAO = bankAccountDAO;
    }

    public static BankAccount createAccount(String numberAccount) {
        BankAccount account = new BankAccount(numberAccount);
        bankAccountDAO.saveAccount(account);
        return account;
    }
    public static BankAccount getAccount(String number) {
        BankAccount account = bankAccountDAO.findAccount(number);
        if(account != null){
            return account;
        }
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
    public static BankAccount doDeposit(String accNumber, double amount, String des) {
        BankAccount account = getAccount(accNumber);
        account.setBalance(account.getBalance() + amount);
        bankAccountDAO.saveAccount(account);
        return account;  //To change body of created methods use File | Settings | File Templates.
    }

    public static BankAccount doWithdraw(String accNumber, double amount, String des) {
        BankAccount account = getAccount(accNumber);
        account.setBalance(account.getBalance() - amount);
        bankAccountDAO.saveAccount(account);
        return account;  //To change body of created methods use File | Settings | File Templates.
    }


}
