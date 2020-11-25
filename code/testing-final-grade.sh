#!/bin/bash

while [ true ]
do
    a=$((${RANDOM} % 10 ))
    b=${RANDOM}
    #mg1="${a}.${b}"
    mg1="${a}"
    a=$((${RANDOM} % 10 ))
    b=${RANDOM}
    #mg2="${a}.${b}"
    mg2="${a}"
    a=$((${RANDOM} % 10 ))
    b=${RANDOM}
    #lg1="${a}.${b}"
    lg1="${a}"
    a=$((${RANDOM} % 10 ))
    b=${RANDOM}
    #lg2="${a}.${b}"
    lg2="${a}"

    echo ${mg1} ${mg2} ${lg1} ${lg2}
    echo ${mg1} ${mg2} ${lg1} ${lg2} | ./run-examples.sh  FinalGrade
    if [ $? -ne 0 ]
    then
        exit 1
    fi
done
