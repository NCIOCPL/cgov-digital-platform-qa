# CancerGov Digital Platform QA Framework

This is the QA automation framework for the [CancerGov Digital Communications Platform](https://github.com/nciocpl/cgov-digital-platform/)

## Prerequisites:
- JDK 1.8.0_144 or later
  - [Oracle's download site](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
  - Installation Instructions
    [Windows](https://docs.oracle.com/javase/8/docs/technotes/guides/install/windows_jdk_install.html#CHDEBCCJ)
    [Mac](https://docs.oracle.com/javase/8/docs/technotes/guides/install/mac_jdk.html)
- [Apache maven](http://maven.apache.org/download.cgi) tool
  - On Mac:
    - Install [Homebrew](https://brew.sh/)
    - `brew install maven`


## Command line execution:

To run the default test suite (all tests), execute the command

    mvn test

#### Specifying the environment

Hostnames are specified in the `config.properties` file, as `environment.hostname.<NAME>` (where `<NAME>` is
the human-friendly name of the environment.) Each `environment.hostname` entry is a fully qualified hostname
(e.g. `environment.hostname.prod=www.cancer.gov`).

The default runtime environment is specified as the value of the `environment.active` property.
(e.g. `environment.active=qa`)


### Specifying a test suite.

The entire set of tests is executed by default.

To execute a specific test suite, execute the command

    mvn test -Dsurefire.suiteXmlFiles=<testfile>

Where `<testfile>` is the test suite file and path. e.g.

    mvn test -Dsurefire.suiteXmlFiles=suites/cross-cutting.xml

Multiple test suites may be specified by separating them with commas.

## Configuration
Most configuration appears in `configuration/config.properties`. Values in the configuration file may be overridden locally
via `configuration/config.override.properties` (the override file is excluded from source control). Select configuration data
may be overridden via command line parameters.  The order of precedence is:

1. Look for a value from the command line.
2. Fallback to a value from `config.override.properties`.
3. Fallback to the value in `config.properties`.


## Folder Structure

* `configuration` - Main configuration files
* `resources/drivers` - Driver files for different browsers on differnt platforms.
* `src` - The source code for this suite.
  * `src/main/java/gov/cancer/framework` - utility classes used by page objects.
  * `src/main/java/gov/cancer/pageobject` - the page object classes used for testing.
  * `src/test/java/gov/cancer/tests` - the test classes.
* `suites` - [TestNG](https://testng.org/) test suite files.
* `target` - Generated files. (Not version controlled.)
* `test-data` - Like it says on the label.
* `test-output` - Reports and the like. (Not version controlled.)

## Architecture

Tests in this framework follow the [PageObject pattern](https://martinfowler.com/bliki/PageObject.html).

The testing system makes use of the [Selenium WebDriver](https://www.seleniumhq.org/projects/webdriver/) browser automation
project to control a web browser and read/modify page values.

All browser interactions are controlled using classes in the `gov.cancer` namespace in `{proj_root}/src/main/java/gov/cancer/`.
High-level page objects are in the `gov.cancer.pageobject` namespace, with additional helper classes in `gov.cancer.framework`.

All test classes reside under the `gov.cancer.tests` namespace in `{proj_root}/src/test/java/gov/cancer/tests`. Test classes work
with methods from the PageObjects. They do **not** import anything from the `org.openqa.selenium` namespace.

Tests are run using the [TestNG](https://testng.org/) framework.


