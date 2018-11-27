#!/bin/sh

DESC="Hello World application"
NAME=LPTool
PIDFILE=/var/run/$NAME.pid
COMMAND=/usr/bin/java -- -jar vertx-hello-1.0.0-SNAPSHOT-fat.jar

d_start() {
    start-stop-daemon --start --background --make-pidfile --pidfile $PIDFILE --exec $COMMAND
}

d_stop() {
    start-stop-daemon --stop --pidfile $PIDFILE
    if [ -e $PIDFILE ]
        then rm $PIDFILE
    fi
}
