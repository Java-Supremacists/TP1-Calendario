#!/bin/sh

which astyle &> /dev/null
tenesAstyle=$(echo $?)
if [ ${tenesAstyle} -ne 0 ]
then
	echo "No tenes astyle"
	echo "Instalalo antes de seguir"
	exit 2
fi

archivosSinComitear=$(git status -s | wc -l)
if [ ${archivosSinComitear} -ne 0 ]
then
	echo "Tenes cosas sin comitear"
	echo "Este script esta pensando para ser usado como unico commit"
	echo "Deja todo comiteado y despues podes correr este programa."
	exit 2
fi

echo "Formateo los archivos main"
astyle src/main/java/*

echo "Borro los archivos viejos"
rm src/main/java/*.orig
