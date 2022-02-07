@ECHO OFF
IF "%1"=="start" (
    ECHO start toss_sample
    start "toss_sample" java -jar -Dspring.profiles.active=local toss_sample.jar
) ELSE IF "%1"=="stop" (
    ECHO stop toss_sample
    TASKKILL /FI "WINDOWTITLE eq toss_sample"
) ELSE (
    ECHO please, use "toss_sample.bat start" or "toss_sample.bat stop"
)
pause