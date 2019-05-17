/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ZakkiFarhan
 */
public class ControllerTest {
    
    public ControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Login method, of class Controller.
     */
    @Test
    public void testLogin() {
        System.out.println("Login");
        String a = "admin";
        String b = "123";
        Controller instance = null;
        instance.Login(a, b);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of Registration method, of class Controller.
     */
    @Test
    public void testRegistration() {
        System.out.println("Registration");
        String uname = "";
        String pass = "";
        String retype = "";
        boolean isCheck = false;
        Controller instance = null;
        instance.Registration(uname, pass, retype, isCheck);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of customerSetting method, of class Controller.
     */
    @Test
    public void testCustomerSetting() {
        System.out.println("customerSetting");
        String oldPass = "";
        String newPass = "";
        String newRePass = "";
        Controller instance = null;
        instance.customerSetting(oldPass, newPass, newRePass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }    
}
