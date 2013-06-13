package Banking;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

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
    private TransactionDAO transactionDAO = mock(TransactionDAO.class);
    private Date time = mock(Date.class);
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

}
