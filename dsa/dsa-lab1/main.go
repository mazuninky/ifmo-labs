package main

import (
	"os"
	"time"
	"math/rand"
	"bufio"
	"strings"
	"fmt"
	"errors"
)

func main() {
	GenerateDictionary(2500)
	dictionaryFile, err := os.Open("dictionary.map")
	if err != nil {
		panic(err)
	}
	defer dictionaryFile.Close()
	scanner := bufio.NewScanner(dictionaryFile)
	scanner.Split(bufio.ScanLines)
	table := NewHashTable(768)
	startTime := time.Now()
	for scanner.Scan() {
		itemArray := strings.Split(scanner.Text(), " ")
		if len(itemArray) != 2 {
			panic(errors.New("bad dictionary format"))
		}
		table.Set(itemArray[0], itemArray[1])
	}
	fmt.Printf("Execution time: %s \n", time.Since(startTime))
	fmt.Printf("Avarage collision time: %f \n", table.AverageCollision())
	fmt.Printf("Avarage depth : %f \n", table.AverageDepth())
	StartInteractiveMode(table)
}

func GenerateDictionary(size int) {
	dictionaryFile, err := os.Create("dictionary.map")
	if err != nil {
		panic(err)
	}
	defer dictionaryFile.Close()

	r := rand.New(rand.NewSource(time.Now().UnixNano()))
	for i := 0; i < size; i++ {
		dictionaryFile.WriteString(RandString(r.Intn(19) + 1))
		dictionaryFile.WriteString(" ")
		dictionaryFile.WriteString(RandString(r.Intn(19) + 1))
		dictionaryFile.Write([]byte("\n"))
	}
}

func StartInteractiveMode(table *HashTable) {
	if table == nil {
		panic(errors.New("nil table"))
	}

	reader := bufio.NewReader(os.Stdin)
	for true {
		fmt.Print("Enter command: ")
		text, _ := reader.ReadString('\n')
		text = strings.TrimSpace(text)
		parts := strings.Split(text, " ")
		switch parts[0] {
		case "set":
			if len(parts) != 3 {
				fmt.Print("Need two arguments!\n")
				break
			}
			if len(parts[1]) < 1 || len(parts[2]) < 1 {
				fmt.Print("Bad arguments!\n")
				break
			}
			table.Set(parts[1], parts[2])
			break
		case "get":
			if len(parts) != 2 {
				fmt.Print("Need one argument!\n")
				break
			}
			if len(parts[1]) < 1 {
				fmt.Print("Bad key!\n")
				break
			}
			value := table.Get(parts[1])
			if value != nil {
				fmt.Printf("For key %s founded value %s\n", parts[1], *value)
			} else {
				fmt.Printf("Can't find value for key %s\n", parts[1])
			}
			break
		case "exit":
			return
		}

	}
}
