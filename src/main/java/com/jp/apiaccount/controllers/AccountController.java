package com.jp.apiaccount.controllers;

import com.jp.apiaccount.documents.Account;
import com.jp.apiaccount.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> findAllAccounts(){
        List<Account> accounts = accountService.findAllAccounts();
        if (accounts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(accounts,HttpStatus.OK);
        }
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable("id") String id){
        Account account = accountService.findById(id);
        if (account != null){
            return new ResponseEntity<>(account,HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
      Account accountDb = accountService.findByDocumentNumber(account.getIdentityDocumentNumber());
      if (accountDb != null){
          return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
      }else {
          return new ResponseEntity<>(accountService.createAccount(account),HttpStatus.CREATED);
      }
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") String id,@RequestBody Account account){
        Account accountDb = accountService.findByDocumentNumber(account.getIdentityDocumentNumber());
        if (accountDb != null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }else {
            return new ResponseEntity<>(accountService.updateAccount(id, account), HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") String id){
        Account account = accountService.findById(id);
        if (account != null){
            accountService.deleteAccount(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
