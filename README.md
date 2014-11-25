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

## Judy

Run the following command from the `judy` directory in the terminal: `java -jar judy-2.1.0.jar -w ../target -c classes -t test-classes`

This will create a `judy/logging/judy.log` file for debugging and a `judy-result.xml` results file. At the moment this seems to result in 0 mutants on my machine, and I'm not quite sure why...

Also, the following addition to the pom.xml should allow Judy to be run via `mvn antrun:ant` but this fails at the moment due to the workspace parameter not being a directory, which is odd because the equivalent command on the terminal works fine...

      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-antrun-plugin</artifactId>
        <version>1.7</version>
        <configuration>
          <target>
            <java dir="judy" jar="judy/judy-2.1.0.jar" fork="true">
              <jvmarg value="-XX:MaxPermSize=2048m"/>
              <jvmarg value="-Xmx2048m"/>
              <jvmarg value="-Xms2048m"/>
              <jvmarg value="-Xmn512m"/>
              <jvmarg value="-Xss512k"/>
              <jvmarg value="-XX:+UseG1GC"/>
              <arg value="-w ${project.build.directory}"/>
              <arg value="-c classes"/>
              <arg value="-t test-classes"/>
            </java>
          </target>
        </configuration>
      </plugin>
      