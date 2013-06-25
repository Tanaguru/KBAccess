/*
 * KBAccess - Collaborative database of accessibility examples
 * Copyright (C) 2012-2016  Open-S Company
 *
 * This file is part of KBAccess.
 *
 * KBAccess is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.kbaccess.webarchive.loader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author blebail
 */
public class WebarchiveLoader {
    
    private String url;
    private String jobName;
    private Process systemProcess;
    private String confPath="/etc/kbaccess/";
    private File context;
    private File newContext;
    
    private Date currentDate;
    private String archivePrefix = "http://localhost:8080/";
    private String dateFormat = "yyyyMMddHHmmss";
    private SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        
    /*
     * Utils
     */
    private void getContext() throws Exception {
        context = new File(this.confPath + "heritrix-crawler-beans.cxml");
        
        
        newContext = new File("/var/tmp/crawler-beans.cxml");
        
        if (newContext.exists()) {
            newContext.delete();
            newContext = new File("/var/tmp/crawler-beans.cxml");
        }
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(context)));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newContext)));
        
        String line = reader.readLine();
           
        line = line.replace("#URL", url);

        writer.write(line);
        writer.close();
    } 
        
    private void createJob(String jobName) throws Exception {
        this.jobName = jobName;
        systemProcess = Runtime.getRuntime().exec("curl -v -d \"createpath=" + this.jobName + "&action=create\" -k -u admin:heritrix --anyauth --location https://localhost:8443/engine");
        systemProcess.waitFor();        
    }
    
    private void submitContextFile() throws Exception {
        systemProcess = Runtime.getRuntime().exec("curl -v -T " + newContext.getAbsolutePath() + " -k -u admin:admin --anyauth --location https://localhost:8443/engine/job/" + this.jobName + "/jobdir/crawler-beans.cxml");
        systemProcess.waitFor();
    }
    
    private void buildJob() throws Exception {
        systemProcess = Runtime.getRuntime().exec("curl -v -d \"action=build\" -k -u admin:heritrix --anyauth --location https://localhost:8443/engine/job/" + this.jobName);
        systemProcess.waitFor();
    }
    
    private void launchJob() throws Exception {
        currentDate = new Date();
        systemProcess = Runtime.getRuntime().exec("curl -v -d \"action=launch\" -k -u admin:heritrix --anyauth --location https://localhost:8443/engine/job/" + this.jobName);
        systemProcess.waitFor();
    }
    
    public WebarchiveLoader() {
    }
    
    public String createWebarchive(String targetUrl) {
        this.url = targetUrl;
        String webarchiveUrl = null;
        
        try {
            getContext();
            createJob("newJob");
            /*submitContextFile();
            buildJob();
            launchJob();
            
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            StringBuilder strb = new StringBuilder();
            strb.append(archivePrefix);
            strb.append(sdf.format(currentDate));
            strb.append("/");
            strb.append(this.url.replaceAll("/http:/", "").replaceAll("/https:/", ""));
            webarchiveUrl = strb.toString();*/
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return webarchiveUrl;
    }
}
