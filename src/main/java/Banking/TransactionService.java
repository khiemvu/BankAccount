package Banking;

import java.util.List;

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

    public static Transaction createTransaction(String numAcc, long time, double amount, String des) {
        Transaction transaction = new Transaction(numAcc, time,amount,des);
        transactionDao.saveTransaction(transaction);
        return transaction;
    }

    public static List<Transaction> getAllTransaction(String accNumber){
        return transactionDao.getAllTransaction(accNumber);
    }
    public static List<Transaction> getAllTransaction(String accNumber, long startTime, long stopTime){
        return transactionDao.getAllTransaction(accNumber, startTime, stopTime);
    }
    public static List<Transaction> getAllTransaction(String accNumber, int number){
        return transactionDao.getAllTransaction(accNumber,number);
    }

}
