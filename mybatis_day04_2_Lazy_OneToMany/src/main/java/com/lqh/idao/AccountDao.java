package com.lqh.idao;

import com.lqh.domain.Account;

import java.util.List;

public interface AccountDao {
   /**
    * 根据uid查询账户
    */
   public Account findAccountById(int uid);
}
