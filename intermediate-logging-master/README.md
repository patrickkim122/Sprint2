Slide deck - http://spsbtn001/academy/learning/commondevelopment/Shared%20Documents/Week3-Logging.pptx 

Examples covering logging, specifically log4j2 and various configuration options.

For purposes of logging, you should only need to know two classes: LogManager and Logger.

LogManager is used to obtain instances of loggers. For examples, see LogManagerDemo.

Patterns for logging messages are shown in LoggingMessagesDemo.

Logging levels and configuration are shown in ConfigurationDemo. The XML Configuration for this is in log4j2.xml. Information on the configuration is embedded in both the ConfigurationDemo.java class and the log4j2.xml file. To show differences in configuration, the xml file should be edited to show differences in the logging statements.

TestConfiguration overrides are shown in TestConfigurationDemo and log4j2-test.xml

When running these demos, Eclipse does not show newly created files by default. Right-click on the project and select "Refresh" to see newly created files.

Information on these components and their use is available on log4j2's website: https://logging.apache.org/log4j/2.x/index.html
There are links available on the side bar, but for convenience, here are some common links that you may use.
Javadoc: https://logging.apache.org/log4j/2.x/log4j-api/apidocs/index.html
Java API: https://logging.apache.org/log4j/2.x/manual/api.html
Configuration: https://logging.apache.org/log4j/2.x/manual/configuration.html
Appenders: https://logging.apache.org/log4j/2.x/manual/appenders.html
Layouts: https://logging.apache.org/log4j/2.x/manual/layouts.html

When using log4j2, while you need both the log4j2-api and the log4j2-core dependencies for projects, you should strive to only use components in the log4j2-api package. These are stable and will not be changed, while the "core" dependencies can be changed without warning across versions. This demo only covers components in the log4j2-api package.