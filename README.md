### IP Monitor

IP Monitor is a simple application which monitors your public IP address for changes and lets you set different kinds of notification such as email, audio, pop up or executing a command. It can also run in background as a Windows service or Linux/Mac daemon.

#### Features

  * Automatically checks your public IP address for changes
  * Provides several ways of notifying IP changes such as playing a sound, sending an email, displaying a popup or executing a command
  * Can be run as a Windows service or Linux/Mac daemon
  * Provides logging support
  * LookAndFeel support
  * Portable application
  * Multiplatform
  * Open source
  * Free

#### Troubleshooting

##### Enable logging

If you have any issues running the application as a service/daemon you can add some debug information by changing the `files\jsw\wrapper.conf` file:

In order to see some additional details of what is going on behing the scenes when pressing the service/daemon interaction buttons you should turn this line:

```
##include.debug
```

into:

```
#include.debug
```

In order to see more details in the `files\jsw\wrapper.log` file you can turn this line:

```
wrapper.logfile.loglevel=FATAL
```

into:

```
wrapper.logfile.loglevel=DEBUG
```

##### Issues with Java executable path

If a the java executable is not in your path you can either add it or update the following line in the `files\jsw\wrapper.conf` file to reflect the absolute or relative path (relative to the location of the `IPMonitor.jar` file) to the java executable of the Java Virtual Machine you want to use:

```
wrapper.java.command=java
```
