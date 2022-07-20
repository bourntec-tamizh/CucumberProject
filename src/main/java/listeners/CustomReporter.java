package listeners;


import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomReporter implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        for (ISuite suite : suites) {

            System.out.println("Suite name - " + suite.getName());

            Map<String, ISuiteResult> suiteResults = suite.getResults();

            for (ISuiteResult sr : suiteResults.values()) {

                ITestContext tc = sr.getTestContext();


                System.out.println("Test tag name: " + tc.getName() +
                        "Test start time: " + tc.getStartDate() +
                        "Test end time: " + tc.getEndDate() +
                        "Test report output dir: " + tc.getOutputDirectory());

                Collection<ITestNGMethod> failedMethods = tc.getFailedTests().getAllMethods();

                System.out.println("Total failed methods: "+ failedMethods.size() +" and those are: ");

                for(ITestNGMethod itm : failedMethods){
                    System.out.println("Method description: " + itm.getDescription() + " Name: " + itm.getMethodName());
                }


                System.out.println("Passed tests for suite is :" + tc.getPassedTests().getAllResults().size());
                System.out.println("Failed tests for suite is :" + tc.getFailedTests().getAllResults().size());
                System.out.println("Skipped tests for suite is:" + tc.getSkippedTests().getAllResults().size());
            }
        }
        System.out.println("Path - " + outputDirectory);
    }
}