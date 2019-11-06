package main

import (
	"github.com/zerotwoone/dsa-lab2/proto"
	"github.com/zerotwoone/dsa-lab2/algo"
	"github.com/zerotwoone/dsa-lab2/suite"
	"time"
	"io/ioutil"
	"github.com/golang/protobuf/proto"
	"math/rand"
)

var experiments experiment.Experiments

const MIN_N = 100
const MAX_N = 10000
const STEP_N = 100

func main() {
	algoCtx := algo.NewContext(1, 1000000)
	algoCtx.GenerateGraphWithCapacity(MIN_N, MAX_N)

	testSuite := suite.NewSuite(algoCtx)

	testSuite.Handle(
		addEdges(1000), testAMethod(1), testBMethod(1),
		addEdges(100), testAMethod(2), testBMethod(2),
		finallyMethod)

	testSuite.Process()

	data, _ := proto.Marshal(&experiments)

	ioutil.WriteFile("experiments2.bin", data, 0644)
}

var addEdges = func(delim int) suite.SuiteFunc {
	return func(context *algo.AlgoContext, c suite.SuiteContext) {
		dif := context.Size()/delim - context.Edges()
		if dif < 0 {
			dif = 1
		}
		context.AddEdges(dif)
		c.Next()
	}
}

var testBMethod = func(mark int32) suite.SuiteFunc {
	return func(context *algo.AlgoContext, c suite.SuiteContext) {
		start := time.Now()
		context.FordBellman(rand.Intn(context.Size()))
		executionTime := time.Since(start)
		experiments.Experiment = append(experiments.Experiment, &experiment.Experiment{
			N:      int32(context.Size()),
			M:      int32(context.Edges()),
			Time:   executionTime.Nanoseconds(),
			Method: 2,
			Mark:   mark,
		})
		c.Next()
	}
}

var testAMethod = func(mark int32) suite.SuiteFunc {
	return func(context *algo.AlgoContext, c suite.SuiteContext) {
		start := time.Now()
		context.DijkstraMark(rand.Intn(context.Size()))
		executionTime := time.Since(start)
		experiments.Experiment = append(experiments.Experiment, &experiment.Experiment{
			N:      int32(context.Size()),
			M:      int32(context.Edges()),
			Time:   executionTime.Nanoseconds(),
			Method: 1,
			Mark:   mark,
		})
		c.Next()
	}
}

var finallyMethod = func(context *algo.AlgoContext, c suite.SuiteContext) {
	if context.Size()+STEP_N > MAX_N {
		return
	}

	context.ResizeGraph(context.Size() + STEP_N)
	c.Next()
}
