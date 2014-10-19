#!/bin/bash -e

[ -z "$1" ] && echo Missing project argument && exit 1
PROJECT=$1
shift

[ -z "$1" ] && echo Missing paths to jars && exit 1

# check for the existence of an xtend file
find $PROJECT -iname "*.xtend" -type f | grep -q . || exit 0

# notify user of arguments
echo `basename "$0"` $PROJECT "$@"

# generate the java files
for P in "$@"; do
  CLASSPATH=$CLASSPATH:$(find "$P" -iname "*.jar" | paste -s -d ":" -)
done
java -cp "$CLASSPATH" org.eclipse.xtend.core.compiler.batch.Main \
  -d $PROJECT/xtend-gen \
  -classpath "$CLASSPATH" \
  $PROJECT/src 2>&1 > /dev/null
