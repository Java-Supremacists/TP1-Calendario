#!/bin/sh
#TODO: Esto no chequea que se hayan formateado archivos. Deberia chequearlo

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
astyle src/main/java/* &> /dev/null

echo "Borro los archivos viejos de main"
rm src/main/java/*.orig &> /dev/null


echo "Formateo los archivos test"
astyle src/test/java/* &> /dev/null

echo "Borro los archivos viejos de test"
rm src/test/java/*.orig &> /dev/null

echo "Hago el commit de los archivos formateos"
git add .
git commit -m "Commit de formateo, nada de codigo cambiando"
hashCommit=$(git rev-parse HEAD)

firmaFormateo="Formateo del codigo"
fecha+="#"
fecha+=$(date)
echo ${fecha} >> .git-blame-ignore-revs
echo ${hashCommit} >> .git-blame-ignore-revs
echo "" >> .git-blame-ignore-revs


echo "Hago el commit de lo anadido al archivo .git-blame-ignore-revs"
git add .
git commit -m "Anado hash a .git-blame-ignore-revs"
