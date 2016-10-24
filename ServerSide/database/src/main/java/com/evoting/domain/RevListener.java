package com.evoting.domain;

import org.hibernate.envers.RevisionListener;



public class RevListener implements RevisionListener {
    public void newRevision(Object obj) {
        RevEntity revEntity = (RevEntity) obj;

        revEntity.setUserType("Admin");
        //revEntity.setUsername("???"); //TODO
        revEntity.setAction("???");
    }
}
