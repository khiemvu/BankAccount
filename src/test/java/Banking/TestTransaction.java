package Banking;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: All_in_one
 * Date: 6/13/13
 * Time: 10:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestTransaction {
    @Mock
    private TransactionDAO transactionDAO;
    @Mock
    private Date time;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        TransactionService.initTransactionDAO(transactionDAO);

    }
    public Transaction createNewTransaction(long time, double amount,String des){
        return TransactionService.createTransaction("0123456789", time, amount, des);
    }
    @Test
    public void testSaveTimestampInTransactionDeposit(){

        when(time.getTime()).thenReturn(1000L);

        createNewTransaction(1000L,50.0,"Deposit");
        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);

        verify(transactionDAO).saveTransaction(argument.capture());

        assertEquals(1000L, argument.getValue().getTime());
        assertEquals(50, 0.01, argument.getValue().getBalance());
        assertEquals("Deposit", argument.getValue().getDes());
    }
    @Test
    public void testSaveTimestampInTransactionWithdraw(){

        when(time.getTime()).thenReturn(1000L);

        createNewTransaction(1000L,20.0,"Withdraw");

        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);

        verify(transactionDAO).saveTransaction(argument.capture());

        assertEquals(1000L, argument.getValue().getTime());
        assertEquals(20, 0.01, argument.getValue().getBalance());
        assertEquals("Withdraw", argument.getValue().getDes());
    }
    @Test
     public void testGetAllTransaction(){

        for(int i = 0; i < 3; i++){
            createNewTransaction(1000L + i,100.0 + i,"Deposit" + i);
        }
        for(int i = 0; i < 3; i++){
            createNewTransaction(1000L + i,20.0 + i,"Withdraw" + i);
        }

        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionDAO, times(6)).saveTransaction(argument.capture());

        List<Transaction> listTransaction = argument.getAllValues();
        when(transactionDAO.getAllTransaction("0123456789")).thenReturn(listTransaction);
        when(time.getTime()).thenReturn(1002L);
        assertEquals(6, listTransaction.size());
        assertEquals("Deposit0", listTransaction.get(0).getDes());
        assertEquals(100.0, 0.0,listTransaction.get(0).getBalance());
        assertEquals("Withdraw2", listTransaction.get(5).getDes());
        assertEquals(22.0, 0.0, listTransaction.get(5).getBalance());
        assertEquals(1002L, listTransaction.get(5).getTime());

    }
    @Test
    public void testGetAllTransactionInEspaceTime(){
        when(time.getTime()).thenReturn(52L);
        for(int i = 0; i < 3; i++){
            createNewTransaction(50L + i,100.0 + i,"Deposit" + i);
        }
        for(int i = 0; i < 3; i++){
            createNewTransaction(50L + i,20.0 + i,"Withdraw" + i);
        }

        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionDAO, times(6)).saveTransaction(argument.capture());

        List<Transaction> listTransaction = argument.getAllValues();
        when(transactionDAO.getAllTransaction("0123456789", 10L, 100L)).thenReturn(listTransaction);

        assertEquals(6, listTransaction.size());
        assertEquals("Deposit0", listTransaction.get(0).getDes());
        assertEquals(101.0, 0.0,listTransaction.get(1).getBalance());
        assertEquals("Withdraw1", listTransaction.get(4).getDes());
        assertEquals(21.0, 0.0, listTransaction.get(4).getBalance());
        assertEquals(52L, listTransaction.get(5).getTime());

    }
  @Test
  public void testGetNTransactionNewExecute(){
        when(time.getTime()).thenReturn(1100L, 1101L);
        for(int i = 0; i < 3; i++){
            createNewTransaction(1100L + i,20.0 + i,"Withdraw" + i);
        }

        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionDAO, times(3)).saveTransaction(argument.capture());

        List<Transaction> listTransaction = argument.getAllValues();
        when(transactionDAO.getAllTransaction("0123456789", 3)).thenReturn(listTransaction);

        assertEquals(3, listTransaction.size());
        assertEquals("Withdraw0", listTransaction.get(0).getDes());
        assertEquals(20.0, 0.0,listTransaction.get(0).getBalance());
        assertEquals(1100L, listTransaction.get(0).getTime());
        assertEquals(1101L, listTransaction.get(1).getTime());

    }
}
