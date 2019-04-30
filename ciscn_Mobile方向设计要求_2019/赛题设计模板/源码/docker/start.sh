#!/bin/sh
docker build -t android_web:php5.3 . && docker run -d -p 8080:80 android_web:php5.3