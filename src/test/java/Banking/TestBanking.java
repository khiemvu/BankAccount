package Banking;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: All_in_one
 * Date: 6/12/13
 * Time: 0:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestBanking {
    private BankAccountDAO mockAccountData = mock(BankAccountDAO.class);
    @Before
    public void setUp(){
        reset(mockAccountData);
        BankAccountService.setAccountDao(mockAccountData);
    }
    @Test
    public void testCreateNewBankAccount(){
        BankAccountService.createAccount("0123456789");

        ArgumentCaptor<BankAccount> argumentData = ArgumentCaptor.forClass(BankAccount.class);
        verify(mockAccountData).saveAccount(argumentData.capture());
        assertEquals(0.0, argumentData.getValue().getBalance());
        assertEquals("0123456789", argumentData.getValue().getNumber());
    }
    @Test
    public void testGetInfoAccount(){
        BankAccount account = BankAccountService.getAccount("0123456789");
        verify(mockAccountData).getAccount("0123456789");
        assertEquals("Account really existed! The balance is: 0.0", account.getMes());
    }
    @Test
    public void testWhenUserDoDeposit(){
        BankAccount account = BankAccountService.createAccount("0123456789");
        BankAccountService.doDeposit(200,account);
        ArgumentCaptor<BankAccount> argumentData = ArgumentCaptor.forClass(BankAccount.class);
        verify(mockAccountData,times(2)).saveAccount(argumentData.capture());
        List<BankAccount> listAccount = argumentData.getAllValues();
        assertEquals(100, 0.01, listAccount.get(1).getBalance());

    }
    @Test
    public void testWhenUserWithDraw(){
        BankAccount account = BankAccountService.createAccount("0123456789");
        BankAccountService.doDeposit(200,account);
        BankAccountService.doWithdraw(150,account);
        ArgumentCaptor<BankAccount> argumentData = ArgumentCaptor.forClass(BankAccount.class);
        verify(mockAccountData,times(3)).saveAccount(argumentData.capture());
        List<BankAccount> listAccount = argumentData.getAllValues();
        assertEquals(50, 0.01, listAccount.get(2).getBalance());
    }
}
