/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timah.poc;

import java.util.Date;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


/**
 *
 * @author Syarbeat
 */
@Stateless
public class InsertCSVFileToDB implements InsertCSVFileToDBRemote {

    @Schedule(hour = "*", minute = "*", second = "*/30")
    @Override
    public void myTimer() {
        System.out.println("Proses file spm-sap [START]: " + new Date());
        InsertData();
        System.out.println("Proses file spm-sap [END] --: " + new Date());
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

  
    public void InsertData() {
        
        CamelContext context = new DefaultCamelContext();
        
        try{
            context.addRoutes(new RouteBuilder() {
                public void configure() {
                from("file:E:/data/inbox?noop=false")
                .to("file:E:/data/outbox");
                }
            });
            
            context.start();
            Thread.sleep(10000);
            context.stop();
            
        }catch(Exception ex)
        {
            
        }
    }
}
