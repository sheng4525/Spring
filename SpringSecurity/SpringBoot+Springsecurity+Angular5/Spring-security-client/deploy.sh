#!/bin/bash

echo off
mvn versions:set versions:commit -DnewVersion="1.0.0-SNAPSHOT"
mvn clean install -Dmaven.test.skip=true
mvn deploy
echo on
