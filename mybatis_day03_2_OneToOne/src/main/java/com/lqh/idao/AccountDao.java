package com.lqh.idao;

import com.lqh.domain.Account;
import com.lqh.domain.AccountVo;
import com.lqh.domain.User;

import java.util.List;

public interface AccountDao {
    public List<AccountVo> findAccountVo();

    public List<Account> findAllAccount();
}
