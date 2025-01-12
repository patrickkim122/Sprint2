package com.fdmgroup.Sprint2Submission;

import java.util.List;

public interface AccountService {
	public List<Account> getAccounts();
	
	public void removeAccount(Account account);
	
	public Account createAccount(Account account);
	
}
