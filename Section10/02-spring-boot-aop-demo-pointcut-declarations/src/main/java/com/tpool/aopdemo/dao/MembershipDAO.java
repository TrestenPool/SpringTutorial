package com.tpool.aopdemo.dao;

import java.util.List;

public interface MembershipDAO {
    void addAccount(String x);
    void getAccount();
    void setAccount();

    List<String> RetrieveMemberships();

    void simulateException();
}
