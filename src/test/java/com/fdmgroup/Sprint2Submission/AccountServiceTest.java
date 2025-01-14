package com.fdmgroup.Sprint2Submission;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

class AccountServiceTest {

	AccountServiceImpl accountService;
	
	@Mock
	AccountReaderDAO mockAccReaderDao;
	
	@Mock
	AccountWriterDAO mockAccWriterDao;
	
	@BeforeEach
	void setUp() throws Exception {
		accountService = new AccountServiceImpl(mockAccReaderDao, mockAccWriterDao);
	}

	@Test
	void testWhenThereAreNoAccounts_GetAccount_ReturnsNull() {
		
		// Arrange
		List<Account> expected = new ArrayList<>();
		
		// Act
		when(mockAccReaderDao.readAccounts()).thenReturn(expected);
		
		List<Account> actual = accountService.getAccounts();
		
		// Assert
		assertEquals(actual, expected);
	}
	
	@Test
	void testWhenThereIsOneAccount_GetAccount_ReturnsOneAccount() {
		
		// Arrange
		Account account1 = new Account();
		List<Account> expected = new ArrayList<>();
		expected.add(account1);
		
		// Act
		when(mockAccReaderDao.readAccounts()).thenReturn(expected);
		
		List<Account> actual = accountService.getAccounts();
		
		// Assert
		assertEquals(actual, expected);
	}
	
	@Test
	void testWhenThereAreTwoAccounts_GetAccount_ReturnsBothAccounts() {
		
		// Arrange
		Account account1 = new Account();
		Account account2 = new Account();
		List<Account> expected = new ArrayList<>();
		expected.add(account1);
		expected.add(account2);
		
		// Act
		when(mockAccReaderDao.readAccounts()).thenReturn(expected);
		
		List<Account> actual = accountService.getAccounts();
		
		// Assert
		assertEquals(actual, expected);
	}
	
	@Test
	void testWhen_GetAccount_IsCalledThreeTimes_AccountReaderDAO_ReadAccounts_IsCalledThreeTimes() {
		
		// Arrange
		List<Account> expected = new ArrayList<>();
		
		// Act
		when(mockAccReaderDao.readAccounts()).thenReturn(expected);
		
		accountService.getAccounts();
		accountService.getAccounts();
		accountService.getAccounts();
		
		// Assert
		verify(mockAccReaderDao, times(3)).readAccounts();
	}
	
	@Test
	void testWhen_RemoveAccount_IsCalledOnce_AccountWriterDAO_DeleteAccount_IsCalledOnce() {
		
		// Arrange
		Account account1 = new Account();
		
		// Act
		accountService.removeAccount(account1);
		
		// Assert
		verify(mockAccWriterDao, times(1)).deleteAccount(account1);
	}
	
	@Test
	void testWhen_RemoveAccount_IsCalledTwice_AccountWriterDAO_DeleteAccount_IsCalledTwice() {
		
		// Arrange
		Account account1 = new Account();
		Account account2 = new Account();
		
		// Act
		accountService.removeAccount(account1);
		accountService.removeAccount(account2);
		
		// Assert
		verify(mockAccWriterDao, times(1)).deleteAccount(account1);
		verify(mockAccWriterDao, times(1)).deleteAccount(account2);
		
	}
	
	@Test
	void testWhen_CreateAccount_IsCalled_AccountWriterDAO_CreateAccount_ReturnsAccount() {
		
		// Arrange
		Account account1 = new Account();
		Account expected = account1;
		// Act
		when(mockAccWriterDao.createAccount(account1)).thenReturn(account1);
		Account result = accountService.createAccount(account1);
		// Assert
		assertEquals(result, expected);
		
	}
	
	@Test
	void testWhen_CreateAccount_IsCalledTwice_AccountWriterDAO_CreateAccount_IsCalledTwice() {
		
		// Arrange
		Account account1 = new Account();
		// Act
		when(mockAccWriterDao.createAccount(account1)).thenReturn(account1);
		accountService.createAccount(account1);
		accountService.createAccount(account1);
		// Assert
		verify(mockAccWriterDao, times(2)).createAccount(account1);
		
	}

}
