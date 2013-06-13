package Banking;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    @Mock
    private BankAccountDAO mockAccountData;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        BankAccountService.setAccountDao(mockAccountData);
    }
    public BankAccount createBankAccount(String num){
        return BankAccountService.createAccount(num);
    }

    @Test
    public void testCreateNewBankAccount(){
        createBankAccount("0123456789");

        ArgumentCaptor<BankAccount> argumentData = ArgumentCaptor.forClass(BankAccount.class);
        verify(mockAccountData).saveAccount(argumentData.capture());

        assertEquals(0.0, argumentData.getValue().getBalance());
        assertEquals("0123456789", argumentData.getValue().getNumber());
    }
    @Test
    public void testGetInfoAccount(){
        BankAccount account = createBankAccount("0123456789");

        ArgumentCaptor<BankAccount> argumentData = ArgumentCaptor.forClass(BankAccount.class);
        when(mockAccountData.getAccount("0123456789")).thenReturn(account);

        verify(mockAccountData).saveAccount(argumentData.capture());
        assertEquals("Account really existed! The balance is: 0.0", account.getMes());

    }
    @Test
    public void testWhenUserDoDeposit(){
        BankAccount account = createBankAccount("0123456789");

        when(mockAccountData.findAccount(anyString())).thenReturn(account);

        BankAccountService.doDeposit(account.getNumber(),200, "Deposit");

        ArgumentCaptor<BankAccount> argumentData = ArgumentCaptor.forClass(BankAccount.class);

        verify(mockAccountData,times(2)).saveAccount(argumentData.capture());
        List<BankAccount> listAccount = argumentData.getAllValues();
        assertEquals(200.0, listAccount.get(1).getBalance());

    }
    @Test
    public void testWhenUserWithDraw(){
        BankAccount account = createBankAccount("0123456789");

        when(mockAccountData.findAccount(anyString())).thenReturn(account);

        BankAccountService.doDeposit(account.getNumber(), 200, "Deposit");
        BankAccountService.doWithdraw(account.getNumber(),150, "Withdraw");

        ArgumentCaptor<BankAccount> argumentData = ArgumentCaptor.forClass(BankAccount.class);

        verify(mockAccountData,times(3)).saveAccount(argumentData.capture());
        List<BankAccount> listAccount = argumentData.getAllValues();
        assertEquals(50.0, listAccount.get(2).getBalance());
    }
}
