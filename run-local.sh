BLUE=94
GRAY=89
YELLOW=93

color() {
    message=$1
    [[ "$2" == "" ]] && color=$YELLOW || color=$2
    echo "[${color}m${message}[0m"
}

eval_echo() {
    command=$1
    [[ "$2" == "" ]] && color=$BLUE || color=$2
    color "${command}" $color
    echo
    eval $command
}

start_line=$1

if [ -z "$start_line" ]; then
    color "Please pass the number of the starting line as an argument" $YELLOW
    exit 1
fi

eval_echo ". env-read.sh"

if [ $start_line -le 1 ]; then
  eval_echo "./mvnw clean install -DskipTests=true"
fi 

cd target

if [ $start_line -le 2 ]; then
  eval_echo "docker-compose down"
  eval_echo "docker-compose up -d"
  eval_echo "sleep 30"
fi
  
if [ $start_line -le 3 ]; then
  eval_echo "java -jar -DDB_HOST=$DB_HOST -DDB_NAME=$DB_NAME -DDB_PASSWORD=$DB_PASSWORD -DDB_PORT=$DB_PORT -DDB_USERNAME=$DB_USERNAME -DDELETE_SECRET=$DELETE_SECRET apofig-blog-0.0.1-SNAPSHOT.jar"
fi