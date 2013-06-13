package Banking;

/**
 * Created with IntelliJ IDEA.
 * User: All_in_one
 * Date: 6/13/13
 * Time: 10:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionService {
    private static TransactionDAO transactionDao;

    public static void initTransactionDAO(TransactionDAO transactionDao){
        TransactionService.transactionDao = transactionDao;
    }

    public static Transaction createTransactionDeposit(String numAcc, long time, double amount, String deposit) {
        Transaction transaction = new Transaction(numAcc, time,amount,deposit);
        transactionDao.saveTransaction(transaction);
        return transaction;
    }
    public static Transaction createTransactionWithdraw(String numAcc, long time, double amount, String deposit) {

        Transaction transaction = new Transaction(numAcc,time,amount,deposit);
        transactionDao.saveTransaction(transaction);
        return transaction;
    }

}
