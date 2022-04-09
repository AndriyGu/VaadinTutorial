package com.example.application.it.elements;

import com.vaadin.flow.component.login.testbench.LoginFormElement;
import com.vaadin.flow.component.orderedlayout.testbench.VerticalLayoutElement;
import com.vaadin.testbench.annotations.Attribute;
import org.junit.Assert;
import org.junit.Test;

@Attribute(name = "class", contains = "login-view")
public class LoginViewElement extends VerticalLayoutElement {
    public boolean login(String userName, String password){
            LoginFormElement form = $(LoginFormElement.class).first();

            form.getUsernameField().setValue(userName);
            form.getPasswordField().setValue(password);
            form.getSubmitButton().click();

            return !$(LoginViewElement.class).onPage().exists();
        }
    }
