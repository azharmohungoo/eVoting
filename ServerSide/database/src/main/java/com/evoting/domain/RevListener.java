package com.evoting.domain;

import org.hibernate.envers.RevisionListener;

/**
 * Created by Azhar on 2016/07/17.
 */

public class RevListener implements RevisionListener {
    public void newRevision(Object obj) {
        RevEntity revEntity = (RevEntity) obj;

        revEntity.setUserType("Admin");
        //revEntity.setUsername("???"); //TODO
        revEntity.setAction("???");
    }
}
