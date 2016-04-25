#!/bin/sh
cd "$(dirname "$0")"
osascript -e "do shell script \"java -jar IPMonitor.jar\" with administrator privileges"
