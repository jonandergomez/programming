#!/bin/bash


if [ -f owid-covid-data.csv ]
then
    mv owid-covid-data.csv owid-covid-data.csv-old
fi
wget https://covid.ourworldindata.org/data/owid-covid-data.csv

if [ -f datos_provincias.csv ]
then
    mv datos_provincias.csv datos_provincias.csv-old
fi
wget https://cnecovid.isciii.es/covid19/resources/datos_provincias.csv
