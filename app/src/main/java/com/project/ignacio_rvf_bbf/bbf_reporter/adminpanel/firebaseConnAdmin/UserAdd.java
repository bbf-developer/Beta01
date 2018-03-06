package com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin;

/**
 * Created by Ignacio-RVF-BBF on 06/03/2018.
 */

public class UserAdd {

    private String keyId;
    private String mail;
    private String password;
    private String atributo;

    public UserAdd() {
    }

    public UserAdd(String keyId, String mail, String password, String atributo) {
        this.keyId = keyId;
        this.mail = mail;
        this.password = password;
        this.atributo = atributo;
    }

    public String getKeyId() {
        return keyId;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getAtributo(){
        return atributo;
    }
}
