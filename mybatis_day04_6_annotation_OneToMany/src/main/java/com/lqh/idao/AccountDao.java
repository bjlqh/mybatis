package com.lqh.idao;

import com.lqh.domain.Account;
import com.lqh.domain.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountDao {

    @Select("select * from account where uid =#{uid}")
    public Account findAccountById(int uid);

    public void saveAccount(Account account);
}
