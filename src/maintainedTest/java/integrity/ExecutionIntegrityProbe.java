package integrity;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExecutionIntegrityProbe {

    @Test
    public void reportsRequestedOutcome() {
        String requestedOutcome =
                System.getProperty("integrityProbe", "pass");

        Assert.assertEquals(
                requestedOutcome,
                "pass",
                "The integrity probe received a requested failure outcome."
        );
    }
}