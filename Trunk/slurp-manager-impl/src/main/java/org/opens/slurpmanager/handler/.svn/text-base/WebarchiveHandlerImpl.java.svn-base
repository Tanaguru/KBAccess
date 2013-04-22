package org.opens.slurpmanager.handler;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.opens.slurpmanager.entity.factory.subject.WebarchiveFactory;
import org.opens.slurpmanager.entity.factory.subject.WebresourceFactory;
import org.opens.slurpmanager.crawler.Crawler;
import org.opens.slurpmanager.crawler.CrawlerImpl;
import org.opens.slurpmanager.entity.service.subject.WebarchiveDataService;
import org.opens.slurpmanager.entity.service.subject.WebresourceDataService;
import org.opens.slurpmanager.entity.subject.Webarchive;
import org.opens.slurpmanager.entity.subject.Webresource;
import org.opens.slurpmanager.exception.WebarchiveCreationException;
import org.opens.slurpmanager.scope.CrawlScope;

/**
 * Hello world!
 *
 */
public class WebarchiveHandlerImpl implements WebarchiveHandler {

    private static final String HTTP_PREFIX = "http://";
    private static final String HTTPS_PREFIX = "https://";

    /**
     * 
     */
    protected WebarchiveFactory webarchiveFactory;
    public WebarchiveFactory getWebarchiveFactory() {
        return webarchiveFactory;
    }

    public void setWebarchiveFactory(WebarchiveFactory webarchiveFactory) {
        this.webarchiveFactory = webarchiveFactory;
    }

    /**
     *
     */
    protected WebresourceFactory webresourceFactory;
    public WebresourceFactory getWebresourceFactory() {
        return webresourceFactory;
    }

    public void setWebresourceFactory(WebresourceFactory webresourceFactory) {
        this.webresourceFactory = webresourceFactory;
    }

    /**
     *
     */
    protected WebresourceDataService webresourceDataService;
    public WebresourceDataService getWebresourceDataService() {
        return webresourceDataService;
    }

    public void setWebresourceDataService(WebresourceDataService webresourceDataService) {
        this.webresourceDataService = webresourceDataService;
    }

    /**
     *
     */
    protected WebarchiveDataService webarchiveDataService;
    public WebarchiveDataService getWebarchiveDataService() {
        return webarchiveDataService;
    }

    public void setWebarchiveDataService(WebarchiveDataService webarchiveDataService) {
        this.webarchiveDataService = webarchiveDataService;
    }

    /**
     * 
     */
    protected Crawler crawler;
    public Crawler getCrawler() {
        return crawler;
    }

    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    /**
     * Create an archive from an url, a scope (page or site) and a description
     * @param url
     * @param scope
     * @param description
     * @return the accessible url of the webarchive, or an error message
     */
    @Override
    synchronized public String create(String url, CrawlScope scope, String description)
            throws WebarchiveCreationException {
//        Crawler localCrawler = initializeLocalCrawler();
        crawler.setUrl(addProtocolToUrl(url));
        crawler.setScope(scope);
        if (crawler.run()) {
            Webresource webresource;
            if (webresourceDataService.findByUrl(url) != null) {
                webresource = webresourceDataService.findByUrl(url);
            } else {
                webresource = webresourceDataService.create();
                webresource.setUrl(url);
                webresource.setDate(new Date());
            }

            Webarchive webarchive = webarchiveDataService.create();
            webresource.addWebarchive(webarchive);
            webarchive.setWebresourceParent(webresource);
            webarchive.setScope(scope.getType());
            webarchive.setDate(crawler.getResultDate());
            webarchive.setUrl(crawler.getResult());
            webarchive.setDescription(description);
            webresourceDataService.saveOrUpdate(webresource);
//            webarchiveDataService.saveOrUpdate(webarchive);
            return webarchive.getUrl();
        } else {
            throw new WebarchiveCreationException(((CrawlerImpl)crawler).getErrorMessage());
        }
    }

    public List<String> retrieveAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String retrieve(String url) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(String url) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(String url) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param url
     * @return
     */
    private String addProtocolToUrl(String url){
        if(url.startsWith(HTTP_PREFIX) || url.startsWith(HTTPS_PREFIX)) {
            return url;
        } else {
            StringBuffer strBuffer = new StringBuffer();
            strBuffer.append(HTTP_PREFIX);
            strBuffer.append(url);
            return strBuffer.toString();
        }
    }

//    private Crawler initializeLocalCrawler(){
//        Crawler localCrawler = new CrawlerImpl();
//        ((CrawlerImpl)localCrawler).setArchivePrefix(
//                ((CrawlerImpl)crawler).getArchivePrefix());
//        ((CrawlerImpl)localCrawler).setCrawlConfigFilePath(
//                ((CrawlerImpl)crawler).getCrawlConfigFilePath());
//        ((CrawlerImpl)localCrawler).setHeritrixFileName(
//                ((CrawlerImpl)crawler).getHeritrixFileName());
//        ((CrawlerImpl)localCrawler).setOutputDir(
//                ((CrawlerImpl)crawler).getOutputDir());
//        ((CrawlerImpl)localCrawler).setTemporaryDir(
//                ((CrawlerImpl)crawler).getTemporaryDir());
//        ((CrawlerImpl)localCrawler).setWarcDir(
//                ((CrawlerImpl)crawler).getWarcDir());
//        ((CrawlerImpl)localCrawler).setWarcExtension(
//                ((CrawlerImpl)crawler).getWarcExtension());
//        return localCrawler;
//    }

}