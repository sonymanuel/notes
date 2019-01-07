#!/bin/bash -x

#versionfile=$(dirname $0)/../src/main/resources/version.txt
#version=`cat $versionfile | grep version | cut -d= -f2 `

echo commit=`git log -n 1 | grep commit | cut -d\  -f2` > $versionfile
#echo "version=`expr $version + 1`" >> $versionfile
