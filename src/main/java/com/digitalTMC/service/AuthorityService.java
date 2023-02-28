package com.digitalTMC.service;

public interface AuthorityService {
    void resetAll();
    boolean checkCatalogConsistency();
    boolean isReadable(String pagePerm);
    boolean isWritable(String pagePerm);
}
