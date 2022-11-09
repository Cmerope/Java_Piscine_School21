# удаляем директорию target если она есть
rm -rf target

# создаем директорию target
mkdir target

# компилируем проект
javac  src/java/edu/school21/printer/*/*.java -d ./target

#Копируем ресурсы
cp -R src/resources target

# создаем jar файл
jar cfmv target/images-to-chars-printer.jar src/manifest.txt -C target edu -C src resources

# запускаем jar файл
java -jar target/images-to-chars-printer.jar . 0
