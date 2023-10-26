package com.tpool.aopdemo.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public void addAccount(String x) {
        System.out.println("membership addAccount()");
        System.out.println("x: " + x);
    }

    @Override
    public void setAccount() {
        System.out.println("setting account here...");
    }

    @Override
    public List<String> RetrieveMemberships() {
        List<String> list = new ArrayList<>(List.of("Tresten", "Briana", "Noah", "Klari"));
        return list;
    }

    @Override
    public void getAccount(){
        throw new RuntimeException("oops");
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        }
//        catch (Exception e) {
//
//        }
//
//        System.out.println("Executing MembershipDAOImpl getAccount()...");
    }

    @Override
    public void simulateException() {
        throw new RuntimeException("\n\n====================================\nRUNTIME EXCEPTION ON LINE 50\n=============================\n\n");
    }
}
