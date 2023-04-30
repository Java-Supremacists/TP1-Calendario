#!/bin/sh


archivosSinComitear=$(git status -s | wc -l)

if [ ${archivosSinComitear} -ne 0 ]
then
	echo "tenes cosas sin comitear"
fi

echo ${archivosSinComitear}
echo "Formateo los archivos main"
astyle src/main/java/*
