package com.example.application.it;

import com.vaadin.flow.component.login.testbench.LoginFormElement;
import org.junit.Assert;
import org.junit.Test;

public class LoginIT extends AbstractTest{
    public LoginIT() {
        super("");
    }

    @Test
    public void loginAsValidUserSucceeds(){
        LoginFormElement form = $(LoginFormElement.class).first();

        form.getUsernameField().setValue("user");
        form.getPasswordField().setValue("userpass");
        form.getSubmitButton().click();

        Assert.assertFalse($(LoginFormElement.class).exists());
    }
}
