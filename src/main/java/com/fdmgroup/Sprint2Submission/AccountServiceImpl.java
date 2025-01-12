package com.fdmgroup.Sprint2Submission;

import java.util.List;

public class AccountServiceImpl implements AccountService {

	private AccountReaderDAO accReaderDao;
	private AccountWriterDAO accWriterDao;
	
	public AccountServiceImpl(AccountReaderDAO accReaderDao, AccountWriterDAO accWriterDao) {
		this.accReaderDao = accReaderDao;
		this.accWriterDao = accWriterDao;
	}
	
	@Override
    public List<Account> getAccounts() {
        return accReaderDao.readAccounts();
    }

    @Override
    public void removeAccount(Account account) {
        accWriterDao.deleteAccount(account);
    }

    @Override
    public Account createAccount(Account account) {
        return accWriterDao.createAccount(account);
    }

}
