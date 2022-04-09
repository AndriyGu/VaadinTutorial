package com.example.application.it;

import com.example.application.it.elements.LoginViewElement;
import com.vaadin.flow.component.login.testbench.LoginFormElement;
import org.junit.Assert;
import org.junit.Test;

public class LoginIT extends AbstractTest{
    public LoginIT() {
        super("");
    }

    @Test
    public void loginAsValidUserSucceeds(){
        LoginViewElement loginView = $(LoginViewElement.class).onPage().first();
        Assert.assertTrue(loginView.login("user", "userpass"));


//        LoginFormElement form = $(LoginFormElement.class).first();
//
//        form.getUsernameField().setValue("user");
//        form.getPasswordField().setValue("userpass");
//        form.getSubmitButton().click();
//
//        Assert.assertFalse($(LoginFormElement.class).exists());
    }

    @Test
    public void loginAsValidUserFails() {
        LoginViewElement loginView = $(LoginViewElement.class).onPage().first();
        Assert.assertFalse(loginView.login("user", "urpass"));
    }
}
