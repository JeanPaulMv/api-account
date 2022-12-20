package com.jp.apiaccount.repositories;

import com.jp.apiaccount.documents.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account,String> {

    public Account findByIdentityDocumentNumber(String numberDocument);
}
