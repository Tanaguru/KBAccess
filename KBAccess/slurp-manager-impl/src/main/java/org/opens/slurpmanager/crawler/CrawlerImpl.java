/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.slurpmanager.crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.archive.crawler.framework.CrawlJob;
import org.archive.net.UURIFactory;
import org.opens.slurpmanager.exception.WrongURIException;
import org.opens.slurpmanager.handler.WebarchiveHandlerImpl;
import org.opens.slurpmanager.scope.CrawlScope;

/**
 *
 * @author jkowalczyk
 */
public class CrawlerImpl implements Crawler {

    private static final String UNREACHABLE_URI_MSG = "The url is unreachable";
    private CrawlScope scope;
    private String url;
    private File currentJobOutputDir;
    private final String urlStrToReplace = "# URLS HERE";
    protected CrawlJob crawlJob;
    protected String resultFilePath;
    private String dateFormat = "yyyyMMddHHmmss";
    private SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    
    private static final Logger logger = Logger.getLogger(CrawlerImpl.class.getName());

    /**
     * 
     */
    private String crawlConfigFilePath = "/etc/slurp-manager/context/crawler/";

    public String getCrawlConfigFilePath() {
        return crawlConfigFilePath;
    }

    public void setCrawlConfigFilePath(String crawlConfigFilePath) {
        this.crawlConfigFilePath = crawlConfigFilePath;
    }
    /**
     * 
     */
    protected String heritrixFileName = "slurp-crawler-beans.cxml";

    public String getHeritrixFileName() {
        return heritrixFileName;
    }

    public void setHeritrixFileName(String heritrixFileName) {
        this.heritrixFileName = heritrixFileName;
    }
    /**
     *
     */
    protected String warcDir = "latest/warcs";

    public String getWarcDir() {
        return warcDir;
    }

    public void setWarcDir(String warcDir) {
        this.warcDir = warcDir;
    }
    /**
     * 
     */
    protected String warcExtension = ".warc";

    public String getWarcExtension() {
        return warcExtension;
    }

    public void setWarcExtension(String warcExtension) {
        this.warcExtension = warcExtension;
    }
    /**
     *
     */
    private String temporaryDir = "/var/tmp/slurp-manager";

    public String getTemporaryDir() {
        return this.temporaryDir;
    }

    public void setTemporaryDir(String temporaryDir) {
        this.temporaryDir = temporaryDir;
    }
    /**
     *
     */
    private File outputDir = new File("/var/tmp/wayback/warcs");

    public String getOutputDir() {
        return this.outputDir.getName();
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = new File(outputDir);
    }
    /**
     *
     */
    private String archivePrefix = "http://localhost:8080/wayback/wayback";

    public String getArchivePrefix() {
        return archivePrefix;
    }

    public void setArchivePrefix(String archivePrefix) {
        this.archivePrefix = archivePrefix;
    }
    private Date currentDate;
    @Override
    public Date getResultDate() {
        return currentDate;
    }

    @Override
    public String getResult() {
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return archivePrefix + sdf.format(currentDate) + "/" + url;
    }

    private String errorMessage;
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setScope(CrawlScope scope) {
        this.scope = scope;
    }

    @Override
    public boolean run() {
        setErrorMessage("");
        try {
            this.crawlJob = new CrawlJob(initializeCrawlContext(url));
        } catch (WrongURIException ex){
            errorMessage = ex.getMessage();
            return false;
        }
        if (crawlJob.isLaunchable()) {
            Logger.getLogger(WebarchiveHandlerImpl.class.getName()).info(
                    "crawljob is launchable");
            crawlJob.checkXML();
            crawlJob.launch();
            if (!crawlJob.isRunning()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(
                            WebarchiveHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    errorMessage = ex.getMessage();
                    return false;
                }
            }
        }
        Logger.getLogger(WebarchiveHandlerImpl.class.getName()).info(
                "is crawlJob running? " + crawlJob.isRunning());
        while (crawlJob.isRunning()) {
            try {
                if (crawlJob.isUnpausable()) {
                    crawlJob.getCrawlController().getFrontier().run();
                }
                Logger.getLogger(WebarchiveHandlerImpl.class.getName()).info(
                        "crawljob is running");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                errorMessage = e.getMessage();
                return false;
            }
        }
        crawlJob.terminate();
        Logger.getLogger(WebarchiveHandlerImpl.class.getName()).info(
                "crawljob terminated");
        if (crawlJob.teardown()) {
            Logger.getLogger(WebarchiveHandlerImpl.class.getName()).info(
                "crawljob teardown");
            if (!copyWarcFileIntoRepository()){
                errorMessage = UNREACHABLE_URI_MSG + " : " + url;
                Logger.getLogger(WebarchiveHandlerImpl.class.getName()).warning(errorMessage);
                return false;
            }
            removeConfigFile(currentJobOutputDir);
        }
        return true;
    }

    /**
     * This method initializes the heritrix context before starting the crawl
     * @return
     */
    private File initializeCrawlContext(String url) throws WrongURIException{
        // Create one directory
        currentDate = new Date();
        currentJobOutputDir = new File(temporaryDir + "/" + "crawl" + "-" + currentDate.getTime());
        if (!currentJobOutputDir.exists()) {
            boolean success = currentJobOutputDir.mkdir();
            if (success) {
                Logger.getLogger(WebarchiveHandlerImpl.class.getName()).info(
                        "Directory: " + currentJobOutputDir + " created");
            }
        }
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(crawlConfigFilePath + "/" + heritrixFileName));

            String c;
            StringBuffer newContextFile = new StringBuffer();
            while ((c = in.readLine()) != null) {
                if (c.equalsIgnoreCase(urlStrToReplace)) {
                    try {
                    newContextFile.append(UURIFactory.getInstance(url).toString());
                    } catch (IOException ex) {
                        Logger.getLogger(WebarchiveHandlerImpl.class.getName()).severe(ex.getMessage());
                        throw new WrongURIException(ex.getMessage());
                    }
                } else {
                    newContextFile.append(c);
                }
                newContextFile.append("\r");
            }
            FileWriter fw = new FileWriter(currentJobOutputDir.getPath() + "/" + heritrixFileName);
            fw.write(newContextFile.toString());
            fw.close();
            in.close();

        } catch (IOException ex) {
            Logger.getLogger(WebarchiveHandlerImpl.class.getName()).severe(ex.getMessage());
        }
        return new File(currentJobOutputDir.getPath() + "/" + heritrixFileName);
    }

    /**
     * Clean-up configuration files before leaving
     * @param file
     * @return
     */
    private boolean removeConfigFile(File file) {
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    removeConfigFile(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (file.delete());
    }

    /**
     * Copy the result of the crawl (warc file) into wayback directory
     * @param file
     * @return
     */
    private boolean copyWarcFileIntoRepository() {
        logger.info("copyWarcFileIntoRepository");
        
        boolean result = false;
        
        logger.info(currentJobOutputDir.getAbsolutePath() + "/" + warcDir);
        
        File warcFileDir = new File(currentJobOutputDir + "/" + warcDir);
        
        File sourceWarcFile = null;
        if (warcFileDir.exists()) {
            File[] files = warcFileDir.listFiles();
            if (files.length == 1) {
                String fileName = files[0].getName();
                if (fileName.substring(fileName.lastIndexOf("."), fileName.length()).equalsIgnoreCase(warcExtension)) {
                    sourceWarcFile = new File(warcFileDir.getPath() + "/" + fileName);
                    logger.info("sourceWarcFile " + sourceWarcFile);
                }
            }
        } else {
            logger.info("warcDirFIle does not exist");
        }
        
        FileInputStream sourceFile = null;
        FileOutputStream destinationFile = null;
        
        if (sourceWarcFile != null) {
            logger.info("sourceWarcFile != null");
            try {
                sourceFile = new FileInputStream(sourceWarcFile);
                destinationFile = new FileOutputStream(outputDir + "/" + sourceWarcFile.getName());
                logger.info(outputDir + "/" + sourceWarcFile.getName());
                byte buffer[] = new byte[512 * 1024];
                int nbLecture;
                while ((nbLecture = sourceFile.read(buffer)) != -1) {
                    destinationFile.write(buffer, 0, nbLecture);
                }
                result = true;
            } catch (FileNotFoundException f) {
                result = false;
                Logger.getLogger(WebarchiveHandlerImpl.class.getName()).severe(f.getMessage());
            } catch (IOException e) {
                result = false;
                Logger.getLogger(WebarchiveHandlerImpl.class.getName()).severe(e.getMessage());
            } finally {
                try {
                    sourceFile.close();
                } catch (Exception e) {
                    Logger.getLogger(WebarchiveHandlerImpl.class.getName()).severe(e.getMessage());
                }
                try {
                    destinationFile.close();
                } catch (Exception e) {
                    Logger.getLogger(WebarchiveHandlerImpl.class.getName()).severe(e.getMessage());
                }
            }
            saveArchiveDate(sourceWarcFile.getName());
            logger.info("saveArchiveDate " + sourceWarcFile.getAbsolutePath());
        } else {
            result = false;
        }
        if (result) {
            saveArchiveDate(sourceWarcFile.getName());
            logger.info("saveArchiveDate " + sourceWarcFile.getAbsolutePath());
        }
        
        logger.info("End of CopyWarcFileIntoRepository");
        return result;
    }

    /**
     * Save the date of the archive from its name
     * assuming that the archive name respects the following pattern
     * ${prefix}-${fetch-time}-${index}.warc
     * The fetch time is located between the two '-' characters.
     */
    private void saveArchiveDate(String archiveName) {
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            currentDate = sdf.parse(archiveName.substring(archiveName.indexOf('-') + 1,
                    archiveName.lastIndexOf('-')));
        } catch (ParseException ex) {
            Logger.getLogger(CrawlerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (currentDate != null) {
            //wayback seems to add 1 second to the date comparing to heritrix date
            currentDate.setTime(currentDate.getTime() + 1000);
        } else {
            currentDate = new Date();
        }
    }
}
