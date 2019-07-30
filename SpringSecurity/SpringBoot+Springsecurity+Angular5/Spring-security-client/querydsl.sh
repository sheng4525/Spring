#!/bin/bash

echo off
mvn clean 
mvn compile -P querydsl-prepare 
mvn package -P all
echo on
