package net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import net.fhirfactory.pegacorn.sanctuary.deployment.datasets.common.DateUtils;

/**
 * @author Jasen Schremmer
 */
public class DateUtilsTest {

    @Test
    public void testGetDate() throws Exception {
        Date result = DateUtils.getDate(null, null);
        assertNull(result);
    }

    @Test
    public void testFormat() throws Exception {
        String result = DateUtils.format(null, null);
        assertNull(result);
        result = DateUtils.format("", null, null);
        assertNull(result);
    }    
    
    @Test
    public void testConvertDateToDefaultTimeZone() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YYYY_MM_DD_T_HH_MM_SS_Z_INPUT);
        Date gmtDate = sdf.parse("2021-01-20T13:00:00GMT");
        Date result = DateUtils.convertDateToDefaultTimeZone(gmtDate);
        String resultStr = sdf.format(result);
        assertEquals("2021-01-21T00:00:00AEDT", resultStr);
    }

}
