# Pomeedoro - помодоро таймер

Простой и удобный помодоро таймер для любого декстопа.  

# Установка

- Если у вас GNU/Linux, то Pomeedoro установить через удобный вам способ [см.релизы](https://codeberg.org/Itcor/Pomeedoro/releases).    
- Если же у вас другая операционная система, то попробуйте самостоятельно собрать приложение [см. сборка](#сборка) 

# Особенности

Интуитивный интерфейс состоит всего из трёх кнопок: пауза, пуск, пропуск. По окончании работы/отдыха будет звуковой сигнал, так что вы не пропустите конец работы.
Отрезок времени работы: 30 минут, отрезок времени отдыха: 5 минут.

# Сборка

## Для Debian-подобных:
Зависимости:
```
sudo apt install openjdk-21-jdk git fakeroot binutils
```
Сборка:
```
git clone https://codeberg.org/Itcor/Pomeedoro
cd Pomeedoro
./gradlew clean buildDeb -PinstallerType=deb
cd build
sudo apt install ./*.deb
```

## Для Fedora-подобных:
Зависимости:
```
sudo dnf install java-21-openjdk-devel git rpm-build
```
Сборка:
```
git clone https://codeberg.org/Itcor/Pomeedoro
cd Pomeedoro
./gradlew clean buildRpm -PinstallerType=rpm
cd build
sudo dnf install ./*.rpm
```

## Для других GNU/Linux:
Зависимости (установите через ваш пакетный менеджер):
```
jdk21-openjdk git base-devel
```
Сборка:
```
git clone https://codeberg.org/Itcor/Pomeedoro
cd Pomeedoro
./gradlew clean buildTar
cd build
sudo mkdir -p /opt
sudo tar -xzf ./*.tar.gz -C /opt/
sudo ln -sf /opt/Pomeedoro/bin/pomeedoro /usr/local/bin/pomeedoro
```

## Для macOS:
Зависимости:
```
xcode-select --install
brew install openjdk@21 git
```
Сборка:
```
git clone https://codeberg.org/Itcor/Pomeedoro
cd Pomeedoro
./gradlew clean jpackage -PinstallerType=dmg
cd build
open *.dmg
```

## Для Windows:
Зависимости:
- JDK 21
- Git

Сборка:
```
git clone https://codeberg.org/Itcor/Pomeedoro
cd Pomeedoro
gradlew.bat clean jpackage -PinstallerType=exe
cd build
.\*.exe
```
