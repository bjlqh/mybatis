package com.lqh.idao;

import com.lqh.domain.Account;

import java.util.List;

public interface AccountDao {
   public List<Account> findAllAccounts();
}
