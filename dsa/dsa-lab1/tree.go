package main

type BSTNode struct {
	left  *BSTNode
	right *BSTNode
	key   string
	value string
}

func NewBSTNode(key string, value string) *BSTNode {
	return &BSTNode{nil, nil, key, value}
}

func (node *BSTNode) Set(key string, value string) {
	if node.key > key {
		if node.left == nil {
			node.left = NewBSTNode(key, value)
		} else {
			node.left.Set(key, value)
		}
	} else if node.key == key {
		node.value = value
	} else {
		if node.right == nil {
			node.right = NewBSTNode(key, value)
		} else {
			node.right.Set(key, value)
		}
	}
}

func (node *BSTNode) Find(key string) *string {
	if node.key == key {
		return &node.value
	} else if node.key > key {
		if node.left == nil {
			return nil
		} else {
			return node.left.Find(key)
		}
	} else {
		if node.right == nil {
			return nil
		} else {
			return node.right.Find(key)
		}
	}
}

type BSTree struct {
	root *BSTNode
}

func NewEmptyBSTree() *BSTree {
	return &BSTree{nil}
}

func NewBSTree(key string, value string) *BSTree {
	return &BSTree{NewBSTNode(key, value)}
}

func (tree *BSTree) Set(key string, value string) {
	if tree.root == nil {
		tree.root = NewBSTNode(key, value)
	} else {
		tree.root.Set(key, value)
	}
}

func (tree *BSTree) Find(key string) *string {
	if tree.root == nil {
		return nil
	} else {
		return tree.root.Find(key)
	}
}

func (tree *BSTree) CalculateDepth() int {
	return CalculateDepth(tree.root)
}

func CalculateDepth(node *BSTNode) int {
	if node == nil {
		return 0
	}
	leftDepth := CalculateDepth(node.left)
	rightDepth := CalculateDepth(node.right)
	if leftDepth > rightDepth {
		return leftDepth + 1
	} else {
		return rightDepth + 1
	}
}
