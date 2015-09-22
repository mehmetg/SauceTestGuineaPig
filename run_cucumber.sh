#!/usr/bin/env bash
bash -c "export OS=\"Windows 7\";export VERSION=\"41\";export BROWSER=\"chrome\";./gradlew clean cucumber" > /dev/null 2>&1 &
bash -c "export OS=\"Windows XP\";export VERSION=\"8\";export BROWSER=\"internet explorer\";./gradlew clean cucumber" > /dev/null 2>&1 &
bash -c "export OS=\"Windows 7\";export VERSION=\"9\";export BROWSER=\"internet explorer\";./gradlew clean cucumber" > /dev/null 2>&1 &
#        // windows xp, IE 8
#        browsers.add(["Windows XP", "8", "internet explorer", null, null] as String[]);
#
#        // windows 7, IE 9
#        browsers.add(["Windows 7", "9", "internet explorer", null, null] as String[]);