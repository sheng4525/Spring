@echo off
call mvn versions:set versions:commit -DnewVersion="1.0.0-SNAPSHOT"
call mvn clean install -Dmaven.test.skip=true
call mvn deploy
@echo on
pause
