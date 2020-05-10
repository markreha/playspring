# A simple Java Spring application that can be used for testing in various Cloud Platforms like Azure and Heroku. See the following directions for deploying a Java Spring application to Azure, AWS, Heroku, and Google Cloud.

## Important Fies
* Procfile - start file for both Heroku Cloud Platform

## Deployment to Cloud Platform Instructions
## Azure
1. Log into the Azure Portal. 
2. Click the '+ Create a resource' icon from the Azure Portal.
3. From the 'Search the Marketplace' search box select Web App + MySQL option. Click the Create button, give your App a name, make sure the Microsoft Azure for Students subscription is selected, and click the Create button. After a few minutes your application will be created. It is advised that you pin this application to your Dashboard. 
NOTE: with the Azure for Students Starter account you can only provision 2 containers in the same App Service Plan (i.e. within the same Region). If you provision more than 2 containers Microsoft will shut down all your applications. You will then either have to delete some of your applications or simply use a different region when you create your application.
4. Open your application from your Dashboard by clicking on it from your Dashboard. Select the Overview menu from the left pane and then click the URL from the right pane to open your application. Make sure the default Microsoft Azure Developer page is displayed. This is an important step! This will ensure that phpMyAdmin is accessible via single sign on. phpMyAdmin DOES NOT require any credentials to log in. If you get prompted for credentials, then access your application via its URL and/or simply wait and try again.
5. Click on the Configuration menu from the left pane. Click the General Settings tab. Select the Java stack, select Java 8, and Tomcat from the Java Container options.
6. Initialize the MySQL Database:
    * Open your application.
    * Under the Settings section click the MySQL In App icon, make sure your Database is enabled, and click the Manage icon to open phpMyAdmin. Import your Database DDL.
    * Under the Development Tools section click the Console icon.
    * Navigate to the D:\home\data\mysql directory and display the ‘MYSQLCONNSTR_localdb.txt' file using the type command to get your MySQL Connection Properties. Note the DB connection information to get your DB hostname, post, and credentials.
7. Possible Configuration for Application:
    * If you have a large application (especially a Spring MVC Framework application) you will need to increase the JVM heap size (which defaults to a very small 16M startup heap size). If your application runs out of memory or does not start due to running out of memory then you will need to complete the following steps.
    * Under the Development Tools section click the Advanced Tools icon. Click on the Go hyperlink to open the Tool. Click on the CMD menu option under the Debug Console menu. Navigate to the site->wwwroot directory.
    * Create a file named web.config in the wwwroot directory.
    * Add the following content to the web.config file (you might need to change the version of Tomcat in the httpPlatform tag to match the version of Tomcat setup in your Azure Configuration options):
	<?xml version="1.0" encoding="UTF-8"?>
		<configuration>
		  <system.webServer>
			<handlers>
			  <remove name="httpPlatformHandlerMain" />
			  <add name="httpPlatformHandlerMain" path="*" verb="*" modules="httpPlatformHandler" resourceType="Unspecified"/>
			</handlers>
			<httpPlatform processPath="D:\Program Files (x86)\apache-tomcat-8.5.34\bin\startup.bat" requestTimeout="00:04:00" arguments="start" startupTimeLimit="60" startupRetryCount="3" >
				<environmentVariables>
					<environmentVariable name="CATALINA_OPTS" value="-Xms256m -Xmx256m  -Dport.http=%HTTP_PLATFORM_PORT%" />
				</environmentVariables>
			  </httpPlatform>
		  </system.webServer>
		</configuration>
    * Stop your application. The start your application (do not restart but stop and start) for the configuration settings to take place.
8. Build and Deploy your Application:
    * Open your application.
    * Update your MySQL Database Connection properties in your application (note your hostname will need to be formatted as hostname:port).
    * In Eclipse run a Maven build script or right click on your project and use the Export->WAR file menu options to create a WAR file for your project. Rename the WAR file with a ZIP extension.
    * Under the Development Tools section click the Advanced Tools icon. Click on the Go hyperlink to open the Tool.
    * Click on the CMD menu option under the Debug Console menu. Navigate to the              site->wwwroot->webapps->ROOT directory. Drag and drop your application ZIP file onto the right edge of the web page (you will see a small zipper icon when your zip file is being extracted into your ROOT directory). DO NOT drag and drop your zip file to the middle of the page as this will simply copy the file to your ROOT directory. After a few minutes your application should deployed and accessible with your Azure application URL.
        * NOTE: one option for deployment and rather than using zip files and the Azure Zip Deploy features is to use an Eclipse Plug-in. The following extension can be installed in Eclipse and used to deploy a Spring application on to Azure:
	    * http://marketplace.eclipse.org/content/azure-toolkit-eclipse

## Heroku
1. Create app in Heroku:
    * Create App button from the main page. Give your application a name. Click the Create App button.
    * On the Project page, select the Deploy Tab, and link your application to your GitHub repository (BitBucket is not supported on Heroku). If you are not using GiHub, you can either copy your BitBucket repository to GitHub or use the Heroku CLI, as documented below.
    * On the Project page, select the Settings Tab, click the Add Buildpack button, for Java click the Java button, click the Save Changes button.
    * On the Project page, select the Resources Tab, under the Add-ons search for JawsDB MySQL, select JawsDB MySQL from the search list, select the Free plan, click the Provision button.
        * NOTE: If you fail to connect too many times to the database Heroku may lock you out from connecting to your database. If you repeatedly cannot connect to your database and are sure your configuration is correct, then delete your current JawsDB MySQL database Add-on and add a new JawsDB MySQL database.
2. Connect MySQL Workbench to the instance of MySQL Database. Run your DDL Script to configure the database.
3. You can deploy your application either thru the Heroku CLI or using a GitHub repository. Follow either steps below.
    * To deploy using the Heroku CLI use the following steps:
        * Download the Heroku Command Line Interface (CLI) from https://devcenter.heroku.com.
        * Build a WAR file using either Eclipse or Maven.
        * Deploy your WAR file by using the resources at https://devcenter.heroku.com/articles/war-deployment.
            * heroku war:deploy <path_to_war_file> --app <app_name>
        * Test the app: https://[APP NAME].herokuapp.com.
    * To deploy using GitHub and a Build Pipeline use the following steps:
        * Update your App Configuration as necessary in your GitHub repository or clone the Heroku GIT repository provided by Heroku (go to your App Settings to get the URL) and add your app code to the cloned repository.
        * Add the webapp-runner Maven Plugin to your POM file:
        ```xml
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-dependency-plugin</artifactId>
            <version>3.0.2</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals><goal>copy</goal></goals>
                    <configuration>
                        <artifactItems>
                            <artifactItem>
                                <groupId>com.github.jsimone</groupId>
                                <artifactId>webapp-runner</artifactId>
                                <version>8.5.31.0</version>
                                <destFileName>webapp-runner.jar</destFileName>
                            </artifactItem>
                        </artifactItems>
                    </configuration>
                </execution>
            </executions>
        </plugin>
        ```
    * Set the environment variable MAVEN_CUSTOM_OPTS in your App Settings to       -P[Profile Name] if you are using Maven Profiles.
    * Add a Maven Heroku Procfile to the root of your project:
        * web: java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war
    * To deploy using your GitHub repository and a Build Pipeline use the following steps:
        * Update your App Configuration as noted above and push the changes to your GIT repository.
        * Create Heroku Pipeline and add your Spring app to it.
        * Go to your Heroku Pipeline and click on your Spring app, select the Deploy menu option, and make sure the Enable Automatic Deploys is enabled from your master branch.
    * Start a Build by performing either of one the following operations:
        * Go to your Heroku Pipeline and click on your Spring app, select the Deploy menu option, and then click the Deploy Branch button.
        * Push a code change to your GitHub repository.
    * Test your app at: https://[APP NAME].herokuapp.com.

## AWS
1. Log into AWS and select Services from the main menu.
2. Select RDS.
3. Under the Create database section click the Create database button.
4. Select the MySQL engine option and the 5.6 version edition radio button. Check the 'Enable options for free tier'. Click the Next button.
5. Fill out the Specify DB details form:
    * From Settings enter DB instance identifier enter instance name (i.e., mydatabaseinstance).
    * From Settings enter Master username and password.
    * Click the Next button.
    * Under Network and Security section set the Public Accessibility setting to Yes.
    * Leave all defaults in the Configure advanced settings form.
6. From the RDS Dashboard select your database instance.
    * Your database URL is listed under the Connect section under the Endpoint value.
    * Make your database accessible from a Java application by clicking the Security groups link under the Details section for the database.
    * In the Security Group setup select the Inbound Tab. Click the Edit button. Under the Source dropdown select the Anywhere option.
    * In MySQL Workbench setup a connection using the AWS Database Endpoint URI and credentials. Create the 'cst-323' schema and tables by running the DDL script created from your development environment.
7. Create and Configure an AWS Tomcat Application:
    * Select Elastic Beanstalk service.
    * Click the 'Create new Application' link from the top right menu.
    * Give your Application Name (i.e., TestApp). Click the Create button.
    * Create your Application Environment by clicking the 'Create one now' link.
    * Select the 'Web server environment' and click the Select button.
    * Fill out the following fields in the Creating web server environment form:
        * From Environment information Domain: Give your Application a name (i.e. test-app).
        * From Base configuration: Select Tomcat from the Preconfigured platform options. Upload a WAR file of your Java application.
        * Click the Create Environment button. Wait for environment to get built.
        * From the Elastic Beanstalk application screen click the App URL to validate application is running properly.
8. Deploying Manually:
    * Create a WAR file using Maven.
    * Log into AWS and select Services from the main menu.
    * Select Elastic Beanstalk. Select your Application.
    * Click the Upload and Deploy button. Upload your WAR file and give your build a label. Click the Deploy button.
9. Deploying using an AWS Code Pipeline:
    * Update app code in source control: Set up config folder and Maven Profile.
    * Add a buildspec.yml to the root of your application code.
    * Log into AWS and select Services from the main menu.
    * Select the CodePipeline service.
    * Click the Create Pipeline button.
    * Give your pipeline a name (i.e. TestAppPipeline). Click the Next step button.
    * Select GitHub from the Source provider dropdown. Click the Connect to GitHub button and select your repo and master branch. Click the Next step button.
    * Select AWS CodeBuild from the Build provider dropdown. Select the Create a new build project option. Give your build a name. Select Ubuntu operating system with the Java OpenJDK 8 runtime.
    * Create a Service Role with a name (i.e. testapp-build-role)
    * Click the Save build project button. Click the Next step button.
    * Select AWS Elastic Beanstalk from the Deployment provider dropdown. Choose your Application and Environment from the dropdowns. Click the Next step button.
    * Create an AWS Service Role. Click the Next step button.
    * Click the Create pipeline button.
        * To build and deploy your application:
        * Select the CodePipeline service from the Services dashboard. Open the Pipeline.
        * Either make a change to code in GitHub or click the Release change button to start a build and deployment.
10. To access your application:
    * Select the Elastic Beanstalk service from the Services dashboard. Open your Application.
    * Test your application: https://[APP NAME].[AWS REGION].elasticbeanstalk.com/

## Google Cloud
1. Create an App Engine application of type Java using the following steps:
    * Select App Engine from the Main Menu.
    * Click the ‘Select a Project’ dropdown list and then click the New Project icon.
    * Give your Project a Name and click the Create button.
    * From the Welcome to App Engine screen click the Create Application button.
    * Select a Region from the US and click the Create App button.
    * Select Java from Language list and a Standard Environment. Click the Next button.
2. Clone your Application Code from GIT (from Google Cloud Shell) using the following steps:
    * Open up a Cloud Shell from the Activate Cloud Shell icon in the top menu. From the Cloud Shell perform the following operations. 
        * NOTE: once you have a Cloud Shell open if you click on the Pencil icon from the Cloud Shell menu this will open a tree view of your code, which allows you to edit some of your configuration files. Once you are in the editor you can also upload files into your project.
    * Run the following command from the Cloud Shell:
        * git clone [URL to your Test App Repo]
3. 	Configure your application using the following steps:
    * Update POM file:
    ```xml
    <plugin>
   	    <groupId>com.google.cloud.tools</groupId>
   	    <artifactId>appengine-maven-plugin</artifactId>
   	    <version>1.3.1</version>
     </plugin>
    <dependency>
   	    <groupId>com.google.cloud.sql</groupId>
   	    <artifactId>mysql-socket-factory</artifactId>
   	    <version>1.0.5</version>
    </dependency>
    <dependency>
   	    <groupId>com.google.api-client</groupId>
   	    <artifactId>google-api-client</artifactId>
   	    <version>1.23.0</version>
    </dependency>
    <dependency>
   	    <groupId>com.google.api-client</groupId>
   	    <artifactId>google-api-client-appengine</artifactId>
   	    <version>1.21.0</version>
    </dependency>
   ```
    * Add an app.yaml configuration for a Java app into the root directory of the application. In order to get MySQL database connectivity, you must add the following entry and replace the cloud_sql_instances setting with the Instance Connection Name for your MySQL database instance. There are sample files available in the Google Cloud documentation or one can be provided by your instructor.  
    * Add an appengine-web.xml configuration file to the WEB-INF directory of your project. There are sample files available in the Google Cloud documentation or one can be provided by your instructor.
    * If you are using logging you should configure the path for your log file to /tmp/[APP_NAME]/logs/[LOG_FILENAME].log in appengine-web.xml. 
    * Update your database configuration for your application (i.e. config.properties to setup db.connection property for Google MySQL database).
        * NOTE: the JDBC Connection String for MySQL requires the following format:
            * jdbc:mysql://google/[SCHEMA]?socketFactory=com.google.cloud.sql.mysql.SocketFactory&amp;cloudSqlInstance=[PROJECT_NAME_ID]:[DB_REGION]:[DB_INSTANCE_NAME] 
4. Create the MySQL Database Container and initialize the schema in the Google Cloud Platform using the following steps:
    * Select SQL menu item from the Main Menu.
    * Select MySQL Database Engine and click the Next button.
    * Select the MySQL Second Generation type.
    * Fill out the Instance ID, root password, and region.
    * Expand the Show Configuration Options. Select the desired MySQL version under the ‘Choose data version” dropdown. Expand the ‘Configure machine type and storage’ dropdown. Click the Change button. Select the db-f1-micro under the Shared-core machines options. Click the Select button. NOTE: it is extremely important that these options are set to avoid being charged by Google for your database usage.
    * Click the Create button.
    * Open the instance of the new Database and note your Public IP Address.
    * Select the Users menu and then create a new user [DB_USERNAME]/[DB_PASSWORD] that is available for all hosts. Click the Create button.
    * Select the Database menu and then create a new Database (your schema).
    * Get your public IP Address by going to your browser and in the search bar enter ‘My IP’. Note your IP Address for the next step.
    * Select the Connections menu and under Authorization Networks click Add Network button, name of GCU, network of your IP Address (from previous step), click Done and Save buttons.
    * Setup a MySQL Workbench connection using the databases IP address (listed in the Overview menu) and your database credentials (setup from the prior step).
    * Connect to the database in MySQL Workbench and run your DDL script.
    * In the main Google menu go to APIs & Services, click on the Library menu, search for Google Cloud SQL, and make sure both Cloud SQL and Cloud SQL Admin API are enabled.
    * Update your database configuration for your application (i.e. config.properties to setup db.connection property for Google MySQL database).
        * NOTE: the JDBC Connection String for MySQL requires the following format:
            * jdbc:mysql://google/[SCHEMA]?socketFactory=com.google.cloud.sql.mysql.SocketFactory&amp;cloudSqlInstance=[PROJECT_NAME_ID]:[DB_REGION]:[DB_INSTANCE_NAME]
5. Build and Deploy your application using the following steps:
    * Open your Cloud Shell.
    * cd to your cloned project root directory
    * Optionally Test locally in Shell: 
        * mvn -P[PROFILE] clean appengine:run
            * NOTE: use of Maven Profiles is optional and if not used then leave the P command line option off when running mvn from the command line.
            * NOTE: comment out the google-api-client and google-api-client-appengine as dependencies in your maven file
        * TEST: click on the Web Preview icon in the Shell and go to https://[project name].appspot.com/
    * Deploy to App Engine: mvn -P[PROFILE] clean appengine:deploy
        * NOTE: use of Maven Profiles is options and if not used then leave the P command line option off when running mvn from the command line.
    * Test at https://[PROJECT_NAME].appspot.com/
        * To view Exception Stack Track and Error logs you can go to the Home menu item and go under the Error Reporting section to view your most recent errors.
        * To view Application logs (to the console) go to App Engine->Versions and select Logs from the Tools dropdown.
