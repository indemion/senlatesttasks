Автор: Карпиков Дмитрий

Для корректное работы в терминалах windows с кодировкой "cp866" был добавлен небольшой пакет "consoleconfig", который на основе OS выбирает кодировку. Не факт что решение грамотное, но придумал только это.

Запуск приложений:
- Конвертер валют
```
javac -sourcepath src -d bin src/currencyconverter/Main.java
java -cp bin currencyconverter.Main
```
- Игра "Виселица"
```
javac -sourcepath src -d bin src/hangman/Main.java
java -cp bin hangman.Main
```
- Генератор паролей
```
javac -sourcepath src -d bin src/passwordgen/Main.java
java -cp bin passwordgen.Main
```
