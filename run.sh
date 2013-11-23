#mvn dependency:copy-dependencies
#mvn compile
CP="./target/classes/:./target/dependency/*"
OPTIONS="-cp $CP"
MAIN="Home.NaiveBayes"
java $OPTIONS $MAIN $*
