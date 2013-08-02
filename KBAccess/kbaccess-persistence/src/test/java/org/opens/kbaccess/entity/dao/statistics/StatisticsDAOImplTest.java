/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.kbaccess.entity.dao.statistics;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import org.opens.kbaccess.entity.dao.subject.TestcaseDAOImpl;
import org.opens.kbaccess.entity.statistics.AccountStatistics;
import org.opens.kbaccess.entity.subject.TestcaseImpl;
import org.opens.kbaccess.utils.AbstractDaoTestCase;

/**
 *
 * @author blebail
 */
public class StatisticsDAOImplTest extends AbstractDaoTestCase {
    
    public StatisticsDAOImplTest(String testName) {
        super(testName, "src/test/resources/datasets/dataset.xml");
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    protected StatisticsDAO getBean() {
        return (StatisticsDAO) springBeanFactory.getBean("statisticsDAO");
    }
    
    /**
     * Test of findAllReferenceTestOrderByTestcaseCount method, of class StatisticsDAOImpl.
     */
    public void testFindAllReferenceTestOrderByTestcaseCount() {
        System.out.println("findAllReferenceTestOrderByTestcaseCount : [nuc] desc");
        boolean asc = false;
        StatisticsDAO instance = getBean();
        
        Map<String, Long> result = instance.findAllReferenceTestOrderByTestcaseCount(asc);
        assertEquals(13, result.size());
        
        assertEquals(1L, result.get("AW21-0101").longValue());
        assertEquals(1L, result.get("Rgaa22-0101").longValue());
        assertEquals(1L, result.get("WCAG20-GL10").longValue());
        assertEquals(0L, result.get("WCAG20-010201").longValue());
        
        Long testcaseCount = Long.MAX_VALUE;
        
        for(Map.Entry<String, Long> entry : result.entrySet()) {
            Long entryTestcaseCount = entry.getValue();
            System.out.println(entryTestcaseCount);
            assertTrue(entryTestcaseCount <= testcaseCount);
            testcaseCount = entryTestcaseCount;
        }
        
        assertNull(result.get("zfdozefen"));
        
        System.out.println("findAllReferenceTestOrderByTestcaseCount : [nuc] asc");
        asc = true;
        result.clear();
        result = instance.findAllReferenceTestOrderByTestcaseCount(asc);
        assertEquals(13, result.size());
        
        testcaseCount = Long.MIN_VALUE;
        
        for(Map.Entry<String, Long> entry : result.entrySet()) {
            Long entryTestcaseCount = entry.getValue();
            assertTrue(entryTestcaseCount >= testcaseCount);
            testcaseCount = entryTestcaseCount;
        }
    }

    /**
     * Test of findAccountOrderByTestcaseCount method, of class StatisticsDAOImpl.
     */
    public void testFindAccountOrderByTestcaseCount() {
        System.out.println("findAccountOrderByTestcaseCount : [nuc] desc limit 2");
        boolean asc = false;
        int limit = 2;
        StatisticsDAO instance = getBean();
        
        List<AccountStatistics> result = instance.findAccountOrderByTestcaseCount(asc, limit);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId().longValue());
        assertEquals(3L, result.get(0).getTestcaseCount().longValue());
        
        System.out.println("findAccountOrderByTestcaseCount  : [nuc] asc no limit");
        asc = true;
        limit = 0;
        
        result.clear();
        result = instance.findAccountOrderByTestcaseCount(asc, limit);
        assertEquals(2, result.size());
        assertEquals(2L, result.get(0).getId().longValue());
        assertEquals(2L, result.get(0).getTestcaseCount().longValue());
    }
    
    /**
     * Test of getEntityManager method, of class StatisticsDAOImpl.
     */
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        StatisticsDAO instance = getBean();
        
        assertNotNull(instance.getEntityManager().getClass());
    }

    /**
     * Test of setEntityManager method, of class StatisticsDAOImpl.
     */
    
    public void testSetEntityManager() {
        System.out.println("setEntityManager");
        StatisticsDAO instance = getBean();
        
        EntityManagerFactory entityManagerFactory = (EntityManagerFactory)springBeanFactory.getBean("entityManagerFactory");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        instance.setEntityManager(entityManager);
        
        assertEquals(entityManager, instance.getEntityManager());
        assertNotNull(instance.getEntityManager());
    }
    
}
