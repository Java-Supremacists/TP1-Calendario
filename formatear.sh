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

echo "Borro los archivos viejos de main"
rm src/main/java/*.orig


echo "Formateo los archivos test"
astyle src/test/java/*

echo "Borro los archivos viejos de test"
rm src/test/java/*.orig

echo "Hago el commit de los archivos formateos"
git add .
git commit -m "Commit de formateo, nada de codigo cambiando"
hashCommit=$(git rev-parse HEAD)

firmaFormateo="Formateo del codigo"
firma+=$(date)
echo ${firma} >> .git-blame-ignore-revs
echo ${hashCommit} >> .git-blame-ignore-revs
echo "" >> .git-blame-ignore-revs
