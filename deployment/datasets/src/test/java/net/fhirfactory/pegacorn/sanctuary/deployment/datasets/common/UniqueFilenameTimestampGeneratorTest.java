package net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.DateUtils;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.UniqueFilenameTimestampGenerator;

/**
 * 
 * @author Brendan Douglas
 *
 */
public class UniqueFilenameTimestampGeneratorTest {
    private static final DateTimeFormatter FILE_NAME_TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern(DateUtils.YYYYMMDD_HHMMSS);
    
    private static UniqueFilenameTimestampGenerator uniqueFilenameTimestampGenerator = new UniqueFilenameTimestampGenerator();
    
    private static final int MAX_THREADS = 20;
    
    /**
     * Make sure a duplicate timestamp is not generated for a prefix.
     * 
     * @throws Exception
     */
    @Test
    public void testUniqeTimestampGeneration() throws Exception {
        Set<String>generatedDateTimes = new HashSet<>();
        
        List<GenerateTimestamp>threads = new ArrayList<>();
        
        for (int i = 0; i < MAX_THREADS; i++) {
            GenerateTimestamp thread = new GenerateTimestamp("TEST1");
            threads.add(thread);
        }
        
        for (int i = 0; i < MAX_THREADS; i++) {
            GenerateTimestamp thread = new GenerateTimestamp("TEST2");
            threads.add(thread);
        }
        
        for (int i = 0; i < MAX_THREADS; i++) {
            GenerateTimestamp thread = new GenerateTimestamp("TEST3");
            threads.add(thread);
        }

        for (GenerateTimestamp thread : threads) {    
            thread.start();
        }

        for (GenerateTimestamp thread : threads) {
            thread.join();
        }
        
        for (GenerateTimestamp thread : threads) {
            ZonedDateTime generatedDateTime = thread.getGeneratedDateTime();
            boolean doesNotExist = generatedDateTimes.add(thread.getPrefix() + generatedDateTime.format(FILE_NAME_TIMESTAMP_FORMAT));
          
                        
            if (!doesNotExist) {
               fail("A duplicate was generated: " + thread.getPrefix() + generatedDateTime.format(FILE_NAME_TIMESTAMP_FORMAT));
           }
       }
        
        
      assertEquals(MAX_THREADS * 3, generatedDateTimes.size());
    }

    
    
    class GenerateTimestamp extends Thread {
        
        private String prefix;
        private ZonedDateTime generatedDateTime;
        
        public GenerateTimestamp(String prefix) {
            this.prefix = prefix;
        }
        
        public ZonedDateTime getGeneratedDateTime() {
            return generatedDateTime;
        }
        
        public String getPrefix() {
            return prefix;
        }

        @Override
        public void run() {
            generatedDateTime = uniqueFilenameTimestampGenerator.getUniqueTimestamp(prefix);            
        }
    }
}
