#!/usr/bin/env bash

rm -rf docs

find classes -type f -name "*.class" -exec rm -f {} \;

rmdir classes/etsinf/prg/agenda2
rmdir classes/etsinf/prg
rmdir classes/etsinf

