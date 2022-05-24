package net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.CSVReader;

import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.CSV;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.CSVRowBean;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.HasHeaderRow;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.MoneyTypeEnum;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.annotation.Date;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.annotation.ExpectedValues;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.annotation.Length;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.annotation.Mandatory;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.annotation.Money;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.annotation.Number;

/**
 * Base class for all CSV test cases. This class provides some convenience
 * methods to compare data.
 * 
 * @author Brendan Douglas
 *
 */
public class BaseCSVTestCase {

    /**
     * Compare each data item stored in the CSV object against what is in the file.
     * This use OpenCSV to re-read the file one line at a time so the comparison can
     * be made.
     * 
     * @param csv
     * @param file
     */
    protected void compareCSV(CSV csv, File file) {

        try {
            int lines = 0;
            boolean firstLine = true;

            try (CSVReader reader = new CSVReader(new FileReader(file))) {
                String[] nextLine;

                while ((nextLine = reader.readNext()) != null) {    
                    
                    if (firstLine && csv instanceof HasHeaderRow) {
                        String[] expectedheadings = ((HasHeaderRow)csv).getRequiredHeadings();
                        
                        for (int i = 0; i < nextLine[i].length(); i++) {
                            assertEquals(expectedheadings[i], nextLine[i]);
                        }
                                                
                        firstLine = false;
                    } else {
                    
                    
                        String[] csvRowData = (csv.getRow(lines)).getRowData();
    
                        assertEquals(nextLine.length, csvRowData.length);
    
                        for (int i = 0; i < csvRowData.length; i++) {    
                            assertEquals(csvRowData[i], nextLine[i]); // Compare
                        }
    
                        lines++;
                    }
                }

                assertEquals(lines, csv.getRows().size());
            }
        } catch (Exception e) {
            fail("Error processing CSV file");
        }
    }

    /**
     * Compare a JSON string against valid content in a file.
     * 
     * @param json
     * @param file
     */
    protected void compareJSON(String json, String fileName) {

        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            content = content.replace("\r\n", "");
            json = json.replace("\n", "");

            assertEquals(json, content);
        } catch (IOException e) {
            fail("Error reading json file");
        }
    }

    /**
     * Does supplied method name have mandatory annotation.
     * 
     * @param methodName
     * @return
     */
    protected boolean isFieldMandatory(CSVRowBean rowBean, String methodName) {
        try {
            Method method = rowBean.getClass().getMethod(methodName);

            return method.getAnnotation(Mandatory.class) != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Does the supplied method have the date annotation with the supplied format.
     * 
     * @param rowBean
     * @param methodName
     * @param format
     * @return
     */
    protected boolean isFieldADate(CSVRowBean rowBean, String methodName, String format) {
        try {
            Method method = rowBean.getClass().getMethod(methodName);

            Date dateAnnotation = method.getAnnotation(Date.class);

            if (dateAnnotation == null) {
                return false;
            }

            return dateAnnotation.value().equals(format);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Does the supplied method have the date annotation with the supplied format.
     * 
     * @param rowBean
     * @param methodName
     * @param format
     * @return
     */
    protected boolean isFieldANumber(CSVRowBean rowBean, String methodName, Class<? extends java.lang.Number> type) {
        try {
            Method method = rowBean.getClass().getMethod(methodName);

            Number numberAnnotation = method.getAnnotation(Number.class);

            if (numberAnnotation == null) {
                return false;
            }

            return numberAnnotation.type().equals(type);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Does the supplied method have the money annotation with the supplied format.
     * 
     * @param rowBean
     * @param methodName
     * @param format
     * @return
     */
    protected boolean isFieldAMoney(CSVRowBean rowBean, String methodName, MoneyTypeEnum type) {
        try {
            Method method = rowBean.getClass().getMethod(methodName);

            Money numberAnnotation = method.getAnnotation(Money.class);

            if (numberAnnotation == null) {
                return false;
            }

            return numberAnnotation.type().equals(type);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Does the supplied method have the length annotation with the correct min and
     * max.
     * 
     * @param rowBean
     * @param methodName
     * @param minLength
     * @param maxLength
     * @return
     */
    protected boolean isFieldALength(CSVRowBean rowBean, String methodName, int minLength, int maxLength) {
        try {
            Method method = rowBean.getClass().getMethod(methodName);

            Length lengthAnnotation = method.getAnnotation(Length.class);

            if (lengthAnnotation == null) {
                return false;
            }

            return lengthAnnotation.min() == minLength && lengthAnnotation.max() == maxLength;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Does the supplied method have the length annotation with the correct min and
     * max.
     * 
     * @param rowBean
     * @param methodName
     * @param minLength
     * @param maxLength
     * @return
     */
    protected boolean isFieldAnExpectedValues(CSVRowBean rowBean, String methodName, String[] expectedValues) {
        try {
            Method method = rowBean.getClass().getMethod(methodName);

            ExpectedValues expectedValuesAnnotation = method.getAnnotation(ExpectedValues.class);

            if (expectedValuesAnnotation == null) {
                return false;
            }

            if (expectedValuesAnnotation.values().length != expectedValues.length) {
                return false;
            }

            for (int i = 0; i < expectedValues.length; i++) {
                String expectedValue = expectedValues[i];
                String configuredExpecteValue = expectedValuesAnnotation.values()[i];

                if (!expectedValue.equals(configuredExpecteValue)) {
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns the number of methods with the supplied annotation.
     * 
     * @param rowBean
     * @param annotationClass
     * @return
     */
    protected int getAnnotationCount(CSVRowBean rowBean, Class<? extends Annotation> annotationClass) {
        int count = 0;

        try {
            Method[] methods = rowBean.getClass().getMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(annotationClass)) {
                    count++;
                }
            }

            return count;
        } catch (Exception e) {
            return 0;
        }
    }
}
