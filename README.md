LoggingSetup
=================================================

![Points](../../blob/badges/points.svg)

For this homework, you will setup and configure the `log4j2` logging library. The **only thing** you should do for this homework assignment is import the homework and create a `log4j2` configuration file in the correct location with the correct configuration.

## Configuration

The `log4j2` third-party package should be setup automatically by `maven` when you import this homework into Eclipse.

Configure the root logger to output `WARN` messages and higher to the console only (no output to the file). Configure the class-specific `LoggingSetup` logger to output `INFO` messages and higher (more severe) to the console and `ALL` messages to a `debug.log` file in the current working directory. You can use the `log4j2.xml` example from lecture as a starting point.

Only output the message and **short** error message (if appropriate) to the console. The expected console output should look like:

```
ROOT LOGGER:
wren
ERROR eagle
Catching finch

CLASS LOGGER:
ibis
wren
ERROR eagle
Catching finch
```

Include the sequence number, level (using only 3 letters), file, line number, thread name, 3 lines from any throwable stack trace (if appropriate), and a newline character (`%n`) in the `debug.log` file. See the `test/resources/debug.log` file for the expected file output. It is also included below:

```
[001 trace] LoggingSetup.java:20 main: toucan
[002 debug] LoggingSetup.java:21 main: dove
[003 info] LoggingSetup.java:22 main: ibis
[004 warn] LoggingSetup.java:23 main: wren
[005 error] LoggingSetup.java:24 main: ERROR java.lang.Exception: eagle
	at LoggingSetup.outputMessages(LoggingSetup.java:24)
	at LoggingSetup.main(LoggingSetup.java:39)
[006 fatal] LoggingSetup.java:25 main: Catching java.lang.RuntimeException: finch
	at LoggingSetup.outputMessages(LoggingSetup.java:25)
	at LoggingSetup.main(LoggingSetup.java:39)
```

You should **NOT** modify the `LoggingSetup.java`, `LoggingSetupTest.java`, or `test/debug.log` files. You should only create a **NEW** file in the correct location to configure log4j2.

## Hints ##

Below are some hints that may help with this homework assignment:

  - The `log4j2.xml` file in the [lecture examples](https://github.com/usf-cs212-summer2021/lectures/blob/main/Debugging/src/main/resources/log4j2.xml) is a good starting point.

  - For configuring the output locations (where to output, file versus console), take a look at the [ConsoleAppender](https://logging.apache.org/log4j/2.x/manual/appenders.html#ConsoleAppender) and [FileAppender](https://logging.apache.org/log4j/2.x/manual/appenders.html#FileAppender) information pages.

  - For configuring the output format (what to output), I recommend you take a look at the [PatternLayout](https://logging.apache.org/log4j/2.x/manual/layouts.html#PatternLayout) information page. It includes all of the possible patterns, like `class`, `date`, `throwable`, `file`, `location`, `line`, `message`, `method`, `n`, `level`, `sequenceNumber`, `threadId`, and `threadName` (you will only use some of these).

  - **Do NOT overwrite the `test/debug.log` file. You should configure log4j2 to write to the path `debug.log` instead.**

These hints are *optional*. There may be multiple approaches to solving this homework.
