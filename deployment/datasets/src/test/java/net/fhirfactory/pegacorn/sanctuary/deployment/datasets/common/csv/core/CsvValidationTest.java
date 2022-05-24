package net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.DateUtils;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.CSVParsingException;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.CSVRowBean;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.MoneyTypeEnum;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.annotation.Date;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.annotation.ExpectedValues;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.annotation.Length;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.annotation.Mandatory;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.annotation.Money;
import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.csv.core.validator.annotation.Number;

/**
 * Tests for the CSV validators.
 * 
 * @author Brendan Douglas
 *
 */
public class CsvValidationTest {

    @Test
    public void testMandatoryValidator() {
        try {
            CSVRowBean bean = new CSVRowBean(new String[] { "B","" }) {

                @Override
                protected int getNumberOfColumns() {
                    return 2;
                }

                public void setField1(String field1) {
                    rowData[0] = field1;
                }

                public void setField2(String field2) {
                    rowData[1] = field2;
                }

                public String getField1() {
                    return rowData[0];
                }

                @Mandatory
                public String getField2() {
                    return rowData[1];
                }
            };

            boolean isValid = bean.validate();
            assertFalse(isValid);
            assertEquals("Field2 is mandatory", bean.getValidationErrors().get(0));
        } catch (CSVParsingException e) {
            fail("There should not be a CsvParsingException");
        }
    }

    @Test
    public void testLengthValidator() {
        try {
            CSVRowBean bean = new CSVRowBean(new String[] { "BBBBB", "BBBBBBBBBB", "BBBB", "BBBBBBBBBBB", "BBBBBBBBBBBBBB","" }) {

                @Override
                protected int getNumberOfColumns() {
                    return 6;
                }

                public void setField1(String field1) {
                    rowData[0] = field1;
                }

                public void setField2(String field2) {
                    rowData[1] = field2;
                }

                public void setField3(String field3) {
                    rowData[2] = field3;
                }

                public void setField4(String field4) {
                    rowData[3] = field4;
                }

                @Length(min = 5, max = 10)
                public String getField1() {
                    return rowData[0];
                }

                @Length(min = 5, max = 10)
                public String getField2() {
                    return rowData[1];
                }

                @Length(min = 5, max = 10)
                public String getField3() {
                    return rowData[2];
                }

                @Length(min = 5, max = 10)
                public String getField4() {
                    return rowData[3];
                }

                @Length(max = 10)
                public String getField5() {
                    return rowData[4];
                }
            };

            boolean isValid = bean.validate();
            assertFalse(isValid);
            assertEquals("Field3 length invalid.  Length must be: 5 to 10 characters", bean.getValidationErrors().get(0));
            assertEquals("Field4 length invalid.  Length must be: 5 to 10 characters", bean.getValidationErrors().get(1));
            assertEquals("Field5 length invalid.  Length must be: Maximum 10 characters", bean.getValidationErrors().get(2));
        } catch (CSVParsingException e) {
            fail("There should not be a CsvParsingException");
        }
    }

    @Test
    public void testExpectedValuesValidator() {
        try {
            CSVRowBean bean = new CSVRowBean(new String[] { "Test", "Three" }) {

                @Override
                protected int getNumberOfColumns() {
                    return 2;
                }

                public void setField1(String field1) {
                    rowData[0] = field1;
                }

                public void setField2(String field2) {
                    rowData[1] = field2;
                }

                @ExpectedValues(values = { "One", "Two", "Three", "Four" })
                public String getField1() {
                    return rowData[0];
                }

                @ExpectedValues(values = { "One", "Two", "Three", "Four" })
                public String getField2() {
                    return rowData[1];
                }
            };

            boolean isValid = bean.validate();
            assertFalse(isValid);
            assertEquals("Field1 contains a not expected value", bean.getValidationErrors().get(0));
        } catch (CSVParsingException e) {
            fail("There should not be a CsvParsingException");
        }
    }

    @Test
    public void testDateValidator() {
        try {
            CSVRowBean bean = new CSVRowBean(new String[] { "01-Jan-2000", "2000-01-15", "01-Jan-20" }) {

                @Override
                protected int getNumberOfColumns() {
                    return 3;
                }

                public void setField1(String field1) {
                    rowData[0] = field1;
                }

                public void setField2(String field2) {
                    rowData[1] = field2;
                }

                public void setField3(String field3) {
                    rowData[2] = field3;
                }

                @Date(DateUtils.DD_MMM_YYYY)
                public String getField1() {
                    return rowData[0];
                }

                @Date(DateUtils.DD_MMM_YYYY)
                public String getField2() {
                    return rowData[1];
                }

                @Date() // Uses the default format.
                public String getField3() {
                    return rowData[2];
                }
            };

            boolean isValid = bean.validate();
            assertFalse(isValid);
            assertEquals("Field2 has an incorrect date format.  Required format is: dd-MMM-yyyy", bean.getValidationErrors().get(0));
        } catch (CSVParsingException e) {
            fail("There should not be a CsvParsingException");
        }
    }

    /**
     * Makes sure only 1 error per field is returned and if a mandatory validator is
     * configured it is the first to be run,
     */
    @Test
    public void testMultipleValidatorsOneField() {
        try {
            CSVRowBean bean = new CSVRowBean(new String[] { "B","" }) {

                @Override
                protected int getNumberOfColumns() {
                    return 2;
                }

                public void setField1(String field1) {
                    rowData[0] = field1;
                }

                public void setField2(String field2) {
                    rowData[1] = field2;
                }

                public String getField1() {
                    return rowData[0];
                }

                @Length(min = 10, max = 20)
                @Mandatory
                public String getField2() {
                    return rowData[1];
                }
            };

            boolean isValid = bean.validate();
            assertFalse(isValid);
            assertEquals("Field2 is mandatory", bean.getValidationErrors().get(0));
        } catch (CSVParsingException e) {
            fail("There should not be a CsvParsingException");
        }
    }

    @Test
    public void testNumberValidator() {
        try {
            CSVRowBean bean = new CSVRowBean(new String[] { "ABC", "10.0", "0000000100.6", "-1000.00", "100" }) {

                @Override
                protected int getNumberOfColumns() {
                    return 5;
                }

                public void setField1(String field1) {
                    rowData[0] = field1;
                }

                public void setField2(String field2) {
                    rowData[1] = field2;
                }

                public void setField3(String field3) {
                    rowData[2] = field3;
                }

                public void setField4(String field4) {
                    rowData[3] = field4;
                }

                public void setField5(String field5) {
                    rowData[4] = field5;
                }

                @Number(type = Integer.class)
                public String getField1() {
                    return rowData[0];
                }

                @Number(type = Integer.class)
                public String getField2() {
                    return rowData[1];
                }

                @Number(type = Double.class)
                public String getField3() {
                    return rowData[2];
                }

                @Number(type = Double.class)
                public String getField4() {
                    return rowData[3];
                }

                @Number(type = Double.class)
                public String getField5() {
                    return rowData[4];
                }
            };

            boolean isValid = bean.validate();
            assertFalse(isValid);
            assertEquals(2, bean.getValidationErrors().size());
            assertEquals("Field1 is an invalid number. The number should be: Integer", bean.getValidationErrors().get(0));
            assertEquals("Field2 is an invalid number. The number should be: Integer", bean.getValidationErrors().get(1));
        } catch (CSVParsingException e) {
            fail("There should not be a CsvParsingException");
        }
    }

    @Test
    public void testMoneyValidator() {
        try {
            CSVRowBean bean = new CSVRowBean(new String[] { "ABC", "10.0", "-10", "0", "0", "-100", "100.00", "1000", "-1000", "0" }) {

                @Override
                protected int getNumberOfColumns() {
                    return 10;
                }

                @Money(type = MoneyTypeEnum.POSITIVE)
                public String getField1() {
                    return rowData[0];
                }

                @Money(type = MoneyTypeEnum.NEGATIVE)
                public String getField2() {
                    return rowData[1];
                }

                @Money(type = MoneyTypeEnum.POSITIVE)
                public String getField3() {
                    return rowData[2];
                }

                @Money(type = MoneyTypeEnum.NEGATIVE)
                public String getField4() {
                    return rowData[3];
                }

                @Money(type = MoneyTypeEnum.POSITIVE)
                public String getField5() {
                    return rowData[4];
                }

                @Money(type = MoneyTypeEnum.NEGATIVE)
                public String getField6() {
                    return rowData[5];
                }

                @Money(type = MoneyTypeEnum.POSITIVE)
                public String getField7() {
                    return rowData[6];
                }

                @Money(type = MoneyTypeEnum.ANY)
                public String getField8() {
                    return rowData[7];
                }

                @Money(type = MoneyTypeEnum.ANY)
                public String getField9() {
                    return rowData[8];
                }

                @Money(type = MoneyTypeEnum.ANY)
                public String getField10() {
                    return rowData[9];
                }
            };

            boolean isValid = bean.validate();
            assertFalse(isValid);
            List<String> expected = new ArrayList<String>();
            expected.add("Field1 is an invalid money amount");
            expected.add("Field2 must be: NEGATIVE");
            expected.add("Field3 must be: POSITIVE");
            assertEquals(expected, bean.getValidationErrors());
        } catch (CSVParsingException e) {
            fail("There should not be a CsvParsingException");
        }
    }
}
