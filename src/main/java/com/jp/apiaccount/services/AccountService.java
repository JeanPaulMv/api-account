package com.jp.apiaccount.services;

import com.jp.apiaccount.documents.Account;

import java.util.List;

public interface AccountService {

    Account createAccount(Account account);
    List<Account> findAllAccounts();
    Account updateAccount(String id,Account account);
    void deleteAccount(String id);

    Account findByDocumentNumber(String documentNumber);
    Account findById(String id);

}
