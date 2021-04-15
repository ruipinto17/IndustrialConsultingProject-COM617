/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.utils.URIBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tebog
 */
public class ConnectionTest {
    
    private String metric = "jmeter";
    private String host = "webmontioring.uksouth.cloudapp.azure";
    private int Port = 443;
    private URI uri ;
    
    @BeforeClass
    public void setUpClass() {
        try {
            uri = new URIBuilder().setScheme("http").setHost(host).setPath("/query?pretty=true").setParameter("db", metric).setParameter("u", "ICPuser").setParameter("p", "ICPpass").build();
                    } catch (URISyntaxException ex) {
            Logger.getLogger(ConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
