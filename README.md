# Pomeedoro - Pomodoro Timer

A simple and user-friendly Pomodoro timer for any desktop.

# Installation

- If you are using GNU/Linux, you can install Pomeedoro using your preferred method [see releases](https://codeberg.org/Itcor/Pomeedoro/releases).
- If you are on a different operating system, you can build the application yourself [see Building](#building).

# Features

The intuitive interface features just three buttons: Pause, Start, and Skip. An audio alert will sound at the end of each work or break session, ensuring you won't miss the end of your focus time.
Work interval: 30 minutes. Break interval: 5 minutes.

# Building

## For Debian-based systems:
Dependencies:
```
sudo apt install openjdk-21-jdk git fakeroot binutils
```
Build:
```
git clone https://codeberg.org/Itcor/Pomeedoro
cd Pomeedoro
./gradlew clean buildDeb -PinstallerType=deb
cd build
sudo apt install ./*.deb
```

## For Fedora-based systems:
Dependencies:
```
sudo dnf install java-21-openjdk-devel git rpm-build
```
Build:
```
git clone https://codeberg.org/Itcor/Pomeedoro
cd Pomeedoro
./gradlew clean buildRpm -PinstallerType=rpm
cd build
sudo dnf install ./*.rpm
```

## For other GNU/Linux systems:
Dependencies (install via your package manager):
```
jdk21-openjdk git base-devel
```
Build:
```
git clone https://codeberg.org/Itcor/Pomeedoro
cd Pomeedoro
./gradlew clean buildTar
cd build
sudo mkdir -p /opt
sudo tar -xzf ./*.tar.gz -C /opt/
sudo ln -sf /opt/Pomeedoro/bin/pomeedoro /usr/local/bin/pomeedoro
```

## For macOS:
Dependencies:
```
xcode-select --install
brew install openjdk@21 git
```
Build:
```
git clone https://codeberg.org/Itcor/Pomeedoro
cd Pomeedoro
./gradlew clean jpackage -PinstallerType=dmg
cd build
open *.dmg
```

## For Windows:
Dependencies:
- JDK 21
- Git

Build:
```
git clone https://codeberg.org/Itcor/Pomeedoro
cd Pomeedoro
gradlew.bat clean jpackage -PinstallerType=exe
cd build
.\*.exe
```