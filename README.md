# Mutation tools test
This is a simple set of examples of how to run various Java mutation testing tools via Maven.

To run the JUnit tests, use `mvn test`. You should see output ending with:

    -------------------------------------------------------
     T E S T S
    -------------------------------------------------------
    Running uk.ac.york.cs.AppTest
    Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.046 sec

    Results :

    Tests run: 4, Failures: 0, Errors: 0, Skipped: 0

    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 2.572 s
    [INFO] Finished at: 2014-11-25T14:34:40+00:00
    [INFO] Final Memory: 10M/81M
    [INFO] ------------------------------------------------------------------------


## PIT
To run the PIT mutation testing tool, use `mvn test` and then `mvn org.pitest:pitest-maven:mutationCoverage`. You should see output ending with:

    --------------------------------------------------------------------------------
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 3.787 s
    [INFO] Finished at: 2014-11-25T14:36:15+00:00
    [INFO] Final Memory: 6M/81M
    [INFO] ------------------------------------------------------------------------

The mutation testing results will be at `target/pit-reports/DATE/index.html` (human-readable report) and at `target/pit-reports/DATE/mutations.xml` (machine processable report).

Note that to add support for PIT to the project, the following XML had to be added under the `<build><plugins>...</plugins></build>` part of `pom.xml`:

    <plugin>
        <groupId>org.pitest</groupId>
        <artifactId>pitest-maven</artifactId>
        <version>1.1.2</version>
        <configuration>
            <targetClasses>
                <param>uk.ac.york.cs*</param>
            </targetClasses>
            <targetTests>
                <param>uk.ac.york.cs*</param>
            </targetTests>
            <outputFormats>
              <param>HTML</param>
              <param>XML</param>
            </outputFormats>
        </configuration>
    </plugin>
    
Further configuration options for PIT are described at: http://pitest.org/quickstart/maven/

