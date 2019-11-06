#!/bin/sh

ERR=~/lab1_err

# $1 - path to dir
chageDir() {
  if [ ! -d "$1" ]; then
    echo "директория $1 отсуствует"
    return
  fi

  # shellcheck disable=SC2164
  cd "$1" 2>$ERR
  # shellcheck disable=SC2181
  if [ "$?" != "0" ]; then
    echo "Произошла ошибка при сменен каталога" >&2
  fi
}

# $1 - name of file
createFile() {
  if [ -d "$1" ] || [ -f "$1" ]; then
    echo "$1 уже существует"
    return
  fi

  # shellcheck disable=SC2164
  #  touch -- "$1" 2>$ERR
  >"$1" 2>$ERR
  # shellcheck disable=SC2181
  if [ "$?" != "0" ]; then
    echo "Произошла ошибка при создание файла" >&2
  fi
}

# $1 - path to file
deleteFile() {
  if [ -d "$1" ]; then
    echo "$1 является директорией"
    return
  fi

  if [ ! -f "$1" ]; then
    echo "файл $1 отсуствует"
    return
  fi

  # shellcheck disable=SC2164
  rm -- "$1" 2>$ERR
  # shellcheck disable=SC2181
  if [ "$?" != "0" ]; then
    echo "Произошла ошибка при удаление файла $1" >&2
  fi
}

# $1 - path to file
permitWrite() {
  if [ ! -f "$1" ]; then
    echo "файл $1 отсуствует"
    return
  fi

  if [ -d "$1" ]; then
    echo "$1 это директория"
    return
  fi

  # shellcheck disable=SC2164
  chmod a+w "$1" 2>$ERR
  # shellcheck disable=SC2181
  if [ "$?" != "0" ]; then
    echo "Произошла ошибка при выдаче прав на запись в файл $1" >&2
  fi
}

menu="1. Напечатать имя текущего каталога
2. Сменить текущий каталог
3. Создать файл
4. Предоставить всем право на запись в файл
5. Удалить файл
6. Выйти из программы"

number=''

while [ "$number" != "6" ]; do

  echo "$menu"

  echo "Введите номер пункта меню: "

  read number

  case "$number" in
  "1")
    basename "$PWD" 2>$ERR
    # shellcheck disable=SC2181
    if [ "$?" != "0" ]; then
      echo "Произошла ошибка при выводе текущей директории" >&2
    fi
    ;;
  "2")
    echo "Введите путь к новому каталогу:"
    read -r path

    chageDir "$path"
    ;;
  "3")
    echo "Введите название файла:"
    read -r name

    createFile "$name"
    ;;
  "4")
    echo "Введите путь к файлу: "
    read -r path

    permitWrite "$path"
    ;;
  "5")
    echo "Введите путь к файлу: "
    read -r path

    echo "Действительно хотите удалить файл $path?"
    read argree

    if [ "${argree}" = "y" ] || [ "$argree" = "ye" ] || [ "$argree" = "yes" ] ||
      [ "${argree}" = "Y" ] || [ "$argree" = "YE" ] || [ "$argree" = "YES" ]; then
      deleteFile "$path"
    fi
    ;;

  "6") ;;

  *)
    echo "Неизвестный пункт меню"
    ;;
  esac

done
