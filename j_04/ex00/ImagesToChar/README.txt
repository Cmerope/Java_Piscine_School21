# удаляем директорию target если она есть
rm -rf target

# создаем директорию target
mkdir target

# компилируем проект
javac  src/java/edu/school21/printer/*/*.java -d ./target

# запускаем программу
java -classpath ./target edu.school21.printer.app.Program . 0 /Users/cmerope/Desktop/it.bmp
