package com.test;

import com.engine.KeywordEngine;
import org.testng.annotations.Test;

public class LoginTest {
     KeywordEngine keywordEngine= new KeywordEngine();

    @Test
    public void loginTest() throws Exception {

        keywordEngine.execution("Login");
    }


    @Test
    public void signUpTest() throws Exception {
        keywordEngine.execution("SignUp");
    }

}