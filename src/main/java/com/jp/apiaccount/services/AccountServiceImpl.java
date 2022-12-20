package com.jp.apiaccount.services;

import com.jp.apiaccount.documents.Account;
import com.jp.apiaccount.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account updateAccount(String id, Account account) {
        Account accountDb = accountRepository.findById(id).orElse(null);
        accountDb.setFullName(account.getFullName());
        accountDb.setPhoneNumber(account.getPhoneNumber());
        accountDb.setIdentityDocumentType(account.getIdentityDocumentType());
        accountDb.setIdentityDocumentNumber(account.getIdentityDocumentNumber());
        return accountRepository.save(accountDb);
    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account findByDocumentNumber(String documentNumber) {
        return accountRepository.findByIdentityDocumentNumber(documentNumber);
    }

    @Override
    public Account findById(String id) {
        return accountRepository.findById(id).orElse(null);
    }
}
