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
    @Test
    public void testSaveTimestampInTransactionDeposit(){

        when(time.getTime()).thenReturn(1000L);

        TransactionService.createTransactionDeposit("0123456789", time.getTime(), 50.0, "Deposit");
        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);

        verify(transactionDAO).saveTransaction(argument.capture());

        assertEquals(1000L, argument.getValue().getTime());
        assertEquals(50, 0.01, argument.getValue().getBalance());
        assertEquals("Deposit", argument.getValue().getDes());
    }
    @Test
    public void testSaveTimestampInTransactionWithdraw(){

        when(time.getTime()).thenReturn(1000L);

        TransactionService.createTransactionWithdraw("0123456789", time.getTime(), 20.0, "Withdraw");

        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);

        verify(transactionDAO).saveTransaction(argument.capture());

        assertEquals(1000L, argument.getValue().getTime());
        assertEquals(20, 0.01, argument.getValue().getBalance());
        assertEquals("Withdraw", argument.getValue().getDes());
    }
    @Test
     public void testGetAllTransaction(){
        when(time.getTime()).thenReturn(1000L);
        TransactionService.createTransactionWithdraw("0123456789", time.getTime(), 200.0, "Deposit");
        TransactionService.createTransactionWithdraw("0123456789", time.getTime(), 20.0, "Withdraw");
        TransactionService.createTransactionWithdraw("0123456789", time.getTime(), 20.0, "Withdraw");

        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionDAO, times(3)).saveTransaction(argument.capture());

        List<Transaction> listTransaction = argument.getAllValues();
        when(transactionDAO.getAllTransaction("0123456789")).thenReturn(listTransaction);

        assertEquals(3, listTransaction.size());
        assertEquals("Deposit", listTransaction.get(0).getDes());
        assertEquals(200.0, 0.0,listTransaction.get(0).getBalance());
        assertEquals("Withdraw", listTransaction.get(2).getDes());
        assertEquals(20.0, 0.0, listTransaction.get(0).getBalance());
        assertEquals(1000L, listTransaction.get(0).getTime());

    }
    @Test
    public void testGetAllTransactionInEspaceTime(){
        when(time.getTime()).thenReturn(50L);
        TransactionService.createTransactionWithdraw("0123456789", time.getTime(), 200.0, "Deposit");
        TransactionService.createTransactionWithdraw("0123456789", time.getTime(), 20.0, "Withdraw");
        TransactionService.createTransactionWithdraw("0123456789", time.getTime(), 20.0, "Withdraw");

        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionDAO, times(3)).saveTransaction(argument.capture());

        List<Transaction> listTransaction = argument.getAllValues();
        when(transactionDAO.getAllTransaction("0123456789", 10L, 100L)).thenReturn(listTransaction);

        assertEquals(3, listTransaction.size());
        assertEquals("Deposit", listTransaction.get(0).getDes());
        assertEquals(200.0, 0.0,listTransaction.get(0).getBalance());
        assertEquals("Withdraw", listTransaction.get(2).getDes());
        assertEquals(20.0, 0.0, listTransaction.get(0).getBalance());
        assertEquals(50L, listTransaction.get(0).getTime());

    }

}
