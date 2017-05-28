package com.example.hazelcast.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicReference;
import com.hazelcast.core.ILock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by mehyil on 27.05.2017.
 */
@Service
public class HazelcastActiveService implements ActiveService {
    @Autowired
    private HazelcastInstance instance;

    public void active() {
        ILock lock = instance.getLock("activeLock");
        try {
            if (lock.tryLock(10, TimeUnit.SECONDS)) {
                try {
                    IAtomicReference<Boolean> activeStatus = instance.getAtomicReference("activeStatus");
                    if (activeStatus.compareAndSet(null,true)) {
                        System.out.println("We are started!");
                    }
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}