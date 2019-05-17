/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
public class ModelTest {
    
    public ModelTest() {
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
     * Test of isValidRegistration method, of class Model.
     */
    @Test
    public void testIsValidRegistration() {
        System.out.println("isValidRegistration");
        String uname = "test";
        String pass = "test";
        Model instance = new Model();
        boolean expResult = true;
        boolean result = instance.isValidRegistration(uname, pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.        
    }

    /**
     * Test of isValidLogin method, of class Model.
     */
    @Test
    public void testIsValidLogin() {
        System.out.println("isValidLogin");
        String a = "admin";
        String b = "123";
        Model instance = new Model();
        boolean expResult = true;
        boolean result = instance.isValidLogin(a, b);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidOldPass method, of class Model.
     */
    //@Test
    public void testIsValidOldPass() {
        System.out.println("isValidOldPass");
        String a = "";
        Model instance = new Model();
        boolean expResult = false;
        boolean result = instance.isValidOldPass(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidNewPass method, of class Model.
     */
    //@Test
    public void testIsValidNewPass() {
        System.out.println("isValidNewPass");
        String a = "";
        Model instance = new Model();
        boolean expResult = false;
        boolean result = instance.isValidNewPass(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJumlahData method, of class Model.
     */
    //@Test
    public void testGetJumlahData() {
        System.out.println("getJumlahData");
        String tableName = "";
        Model instance = new Model();
        int expResult = 0;
        int result = instance.getJumlahData(tableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class Model.
     */
    //@Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Model instance = new Model();
        int expResult = 0;
        int result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getID method, of class Model.
     */
    //@Test
    public void testGetID() {
        System.out.println("getID");
        Model instance = new Model();
        int expResult = 0;
        int result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setID method, of class Model.
     */
    //@Test
    public void testSetID() {
        System.out.println("setID");
        int id = 0;
        Model instance = new Model();
        instance.setID(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
