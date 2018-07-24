package cz.gov.data.rpp.xls2rdf;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class) public class TestUtils {

    private final String input;
    private final String expected;
    public TestUtils(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters public static Collection<Object[]> xlsToParse() {
        return Arrays.asList(new Object[][] {
            {"Nařízení Evropského parlamentu a Rady (EU) č. 70/2012 ze dne 18. ledna 2012 "
             + "o statistickém vykazování silniční přepravy zboží",
             "nařízení-evropského-parlamentu-a-rady--eu--č"
             + ".-70-2012-ze-dne-18.-ledna-2012-o-statistickém-vykazování-silniční-přepravy-zbo"
             + "ží"}});
    }

    @Test public void test() {
        Assert.assertEquals(expected, Utils.urify(input));
    }
}
