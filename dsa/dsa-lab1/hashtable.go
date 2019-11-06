package main

type HashTable struct {
	size  uint32
	table []*BSTree
	collisionTable []int
}

func HashFunction(value string) uint32 {
	switch len(value) {
	case 0:
		return 0
	case 1:
		return uint32(value[0])
	case 2:
		return uint32(value[0] + value[1])
	case 3:
		return uint32(value[0] + value[1] + value[2])
	default:
		return uint32(value[0] + value[len(value)-1] + value[len(value)-2])
	}
}

func  NewHashTable(size uint32) *HashTable {
	return &HashTable{size, make([]*BSTree, size), make([]int, size)}
}

func (table *HashTable) Set(key string, value string) {
	index := HashFunction(key) % table.size
	if table.table[index] == nil {
		table.table[index] = NewBSTree(key, value)
	} else {
		table.table[index].Set(key, value)
	}
	table.collisionTable[index]++
}

func (table *HashTable) Get(key string) *string {
	index := HashFunction(key) % table.size
	if table.table[index] == nil {
		return nil
	} else {
		return table.table[index].Find(key)
	}
}

func (table *HashTable) AverageCollision() float32 {
	sum := 0
	count := 0
	var i uint32
	for i = 0; i < table.size; i++ {
		if table.collisionTable[i] != 0 {
			count++
			sum += table.collisionTable[i]
		}
	}
	return float32(sum) / float32(count)
}

func (table *HashTable) AverageDepth() float32 {
	sum := 0
	count := 0
	var i uint32
	for i = 0; i < table.size; i++ {
		if table.table[i] != nil {
			count++
			sum += table.table[i].CalculateDepth()
		}
	}
	return float32(sum) / float32(count)
}

