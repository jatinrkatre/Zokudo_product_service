
package com.cards.zokudo.entities;

import javax.persistence.*;

@Entity
@Table(name = "page_identifier")
public class PageIdentifier extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "page_name")
    private String pageName;

    @Column(name = "is_page_active")
    private boolean isPageActive;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    private Privilege privilege;

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public boolean isPageActive() {
        return isPageActive;
    }

    public void setPageActive(boolean pageActive) {
        isPageActive = pageActive;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}
