#!/bin/bash

while [ true ]
do
    a=$(( ((${RANDOM} % 120 ) / 10) - 1 ))
    b=${RANDOM}
    
    grade="${a}.${b}"
    
    x=$(echo ${grade} | ./run-examples.sh Grades1)
    y=$(echo ${grade} | ./run-examples.sh Grades2)

    echo ${grade}
    #echo ${grade}  ${x}  ${y}

    if [ "${x}" != "${y}" ]
    then
        echo $x $y
        break
    fi
done
